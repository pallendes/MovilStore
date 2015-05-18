package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class PutRequest extends AsyncTask<String, Void, Boolean>{

	private List<Map<String, Object>> hmaplist;
	private static final String URI = "http://192.168.56.1:80/movilstoreapi/transaccion/";
	private Context currentactivity;
	private ProgressDialog progressDialog;
	
	public PutRequest(List<Map<String, Object>> hmaplist, Context currentactivity) {
		
		this.hmaplist = hmaplist;
		this.currentactivity = currentactivity;
	}
	
	@Override
	protected void onPreExecute(){
		progressDialog = new ProgressDialog(currentactivity);
		progressDialog.setMessage("Realizando tu compra...");
		progressDialog.show();
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPut httpput = new HttpPut(URI + params[0]); 
		httpput.setHeader("content-type", "application/json");
		
		try {
			
			if(hmaplist != null){
				StringEntity entity = new StringEntity(this.listmapToJsonString(hmaplist));
				httpput.setEntity(entity);
			}
			
			HttpResponse httpresp = httpclient.execute(httpput);
			String resp = EntityUtils.toString(httpresp.getEntity());
			
			JSONObject jobject = new JSONObject(resp);
			
			if(jobject.getString("Resultado") == "true")
				return true;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		
		if(progressDialog.isShowing())
			progressDialog.dismiss();
		
		
	}
	
	private String listmapToJsonString(List<Map<String, Object>> list)
	{       
	    JSONArray json_arr = new JSONArray();
	    
	    for (Map<String, Object> map : list) {
	        JSONObject json_obj = new JSONObject();
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            
	        	String key = entry.getKey();
	            Object value = entry.getValue();
	            
	            try {
	                json_obj.put(key,value);
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	            
	        }
	        json_arr.put(json_obj);
	    }
	    return json_arr.toString();
	}
	
}
