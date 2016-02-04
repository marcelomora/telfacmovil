package com.accioma.telecosfacturamovil.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.audiofx.Virtualizer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.accioma.telecosfacturamovil.controller.SettingsHandler;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
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
    private EditText mEtEmail;
    private EditText mEtSerie;
    private Button mBtConfirm;
    private String mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_form);
        mEtHost = (EditText) findViewById(R.id.editTextHost);
        mEtUsername = (EditText) findViewById(R.id.editTextUsername);
        mEtPassword = (EditText) findViewById(R.id.editTextPassword);
        mEtEmail = (EditText) findViewById(R.id.editTextEmail);
        mEtSerie = (EditText) findViewById(R.id.editTextSerie);
        mBtConfirm = (Button) findViewById(R.id.btnSettingsConfirm);

        SharedPreferences prefs = getSharedPreferences(SettingsHandler.PREFS_FILE_NAME, Context.MODE_PRIVATE);


        mEtHost.setText(prefs.getString("host", ""));
        mEtUsername.setText(prefs.getString("username", ""));
        mEtPassword.setText(prefs.getString("password", ""));

        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String host = SettingsFormActivity.this.mEtHost.getText().toString();
                Log.i("Settings Form", "excecuteCommand");
                String message;
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    // fetch data
                    message = "Connected";
                    if(validateFields()){
                        SettingsFormActivity.this.getData("http://" + host + ":5000");
                        if(mResponse.trim().equals("World")){
                            SettingsHandler.saveSettings(SettingsFormActivity.this);
                        }
                    }
                } else {
                    message = "Not connected";
                    // display error
                }
                Toast.makeText(SettingsFormActivity.this, message,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getData(String url){
        mResponse = "";

        // Request a string response
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        // Result handling
                        String greeting = "";
                        try{
                            greeting = response.getString("hello");
                        }catch(JSONException je){
                            je.printStackTrace();
                        }

                        mResponse = greeting;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }

    public String getUsername(){
        return mEtUsername.getText().toString();
    }
    public String getHost(){
        return mEtHost.getText().toString();
    }
    public String getPassword(){
        return mEtPassword.getText().toString();
    }

    private boolean validateFields(){
        if( mEtHost.getText().toString().trim().equals("")){
            mEtHost.setError(getString(R.string.host_field_required));
            return false;
        }
        if( mEtUsername.getText().toString().trim().equals("")){
            mEtUsername.setError(getString(R.string.username_field_required));
            return false;
        }
        if( mEtPassword.getText().toString().trim().equals("")){
            mEtPassword.setError(getString(R.string.password_field_required));
            return false;
        }
        if( mEtEmail.getText().toString().trim().equals("")){
            mEtPassword.setError(getString(R.string.email_field_required));
            return false;
        }
        if( mEtSerie.getText().toString().trim().equals("")){
            mEtSerie.setError(getString(R.string.serie_field_required));
            return false;
        }
        return true;
    }
}
