package adapters;

import java.util.List;

import objects.ImgArticulos;
import persistence.ImgArticulosModel;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {

	private Context context;
	private List<ImgArticulosModel> imgArticulos;
	
	public ImageAdapter(Context context, int idarticulo){
		this.context = context;
		imgArticulos = new ImgArticulos(context, idarticulo).getImgArticulos();
	}
	
	 @Override
     public Object instantiateItem(ViewGroup container, int position) {
		 
		ImageView imageView = new ImageView(context);
		imageView.setImageBitmap(imgArticulos.get(position).getBitmapImage());
		container.addView(imageView);
		
		return imageView;
         
     }

     @Override
     public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((View)object);
     }

     @Override
     public int getCount() {
         return imgArticulos.size();
     }

     @Override
     public boolean isViewFromObject(View view, Object object) {
         return (view == object);
     }
	
}
	
	