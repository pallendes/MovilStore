package fragments;

import com.movilstore.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GuiInicio extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState){
		
		return inflater.inflate(R.layout.gui_inicio, container, false);
		
	}
	
}
