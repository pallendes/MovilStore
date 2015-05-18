package utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class GetImgRequest extends AsyncTask<Void, Void, JSONArray> {

	private HttpClient httpclient;
	private static final String URI = "http://192.168.56.1:80/movilstoreapi/imgarticulos/";
	private int idarticulo;
	private Context context;
	private ProgressDialog progressDialog;
	
	public GetImgRequest(Context context, int idarticulo) {
		super();
		this.context = context;
		this.idarticulo = idarticulo;
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
		httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(URI + this.idarticulo);
		httpget.setHeader("content-type", "application/json");
		JSONArray jsonresp = null; 
		

		HttpResponse resp;
		try {
			
			resp = httpclient.execute(httpget);
			String respsrt = EntityUtils.toString(resp.getEntity());
			jsonresp =  new JSONArray(respsrt);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return jsonresp;
	}

}
