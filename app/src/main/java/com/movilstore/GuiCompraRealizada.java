package com.movilstore;

import utils.Utils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GuiCompraRealizada extends Activity {

	TextView txtarticulo, txtvendedor;
	ImageView imgview;
	private static String CLASS = "GuiCompraRealizada";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gui_comprado);
	    
	    txtarticulo = (TextView) findViewById(R.id.comp_nombrearticulo);
	    txtvendedor = (TextView) findViewById(R.id.comp_nomvend);
	    imgview = (ImageView) findViewById(R.id.comp_imagen);
	    
	    txtarticulo.setText(getIntent().getStringExtra("articulo"));
	    txtvendedor.setText(getIntent().getStringExtra("vendedor"));
	    
	    try{
	    imgview.setImageBitmap(Utils.stringToBitmap(getIntent().getStringExtra("imagen")));
	    }catch(NullPointerException e){
	    	Log.e(CLASS, e.toString());
	    }
	    
	}
	
	public void backToMain(View v){
		Intent intent = new Intent(this, GUI.class);
		startActivity(intent);
	}

}
