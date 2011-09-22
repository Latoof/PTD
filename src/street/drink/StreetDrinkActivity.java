package street.drink;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class StreetDrinkActivity extends MapActivity {
	
	private DrinkItemizedOverlay m_itemizedoverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
     
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new UserLocationListener(this);
               
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5000, locationListener);
        

        //Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
              
        
        /*
        double x1 = l.getLatitude();
        double y1 = l.getLongitude();
        */
        
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
        m_itemizedoverlay = new DrinkItemizedOverlay(drawable);
         
        
        /* example */


        mapOverlays.add(m_itemizedoverlay);
       
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void positionCallback(Location loc) {
		this.addFlag(loc);
	}
	
	public void drawPoint(int lat, int lon) {
        GeoPoint point = new GeoPoint( lat, lon );
        
        OverlayItem overlayitem = new OverlayItem(point, "A", "This is yAition");
        
        m_itemizedoverlay.addOverlay(overlayitem);
	}
	
	public void addFlag(Location loc) {
        GeoPoint point = new GeoPoint( (int) loc.getLatitude(), (int) loc.getLongitude() );
        
        String lat = String.valueOf((int) loc.getLatitude());
        String lon = String.valueOf((int) loc.getLongitude());
        Log.e("GPS", "point added: lat="+lat+", lon="+lon);
        
        OverlayItem overlayitem = new OverlayItem(point, "You", "This is your position");
        
        m_itemizedoverlay.addOverlay(overlayitem);
        
        
	}
	
}