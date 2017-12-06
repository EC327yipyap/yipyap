package com.example.android.yipyapnewjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class feed2 extends AppCompatActivity {

    private RecyclerView mFeedList;

    private DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Feed");

        mFeedList = (RecyclerView) findViewById(R.id.feed_list);
        mFeedList.setHasFixedSize(true);
        mFeedList.setLayoutManager(new LinearLayoutManager(this));

//        // Attach a listener to read the data at our posts reference
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Malone post = dataSnapshot.getValue(Malone.class);
//                System.out.println(post);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        // Mo's attempts
//        FirebaseRecyclerAdapter<PostMalone, PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostMalone, PostViewHolder>(
//                PostMalone.class,
//                R.layout.feed_row,
//                PostViewHolder.class,
//                mDatabase
//        ) {
//
//            @Override
//            public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(PostViewHolder holder, int position, PostMalone model) {
//
//            }
//
//
//            protected void populateViewHolder(PostViewHolder viewHolder, PostMalone model, int position){
//
//                viewHolder.setTitle(model.getTitle());
//                viewHolder.setDescription(model.getDescription());
//            }
//        };
//        mFeedList.setAdapter(firebaseRecyclerAdapter);


        // Laura Joy's attempts
//        FirebaseRecyclerOptions<PostMalone> options =
//                new FirebaseRecyclerOptions.Builder<PostMalone>()
//                        .setQuery(mDatabase, PostMalone.class)
//                        .build();
//
//        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostMalone, PostViewHolder>(options)
//        {
//            @Override
//            public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View v = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.feed_row, parent, false);
//
//                PostViewHolder vh = new PostViewHolder(v);
//                return vh;
//
//            }
//
//            @Override
//            protected void onBindViewHolder(PostViewHolder holder, int position, PostMalone model) {
//                // We have to put something here!
//            }
//
//            protected void populateViewHolder(PostViewHolder viewHolder, PostMalone model, int position){
//
//                viewHolder.setTitle(model.getTitle());
//                viewHolder.setDescription(model.getDescription());
//            }
//        };
//        mFeedList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public PostViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setTitle(String title){

            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDescription (String description){

            TextView post_description = (TextView) mView.findViewById(R.id.post_description);
            post_description.setText(description);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {

            startActivity(new Intent(feed2.this, Malone.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
