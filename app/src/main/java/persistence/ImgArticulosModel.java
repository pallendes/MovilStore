package persistence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;

public class ImgArticulosModel {

	private int idarticulo, idimagen;
	private String imagen;

	public ImgArticulosModel(int idimagen, int idarticulo, String imagen) {
		this.idimagen = idimagen;
		this.idarticulo = idarticulo;
		this.imagen = imagen;
	}

	public int getIdarticulo() {
		return idarticulo;
	}

	public int idimagen() {
		return idimagen;
	}

	public String getImagen() {
		return imagen;
	}

	//get bipmaps----------------------------------
	public Bitmap getBitmapImage() {
		
		Bitmap image = null;
		
		try{
			image =  stringToBitmap(imagen);
		}catch(NullPointerException e){
			Log.e("IMGART", e.toString());
		}catch(Exception e){
			Log.e("IMGART", e.toString());
		}finally{
//			image = BitmapFactory.decodeResource(context.getResources(), 
//					R.drawable.movilsotoreicon);
		}
		
		return image;
	}
	
	// Convertir y escalar la imagen
	private Bitmap stringToBitmap(String datos) {

		Bitmap foto = null;
		
		try {
			byte[] byteData = Base64.decode(datos, Base64.DEFAULT);
			foto = BitmapFactory.decodeByteArray(byteData, 0, byteData.length);
			Log.e("ImgArticulos", "Imagen convertida");
		} catch (Exception e) {
			Log.e("ImgArticulos", e.toString());
		}

		return getScaledImage(foto);

	}

	private Bitmap getScaledImage(Bitmap foto) {
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
