package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import persistence.DBHelper;
import persistence.PreguntasModel;
import utils.GetRequest;
import android.content.Context;
import android.util.Log;

public class Preguntas extends DBHelper {

	private Context context;
	private static String RESOURCE = "preguntas/";

	public Preguntas(Context context) {
		super(context);
		this.context = context;
	}

	public List<PreguntasModel> getPreguntas(int idarticulo) {
		Log.e("pregutnas", String.valueOf(idarticulo));
		JSONArray jarray = null;

		GetRequest request = new GetRequest(this.context, RESOURCE
				+ idarticulo);
		request.execute();

		ArrayList<PreguntasModel> listpreguntas = new ArrayList<PreguntasModel>();

		try {

			jarray = request.get();

			for (int i = 0; i < jarray.length(); i++) {

				JSONObject jobject = jarray.getJSONObject(i);

				listpreguntas.add(new PreguntasModel(context, jobject
						.getString("idpregunta"),
						jobject.getString("pregunta"), jobject
								.getString("nickname"), jobject
								.getString("fecha_pregunta"), jobject
								.getString("respuesta"), jobject
								.getString("fecha_respuesta")));

			}

		} catch (InterruptedException e) {
			Log.e("Preguntas", "InterruptedException : " + e.toString());
		} catch (ExecutionException e) {
			Log.e("Preguntas", "ExecutionException : " + e.toString());
		} catch (JSONException e) {
			Log.e("Preguntas", "JSONException : " + e.toString());
		}

		return listpreguntas;
	}

}
