package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import persistence.DBHelper;
import persistence.ImgArticulosModel;
import utils.GetImgRequest;
import android.content.Context;
import android.util.Log;

public class ImgArticulos extends DBHelper{

	public Context context;
	private int idarticulo;
	
	public ImgArticulos(Context context, int idarticulo) {
		super(context);
		this.context = context;
		this.idarticulo = idarticulo;
	}
	
	
	public List<ImgArticulosModel> getImgArticulos(){
		
		JSONArray jarray = null;
		
		GetImgRequest request = new GetImgRequest(this.context, this.idarticulo);
		request.execute();
		
		ArrayList<ImgArticulosModel> imgarticulos = new ArrayList<ImgArticulosModel>();
		
		try {
			
			 jarray = request.get();
			
			 for (int i = 0; i < jarray.length(); i++){
				 
				 JSONObject jobject = jarray.getJSONObject(i);
				
				 imgarticulos.add(new ImgArticulosModel(jobject.getInt("idimgarticulo"),
						 jobject.getInt("idarticulo"), jobject.getString("imagen")));
				 
			 }
			 
		} catch (JSONException e) {
			Log.e("ImgArticulos", "JSONException : " + e.toString());
		} catch (InterruptedException e) {
			Log.e("ImgArticulos", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("ImgArticulos", "ExecutionException : " + e.toString());
		}
		
		return imgarticulos;
		
	}
	
	
}
