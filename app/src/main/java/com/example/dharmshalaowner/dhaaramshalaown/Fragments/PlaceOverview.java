package com.example.dharmshalaowner.dhaaramshalaown.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dharmshalaowner.dhaaramshalaown.Adapters.PlaceOverviewAdapter;
import com.example.dharmshalaowner.R;

import java.util.ArrayList;

public class PlaceOverview extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity());
    PlaceOverviewAdapter placeOverviewAdapter;
    ArrayList<String> fragmentName = new ArrayList<>();
    RelativeLayout progressBarLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_overview, container, false);
        initializeTheView(view);

        fragmentName.add("Place Name");
        fragmentName.add("Place Quality");
        fragmentName.add("Place Description");
        fragmentName.add("Place Offers");
        fragmentName.add("Number of Guests");
        fragmentName.add("Place Rent");
        fragmentName.add("Place Security");
        fragmentName.add("Place Images");
        fragmentName.add("Place Room Images");

        placeOverviewAdapter = new PlaceOverviewAdapter(getContext(), fragmentName);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(placeOverviewAdapter);

        return view;
    }

    public ArrayList<String> getFragmentName() {
        return fragmentName;
    }

    void initializeTheView(View view) {
        recyclerView = view.findViewById(R.id.id_mainRecyclerViewPlaceOverview);
        progressBarLayout = view.findViewById(R.id.id_dataSavingProgressBarLayout);
    }

    public void showProgressBar() {
        progressBarLayout.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBarLayout.setVisibility(View.GONE);
    }

    public void saveTheData(RelativeLayout bottomLayout) {
        bottomLayout.setVisibility(View.GONE);
        showProgressBar();
        boolean result = placeOverviewAdapter.saveTheData();
        if(result) {
            hideProgressBar();
            bottomLayout.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(getContext(), "Some error occurred! Please try again after some time!", Toast.LENGTH_SHORT).show();
        }
    }
}