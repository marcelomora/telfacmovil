package com.accioma.telecosfacturamovil.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.accioma.telecosfacturamovil.Consts;
import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.adapter.DrawerAdapter;
import com.accioma.telecosfacturamovil.adapter.ProductListAdapter;
import com.accioma.telecosfacturamovil.model.DaoMaster;
import com.accioma.telecosfacturamovil.model.DaoSession;
import com.accioma.telecosfacturamovil.model.Product;
import com.accioma.telecosfacturamovil.model.ProductDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import de.greenrobot.dao.query.QueryBuilder;

public class ProductListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private Toolbar toolbar;
    private RecyclerView mProductList;
    private ProductListAdapter mProductListAdapter;

    String TITLES[] = {"Facturas","Clientes","Configuracion"};
    int ICONS[] = {R.drawable.ic_coin,R.drawable.ic_account,R.drawable.ic_settings};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Marcelo Mora";
    String EMAIL = "marcelo.mora@accioma.com";
    int PROFILE = R.drawable.mm;

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter = new DrawerAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE, this );                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    RecyclerView mInvoiceList;
    RecyclerView.Adapter mInvoiceAdapter;
    RecyclerView.LayoutManager mInvoiceLlm;

    ImageButton mFab;

    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Consts.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        ProductDao productDao = daoSession.getProductDao();

        setContentView(R.layout.activity_product_list);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager
        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        mRecyclerView.addOnItemTouchListener( new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)){
                    Drawer.closeDrawers();
                    int selection =  rv.getChildAdapterPosition(child);
                    Intent intent = null;
                    switch (selection){
                        case 1:
                            intent = new Intent(ProductListActivity.this, InvoiceListActivity.class );
                            break;
                        case 3:
                            intent = new Intent(ProductListActivity.this, SettingsFormActivity.class );
                            break;
                    }
                    if(intent != null){
                        try{
                            startActivity(intent);
                        }catch (Exception ex){
                            Log.d("Error Activity", ex.getMessage());
                        }
                    }


                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }

        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        QueryBuilder qbProduct = productDao.queryBuilder();

        mProductListAdapter = new ProductListAdapter(this, qbProduct.limit(20).list());
        mProductList = (RecyclerView) findViewById(R.id.product_list);
        mProductList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mProductList.setLayoutManager(llm);
        mProductList.setAdapter(mProductListAdapter);
        //mProductListAdapter = new CustomerListAdapter(getApplicationContext(), this);
        /*
        mFab = (ImageButton) findViewById(R.id.add_customer_button);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( ProductListActivity.this, CustomerFormActivity.class);
                startActivity(intent);
            }
        });
        */

    }

    @Override
    protected void onResume() {
        super.onResume();
        ProductDao productDao = daoSession.getProductDao();
        QueryBuilder qbProduct = productDao.queryBuilder();
        mProductListAdapter = new ProductListAdapter(this, qbProduct.limit(20).list());
        mProductList.setAdapter(mProductListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        /*
        MenuItem searchItem = menu.findItem(R.id.action_product_search);
        SearchManager searchManager = (SearchManager) ProductListActivity.this.getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (searchItem != null){
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ProductListActivity.this.getComponentName()));
        }
        searchView.setOnQueryTextListener(this);
        */
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("ProductList TextSubmit", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("ProductList TextChange", newText);
        QueryBuilder.LOG_SQL = true;
        final List<Product> filteredProductList = filter(newText);
        mProductListAdapter.animateTo(filteredProductList);
        mRecyclerView.scrollToPosition(0);
        return true;
    }

    private List<Product> filter(String query){
        daoSession.clear();
        ProductDao productDao = daoSession.getProductDao();
        QueryBuilder qb = productDao.queryBuilder();
        List<Product> filteredProductList = qb.limit(20)
                .whereOr(ProductDao.Properties.Name.like("%" + query + "%"),
                         ProductDao.Properties.Description.like("%" + query + "%")).list();
        return filteredProductList;

    }
}
