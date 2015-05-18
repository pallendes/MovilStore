package com.movilstore;

import java.util.ArrayList;
import java.util.List;

import utils.GuiTabListener;
import adapters.ListDrawerAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import fragments.GuiAllProducts;
import fragments.GuiCategorias;
import fragments.GuiInicio;
import fragments.GuiSubastas;

public class GUI extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	
	private List<String> itemsdrawer;
	private List<Integer> iconos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gui_drawer);

		setLists();
		
		// Navigation drawer
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.right_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mDrawerList.setAdapter(new ListDrawerAdapter(this, itemsdrawer, iconos));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, 
						mDrawerLayout, 
						R.drawable.ic_drawer, 
						R.string.app_name, 
						R.string.app_name 
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); 
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// //////////////////////////////////////////////////////////////////////////////////

		ActionBar bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		bar.addTab(bar.newTab().setText("Categorias")
				.setTabListener(new GuiTabListener(new GuiCategorias())));
		bar.addTab(bar.newTab().setText("Inicio")
						.setTabListener(new GuiTabListener(new GuiInicio())), true);
		bar.addTab(bar.newTab().setText("Subastas")
				.setTabListener(new GuiTabListener(new GuiSubastas())));
		bar.addTab(bar.newTab().setText("Recientes")
				.setTabListener(new GuiTabListener(new GuiAllProducts())));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.mnuusrdata) {
			startActivity(new Intent(this, GuiUserData.class));
		}
		return false;
	}

	public void nuevoArticulo(View view) {

		Intent intent = new Intent(this, GuiCrearVenta.class);
		startActivity(intent);

	}
	
	/* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	
        	if(itemsdrawer.get(position) == "Mi Cuenta"){
        		startActivity(new Intent(GUI.this, GuiUserData.class));
        	}
        	
        	if(itemsdrawer.get(position) == "Nuevo Articulo")
        		startActivity(new Intent(GUI.this, GuiCrearVenta.class));
        	
        	if(itemsdrawer.get(position) == "Cerrar Sesion"){
        		SharedPreferences settings = getSharedPreferences(LogginActivity.PREFS_NAME, 0);
        		SharedPreferences.Editor editor = settings.edit();
        		editor.clear();
        		editor.commit();
        		finish();
        	}
        }
    }
	
	private void setLists(){
	
		itemsdrawer = new ArrayList<String>();
		iconos = new ArrayList<Integer>();
		
		itemsdrawer.add("Mi Cuenta");
		itemsdrawer.add("Nuevo Articulo");
		itemsdrawer.add("Cerrar Sesion");
		
		iconos.add(R.drawable.user_dark);
		iconos.add(R.drawable.new_product_dark);
		iconos.add(R.drawable.closesession);
		
	}

}
