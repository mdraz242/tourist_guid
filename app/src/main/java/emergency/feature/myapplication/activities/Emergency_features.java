package emergency.feature.myapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import emergency.feature.myapplication.R;

public class Emergency_features extends AppCompatActivity {

    CardView sos, send_location, quick_call, police_station, quick_sms;
    LocationManager locationManager;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_features);

        sos = findViewById(R.id.soss);
        send_location = findViewById(R.id.send_location);
        quick_call = findViewById(R.id.quick_call);
        police_station = findViewById(R.id.police_location);
        quick_call = findViewById(R.id.sms);

        locationManager = (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplication(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        }
        try {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);

         //   CITYNAME = LName(location.getLatitude(), location.getLongitude());

        } catch (Exception e) {
        }


        police_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Police Station near me"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });


    }
    public void onLocationChanged(@NonNull Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();

    }

    public void send_location_WP() {
     //   String whatsAppMessage = name + "\n I am here, Help me \n" + "http://maps.google.com/?q=" + latitude + "," + longitude;
         String whatsAppMessage2 ="Arbaj"+ "\n I am in problem, Help me \n"+ "http://maps.google.com/?q=" + latitude + "," + longitude;
        try {
            String mobile = "91" + "8445740130";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=" + mobile + "&text=" + whatsAppMessage2)));
        } catch (Exception e) {
            //whatsapp app not install
        }

    }
}