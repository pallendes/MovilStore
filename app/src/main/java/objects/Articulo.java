package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import persistence.ArticuloModel;
import persistence.DBHelper;
import utils.GetRequest;
import android.content.Context;
import android.util.Log;

public class Articulo extends DBHelper {

	private Context context;
	// private int limit = 10;
	private String resource;

	public Articulo(Context context) {
		super(context);
		this.context = context;
		this.resource = "articulos/";
	}

	public Articulo(Context context, String resource) {
		super(context);
		this.context = context;
		this.resource = "articulos/" + resource;
	}

	public ArticuloModel getArticulo(int id) {

		ArticuloModel articulo = null;

		JSONArray jarray = null;

		GetRequest request = new GetRequest(this.context, this.resource + id);
		request.execute();

		try {

			jarray = request.get();

			JSONObject jobject = jarray.getJSONObject(0);

			articulo = new ArticuloModel(jobject.getInt("idarticulo"),
					jobject.getString("nombre"), jobject.getInt("precio"),
					jobject.getString("descripcion"),
					jobject.getString("idemail"),
					jobject.getString("fechapublicacion"),
					jobject.getString("nickname"),
					jobject.getString("tipo_producto"),
					jobject.getString("sub_categoria"),
					jobject.getString("categoria"),
					jobject.getInt("reputacion"), jobject.getString("estado"),
					jobject.getString("imagenmuestra"));

		} catch (InterruptedException e) {
			Log.e("Articulo", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("Articulo", "ExecutionException : " + e.toString());
		} catch (JSONException e) {
			Log.e("Articulo", "JSONException : " + e.toString());
		}
		
		return articulo;
	}

	public List<ArticuloModel> getArticulos() {

		ArrayList<ArticuloModel> articulos = new ArrayList<ArticuloModel>();

		JSONArray jarray = null;

		GetRequest request = new GetRequest(this.context, this.resource);
		request.execute();

		try {

			jarray = request.get();

			for (int i = 0; i < jarray.length(); i++) {

				JSONObject jobject = jarray.getJSONObject(i);

				articulos.add(new ArticuloModel(jobject.getInt("idarticulo"),
						jobject.getString("nombre"), jobject.getInt("precio"),
						jobject.getString("descripcion"), jobject
								.getString("idemail"), jobject
								.getString("fechapublicacion"), jobject
								.getString("nickname"), jobject
								.getString("tipo_producto"), jobject
								.getString("sub_categoria"), jobject
								.getString("categoria"), jobject
								.getInt("reputacion"), jobject
								.getString("estado"), jobject
								.getString("imagenmuestra")));
			}

		} catch (InterruptedException e) {
			Log.e("Articulo", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("Articulo", "ExecutionException : " + e.toString());
		} catch (JSONException e) {
			Log.e("Articulo", "JSONException : " + e.toString());
		}

		return articulos;
	}

}
