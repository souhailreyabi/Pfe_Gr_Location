package com.estm.GR_Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/*
1.OUR LAUNCHER ACTIVITY
2.INITIALIZE SOME UI STUFF
3.WE START SENDER ON BUTTON CLICK
 */
public class TestMainActivity extends AppCompatActivity {
    String urlAddress="http://10.0.2.2/android/poster.php";
    EditText nameTxt,posTxt,teamTxt;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //INITIALIZE UI FIELDS
        nameTxt= (EditText) findViewById(R.id.nameEditTxt);
        posTxt= (EditText) findViewById(R.id.posEditTxt);
        teamTxt= (EditText) findViewById(R.id.teamEditTxt);
        saveBtn= (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //START ASYNC TASK
              //  SenderLogin s=new SenderLogin(TestMainActivity.this,urlAddress,nameTxt,posTxt,teamTxt);
              //  s.execute();
            }
        });
    }
}