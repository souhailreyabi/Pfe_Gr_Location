package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.ButterKnife;
import butterknife.Bind;

public class DriverActivity extends AppCompatActivity {
    String email = "" ;
    String password = "" ;
    String urlAddress="http://192.168.42.79/GR_locat/public/Services/ChauffeurServices/login";
    EditText nameTxt,posTxt;
    private String response;
    private static final String TAG = "DriverActivity";
    private static final int REQUEST_ = 0;
    ProgressDialog progressDialog ;
    SenderLogin s ;
    @Bind(R.id.input_idautocar) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_customer) TextView _customerLink;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        ButterKnife.bind(this);
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


               //START ASYNC TASK
                //SenderLogin s=new SenderLogin(DriverActivity.this,urlAddress,nameTxt,posTxt);
                //s.execute();
if(LoginDriver()) {
    s = new SenderLogin(DriverActivity.this, urlAddress, _emailText, _passwordText);
    s.execute();
    SharedPreferences sharedPreferences = getSharedPreferences("prefs.xml", Context.MODE_PRIVATE);
    sharedPreferences.edit().putBoolean("logedIn", true).apply();
    Intent myintent = new Intent(DriverActivity.this, MainActivityDriver.class);
    startActivity(myintent);
}


            }
        });

        _customerLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Customer activity
                Intent intent = new Intent(getApplicationContext(),CustomerActivity.class);
                startActivityForResult(intent, REQUEST_);
                finish();
                overridePendingTransition(R.anim.push_left_in, R
                        .anim.push_left_out);
            }
        });
    }
/*
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        s=new SenderLogin(DriverActivity.this,urlAddress,_emailText,_passwordText);
        s.execute();

    }*/

    public boolean LoginDriver() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return false ;
        }

        _loginButton.setEnabled(false);


/*
         progressDialog = new ProgressDialog(DriverActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);

        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        progressDialog.dismiss();
                    }
                }, 10000);
*/
       return true ;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_) {
            if (resultCode == RESULT_OK) {

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the OldMainActivity
        moveTaskToBack(true);
    }


        public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8 || password.length() > 16) {
            _passwordText.setError("between 8 and 16 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
