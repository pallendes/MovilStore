package adapters;

import java.util.List;

import com.movilstore.R;

import persistence.SubCategoriasModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubCategoriaAdapter extends BaseAdapter {

	private List<SubCategoriasModel> subcategorias;
	private Context context;
	
	public SubCategoriaAdapter(Context context, List<SubCategoriasModel> 
		subcategorias) {
		
		this.subcategorias = subcategorias;
		this.context = context;
		
	}

	public int getCount() {
		return this.subcategorias.size();
	}

	public Object getItem(int position) {
		return this.subcategorias.get(position);
	}

	public long getItemId(int position) {
		return this.subcategorias.get(position).getIdsubcat();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_sub_categorias, null);
		}else
			view = convertView;
		
		TextView txtsubcat = (TextView) view.findViewById(R.id.txtsubcat);
		txtsubcat.setText(this.subcategorias.get(position).getNombre());
		
		return view;
	}

}
