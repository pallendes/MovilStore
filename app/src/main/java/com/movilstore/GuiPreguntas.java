package com.movilstore;

import objects.Preguntas;
import utils.Utils;
import adapters.PreguntasAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class GuiPreguntas extends Activity {

	TextView txtvendedor, txtarticulo;
	RatingBar rbarreputacion;
	ImageView imview;
	private static String CLASS = "GuiPreguntas";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gui_preguntas_vendedor);
	    
	    getActionBar().setTitle("Preguntas al vendedor");
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    
	    txtvendedor = (TextView) findViewById(R.id.preg_nom_vendedor);
	    txtarticulo = (TextView) findViewById(R.id.preg_txt_articulo);
	    rbarreputacion = (RatingBar) findViewById(R.id.preg_ratingbar);
	    imview = (ImageView) findViewById(R.id.preg_vend_img);

	    Intent intent = getIntent();
	    
	    txtvendedor.setText(intent.getStringExtra("vendedor"));
	    txtarticulo.setText(intent.getStringExtra("articulo"));
	    rbarreputacion.setRating(intent.getIntExtra("reputacion", 0));
	    
	    try{
	    	imview.setImageBitmap(Utils.stringToBitmap(intent
	    		.getStringExtra("imgarticulo")));
	    }catch(NullPointerException e){
	    	Log.e(CLASS, e.toString());
	    }
	    
	    ListView listpreguntas = (ListView) findViewById(R.id.preg_list);
	    listpreguntas.setAdapter(new PreguntasAdapter
	    		(new Preguntas(this).getPreguntas(intent.getIntExtra("idarticulo", 0)), this));
	    
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.mnu_preguntas, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.mnuusrdata) {
			startActivity(new Intent(this, GuiUserData.class));
		}
		return false;
	}

}
