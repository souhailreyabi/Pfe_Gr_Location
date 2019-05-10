package com.estm.GR_Location;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Circle;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivityCustomer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_with_drawler_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ImageView imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.deft);
        String ur = Sender.getSocieteObj().getLogo();

        StringBuffer sbr = new StringBuffer(ur);
        sbr.replace(7, 21, "192.168.42.79");
        final String imgurl = sbr.toString();

        Picasso.with(navigationView.getHeaderView(0).getContext()).load(imgurl).into(imageView);

        TextView txt=(TextView)navigationView.getHeaderView(0).findViewById(R.id.nemom);
        txt.setText(Sender.getSocieteObj().getNom().toString());

        navigationView.setNavigationItemSelectedListener(this);



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        FragmentManager fragment=getSupportFragmentManager();
        voyage v =new voyage();
        fragment.beginTransaction().replace(R.id.content_main,v,v.getTag()).commit();
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void logout(){

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
// Déja


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_autocar) {
            FragmentManager fragment=getSupportFragmentManager();
            autocar aut =new autocar();

            fragment.beginTransaction().replace(R.id.content_main,aut,aut.getTag()).commit();
            Toast.makeText(this,"bienvenue",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_voyage) {
            FragmentManager fragment=getSupportFragmentManager();
            voyage v =new voyage();
            fragment.beginTransaction().replace(R.id.content_main,v,v.getTag()).commit();

        } else if (id == R.id.nav_societe) {
            FragmentManager fragment=getSupportFragmentManager();
            societe s =new societe();
            fragment.beginTransaction().replace(R.id.content_main,s,s.getTag()).commit();


        }
        else if (id == R.id.nav_plainte) {
            FragmentManager fragment=getSupportFragmentManager();
            plainte p =new plainte();
            fragment.beginTransaction().replace(R.id.content_main,p,p.getTag()).commit();


        }else if(id==R.id.nav_logoutc){
            final ProgressDialog progressDialog = new ProgressDialog(MainActivityCustomer.this,
                    R.style.AppTheme_Dark_Dialog);
            SharedPreferences sp=getSharedPreferences("ID",MODE_PRIVATE);
            SharedPreferences.Editor e=sp.edit();
            e.clear();
            e.commit();

            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Logout... Thanks!!");
            progressDialog.show();
            startActivity(new Intent(this,CustomerActivity.class));
            Toast.makeText(this,"Thanks For Your visit  ",Toast.LENGTH_LONG).show();
            finish();   //finish current activity

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private Bitmap LoadImage(String URL, BitmapFactory.Options options)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String strURL) throws IOException{
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();

        try{
            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setRequestMethod("Post");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
        }
        return inputStream;
    }

}
