package com.estm.GR_Location;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.estm.GR_Location.manager_Dr.Autocar;
import com.estm.GR_Location.manager_Dr.Employer;
import com.estm.GR_Location.manager_Dr.Pauses;
import com.estm.GR_Location.manager_Dr.Societe;
import com.estm.GR_Location.manager_Dr.Trajet;
import com.estm.GR_Location.services.LocationTracker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivityDriver extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Trajet trajet ;
    public static Autocar autocar ;
    public static Societe societe ;
    public static List<Pauses> listPauses;
    public static Employer chauffeur ;
    public static Employer assistant ;

    public boolean checked ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_with_drawler_driver);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mabar);
        setSupportActionBar(toolbar);
        //service runner
        checked = true  ;
        startService(new Intent(this, LocationTracker.class));
        //service runner


        setTitle("Côté chauffeur");

        trajet = SenderLogin.getTrajetObj() ;
        autocar = SenderLogin.getAutocarObj() ;
        assistant = SenderLogin.getAssistantObj() ;
        chauffeur = SenderLogin.getChauffeurObj();
        societe = SenderLogin.getSocieteObj() ;
        listPauses = SenderLogin.getListPauses() ;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_driver);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_driver);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    String email = "" ;
    String password = "" ;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(MainActivityDriver.this,"Setting :"+autocar.toString(),Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
// Déja


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String  Date_Depart = trajet.getDate_Depart();
        //String  Date_Depart = "08-04-2017 17:15:30";
        String  Date_Arr = trajet.getDate_Arr_Attendue();
        // String  Date_Arr =  "08-04-2017 17:16:30";
        //  String Date_system = DAY_OF_MONTH +"-"+MONTH+"-"+YEAR+" "+HOUR+":"+minute+":"+SECOND ;
        String Date_system =  new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()); ;
        Date date_Depart =null ,dateSystem=null ,date_Arr =null;
        try {
            date_Depart = formatter.parse(Date_Depart);
            dateSystem = formatter.parse(Date_system);
            date_Arr = formatter.parse(Date_Arr);
        } catch (Exception e) {
            e.printStackTrace();

        }
        boolean test = true ;
if(id == R.id.nav_home  ) {
    if (test) {
        //     if(dateSystem.compareTo(date_Depart) >= 0 && dateSystem.compareTo(date_Arr) <= 0){

        //Toast.makeText(getActivity(),"Time is Now",Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Home", Toast.LENGTH_LONG).show();
        // TrajetFragment trajetFragment = new TrajetFragment();
        TrajetFragment newFragment = new TrajetFragment();
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.relative_layout_for_fragment, newFragment);
        transaction.addToBackStack(null);
        checked = false;
        transaction.commit();
    } else {
        //Toast.makeText(getActivity(),"Time is Not now",Toast.LENGTH_LONG).show();
        /// start
        Toast.makeText(this, "Home", Toast.LENGTH_LONG).show();
        HomeFragment homeFragment = new HomeFragment();
        Bundle data = new Bundle();//create bundle instance
        data.putParcelable("trajet", trajet);//put string to pass with a key value
        homeFragment.setArguments(data);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.relative_layout_for_fragment,
                homeFragment,
                homeFragment.getTag()
        ).commit();
        //end
    }
} else if (id == R.id.nav_profile) {
           // Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show();
            ProfileFragment profileFragment = new ProfileFragment();
            Bundle data = new Bundle();//create bundle instance
            data.putParcelable("chauffeur",chauffeur);//put string to pass with a key value
            profileFragment.setArguments(data);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,
                    profileFragment,
                    profileFragment.getTag()
            ).commit();
        }else if (id == R.id.nav_assistant) {
            //Toast.makeText(this,"Assistant",Toast.LENGTH_LONG).show();
            AssistantFragment assistantFragment = new AssistantFragment();
            Bundle data = new Bundle();//create bundle instance
            data.putParcelable("assistant",assistant);//put string to pass with a key value
            assistantFragment.setArguments(data);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,
                    assistantFragment,
                    assistantFragment.getTag()
            ).commit();

        } else if (id == R.id.nav_societe) {
            //Toast.makeText(this,"Societe",Toast.LENGTH_LONG).show();
            SocieteFragment societeFragment = new SocieteFragment();
            Bundle data = new Bundle();//create bundle instance
            data.putParcelable("societe",societe);//put string to pass with a key value
            societeFragment.setArguments(data);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,
                    societeFragment,
                    societeFragment.getTag()
            ).commit();

        } else if (id == R.id.nav_contactReclamation) {
            //Toast.makeText(this,"Contact / Reclamation",Toast.LENGTH_LONG).show();
            ContactReclamationFragment contactReclamationFragment = new ContactReclamationFragment();
            Bundle data = new Bundle();//create bundle instance
            data.putParcelable("trajet",trajet);//put string to pass with a key value
            contactReclamationFragment.setArguments(data);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_for_fragment,
                    contactReclamationFragment,
                    contactReclamationFragment.getTag()
            ).commit();
        } else if (id == R.id.nav_logout) {
           //Toast.makeText(this,"Log out ... ",Toast.LENGTH_LONG).show();
            SenderLogin.logout(false) ;
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_driver);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
