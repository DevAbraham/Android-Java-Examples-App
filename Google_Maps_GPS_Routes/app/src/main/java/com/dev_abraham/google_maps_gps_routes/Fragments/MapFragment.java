package com.dev_abraham.google_maps_gps_routes.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev_abraham.google_maps_gps_routes.R;
import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements  OnMapReadyCallback , GoogleMap.OnMarkerDragListener, View.OnClickListener , LocationListener ,  GoogleMap.OnMarkerClickListener, RoutingListener {

    private static final int REQ_PERMISSION = 1000;

    private View rootView;
    private GoogleMap mMap;
    private MapView mapView;
    private Geocoder geocoder;
    private List<Address> address;
    private Marker markerPosition;
    private Marker markerCosolea;
    private Marker markerMina;
    boolean myLocation = false;
    private FloatingActionButton fbtnGPS;
    private LocationManager locationManager;
    private Location currentLocation;

    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        fbtnGPS = (FloatingActionButton) rootView.findViewById(R.id.fbtnGPS);
        fbtnGPS.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView=(MapView) rootView.findViewById(R.id.map);
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!myLocation){
            if(checkPermission()&& mMap!=null){
           setPosition();
            }
        }
      }

    private boolean isGPSEnabled(){
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(),Settings.Secure.LOCATION_MODE);
            if(gpsSignal==0){
                //GPS no esta activado
                return false;
            }else{
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showInfoAlert(){
        new AlertDialog.Builder(getContext())
                .setTitle("GPS Signal")
                .setMessage("You dont have gps signal enable.Would you like enable now?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel",null)
                .show();
    }

    private void setPosition(){
        //mMap.setMyLocationEnabled(true);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, this);

        myLocation=true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        // Add a marker in Sydney and move the camera
        mMap.getUiSettings().setMapToolbarEnabled(false);

        //Agrega Marcador
        LatLng cosoleacaque = new LatLng(18.005321185858122, -94.62714186277454);
        MarkerOptions marker = new MarkerOptions();
        marker.position(cosoleacaque);
        marker.title("Mi marcador");
        marker.snippet("Snippet del marcador");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.modulo));
        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(18);
        markerCosolea = mMap.addMarker(marker);

        LatLng mina = new LatLng(18.018, -94.627141);
        MarkerOptions markerMinat = new MarkerOptions();
        markerMinat.position(mina);
        markerMinat.title("Mi marcador");
        markerMinat.snippet("Snippet del marcador");
        markerMinat.icon(BitmapDescriptorFactory.fromResource(R.drawable.modulo));
        markerMina = mMap.addMarker(markerMinat);

        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);
        geocoder = new Geocoder( getContext(), Locale.getDefault());

        if(checkPermission()){
           setPosition();
        }
        else {
            askPermission();
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitud = marker.getPosition().longitude;

        try {
            address =  geocoder.getFromLocation(latitude,longitud,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String getAddressLine = address.get(0).getAddressLine(0);
        String getLocality = address.get(0).getLocality();
        String getAdminArea = address.get(0).getAdminArea();
        String getPostalCode = address.get(0).getPostalCode();
        marker.setSnippet(getLocality + " "+getAdminArea + " ("+getPostalCode+")");
        marker.showInfoWindow();
    }

    @Override
    public void onClick(View v) {
        if(!this.isGPSEnabled()){
            showInfoAlert();
        }else{
            if(checkPermission()){
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location == null){
                    location= locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                currentLocation = location;
                if(currentLocation!=null){
                    createOrUpdateByLocation(location);
                    zoomCamera(location);
                }
            }
        }
    }

    // Check for permission to access Location
    private boolean checkPermission() {
        Log.d("TAG", "checkPermission()");
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }

    private void askPermission() {
        Log.d("TAG", "askPermission()");
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                REQ_PERMISSION
        );
    }

    private  void zoomCamera(Location location){
        CameraPosition cameraMaps = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(),location.getLongitude()))
                .zoom(15)
                .bearing(10)
                .tilt(90)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraMaps));
    }

    private void createOrUpdateByLocation( Location location){
        if(markerPosition ==null){
            MarkerOptions markerO = new MarkerOptions();
            markerO.position(new LatLng(location.getLatitude(),location.getLongitude()));
            markerO.snippet("Posicion Del Usuario");
            markerO.icon(BitmapDescriptorFactory.fromResource(R.drawable.localizar));
            markerPosition = mMap.addMarker(markerO);
            zoomCamera(location);
        }else{
            markerPosition.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Log.e("TAG","Position change");
        createOrUpdateByLocation(location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.equals(markerCosolea))
        {

            new AlertDialog.Builder(getContext())
                    .setTitle("Marcador Cosolea")
                    .setMessage("Trace route?")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        //Trazar ruta
                            route();
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
                    }

        return true;
    }

    ProgressDialog progressDialog;

    public void route(){

        LatLng end = new LatLng(markerCosolea.getPosition().latitude, markerCosolea.getPosition().longitude);
        LatLng start= new LatLng(markerPosition.getPosition().latitude, markerPosition.getPosition().longitude);
        progressDialog = ProgressDialog.show(getContext(), "Please wait.",
                "Loading", true);

        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(this)
                .alternativeRoutes(true)
                .waypoints(start, end)
                .key("")//Api key google maps
                .build();
        routing.execute();
    }
    //https://github.com/jd-alexander/Google-Directions-Android
    @Override
    public void onRoutingFailure(RouteException e) {
        progressDialog.dismiss();
        if(e != null) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(), "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRoutingStart() {

    }
    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent,R.color.design_default_color_primary_dark   ,R.color.primary_dark_material_light};

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {

        
        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int i = 0; i <route.size(); i++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = mMap.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
        }

        progressDialog.dismiss();
    }

    @Override
    public void onRoutingCancelled() {
        Log.i("TAG ", "Routing was cancelled.");
    }
}
