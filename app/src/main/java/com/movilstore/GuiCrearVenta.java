package com.movilstore;

import com.movilstore.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class GuiCrearVenta extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gui_crear_venta);
	    
	    ActionBar ab = getActionBar();
	    ab.setDisplayHomeAsUpEnabled(true);
	    
	}

}
