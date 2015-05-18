package persistence;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;

public class UsuarioModel {

	private String idemail;
	private String rut;
	private String nombre;
	private String apellido;
	private String nickname;
	private float reputacion;
	private String telefono;
	private String foto;

	public UsuarioModel(String idemail, String rut, String nombre,
			String apellido, String nickname, float reputacion,
			String telefono, String foto) {

		this.idemail = idemail;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nickname = nickname;
		this.reputacion = reputacion;
		this.telefono = telefono;
		this.foto = foto;
	}

	public String getIdemail() {
		return idemail;
	}

	public String getRut() {
		return rut;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNickname() {
		return nickname;
	}

	public float getReputacion() {
		return reputacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getFoto() {
		return foto;
	}

	public Bitmap getFotoBitmap() {
		return stringToBitmap(foto);
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
