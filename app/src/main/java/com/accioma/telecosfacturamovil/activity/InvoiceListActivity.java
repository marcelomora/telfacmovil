package com.accioma.telecosfacturamovil.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.accioma.telecosfacturamovil.Consts;
import com.accioma.telecosfacturamovil.adapter.CustomerListAdapter;
import com.accioma.telecosfacturamovil.adapter.DrawerAdapter;
import com.accioma.telecosfacturamovil.adapter.InvoiceListAdapter;
import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.model.Customer;

public class InvoiceListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String TITLES[] = {"Facturas","Clientes"};
    int ICONS[] = {R.drawable.ic_coin,R.drawable.ic_account};

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_list);
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
                        case 2:
                            intent = new Intent(InvoiceListActivity.this, CustomerListActivity.class);
                            intent.putExtra(Consts.PARENT_ACTIVITY_MODE, CustomerListAdapter.EDIT_CUSTOMER);
                            break;
                    }
                    if(intent != null){
                        startActivity(intent);
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

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


        //Invoice List
        mInvoiceList = (RecyclerView) findViewById(R.id.invoice_list);
        mInvoiceLlm = new LinearLayoutManager(this);
        mInvoiceList.setLayoutManager(mInvoiceLlm);
        mInvoiceAdapter = new InvoiceListAdapter();
        mInvoiceList.setAdapter(mInvoiceAdapter);

        mFab = (ImageButton) findViewById(R.id.add_invoice_button);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( InvoiceListActivity.this, InvoiceFormActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invoice, menu);
        return true;
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
}
