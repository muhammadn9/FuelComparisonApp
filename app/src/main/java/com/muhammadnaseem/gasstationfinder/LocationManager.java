package com.muhammadnaseem.gasstationfinder;

import android.content.Context;

public class LocationManager {

    private Context mContext;
    private LocationListener mListener;
    private double latitude;
    private double longitude;

    public LocationManager(Context context, LocationListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void getLocation() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mListener);
    }

    public interface LocationListener {
        void onLocationChanged(Location location);
    }

    public void setLocation(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
