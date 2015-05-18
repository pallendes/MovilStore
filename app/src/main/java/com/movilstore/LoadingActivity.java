package com.movilstore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class LoadingActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.load_activity);
	    
	    Thread timer = new Thread(){
	    	
	    	public void run(){
	    		try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					Intent intent = new Intent("com.movilstore.LoadingActivty");
					startActivity(intent);
				}
	    	}
	    	
	    };
	   
	    timer.start();
	    
	}

}
