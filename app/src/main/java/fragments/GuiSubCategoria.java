package fragments;

import java.util.List;

import objects.SubCategoria;
import persistence.SubCategoriasModel;
import adapters.SubCategoriaAdapter;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.movilstore.GuiArticulos;

public class GuiSubCategoria extends ListFragment {
	
	private List<SubCategoriasModel> listsubcat;
	
	@Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);{
       
        	Bundle args = getArguments();
        	SubCategoria scat = new SubCategoria(getActivity());
        	listsubcat = scat.getSubCategorias(args.getInt("idcategoria"));
        	
    		SubCategoriaAdapter adapter = new SubCategoriaAdapter(getActivity()
    				.getApplicationContext(), listsubcat);
    		
    		setListAdapter(adapter);
    		
    		getListView().setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Intent i = new Intent(getActivity(), GuiArticulos.class);
					i.putExtra("sbname", listsubcat.get(arg2).getNombre());
					startActivity(i);
				}
			});
        }
	}
	
}
