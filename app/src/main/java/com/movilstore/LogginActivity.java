package com.movilstore;

import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.GetRequest;
import utils.Utils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LogginActivity extends Activity {

	public static final String PREFS_NAME = "PrefsFile";
	public static final String USER_ID = "user_id";
	private EditText txtidemail, txtpass;
	public static final String URI = "loggin/";
	private CheckBox logcheckbox;
	private SharedPreferences settings;
	private SharedPreferences.Editor editor;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gui_loggin);
		
		settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();
		
		txtidemail = (EditText) findViewById(R.id.txtlogusr);
		txtpass = (EditText) findViewById(R.id.txtpass);
		logcheckbox = (CheckBox) findViewById(R.id.logcheckbox);
		
		if(settings.getBoolean("remember", false)){
			
			String user_id = settings.getString("user_id", null);
			String pass = settings.getString("pass", null);
			
			if(user_id != null && pass != null){
				validar(user_id, pass);
			}
		
		}
		
	}

	public void ingresar(View view) {
		validar(txtidemail.getText().toString(), txtpass.getText().toString());
	}
	
	private void validar(String user_id, String pass){
		
		if(user_id != "" || pass != ""){
			
			GetRequest grequest = new GetRequest(this, URI + 
					user_id + "/" + pass);
			grequest.execute();
			
			JSONArray jarray = null;
			
			try {
				
				jarray = grequest.get();
				JSONObject jobject = jarray.getJSONObject(0);
				
				if (Utils.checkInternetConnection(this)){
					
					if(jobject.getString("status") == "true"){
						
						setStoreData(user_id, pass);
						
						startActivity(new Intent(this, GUI.class));
						
					}
					else{
						Toast.makeText(this, "Usuario o contraseña " +
								"incorrecta", Toast.LENGTH_LONG).show();
					}
				}
				else
					showDialog();
				
			} catch (InterruptedException e) {
				Log.e("Loggin", e.toString());
			} catch (ExecutionException e) {
				Log.e("Loggin", e.toString());
			} catch (JSONException e) {
				Log.e("Loggin", e.toString());
			}
			
		}else
			Toast.makeText(this, "Debes ingresar con tu nombre de usuario y contraseña " +
					"incorrecta", Toast.LENGTH_LONG).show();

	}

	private void setStoreData(String user, String pass) {
		
		editor.putString("user_id", user);
		editor.putString("pass", pass);
		editor.putBoolean("remember", logcheckbox.isChecked());
		
		editor.commit();
		
	}

	private void showDialog() {

		Builder adialog = new AlertDialog.Builder(this);
		adialog.setTitle("Sin conexion a internet");
		adialog.setMessage("Para poder acceder a Movil Store debes estar conectado"
				+ "a internet...¿Deseas activar tu conexion?");
		adialog.setPositiveButton("Si!", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivityForResult(new Intent(
						android.provider.Settings.ACTION_WIRELESS_SETTINGS), 0);
			}
		});
		adialog.setNegativeButton("No gracias :)", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		adialog.show();
	}

}
