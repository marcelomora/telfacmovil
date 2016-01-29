package com.accioma.telecosfacturamovil.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.accioma.telecosfacturamovil.Consts;
import com.accioma.telecosfacturamovil.R;
import com.accioma.telecosfacturamovil.model.CustomerDao;
import com.accioma.telecosfacturamovil.model.Customer;
import com.accioma.telecosfacturamovil.model.DaoMaster;
import com.accioma.telecosfacturamovil.model.DaoSession;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class CustomerFormActivity extends AppCompatActivity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    // LogCat tag
    private static final String TAG = CustomerFormActivity.class.getSimpleName();

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    // boolean flag to toggle periodic location updates
    private boolean mRequestingLocationUpdates = false;

    private LocationRequest mLocationRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters

    // UI elements
    private TextView mTvLocation;
    private Button btnShowLocation, btnStartLocationUpdates;
    private Toolbar toolbar;
    private TextView mTvFirstName;
    private TextView mTvLastName;
    private TextView mTvFin;
    private EditText mTvEmail;
    private TextView mTvContact;
    private TextView mTvMobilePhone;
    private TextView mTvPhone;
    private TextView mTvAddressLine1;
    private TextView mTvAddressLine2;
    private TextView mTvAddressLine3;

    private Long mCustomerId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_form);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mTvFirstName = (TextView) findViewById(R.id.editTextFirstName);
        mTvLastName = (TextView) findViewById(R.id.editTextLastName);
        mTvFin = (TextView) findViewById(R.id.editTextFIN);
        mTvEmail = (EditText) findViewById(R.id.editTextEmail);
        mTvContact = (TextView) findViewById(R.id.editTextContactName);
        mTvMobilePhone = (TextView) findViewById(R.id.editTextMobile);
        mTvPhone = (TextView) findViewById(R.id.editTextLandline);
        mTvAddressLine1 = (TextView) findViewById(R.id.editTextAddress1);
        mTvAddressLine2 = (TextView) findViewById(R.id.editTextAddress2);
        mTvAddressLine3 = (TextView) findViewById(R.id.editTextAddress3);

        Intent intent = getIntent();
        if (intent.hasExtra("CUSTOMER")){
            Customer customer = (Customer)intent.getSerializableExtra("CUSTOMER");
            mCustomerId = customer.getId();
            mTvFirstName.setText(customer.getFirstname());
            mTvLastName.setText(customer.getLastname());
            mTvFin.setText(customer.getFin());
            mTvEmail.setText(customer.getEmail());
            mTvContact.setText(customer.getContact_name());
            mTvMobilePhone.setText(customer.getMobile_phone());
            mTvPhone.setText(customer.getPhone());
            mTvAddressLine1.setText(customer.getAddress1());
            mTvAddressLine2.setText(customer.getAddress2());
            mTvAddressLine3.setText(customer.getAddress3());
        } else {
            mCustomerId = null;
        }

        mTvLocation = (TextView) findViewById(R.id.tvLocation);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        // First we need to check availability of play services
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }

        // Show location button click listener
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayLocation();
            }
        });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());
    }

    @Override
    public void onConnected(Bundle bundle) {
        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to display the location on UI
     * */
    private void displayLocation() {

        mLastLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            mTvLocation.setText(latitude + ", " + longitude);

        } else {

            mTvLocation
                    .setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case android.R.id.home:
                //onBackPressed();
                this.finish();
                return true;

            case R.id.action_save_customer:
                String msg = validateFields();
                if (msg.toLowerCase().equals("valid")){
                    save();
                    finish();
                }else {
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }

    private String validateFields() {
        if (!Consts.isValid(mTvEmail, Consts.EMAIL_REGEX, "Email incorrecto", true)){
            return "Email incorrecto";
        }
        return "valid";
    }

    private void save(){

        String name = mTvLastName.getText().toString() + " " + mTvFirstName.getText().toString();
        String firstName = mTvFirstName.getText().toString();
        String lastName = mTvLastName.getText().toString();
        String fin = mTvFin.getText().toString();
        String email = mTvEmail.getText().toString();
        String contact = mTvContact.getText().toString();
        String mobilePhone = mTvMobilePhone.getText().toString();
        String phone = mTvPhone.getText().toString();
        String addressLine1 = mTvAddressLine1.getText().toString();
        String addressLine2 = mTvAddressLine2.getText().toString();
        String addressLine3 = mTvAddressLine3.getText().toString();
        String location = mTvLocation.getText().toString();

        Customer customer = new Customer(mCustomerId,
                name, lastName, firstName, fin, email, contact, mobilePhone, phone,
                addressLine1, addressLine2, addressLine3, location
                );

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Consts.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();

        CustomerDao customerDao = daoSession.getCustomerDao();
        customerDao.insertOrReplace(customer);


    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }
}
