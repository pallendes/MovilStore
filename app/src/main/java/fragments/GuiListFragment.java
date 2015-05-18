package fragments;

import java.util.List;

import objects.Categoria;
import persistence.CategoriasModel;
import adapters.CategoriasAdapter;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;

public class GuiListFragment extends ListFragment implements LoaderCallbacks<List<CategoriasModel>>{
	
	@Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);{
        	
    		this.setListAdapter(new CategoriasAdapter(getActivity().getApplicationContext(),
    				new Categoria(getActivity()).getCategorias()));
    		
    		LoaderManager lm = getLoaderManager();
    		lm.initLoader(0, null, this);
    		
        }
	}

	public Loader<List<CategoriasModel>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return null;
	}

	public void onLoadFinished(Loader<List<CategoriasModel>> arg0,
			List<CategoriasModel> data) {
		// TODO Auto-generated method stub
		
	}

	public void onLoaderReset(Loader<List<CategoriasModel>> arg0) {
		// TODO Auto-generated method stub
		
	}


}
