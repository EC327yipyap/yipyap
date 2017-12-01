package com.example.android.yipyapnewjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
    }


    public void launchFeed (View view)
    {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
    }

}
