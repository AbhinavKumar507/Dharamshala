package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dharmshalaowner.Domain.User;
import com.example.dharmshalaowner.R;
import com.example.dharmshalaowner.dhaaramshalaown.Activities.LoginActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class profile extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView nav_view;
    androidx.appcompat.widget.Toolbar toolbar;

    Button btnUpdate;
    ImageView imagePro;
    TextView name, email, phone;

    String uid;
    Uri uri;

    private static ArrayList<String> userData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.pName);
        email = view.findViewById(R.id.pMail);
        phone = view.findViewById(R.id.pPhn);


        drawerLayout = view.findViewById(R.id.drawerLayout);
        nav_view = view.findViewById(R.id.nav_view);
        toolbar = view.findViewById(R.id.toolbar);


        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

//      Navigation drawer menu
        nav_view.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//      for back pressing activity
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

//      itemclick listner
        nav_view.setNavigationItemSelectedListener(this);


        // profile data fetching
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // creating object to get instance of whole database
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        // getting reference of a node from database
        DatabaseReference node = db.getReference().child(uid).child("Owner Data");
        node.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                name.setText("NAME :  " +user.getName());
                phone.setText("PH-NO :  " +user.getNumber());
                email.setText("EMAIL :  " +user.getEmail());
                // imagePro.setImageURI(user.getDpUrl()); //use for local fetch

                Toast.makeText(getContext(), "Hello " + user.getName() + " !", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /* profile data fetching*/


        imagePro = view.findViewById(R.id.imagePro);
        imagePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(profile.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(480)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)     //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);

            }
        });


        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //photoUploader();
                uploadImage();
            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();

            imagePro.setImageURI(uri);


        }
    }


    // UploadImage method
    public void uploadImage() {
        if (uri != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference


            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference uploader = storage.getReference().child("image");
            Toast.makeText(getContext(), "uri: " + uri.getLastPathSegment() + " ref:" + uploader.toString(), Toast.LENGTH_SHORT).show();


            // adding listeners on upload
            // or failure of image
            uploader.putFile(uri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(getContext(),
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(getContext(),
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();


                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");
                                }
                            });
        }

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new about_hotel()).commit();
                break;

            case R.id.nav_profile:
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new profile()).commit();
                break;

            case R.id.nav_share:
               Toast.makeText(getActivity()," share! ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_settings:
                Toast.makeText(getActivity()," setting! ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_help:
                Toast.makeText(getActivity()," help! ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_about_us:
                Toast.makeText(getActivity()," about us! ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_rate_us:
                Toast.makeText(getActivity()," rate us! ",Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_logout:
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;

        }

        return true;
    }
}

