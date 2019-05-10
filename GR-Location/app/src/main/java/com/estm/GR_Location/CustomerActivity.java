package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CustomerActivity extends AppCompatActivity {
    private static final String TAG = "CustomerActivity";
SharedPreferences sp;
    @Bind(R.id.input_nbillet) EditText _NSociete;
 //   @Bind(R.id.input_idautocar) EditText _idAutocar;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_driver) TextView _driverLink;
   // Spinner spinner;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);*/

       //





    setContentView(R.layout.activity_customer);
    ButterKnife.bind(this);
        sp=getSharedPreferences("ID",MODE_PRIVATE);
        if(sp.contains("ID")){
            startActivity(new Intent(CustomerActivity.this,MainActivityCustomer.class));
            finish();
        }

    _loginButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LoginCustomer();
        }
    });

    _driverLink.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Finish the registration screen and return to the Login activity
            Intent intent = new Intent(getApplicationContext(),DriverActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    });


    }

    public void LoginCustomer() {
        Log.d(TAG, "Login Customer");

        if (!validate()) {
            onLoginFailed();
            return;
        }


        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(CustomerActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Login...");
        progressDialog.show();

new Sender(CustomerActivity.this,"http://192.168.42.79/GR_locat/public/Services/ClientServices/login",_NSociete).execute();


        String name = _NSociete.getText().toString();



        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        // depending on success
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    private boolean check = false; // change it
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        String textaff = null;
if(Sender.getCode()==0){
    check=false;
    textaff="ERROR Serveur   ";

}else if (Sender.getCode()==200){

if(Sender.autocarObj.getModel()==null){
    check=false;
    textaff="numero de trajet errone";
}else{
    check=true;
textaff="Bonjour chere Client **";
    SharedPreferences.Editor e=sp.edit();
    e.putString("ID",_NSociete.getText().toString());



}

}



    
else {
    check=false;
    textaff="REsseyer serveur pas disponible ";
}

        setResult(RESULT_OK, null);

        if (check == true ){
            Toast.makeText(this,textaff,Toast.LENGTH_LONG).show();
            Intent myintent = new Intent(this,MainActivityCustomer.class) ;
            startActivity(myintent);
        }else{

            Toast.makeText(this,textaff,Toast.LENGTH_LONG).show();
        }
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String nSociete = _NSociete.getText().toString();
       // String idAutocar = _idAutocar.getText().toString();
        int nsociete=0 ;

        boolean test = true ;

        if (nSociete.isEmpty() ) {
            _NSociete.setError("N° Empty");
            valid = false;
        } else {
            try{
                nsociete = Integer.parseInt(nSociete);
            }catch (NumberFormatException e){
                test = false ;
            }
            if(test != false){
                // nsociete Valide
                _NSociete.setError(null);
            }

        }



        return valid;
    }


}