package com.t.quakealert;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.github.anastr.speedviewlib.SpeedView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;


public class myHome extends AppCompatActivity  {



    String name="Welcome";
    private RecyclerView recyclerView;
 TextView myTextView;
    private static Context mContext2;
    CDF_nowcast myvar=new CDF_nowcast();

private MobileServiceClient mClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home);

        try {
            mClient=new MobileServiceClient("https://quakealert.azurewebsites.net",this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        double prob=myvar.get_prob(4);
        String mySTring=String.valueOf(prob);
        float myfloat= (float) prob;
        final_prob myprob=new final_prob(prob,95);
        final float finalprob= (float) myprob.final_probability();


        Random Random=new Random();
        int random = Random.nextInt(1);Random r = new Random();
        int i1 = r.nextInt(4 - 1) + 1;
        mySTring=mySTring+String.valueOf(i1);
        TodoItem item=new TodoItem();
        item.Text=mySTring;
        mClient.getTable(TodoItem.class).insert(item, new TableOperationCallback<TodoItem>() {
            @Override
            public void onCompleted(TodoItem entity, Exception exception, ServiceFilterResponse response) {


            }
        });
        final SpeedView speedometer = findViewById(R.id.speedView);

// move to 50 Km/s
        speedometer.speedTo(100);

        speedometer.setWithTremble(false);

        speedometer.setSpeedTextColor(Color.WHITE);
        speedometer.setTextColor(Color.WHITE);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                speedometer.speedTo(finalprob*100);
            }
        }, 2000);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView textView=findViewById(R.id.textView3);
                textView.setVisibility(View.VISIBLE);
            }
        }, 1500);



    }



}
