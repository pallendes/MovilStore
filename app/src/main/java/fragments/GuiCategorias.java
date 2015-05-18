package fragments;

import com.movilstore.R;

import objects.Categoria;
import adapters.CategoriasAdapter;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GuiCategorias extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState){
		
		return inflater.inflate(R.layout.gui_categorias, container, false);
		
	}
	
	@Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);{
        	
        	ListView lv = (ListView)getActivity().findViewById(R.id.lvcategorias);
    		CategoriasAdapter adapter = new CategoriasAdapter(getActivity().getApplicationContext(),
    				new Categoria(getActivity()).getCategorias());
    		lv.setAdapter(adapter);
    		
    		lv.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> adapter, View view,
						int position, long id) {
					
					GuiSubCategoria guisubcat = new GuiSubCategoria();
					Bundle argumentos = new Bundle();
					argumentos.putInt("idcategoria", position);
					guisubcat.setArguments(argumentos);
					
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					
					transaction.replace(R.id.container, guisubcat);
					transaction.addToBackStack(null);
					
					transaction.commit();
					
					
				}
			});
    		
        }
	}
	
}
