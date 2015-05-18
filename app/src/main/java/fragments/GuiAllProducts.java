package fragments;

import java.util.List;

import objects.Articulo;
import persistence.ArticuloModel;
import adapters.AllProductsListAdapter;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.movilstore.GuiFichaProducto;
import com.movilstore.R;

public class GuiAllProducts extends Fragment{
	
	private Articulo item;
	ProgressDialog pdialog;
	@Override
	public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState){
	
		return inflater.inflate(R.layout.list_all_products, container, false);
		
	}
	
	@Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);{
        	
        	ListView lv = (ListView) getActivity().findViewById(R.id.list_allproducts);
        	item = new Articulo(getActivity());
        	List<ArticuloModel> listitems = item.getArticulos();
        	
        	AllProductsListAdapter listadapter = 
        			new AllProductsListAdapter(getActivity(), listitems);
        	lv.setAdapter(listadapter);
        	
        	lv.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> adapter, View view,
						int postition, long id) {
					
					Intent intent = 
							new Intent(getActivity().getApplicationContext(), GuiFichaProducto.class);
					
					ArticuloModel articulo = item.getArticulo((int) id);
					
					intent.putExtra("idarticulo", articulo.getIdarticulo());
					intent.putExtra("nombre", articulo.getNombre());
					intent.putExtra("nomvendedor", articulo.getNickname());
					intent.putExtra("reputacion", articulo.getReputacion());
					intent.putExtra("precio", articulo.getPrecio());
					intent.putExtra("descripcion", articulo.getDescripcion());
					intent.putExtra("categoria", articulo.getSubcategoria());
					intent.putExtra("tipo", articulo.getTipoproducto());
					intent.putExtra("imgarticulo", articulo.getImagenMuestra());
					
					startActivity(intent);
		
				}
			});
        	
        }
	}
	
}
