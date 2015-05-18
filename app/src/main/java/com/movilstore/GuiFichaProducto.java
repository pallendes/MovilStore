package com.movilstore;

import java.util.concurrent.ExecutionException;

import utils.PutRequest;
import utils.Utils;
import adapters.ImageAdapter;
import adapters.PagerContainer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class GuiFichaProducto extends Activity {

	private TextView txtnomproducto, txtnomvendedor, txtprecio, txtdesc,
			txttipo, txtcat;
	private RatingBar rbar;
	private PagerContainer mContainer;
	private ImageView imview;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gui_ficha_producto);

		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);

		mContainer = (PagerContainer) findViewById(R.id.pager_container);

		ViewPager pager = mContainer.getViewPager();
		
		try{
			PagerAdapter adapter = new ImageAdapter(this, getIntent().getIntExtra(
					"idarticulo", 0));
			pager.setAdapter(adapter);
			pager.setOffscreenPageLimit(adapter.getCount());
			pager.setPageMargin(15);
			pager.setClipChildren(false);
		}catch(Exception e){
			Log.e("GuiFichaProductos Error: ", e.toString());
		}

		txtnomproducto = (TextView) findViewById(R.id.preg_nom_vendedor);
		txtnomvendedor = (TextView) findViewById(R.id.preg_txt_articulo);
		txtprecio = (TextView) findViewById(R.id.txtficprecio);
		rbar = (RatingBar) findViewById(R.id.preg_ratingbar);
		txtdesc = (TextView) findViewById(R.id.txtficdesc);
		txttipo = (TextView) findViewById(R.id.txtcarac);
		txtcat = (TextView) findViewById(R.id.txtficcat);
		imview = (ImageView) findViewById(R.id.preg_vend_img);

		txtnomproducto.setText(getIntent().getStringExtra("nombre"));
		txtnomvendedor.setText(getIntent().getStringExtra("nomvendedor"));
		txtprecio.setText("$" + getIntent().getIntExtra("precio", 0));
		rbar.setRating(getIntent().getIntExtra("reputacion", 0));
		txtdesc.setText(getIntent().getStringExtra("descripcion"));
		txttipo.setText(getIntent().getStringExtra("tipo"));
		txtcat.setText(getIntent().getStringExtra("categoria"));
		
		try{
			imview.setImageBitmap(Utils.stringToBitmap(getIntent()
					.getStringExtra("imgarticulo")));
		}catch(NullPointerException e){
			Log.e("GuiFichaProducto", e.toString());
		}

	}
	
	public void buyItem(View v){
		
		PutRequest prequest = new PutRequest(null, this);
		prequest.execute(Utils.getLoggedUser(this) + "/" 
				+ getIntent().getIntExtra("idarticulo", 0));
		
		try {

			if(prequest.get()){
				
				Intent intent = new Intent(this, GuiCompraRealizada.class);
				
				intent.putExtra("vendedor", getIntent().getStringExtra("nomvendedor"));
				intent.putExtra("articulo", getIntent().getStringExtra("nombre"));
				intent.putExtra("imagen", getIntent().getStringExtra("imgarticulo"));
				
				startActivity(new Intent(intent));
				
			}
			else{
				Toast.makeText(this, "Ha ocurrido un error, " +
						"intentalo mas tarde", Toast.LENGTH_LONG).show();
			}
			
			
		} catch (InterruptedException e) {
			Log.e("FichaProducto", e.toString());
		} catch (ExecutionException e) {
			Log.e("FichaProducto", e.toString());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mnu_gui_ficha_producto, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent;
		
		switch (item.getItemId()) {
			case android.R.id.home:
				// app icon in action bar clicked; go home
				intent = new Intent(this, GUI.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				return true;
			case R.id.item_question:
				intent = new Intent(this, GuiPreguntas.class);
				intent.putExtra("vendedor", getIntent().getStringExtra("nomvendedor"));
				intent.putExtra("articulo", getIntent().getStringExtra("nombre"));
				intent.putExtra("reputacion", getIntent().getIntExtra("reputacion", 0));
				intent.putExtra("idarticulo", getIntent().getIntExtra("idarticulo", 0));
				intent.putExtra("iconoarticulo", getIntent().getStringExtra("imgarticulo"));
				intent.putExtra("imgarticulo", getIntent().getStringExtra("imgarticulo"));
				startActivity(intent);
				return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
