package emergency.feature.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import emergency.feature.myapplication.R;
import emergency.feature.myapplication.adapter.Hotel_adapter;
import emergency.feature.myapplication.adapter.RecentsAdapter;
import emergency.feature.myapplication.adapter.TopPlacesAdapter;
import emergency.feature.myapplication.models.HotPlaces;
import emergency.feature.myapplication.models.Hotel_model;
import emergency.feature.myapplication.models.LeaserKnown_Places;

public class Hotel_resurant_activities extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    Hotel_adapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hotel_resurant_activities);

        List<HotPlaces> recentsDataList = new ArrayList<>();
        recentsDataList.add(new HotPlaces("Hotel Black Diamond ","Patiala Road, Zirakpur","From 649 INR",R.drawable.a));
        recentsDataList.add(new HotPlaces("Hotel Sameer Residency","Hotel Sameer Residency","From 499 INR",R.drawable.b));
        recentsDataList.add(new HotPlaces("Hotel R K Grand","Near Central School,Ajit Singh Nagar, Mohali","From 949 INR",R.drawable.c));
        recentsDataList.add(new HotPlaces("Collection O Sapphire","Collection O Sapphire","From 948",R.drawable.d));


        setRecentRecycler(recentsDataList);

        List<Hotel_model> hotel_models = new ArrayList<>();
        hotel_models.add(new Hotel_model("Sindhi Sweet","Chandigarh","From 199 INR ",R.drawable.aaa));
        hotel_models.add(new Hotel_model("KALSANG RESTAURANT","Chandigarh","From 299 INR ",R.drawable.bb));
        hotel_models.add(new Hotel_model("Korean Restaurants","Chandigarh","From 149 INR ",R.drawable.aa));


        setTopPlacesRecycler(hotel_models);

    }

    private  void setRecentRecycler(List<HotPlaces> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<Hotel_model> hotel_models){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new Hotel_adapter(this, hotel_models);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
    }
