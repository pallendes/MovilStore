package adapters;

import java.util.List;

import com.movilstore.R;

import persistence.CategoriasModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoriasAdapter extends BaseAdapter {

	private List<CategoriasModel> categorias;
	private Context context;

	public CategoriasAdapter(Context context, List<CategoriasModel> categorias){
		
		this.context = context;
		this.categorias = categorias;
		
	}

	public int getCount() {
		return categorias.size();
	}

	public Object getItem(int position) {
		return categorias.get(position);
	}

	public long getItemId(int position) {
		return categorias.get(position).getIdcategoria();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_categorias, null);
		}else
			view = convertView;
		
		TextView txtcatnombre = (TextView) view.findViewById(R.id.txtcatnombre);
		txtcatnombre.setText(this.categorias.get(position).getNombre());
		
		return view;
	}

}
