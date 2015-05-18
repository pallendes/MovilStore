package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetRequest extends AsyncTask<Void, Void, JSONArray>{
	
	private HttpClient httpclient;
	private static final String URI = "http://192.168.56.1:80/movilstoreapi/";
	private Context context;
	private ProgressDialog progressDialog;
	private String resource;
	
	
	public GetRequest(Context currentactivity, String resource) {
		this.context = currentactivity;
		this.resource = resource;
	}

	@Override
	protected void onPreExecute(){
		progressDialog = new ProgressDialog(context);
		progressDialog.setMessage("Cargando datos...");
		progressDialog.show();
	}
	
	@Override
	protected void onPostExecute(JSONArray result) {
		progressDialog.dismiss();
	}

	@Override
	protected JSONArray doInBackground(Void... params) {
		
		if (!Utils.checkInternetConnection(context))
			Toast.makeText(context, "Debes tener una conexion " +
					"a internet activa", Toast.LENGTH_SHORT).show();
		
		httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(URI + this.resource);
		httpget.setHeader("content-type", "application/json");
		JSONArray jsonresp = null; 
		
		try{
			
			HttpResponse resp = httpclient.execute(httpget);
			String respsrt = EntityUtils.toString(resp.getEntity());

			jsonresp =  new JSONArray(respsrt);

		}catch(Exception e){
			Log.e("GetRequest", "Error al obtener la respuesta " +
					"del servidor: " + e.toString());
		}
		
		return jsonresp;
	}
	
}
