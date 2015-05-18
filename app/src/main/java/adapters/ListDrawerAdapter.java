package adapters;

import java.util.List;
import com.movilstore.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListDrawerAdapter extends BaseAdapter {

	private List<String> listnombres;
	private Context context;
	private List<Integer> iconos;
	
	public ListDrawerAdapter(Context context, List<String> listnombres,
			List<Integer> iconos) {
		this.context = context;
		this.listnombres = listnombres;
		this.iconos = iconos;
	}
	
	@Override
	public int getCount() {
		return listnombres.size();
	}

	@Override
	public Object getItem(int position) {
		return listnombres.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_list_drawer, null);
		} else
			view = convertView;
		
		TextView itemtext = (TextView) view.findViewById(R.id.item_drawer_text);
		ImageView imageview = (ImageView) view.findViewById(R.id.drawer_item_icon);
		
		itemtext.setText(listnombres.get(position));
		imageview.setImageResource(iconos.get(position));
		
		return view;
	}

}
