package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dharmshalaowner.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;

public class Upload_image extends Fragment {
    ImageView image_COVER,image_HOTEL1,image_HOTEL2;
    Button UPLOAD1,UPLOAD2,UPLOAD3;
    private int CurrentProgress = 0;
    private ProgressBar progressBar ;

    private  static ArrayList<String> placeimagelist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_image, container, false);
        image_COVER=view.findViewById(R.id.img_cover);
        image_HOTEL1=view.findViewById(R.id.img_hotel_1);
        image_HOTEL2=view.findViewById(R.id.img_hotel_2);

        UPLOAD1=view.findViewById(R.id.upload1);
        UPLOAD2=view.findViewById(R.id.upload2);
        UPLOAD3=view.findViewById(R.id.upload3);

        /*progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentProgress = CurrentProgress+10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(50);
            }
        });*/


        UPLOAD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Upload_image.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(480)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);

            }
        });

        UPLOAD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Upload_image.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(480)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(2);

            }
        });

        UPLOAD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Upload_image.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(480)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(3);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1)
        {
            Uri uri= data.getData();

            image_COVER.setImageURI(uri);

            placeimagelist = Upload_image.getPlaceimagelist();
            placeimagelist.add(String.valueOf(uri));


        }else if (requestCode==2) {

            Uri uri= data.getData();

            image_HOTEL1.setImageURI(uri);

            placeimagelist = Upload_image.getPlaceimagelist();
            placeimagelist.add(String.valueOf(uri));

        }
        else if (requestCode==3) {

            Uri uri= data.getData();

            image_HOTEL2.setImageURI(uri);

            placeimagelist = Upload_image.getPlaceimagelist();
            placeimagelist.add(String.valueOf(uri));

        }

    }

    public static ArrayList<String> getPlaceimagelist() {

        return placeimagelist;
    }
}