package objects;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.movilstore.LogginActivity;

import persistence.DBHelper;
import persistence.UsuarioModel;
import utils.GetRequest;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

public class Usuario extends DBHelper {

	private static String TABLE = "user_data";
	private Context context;
	
	public Usuario(Context context) {
		super(context);
		this.context = context;
	}

	public UsuarioModel getUserData() {

		openDataBase();

		UsuarioModel userdata = null;

		msdb.delete(TABLE, null, null);
		
		insertData();
		
		Cursor result = msdb.query(TABLE, null, null,
				null, null, null, null, null);

		result = checkCursor(result);

		if (result.moveToFirst()) {
			userdata = new UsuarioModel(result.getString(0),
					result.getString(1), result.getString(2),
					result.getString(3), result.getString(4),
					result.getFloat(5), result.getString(6),
					result.getString(7));
		}

		return userdata;

	}

	private Cursor checkCursor(Cursor result) {

		if (result.getCount() == 0) {
			if (!this.insertData()) {
				Log.e("Usuario", "Error al insertar los datos");
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

		GetRequest request = new GetRequest(context, "usuarios/" + getCurrentUser());
		request.execute();

		int response = 0;

		try {

			jarray = request.get();
			JSONObject jobject = jarray.getJSONObject(0);

			values.put("idemail", jobject.getString("idemail"));
			values.put("rut", jobject.getString("rut"));
			values.put("nombre", jobject.getString("nombre"));
			values.put("apellido", jobject.getString("apellido"));
			values.put("nickname", jobject.getString("nickname"));
			values.put("reputacion", jobject.getInt("reputacion"));
			values.put("telefono", jobject.getString("telefono"));
			values.put("foto", jobject.getString("foto"));

			response = (int) super.msdb.insert(TABLE, null, values);
			
		} catch (InterruptedException e) {
			Log.e("Usuario", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("Usuario", "ExecutionException : " + e.toString());
		} catch (JSONException e) {
			Log.e("Usuario", "JSONException : " + e.toString());
		}

		return (response > 0) ? true : false;

	}
	
	private String getCurrentUser(){
		SharedPreferences prefs = context.getSharedPreferences(LogginActivity.PREFS_NAME, 0);
		return prefs.getString(LogginActivity.USER_ID, null);
	}

}
