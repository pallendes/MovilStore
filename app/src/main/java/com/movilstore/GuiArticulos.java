package com.movilstore;

import java.util.List;

import objects.Articulo;
import persistence.ArticuloModel;
import adapters.AllProductsListAdapter;
import android.app.ActionBar;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GuiArticulos extends ListActivity {

	private static String FILTER = "sub_categoria/";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    String subcategoria = getIntent().getStringExtra("sbname");
	    
	    ActionBar ab = getActionBar();
	    ab.setDisplayHomeAsUpEnabled(true);
	    ab.setTitle(subcategoria);
	    
	    subcategoria = subcategoria.replace(" ", "-");

	    Articulo articulo = new Articulo(this, FILTER + subcategoria);
	    List<ArticuloModel> listitems = articulo.getArticulos();
	    
	    if(listitems.size() == 0){
	    	Toast.makeText(this, "No hay articulos para " +
	    			"mostrar en esta categoria :(", Toast.LENGTH_LONG).show();
	    	finish();
	    }
	    
	    AllProductsListAdapter adapter = new AllProductsListAdapter(this, listitems);
	    setListAdapter(adapter);
	    
	}

}
