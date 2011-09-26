package street.drink;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;


public class DrinkItemizedOverlay extends ItemizedOverlay {
	
	private ArrayList<OverlayItem> m_Overlays = new ArrayList<OverlayItem>();
	private Context m_Context;


	public DrinkItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public DrinkItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  m_Context = context;
	}
	
	public void addOverlay(OverlayItem overlay) {
	    m_Overlays.add(overlay);
	    populate();
	}
	
	protected OverlayItem createItem(int i) {
	  return m_Overlays.get(i);
	}

	public int size() {
	  return m_Overlays.size();
	}
	
	protected boolean onTap(int index) {
		
	
	  OverlayItem item = m_Overlays.get(index);
	  
	  if (m_Context != null) {

		  AlertDialog.Builder dialog = new AlertDialog.Builder(m_Context);

		  dialog.setTitle(item.getTitle());
		  dialog.setMessage(item.getSnippet());
	      Log.e("GPS", "1");

		  dialog.show();
	      Log.e("GPS", "2");

	  }
	  
	  return true;
	  
	}
	


}
