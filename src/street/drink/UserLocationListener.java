package street.drink;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class UserLocationListener implements android.location.LocationListener {

	private StreetDrinkActivity m_mainApp;
	
	public UserLocationListener(StreetDrinkActivity app) {
		this.m_mainApp = app;
	}
	
	@Override
	public void onLocationChanged(Location loc) {
        String lat = String.valueOf(loc.getLatitude());
        String lon = String.valueOf(loc.getLongitude());
        Log.e("GPS", "location changed: lat="+lat+", lon="+lon);
        m_mainApp.positionCallback(loc);
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		m_mainApp.drawPoint(1000,2000);
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		m_mainApp.drawPoint(2000,1000);
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
