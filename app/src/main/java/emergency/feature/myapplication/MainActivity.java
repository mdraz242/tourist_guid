package emergency.feature.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import emergency.feature.myapplication.activities.Emergency_features;
import emergency.feature.myapplication.activities.Hotel_resurant_activities;
import emergency.feature.myapplication.adapter.RecentsAdapter;
import emergency.feature.myapplication.adapter.TopPlacesAdapter;
import emergency.feature.myapplication.models.HotPlaces;
import emergency.feature.myapplication.models.LeaserKnown_Places;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;
    ImageView saftey, hotel;
    String CITYNAME;


    LocationManager locationManager;
    double latitude;
    double longitude;
    TextView getCityName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saftey = findViewById(R.id.saftey);
        getCityName = findViewById(R.id.textView);
        hotel = findViewById(R.id.hotel);


        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Hotel_resurant_activities.class));
            }
        });
        saftey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Emergency_features.class));
            }
        });



        locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        }
        try {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);

            CITYNAME = LName(location.getLatitude(), location.getLongitude());

        } catch (Exception e) {
        }

        getCityName.setText(CITYNAME);

        List<HotPlaces> hotPlaces = new ArrayList<>();
        hotPlaces.add(new HotPlaces("Sukhna Lake","Chandigarh- Sc 1","Approx 5k Visitor Daily",R.drawable.sk));

        hotPlaces.add(new HotPlaces("Rock Garden","Chandigarh- Sc 1","Approx 4k Visitor Daily",R.drawable.rk));

        hotPlaces.add(new HotPlaces("Rose Garden","Chandigarh- Sc 16","",R.drawable.rs));
        hotPlaces.add(new HotPlaces("Chhatbir Zoo","Chhat, Zirakpur","",R.drawable.choter));
        hotPlaces.add(new HotPlaces("Japanese Garden","Chandigarh- Sc 31","",R.drawable.jp));
        hotPlaces.add(new HotPlaces("Nada Sahib Gurudwara","Panchkula","",R.drawable.nand));

        setRecentRecycler(hotPlaces);

        List<LeaserKnown_Places> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new LeaserKnown_Places("Fragrance garden","Chandigarh- Sc 36","",R.drawable.topplaces));
        topPlacesDataList.add(new LeaserKnown_Places("JAYANTI DAM ","India","",R.drawable.jp));
        topPlacesDataList.add(new LeaserKnown_Places("Patel market","India","",R.drawable.pt));
        topPlacesDataList.add(new LeaserKnown_Places("Morni hills","India","",R.drawable.mt));

        setTopPlacesRecycler(topPlacesDataList);

    }

    private  void setRecentRecycler(List<HotPlaces> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<LeaserKnown_Places> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
    public String LName(double lat , double lon) throws IOException {
        String city = "";
        Geocoder geocoder = new Geocoder(getApplication(), Locale.getDefault());
        List<Address> addresses;


        addresses = geocoder.getFromLocation(lat, lon, 0);
        for (Address adr :addresses){

            if (adr.getLocality() !=null && adr.getLocality().length()>0){
                city = adr.getLocality();
                break;
            }

        }
        return city;
    }
    public void onLocationChanged(@NonNull Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();

    }

}
