package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Base64;
import android.util.Log;

public class Utils {

	public static final String PREFS_NAME = "PrefsFile";
	
	// metodo para comprobar si la aplicacion esta conectada a internet
	public static boolean checkInternetConnection(Context context) {

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		State internetmovil = manager.getNetworkInfo(0).getState();
		State wifi = manager.getNetworkInfo(1).getState();

		if (internetmovil == NetworkInfo.State.CONNECTED
				|| internetmovil == NetworkInfo.State.CONNECTING)
			return true;
		else if (wifi == NetworkInfo.State.CONNECTED
				|| wifi == NetworkInfo.State.CONNECTING)
			return true;
		else
			return false;

	}
	
	public static String getLoggedUser(Context context){
		
		SharedPreferences preffs = context.getSharedPreferences(PREFS_NAME, 0);
		
		return preffs.getString("user_id", null);
	}

	public static Bitmap stringToBitmap(String imgarticulo) {
		Bitmap foto = null;

		try {
			byte[] byteData = Base64.decode(imgarticulo, Base64.DEFAULT);
			foto = BitmapFactory.decodeByteArray(byteData, 0, byteData.length);
		} catch (Exception e) {
			Log.e("ImgArticulos", e.toString());
		}

		return getScaledImage(foto);
	}

	private static Bitmap getScaledImage(Bitmap foto) {
		// tamaño actual
		int width = foto.getWidth();
		int height = foto.getHeight();
		// nuevo tamaño
		float scaleWidth = (float) 150 / width;
		float scaleHeight = (float) 170 / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);

		Bitmap fotoescalada = Bitmap.createBitmap(foto, 0, 0, width, height,
				matrix, false);

		return fotoescalada;

	}

}
