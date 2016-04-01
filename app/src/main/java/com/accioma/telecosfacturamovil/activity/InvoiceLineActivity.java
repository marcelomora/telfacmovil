package com.accioma.telecosfacturamovil.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.accioma.telecosfacturamovil.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class InvoiceLineActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText iccText;
    private CoordinatorLayout invoiceLineLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_line);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        invoiceLineLayout = (CoordinatorLayout)findViewById(R.id.invoice_line_layout);
        iccText = (EditText)findViewById(R.id.editTextIcc);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(InvoiceLineActivity.this);
                integrator.initiateScan();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (scanningResult != null){
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //formatTxt.setText("FORMAT: " + scanFormat);
            //contentTxt.setText("CONTENT: " + scanContent);
            iccText.setText(scanContent);

        }
        else{
            Snackbar.make(invoiceLineLayout, "No se reconoce el código de barras", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
    }
}
