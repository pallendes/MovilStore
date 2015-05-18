package utils;

import com.movilstore.R;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class GuiTabListener implements TabListener {
		
	private Fragment fragment;
	
	public GuiTabListener(Fragment fg){
		this.fragment = fg;
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.container, fragment);
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	

}
