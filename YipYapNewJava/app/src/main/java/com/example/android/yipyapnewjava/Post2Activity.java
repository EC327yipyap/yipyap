package com.example.android.yipyapnewjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Post2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);
    }

    public void launchFeed (View view)
    {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
    }
}