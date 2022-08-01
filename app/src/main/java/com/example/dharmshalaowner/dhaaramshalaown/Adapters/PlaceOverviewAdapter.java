package com.example.dharmshalaowner.dhaaramshalaown.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.Domain.DataClass;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Hotel_name;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.NumberOfGuests;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceQuality;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceRent;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceRoomImage;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.PlaceSecurity;
import com.example.dharmshalaowner.dhaaramshalaown.Fragments.Upload_image;
import com.example.dharmshalaowner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaceOverviewAdapter extends RecyclerView.Adapter<PlaceOverviewAdapter.PlaceOverviewViewHolder> {
    Context context;
    private ArrayList<String> fragmentNames;
    private ArrayList<DataClass> placeDescriptionList;
    private ArrayList<DataClass> placeOffersList;
    private Integer[] numberOfGuestsList;
    private String rent;
    private ArrayList<String> placeSecurityList;
    FirebaseDatabase database;
    DatabaseReference reference;

    private  ArrayList<String> placequalitylist;
    private  ArrayList<String> placenamelist;

    private ArrayList<String> placeimagelist;
    private ArrayList<String> placeimagelistroom;


    public PlaceOverviewAdapter(Context context, ArrayList<String> fragmentNames) {
        this.context = context;
        this.fragmentNames = fragmentNames;
    }

    @NonNull
    @Override
    public PlaceOverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_overview_main_recyclerview_items_ui, parent, false);
        return new PlaceOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceOverviewViewHolder holder, int position) {
        String data = fragmentNames.get(position);

        holder.fragmentName.setText(data);

        if(position == 0) {
            placenamelist = Hotel_name.getPlacenamelist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceNameAdaptor(context, placenamelist));
        }

       else if (position== 1){

            placequalitylist = PlaceQuality.getPlacequalitylist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context, 3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceQualityAdaptor(context, placequalitylist));
        }

        else if(position == 2) {
            placeDescriptionList = PlaceDescriptionAdapter.getSelectedPlaceList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceDescriptionAdapterInPlaceOverview(context, placeDescriptionList));
        }

        else if(position == 3) {
            placeOffersList = ChildPlaceOffersAdapter.getPlaceOfferList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context, 2));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceOfferAdapterInPlaceOverview(context, placeOffersList));
        }
        else if(position == 4) {
            numberOfGuestsList = NumberOfGuests.getNumberOfGuestsList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new NumberOfGuestsAdapterInPlaceOverview(context, numberOfGuestsList));
        }
        else if(position == 5) {
            rent = PlaceRent.getPlaceRent();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceRentAdapterInPlaceOverview(rent));
        }

        else if(position == 6) {
            placeSecurityList = PlaceSecurity.getPlaceSecurityList();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new LinearLayoutManager(context));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceSecurityAdapterInPlaceOverview(context, placeSecurityList));
        }
        else if (position == 7){

            placeimagelist = Upload_image.getPlaceimagelist();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context,3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceImageAdapter(context, placeimagelist));
        }
        else if (position == 8){

            placeimagelistroom = PlaceRoomImage.getPlaceimagelistroom();

            holder.childRecyclerViewPlaceOverview.setLayoutManager(new GridLayoutManager(context,3));
            holder.childRecyclerViewPlaceOverview.setAdapter(new PlaceRoomImageAdapter(context, placeimagelistroom));
        }
        else {

        }
    }

    @Override
    public int getItemCount() {
        return fragmentNames.size();
    }

    public class PlaceOverviewViewHolder extends RecyclerView.ViewHolder {
        TextView fragmentName;
        RecyclerView childRecyclerViewPlaceOverview;

        public PlaceOverviewViewHolder(@NonNull View itemView) {
            super(itemView);

            fragmentName = itemView.findViewById(R.id.id_fragmentNamePlaceOverview);
            childRecyclerViewPlaceOverview = itemView.findViewById(R.id.id_childRecyclerViewPlaceOverview);
        }
    }

   /* public boolean saveTheData() {
//      String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Hotel Data");

        HashMap<String, ArrayList<DataClass>> hashMapForDataClass = new HashMap<>();
        hashMapForDataClass.put("PlaceDescriptionList", placeDescriptionList);
        hashMapForDataClass.put("PlaceOffersList", placeOffersList);

        boolean result1 = reference.setValue(hashMapForDataClass).isSuccessful();
        boolean result2 = reference.child("PlaceSecurityList").setValue(placeSecurityList).isSuccessful();
        boolean result3 = reference.child("Rent").setValue(rent).isSuccessful();

        boolean result = true;

        if(!result1 && !result2 && !result3) result = false;

        if(!result) {
            Toast.makeText(context, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();
             *//* TODO: Write the code here for redirecting user to the home page after saving the data. *//*
            return true;
        }
        return false;*/

        public boolean saveTheData() {

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // creating object to get instance of whole database
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        // getting reference of a node from database
        DatabaseReference node = db.getReference().child(uid).child("Hotel Data");

        HashMap<String, ArrayList<DataClass>> hashMapForDataClass = new HashMap<>();
        hashMapForDataClass.put("PlaceDescriptionList", placeDescriptionList);
        hashMapForDataClass.put("PlaceOffersList", placeOffersList);

        HashMap<String, Integer> hashMapForGuestsList = new HashMap<>();
        hashMapForGuestsList.put("Guests", numberOfGuestsList[0]);
        hashMapForGuestsList.put("Beds", numberOfGuestsList[1]);
        hashMapForGuestsList.put("Bedrooms", numberOfGuestsList[2]);
        hashMapForGuestsList.put("Bathrooms", numberOfGuestsList[3]);
        hashMapForGuestsList.put("Private Bathrooms?", numberOfGuestsList[4]);

        boolean result1 = node.setValue(hashMapForDataClass).isSuccessful();
        boolean result2 = node.child("NumberOfGuestsList").setValue(hashMapForGuestsList).isSuccessful();

        boolean result3 = node.child("PlaceSecurityList").setValue(placeSecurityList).isSuccessful();
        boolean result4 = node.child("PlaceQualityList").setValue(placequalitylist).isSuccessful();
        boolean result5 = node.child("Rent").setValue(PlaceRent.getPlaceRent()).isSuccessful();
        boolean result6 = node.child("PlaceNameList").setValue(placenamelist.get(0)).isSuccessful();

        boolean result7 = node.child("PlaceImageList").setValue(placeimagelist).isSuccessful();
        boolean result8 = node.child("PlaceImageListRoom").setValue(placeimagelistroom).isSuccessful();


//
        boolean result = true;

        if(!result1  && !result3 ) result = false;

        if(!result) {
            Toast.makeText(context, "Data Saved Successfully!", Toast.LENGTH_SHORT).show();
            /* TODO: Write the code here for redirecting user to the home page after saving the data. */
            return true;
        }
        return false;
        //return dataSaved;

    }
}