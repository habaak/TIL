package com.example.student.test;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    Button map,image,chart, boulangerie,bar,cafe;
    LinearLayout mapView,imageView,chartView,container;

    PicAdapter picAdapter;
    ArrayList<Pic> list;
    ListView listView;
    private GoogleMap mMap;
    //MarkerOptions marker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        map = findViewById(R.id.button);
        image = findViewById(R.id.button2);
        chart = findViewById(R.id.button3);

        boulangerie = findViewById(R.id.boulangerie);
        bar = findViewById(R.id.bar);
        cafe = findViewById(R.id.cafe);

        mapView = findViewById(R.id.mapView);
        imageView = findViewById(R.id.imageView);
        chartView = findViewById(R.id.chartView);
        container = findViewById(R.id.container);

        list = new ArrayList<>();
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon2));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon3));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon4));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon5));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon6));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon7));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon10));
        list.add(new Pic("000","2018-04-01","재밌쥬?",R.drawable.icon10));
        picAdapter = new PicAdapter(list);

        imageView.setVisibility(View.INVISIBLE);
        chartView.setVisibility(View.INVISIBLE);
    }

    public void clickUnderBtn(View v){
        if (v.getId()==R.id.button){
            mapView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            chartView.setVisibility(View.INVISIBLE);

        }else if(v.getId()==R.id.button2){
            mapView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);
            chartView.setVisibility(View.INVISIBLE);

        }else if(v.getId()==R.id.button3){
            mapView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            chartView.setVisibility(View.VISIBLE);
        }
    }

    public void clickStore(View v){
        Location location = new Location("");
        if(v.getId() == R.id.boulangerie) {
            location.setLatitude(37.4861441);
            location.setLongitude(126.9787639);
        }else if(v.getId() == R.id.bar){
            location.setLatitude(37.4958802);
            location.setLongitude(127.0203059);
        }else if(v.getId() == R.id.cafe){
            location.setLatitude(37.5798148);
            location.setLongitude(126.9845811);
        }
        showCurrentLocation(location);
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng myloc = new LatLng(37.5013068, 127.037471);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
    }

    private void showCurrentLocation(Location location) {
        mMap.clear();
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.addMarker(new MarkerOptions().position(curPoint).title("Busan"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 14));
        mMap.setMyLocationEnabled(true); //자신의 위치
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 14));

    }

    public class ItemAdapter extends BaseAdapter {
        ArrayList<Pic> list;

        public ItemAdapter(){}

        public ItemAdapter(ArrayList<Pic> list){ //Context : 안드로이드 액티비티 정보

            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public void addItem(Pic pic){
            list.add(pic);
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View vw = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vw = inflater.inflate(R.layout.pic, container, true);
            TextView name = vw.findViewById(R.id.textView);
            TextView phone = vw.findViewById(R.id.textView2);
            TextView age = vw.findViewById(R.id.textView3);
            ImageView img = vw.findViewById(R.id.imageView);

            name.setText(list.get(i).getName());
            phone.setText(list.get(i).getDate());
            age.setText(list.get(i).disc);
            img.setImageResource(list.get(i).getResId());

            return vw;
        }
    }

}
