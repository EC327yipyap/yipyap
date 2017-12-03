package com.example.android.yipyapnewjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.firebase.client.Firebase;


public final class MainActivity extends AppCompatActivity {

    Firebase myFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        myFirebase = new Firebase("https://yipyap-44cfc.firebaseio.com");
    }


    public void launchFeed (View view)
    {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
    }


}
