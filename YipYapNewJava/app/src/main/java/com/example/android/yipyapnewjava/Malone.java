package com.example.android.yipyapnewjava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Malone extends AppCompatActivity {
    //Variable List
    private ImageButton mSelectImage;
    private EditText    mPostTitle;
    private EditText    mPostDescription;
    private Button      mSubmitButton;
    private Uri         mImageUri = null;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malone);

        // Firebase Storage and Database access
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Feed");

        // Connecting the buttons/interactable stuff with objects
        mSelectImage = (ImageButton) findViewById(R.id.imageSelect);
        mPostTitle = (EditText) findViewById(R.id.postTitle);
        mPostDescription = (EditText) findViewById(R.id.postDescription);
        mSubmitButton = (Button) findViewById(R.id.submitButton);

        mProgress = new ProgressDialog(this); // loading post object

        // get stuff from the gallery
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPosting();
                
            }
        });
    }


    private void startPosting() {

        mProgress.setMessage("Posting puppies soon...");

        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDescription.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri != null){

            mProgress.show(); // if the user has entered something then we show progress bar

            StorageReference filePath = mStorage.child("Post_Images").child(mImageUri.getLastPathSegment());

            filePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    // taking the local data (picture, title, and description)
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();

                    newPost.child("title").setValue(title_val);
                    newPost.child("description").setValue(desc_val);
                    newPost.child("image").setValue(downloadUrl.toString());

                    mProgress.dismiss();

                    startActivity(new Intent(Malone.this, feed2.class));
                }
            });
        }

    }

    // Pulling the image from gallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            mImageUri = data.getData();

            CropImage.activity(mImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(4,3)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                mSelectImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
