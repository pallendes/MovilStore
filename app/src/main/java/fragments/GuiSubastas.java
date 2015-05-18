package fragments;

import java.util.ArrayList;

import com.movilstore.R;

import adapters.SubastasListAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class GuiSubastas extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState){
		
		return inflater.inflate(R.layout.gui_subastas, container, false);
		
	}
	
	@Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);{
        	
        	ListView lv = (ListView) getActivity().findViewById(R.id.lvsubastas);
        	SubastasListAdapter listadapter = 
        			new SubastasListAdapter(getActivity().getApplicationContext(), getList());
        	lv.setAdapter(listadapter);
    		
        }
	}
	
	public ArrayList<String> getList(){
		
		ArrayList<String> lista = new ArrayList<String>();
		
		lista.add("Objeto1");
		lista.add("Objeto2");
		lista.add("Objeto3");
		
		return lista;
		
	}
	
}
