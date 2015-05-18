package adapters;

import java.util.List;

import com.movilstore.R;

import persistence.ArticuloModel;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class AllProductsListAdapter extends BaseAdapter {

	private List<ArticuloModel> listproductos;
	private Context context;

	public AllProductsListAdapter(Context context, List<ArticuloModel> productos) {

		this.context = context;
		this.listproductos = productos;

	}

	public int getCount() {
		return this.listproductos.size();
	}

	public Object getItem(int arg0) {
		return this.listproductos.get(arg0);
	}

	public long getItemId(int position) {
		return this.listproductos.get(position).getIdarticulo();
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_listallproducts, null);
		} else
			view = convertView;

		TextView txtnomprod = (TextView) view.findViewById(R.id.lvnomproducto);
		TextView lvnomvend = (TextView) view.findViewById(R.id.lvnomvendedor);
		TextView lvprecio = (TextView) view.findViewById(R.id.lvprecio);
		RatingBar rbar = (RatingBar) view.findViewById(R.id.usd_rbar);
		ImageView imageview = (ImageView) view.findViewById(R.id.preg_vend_img);

		txtnomprod.setText(this.listproductos.get(position).getNombre());
		lvnomvend.setText(this.listproductos.get(position).getNickname());
		lvprecio.setText(String.valueOf(this.listproductos.get(position)
				.getPrecio()));
		rbar.setRating(this.listproductos.get(position).getReputacion());
		
		try{
			imageview.setImageBitmap(stringToBitmap
					(this.listproductos.get(position).getImagenMuestra()));
		}catch(NullPointerException e){
			Log.e("APLA", e.toString());
		}
		
		return view;

	}

	// Convertir y escalar la imagen
	private Bitmap stringToBitmap(String datos) {

		Bitmap foto = null;

		try {
			byte[] byteData = Base64.decode(datos, Base64.DEFAULT);
			foto = BitmapFactory.decodeByteArray(byteData, 0, byteData.length);
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
		float scaleWidth = (float) 100 / width;
		float scaleHeight = (float) 100 / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		
		Bitmap fotoescalada = Bitmap.createBitmap(foto, 0, 0, width, height,
					matrix, false);
		
		return fotoescalada;

	}

}
