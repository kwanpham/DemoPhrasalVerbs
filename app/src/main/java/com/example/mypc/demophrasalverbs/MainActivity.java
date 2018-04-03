package com.example.mypc.demophrasalverbs;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mypc.demophrasalverbs.FragmentApp.QuizFrament;
import com.example.mypc.demophrasalverbs.FragmentApp.TuVungFrament;
import com.example.mypc.demophrasalverbs.FragmentApp.TuVungYeuThichFrament;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        InitView();
    }

    private void InitView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentTransaction transactionTuVung = fragmentManager.beginTransaction();
        TuVungFrament tuVungFrament = new TuVungFrament();
        transactionTuVung.replace(R.id.flContent, tuVungFrament);
        transactionTuVung.commit();
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.itTuVung:
                FragmentTransaction transactionTuVung = fragmentManager.beginTransaction();
                transactionTuVung.replace(R.id.flContent, new TuVungFrament());
                transactionTuVung.commit();
                break;
            case R.id.itYeuThich:
                FragmentTransaction transactionTuVungYeuThich = fragmentManager.beginTransaction();
                transactionTuVungYeuThich.replace(R.id.flContent, new TuVungYeuThichFrament());
                transactionTuVungYeuThich.commit();
                break;

            case R.id.itQuiz:

                FragmentTransaction transactionQuiz = fragmentManager.beginTransaction();
                transactionQuiz.replace(R.id.flContent, new QuizFrament());
                transactionQuiz.commit();
                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
