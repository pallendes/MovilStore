package adapters;

import java.util.ArrayList;

import com.movilstore.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubastasListAdapter extends BaseAdapter {

	private ArrayList<String> listproductos;
	private Context context;
	
	public SubastasListAdapter(Context context, ArrayList<String> productos){
		this.context = context;
		this.listproductos = productos;
	}
	
	public int getCount() {
		return this.listproductos.size();
	}

	public Object getItem(int position) {
		return this.listproductos.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_listsubastas, null);
		}else
			view = convertView;
		
		TextView txt = (TextView) view.findViewById(R.id.lvsubnomproducto);
		txt.setText(this.listproductos.get(position));
		
		return view;

	}

}
