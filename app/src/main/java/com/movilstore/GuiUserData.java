package com.movilstore;

import objects.Articulo;
import objects.Usuario;
import persistence.UsuarioModel;
import adapters.AllProductsListAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class GuiUserData extends Activity {

	private final static String RESOURCE = "nickname/";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.gui_user_data);
	    
	    ActionBar actionBar = getActionBar();
	    actionBar.setTitle("Mis Datos");
	    actionBar.setDisplayHomeAsUpEnabled(true);
	 
	    UsuarioModel userdata = new Usuario(this).getUserData();
	    //setear listview de articulos publicados
	    Articulo articulosUsuarios = new Articulo(this, RESOURCE + userdata.getNickname());
	    
	    ListView listview = (ListView) findViewById(R.id.usdlvprodvend);
	    listview.setAdapter(new AllProductsListAdapter(this, 
	    		articulosUsuarios.getArticulos()));
	    
	    //setear datos de la informacion del usuario
	    TextView txtnickname = (TextView) findViewById(R.id.usd_txtnickname);
	    TextView txtnomvendedor = (TextView) findViewById(R.id.usd_txtnomvendedor);
	    TextView txtrut = (TextView) findViewById(R.id.usd_txtrut);
	    TextView txtemail = (TextView) findViewById(R.id.usd_txtemail);
	    RatingBar rbar = (RatingBar) findViewById(R.id.usd_rbar);
	    ImageView iusrfoto = (ImageView) findViewById(R.id.usd_userfoto);
	    
	    txtnickname.setText(userdata.getNickname());
	    txtnomvendedor.setText(userdata.getNombre() + " " + userdata.getApellido());
	    txtrut.setText(userdata.getRut());
	    txtemail.setText(userdata.getIdemail());
	    rbar.setRating(userdata.getReputacion());
	    iusrfoto.setImageBitmap(userdata.getFotoBitmap());
	    
	    
	}

}
