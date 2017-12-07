package com.example.android.yipyapnewjava;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class feed2 extends AppCompatActivity {

    private RecyclerView mFeedList;

    private DatabaseReference mDatabase;

    int upVotes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed2);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Feed");

        mFeedList = (RecyclerView) findViewById(R.id.feed_list);
        mFeedList.setHasFixedSize(true);
        mFeedList.setLayoutManager(new LinearLayoutManager(this));

    }

    public void increment(View view)
    {
        upVotes = upVotes + 1;
        TextView button = (TextView) findViewById(R.id.bone);
        display(upVotes);
    }

    public void display(int upVotes)
    {
        TextView like = (TextView) findViewById(R.id.likes);
        like.setText("" + upVotes);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter <PostMalone,PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostMalone, PostViewHolder>(
                PostMalone.class,
                R.layout.feed_row,
                PostViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, PostMalone model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(getApplicationContext(),model.getImage());
            }
        };

        mFeedList.setAdapter(firebaseRecyclerAdapter);
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

        public void setImage (Context ctx, String image){

            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);

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
