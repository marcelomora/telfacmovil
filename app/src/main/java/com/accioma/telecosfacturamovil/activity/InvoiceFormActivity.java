package com.accioma.telecosfacturamovil.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.accioma.telecosfacturamovil.Consts;
import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.adapter.CustomerListAdapter;
import com.accioma.telecosfacturamovil.adapter.InvoiceLineListAdapter;
import com.accioma.telecosfacturamovil.model.Customer;

public class InvoiceFormActivity extends AppCompatActivity {

    private Customer customer;


    public static final int REQUEST_CUSTOMER = 1;
    private Toolbar toolbar;
    private TextView customerText;
    private RecyclerView invoiceLines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_form);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        customerText = (TextView) findViewById(R.id.customer_name);
        customerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InvoiceFormActivity.this, CustomerListActivity.class );
                intent.putExtra(Consts.PARENT_ACTIVITY_MODE, CustomerListAdapter.SELECT_INVOICE);
                startActivityForResult(intent, REQUEST_CUSTOMER);
                //startActivity(intent);
            }
        });

        invoiceLines = (RecyclerView)findViewById(R.id.invoice_line);

        invoiceLines.setHasFixedSize(true);
        invoiceLines.setLayoutManager(new LinearLayoutManager(this));
        invoiceLines.setAdapter(new InvoiceLineListAdapter(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CUSTOMER){
            try{
                customer = (Customer) data.getSerializableExtra("CUSTOMER");
                customerText.setText(customer.getFirstname() + " " + customer.getLastname());

            }catch (NullPointerException npe){

            }
        }
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

        switch(id){
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
