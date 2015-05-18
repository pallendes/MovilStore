package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import persistence.DBHelper;
import persistence.SubCategoriasModel;
import utils.GetRequest;

public class SubCategoria extends DBHelper {
	
	private static String TABLE = "subcategorias";
	private static String KEY_ID = "idcategorias";
	private Context context;

	public SubCategoria(Context context) {
		super(context);
		this.context = context;
	}
	
	@TargetApi(16)
	public SubCategoriasModel getSubCategoria(int id){
		Log.e("Subcat", "ID = " + id);
		SubCategoriasModel subcategoria = null;
		
		super.openDataBase();
		
		Cursor result = super.msdb.query(true, TABLE, null, KEY_ID + "=" + id, 
				null, null, null, null, null, null);
		
		if(result.getColumnCount() != 0){
			if(result.moveToFirst()){
				subcategoria = new SubCategoriasModel(result.getInt(0), result.getString(1));
			}
		}
		
		super.close();
		
		return subcategoria;
	}
	
	public List<SubCategoriasModel> getSubCategorias(int id){
		
		super.openDataBase();
		
		ArrayList<SubCategoriasModel> subcategorias = new ArrayList<SubCategoriasModel>();
		Cursor result = super.msdb.query(TABLE, null,  KEY_ID + "=" + id, null, null, null, null);
		
		if(result.getCount() == 0){
			this.insertData();
			result = super.msdb.query(TABLE, null, 
					KEY_ID + "=" + id, null, null, null, null);
		}
		
		if(result.moveToFirst()){
			do{
				subcategorias.add(new SubCategoriasModel(result.getInt(0), result.getString(1)));
			}while(result.moveToNext());
		}
		
		super.close();
		
		return subcategorias;
	}
	
	public Boolean insertData() {
		
		ContentValues values = new ContentValues();
		JSONArray jarray = null;

		GetRequest request = new GetRequest(context, "subcategorias");
		request.execute();

		int response = 0;

		try {

			jarray = request.get();
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject jobject = jarray.getJSONObject(i);
	
				values.put("idsubcategoria", jobject.getString("idsubcategorias"));
				values.put("nombre", jobject.getString("nombre"));
				values.put("idcategorias", jobject.getString("idcategorias"));
				
				response = (int) super.msdb.insert(TABLE, null, values);
			}

			
		} catch (InterruptedException e) {
			Log.e("Subcategorias", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("Subcategorias", "ExecutionException : " + e.toString());
		} catch (JSONException e) {
			Log.e("Subcategorias", "JSONException : " + e.toString());
		}

		return (response > 0) ? true : false;

	}

}
