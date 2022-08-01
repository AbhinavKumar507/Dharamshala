package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dharmshalaowner.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;

public class PlaceRoomImage extends Fragment {
    ImageView image_ROOM1,image_ROOM2;
    Button UPLOAD1,UPLOAD2;

    private  static ArrayList<String> placeimagelistroom = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_room_image, container, false);

        image_ROOM1=view.findViewById(R.id.img_room_1);
        image_ROOM2=view.findViewById(R.id.img_room_2);

        UPLOAD1=view.findViewById(R.id.upload1);
        UPLOAD2=view.findViewById(R.id.upload2);


        UPLOAD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(PlaceRoomImage.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(480)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);

            }
        });

        UPLOAD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(PlaceRoomImage.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(480)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(480, 480)	 //Final image resolution will be less than 1080 x 1080(Optional)
                        .start(2);
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

            image_ROOM1.setImageURI(uri);

            placeimagelistroom = PlaceRoomImage.getPlaceimagelistroom();
            placeimagelistroom.add(String.valueOf(uri));


        }else if (requestCode==2) {

            Uri uri= data.getData();

            image_ROOM2.setImageURI(uri);

            placeimagelistroom = PlaceRoomImage.getPlaceimagelistroom();
            placeimagelistroom.add(String.valueOf(uri));

        }
}

    public static ArrayList<String> getPlaceimagelistroom() {

        return placeimagelistroom;
    }
}
