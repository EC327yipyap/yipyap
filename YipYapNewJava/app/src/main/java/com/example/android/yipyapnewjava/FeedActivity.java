package com.example.android.yipyapnewjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }

    public void launchMain (View view)
    {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void launchPost2 (View view)
    {
        Intent i = new Intent(this,Post2Activity.class);
        startActivity(i);
    }
}
