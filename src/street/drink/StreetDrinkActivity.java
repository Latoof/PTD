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
	private MapView m_mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_mapView = (MapView) findViewById(R.id.mapview);
        m_mapView.setBuiltInZoomControls(true);

        Log.e("MAP",m_mapView.getLatitudeSpan()+","+m_mapView.getLongitudeSpan());
        
        List<Overlay> mapOverlays = m_mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
        m_itemizedoverlay = new DrinkItemizedOverlay(drawable, this);
         
        
        /* example */


        drawPoint(0,0);
        mapOverlays.add(m_itemizedoverlay);
       
        startLocationListener();

    }
    
    protected void startLocationListener() {
    	
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new UserLocationListener(this);
               
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 5000, locationListener);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	public void positionCallback(Location loc) {
		this.addFlag(loc);
		        
        m_mapView.getController().setZoom(75);
        m_mapView.getController().setCenter( new GeoPoint((int)(1000000*loc.getLatitude()), (int)(1000000*loc.getLongitude()) ) );

        
        Log.i("GPS","New position detected : "+(int)(1000000*loc.getLatitude())+","+(int)(1000000*loc.getLongitude()));
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