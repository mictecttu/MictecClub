package com.example.android.mictecclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
private ActionBarDrawerToggle toggle;
    private String TAG="MictecClub-> APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.db) {
//            Toast toast=Toast.makeText(getApplicationContext(),"dashboard clicked",Toast.LENGTH_SHORT);
//            toast.show();

            GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
            if (alreadyloggedAccount != null) {
                Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
                onLoggedIn(alreadyloggedAccount);
            } else {
                Log.d(TAG, "Not logged in");
                startActivity(new Intent(MainActivity.this, LoginInActivity.class));
            }



        } else if (id == R.id.events) {
            Toast toast=Toast.makeText(getApplicationContext(),"events clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.classes) {
            Toast toast=Toast.makeText(getApplicationContext(),"classes clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.leaders) {
            Toast toast=Toast.makeText(getApplicationContext(),"leaders clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.members) {
            Toast toast=Toast.makeText(getApplicationContext(),"members clicked",Toast.LENGTH_SHORT);
            toast.show();

        } else if (id == R.id.gallery) {
            Toast toast=Toast.makeText(getApplicationContext(),"gallery clicked",Toast.LENGTH_SHORT);
            toast.show();

        }else if (id == R.id.help) {
            Toast toast=Toast.makeText(getApplicationContext(),"help clicked",Toast.LENGTH_SHORT);
            toast.show();

        }else if(id==R.id.about){
            Toast toast=Toast.makeText(getApplicationContext(),"about clicked",Toast.LENGTH_SHORT);
            toast.show();
        }
        else if(id==R.id.action_settings){
            Toast toast=Toast.makeText(getApplicationContext(),"settings clicked",Toast.LENGTH_SHORT);
            toast.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(ProfileActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
    }

}
