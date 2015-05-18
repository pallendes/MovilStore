package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import persistence.CategoriasModel;
import persistence.DBHelper;
import utils.GetRequest;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

@TargetApi(16)
public class Categoria extends DBHelper{

	private static String TABLE = "Categorias";
	private static String KEY_ID = "idcategorias";
	private Context context;
	
	public Categoria(Context context) {
		super(context);
		this.context = context;
	}
	
	public CategoriasModel getCategoria(int id){
		
		CategoriasModel categoria = null;
		
		super.openDataBase();
		
		Cursor result = super.msdb.query(true, TABLE, null, KEY_ID + "=" + id, 
				null, null, null, null, null, null);
		
		if(result.getColumnCount() != 0){
			if(result.moveToFirst()){
				categoria = new CategoriasModel(result.getInt(0), result.getString(1));
			}
		}
		
		super.close();
		
		return categoria;
	}
	
	public List<CategoriasModel> getCategorias(){
		
		super.openDataBase();
		
		ArrayList<CategoriasModel> categorias = new ArrayList<CategoriasModel>();
		Cursor result = super.msdb.query(TABLE, null, null, null, null, null, null);
		
		result = checkCursor(result);
		
		if(result.moveToFirst()){
			do{
				categorias.add(new CategoriasModel(result.getInt(0), result.getString(1)));
			}while(result.moveToNext());
		}
		
		super.close();
		
		return categorias;
	}
	
	private Cursor checkCursor(Cursor result) {

		if (result.getCount() == 0) {
			if (!this.insertData()) {
				Log.e("Categorias", "Error al insertar los datos");
				return null;
			} else {
				result = msdb.query(TABLE, null, null, null, null, null, null,
						null);
				return result;
			}
		} else
			return result;

	}
	
	public Boolean insertData() {
		
		ContentValues values = new ContentValues();
		JSONArray jarray = null;

		GetRequest request = new GetRequest(context, "categorias");
		request.execute();

		int response = 0;

		try {

			jarray = request.get();
			
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject jobject = jarray.getJSONObject(i);
	
				values.put("idcategorias", jobject.getString("idcategorias"));
				values.put("nombre", jobject.getString("nombre"));
			
				response = (int) super.msdb.insert(TABLE, null, values);
			}
			
		} catch (InterruptedException e) {
			Log.e("Categorias", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("Categorias", "ExecutionException : " + e.toString());
		} catch (JSONException e) {
			Log.e("Categorias", "JSONException : " + e.toString());
		}

		return (response > 0) ? true : false;

	}

}
