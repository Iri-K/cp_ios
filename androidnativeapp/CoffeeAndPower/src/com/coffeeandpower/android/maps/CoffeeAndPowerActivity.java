package com.coffeeandpower.android.maps;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * The main map GUI.
 * Android Activity objects represent individual "screens" that users interact with.
 * The system maintains a stack of these as users move to new activities and return from them.
 * 
 * @author Melinda Green
 */
public class CoffeeAndPowerActivity extends MapActivity {
    private final static int CITY_LEVEL = 14;
    private MapAnnotations mAnnotations; // All glyphs and other overlays.

    // Just a static array of predefined annotations for prototyping.
    // This will grow into a dynamically managed list.
    private static OverlayItem mOverlayItems[] = {
        new OverlayItem(GeoPointUtils.getGeoPoint("1825+Market+Street+San+Francisco"), "Headquarters", "Coffee & Power"),
        new OverlayItem(GeoPointUtils.getGeoPoint("2340+Francisco+Street+San+Francisco"), "Developer", "Melinda Green"),
        new OverlayItem(GeoPointUtils.getGeoPoint("481+York+Street+San+Francisco"), "Developer", "Charity Majors"),
    };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Drawable drawableMarker = getResources().getDrawable(R.drawable.androidmarker);
        mAnnotations = new MapAnnotations(drawableMarker, this);

        // Add all the initial annotations.
        for(OverlayItem item : mOverlayItems)
            addAnnotation(item);

        MapView mapView = (MapView) findViewById(R.id.mapview); // From main.xml
        mapView.setBuiltInZoomControls(true); // Shows the +/- controls as user interacts with the map.
        mapView.getOverlays().add(mAnnotations); // Populates the map with all annotations.
        MapController controller = mapView.getController(); // For programmatically driving the map.
        controller.setCenter(mOverlayItems[0].getPoint()); // Center the map on the C&P headquarters.
        controller.setZoom(CITY_LEVEL); // Innitial zoom level. Users pinch-to-zoom from there.
    }

    private void addAnnotation(OverlayItem annotation) {
        mAnnotations.addOverlay(annotation);
    }

    // Required by base class.
    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

}