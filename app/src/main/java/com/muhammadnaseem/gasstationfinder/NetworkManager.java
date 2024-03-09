package com.muhammadnaseem.gasstationfinder;

import org.json.JSONObject;

public class NetworkManager {

    public static void getGasStations(Context context, double latitude, double longitude, int radius, final VolleyCallback callback) {
        String url = "https://collectapi.com/api/gasPrice/gas-prices-api?lat=" + latitude + "&lon=" + longitude + "&radius=" + radius;

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                List<GasStation> gasStations = new ArrayList<>();
                JSONArray results = jsonObject.getJSONArray("data");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject station = results.getJSONObject(i);
                    GasStation gasStation = new GasStation(
                            station.getString("brand_name"),
                            station.getString("address"),
                            station.getDouble("price"));
                    gasStations.add(gasStation);
                }
                callback.onSuccess(gasStations);
            } catch (JSONException e) {
                e.printStackTrace();
                callback.onError("Error parsing data");
            }
        }, error -> {
            callback.onError("Network Error");
        });
        queue.add(request);
    }

    public interface VolleyCallback {
        void onSuccess(List<GasStation> gasStations);
        void onError(String message);
    }
}
