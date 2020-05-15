package com.example.swannavigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.swannavigation.R;
import com.google.gson.JsonObject;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class HomeFragment extends Fragment {
    private CarmenFeature home;
    private CarmenFeature work;
    private MapView mapView;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mapbox.getInstance(this.getContext(), getString(R.string.access_token));


        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {


                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        addUserLocations();

                        // Add the symbol layer icon to map for future use
                        //   style.addImage(symbolIconId, BitmapFactory.decodeResource(
                        //       DrawerActivity.this.getResources(), R.drawable.blue_marker_view));


                        //  enableLocationComponent(style);
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.

                        //  navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxmap);


// Create an empty GeoJSON source using the empty feature collection
                        setUpSource(style);

// Set up a new symbol layer for displaying the searched location's feature coordinates
                        setupLayer(style);
                    }
                });
                // Set the camera's starting position
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(41.885, -87.679)) // set the camera's center position
                        .zoom(12)  // set the camera's zoom level
                        .tilt(20)  // set the camera's tilt
                        .build();

                // Move the camera to that position
                mapboxMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                Toast.makeText(rootView.getContext(), "TEST", Toast.LENGTH_SHORT).show();
            }

        });
        return rootView;
    }


    private void setUpSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(geojsonSourceLayerId));

    }

    private void setupLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addLayer(new SymbolLayer("SYMBOL_LAYER_ID", geojsonSourceLayerId).withProperties(
                iconImage(symbolIconId),
                iconOffset(new Float[]{0f, -8f})
        ));
    }

    private void addUserLocations() {
        home = CarmenFeature.builder().text("Mapbox SF Office")
                .geometry(Point.fromLngLat(-122.3964485, 37.7912561))
                .placeName("50 Beale St, San Francisco, CA")
                .id("mapbox-sf")
                .properties(new JsonObject())
                .build();

        work = CarmenFeature.builder().text("Mapbox DC Office")
                .placeName("740 15th Street NW, Washington DC")
                .geometry(Point.fromLngLat(-77.0338348, 38.899750))
                .id("mapbox-dc")
                .properties(new JsonObject())
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


}
