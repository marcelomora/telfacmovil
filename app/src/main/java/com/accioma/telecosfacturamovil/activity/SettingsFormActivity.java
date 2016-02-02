package com.accioma.telecosfacturamovil.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.controller.ServiceHandler;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by marcelo on 1/27/16.
 */
public class SettingsFormActivity extends AppCompatActivity{
    private static final String TAG = SettingsFormActivity.class.getSimpleName();
    private EditText mEtHost;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtConfirm;
    private TextView mJsonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_form);
        mEtHost = (EditText) findViewById(R.id.editTextHost);
        mEtUsername = (EditText) findViewById(R.id.editTextUsername);
        mEtPassword = (EditText) findViewById(R.id.editTextPassword);
        mBtConfirm = (Button) findViewById(R.id.btnSettingsConfirm);
        mJsonText = (TextView) findViewById(R.id.jsonTextView);

        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = SettingsFormActivity.this.mEtHost.getText().toString();
                Log.i("Settings Form", "excecuteCommand");
                Runtime runtime = Runtime.getRuntime();
                String message = "";
                try{
                    Process ipAddrProcess = runtime.exec("/system/bin/ping -c 1 " + host);
                    int exitValue = ipAddrProcess.waitFor();
                    Log.i("Settings Form", "ExitValue " + exitValue);
                    if(exitValue==0){
                        message = "Not connected";
                    }else{
                        message = "Connected";
                        ServiceHandler sh = new ServiceHandler();
                        String jsonStr = sh.makeServiceCall(host + ":5000", ServiceHandler.GET);
                        //Log.d("Response", "> " + jsonStr);
                        //SettingsFormActivity.this.mJsonText.setText(jsonStr);
                    }
                }catch(InterruptedException ignore){
                    ignore.printStackTrace();
                    Log.e("String Form", ignore.getMessage());
                }catch(IOException e){
                    e.printStackTrace();
                    Log.i("Settings Form", e.getMessage());
                }
                Toast.makeText(SettingsFormActivity.this, message,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
