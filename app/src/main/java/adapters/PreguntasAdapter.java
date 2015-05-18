package adapters;

import java.util.List;

import com.movilstore.R;

import persistence.PreguntasModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PreguntasAdapter extends BaseAdapter {
	
	private List<PreguntasModel> listpreguntas;
	private Context context;
	
	public PreguntasAdapter(List<PreguntasModel> listpreguntas, Context context) {
		this.listpreguntas = listpreguntas;
		this.context = context;
	}

	@Override
	public int getCount() {
		return listpreguntas.size();
	}

	@Override
	public Object getItem(int arg0) {
		return listpreguntas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return Integer.parseInt(listpreguntas.get(arg0).getIdpregunta());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.item_pregunta_respuesta, null);
		} else
			view = convertView;
		
		TextView txtcontpreg = (TextView) view.findViewById(R.id.preg_txtpregunta);
		TextView txtfechapreg = (TextView) view.findViewById(R.id.preg_fecha);
		TextView txtresp = (TextView) view.findViewById(R.id.preg_txtresp);
		TextView txtfecharesp = (TextView) view.findViewById(R.id.preg_txtfecharesp);
		TextView txtautor = (TextView) view.findViewById(R.id.preg_nomautor);
		
		txtcontpreg.setText(listpreguntas.get(position).getPregunta());
		txtfechapreg.setText(listpreguntas.get(position).getFechaPregunta());
		txtresp.setText(listpreguntas.get(position).getRespuesta());
		txtautor.setText(listpreguntas.get(position).getNickName());
		
		if(listpreguntas.get(position).getFechaRespuesta() == "null")
			txtfecharesp.setText("");
		else
			txtfecharesp.setText(listpreguntas.get(position).getFechaRespuesta());
		
		return view;
	}

}
