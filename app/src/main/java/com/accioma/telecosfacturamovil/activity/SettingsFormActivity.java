package com.accioma.telecosfacturamovil.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accioma.telecosfacturamovil.R;

/**
 * Created by marcelo on 1/27/16.
 */
public class SettingsFormActivity extends AppCompatActivity{
    private static final String TAG = SettingsFormActivity.class.getSimpleName();
    private EditText mEtHost;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_form);
        mEtHost = (EditText) findViewById(R.id.editTextHost);
        mEtUsername = (EditText) findViewById(R.id.editTextUsername);
        mEtPassword = (EditText) findViewById(R.id.editTextPassword);
        mBtConfirm = (Button) findViewById(R.id.btnSettingsConfirm);

        mBtConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsFormActivity.this, SettingsFormActivity.this.mEtHost.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
