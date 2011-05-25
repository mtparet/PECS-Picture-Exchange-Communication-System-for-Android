package open.protto.abba.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import open.protto.abba.R;
import open.protto.abba.data.OnePicture;
import open.protto.abba.lib.Lib_common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class PictureAdapter extends BaseAdapter {

	private Context context;
	private int nb;
	private ArrayList<OnePicture> alPicture;
	
	public PictureAdapter(Context context, int nb){
		this.context = context;
		this.nb = nb;
		this.alPicture = Lib_common.getListPicture(0, nb, context);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alPicture.size();
	}

	@Override
	public OnePicture getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.alPicture.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {

		arg1 = (RelativeLayout)RelativeLayout.inflate(context, open.protto.abba.R.layout.picture_list, null);
		ImageView iv = (ImageView)arg1.findViewById(R.id.imageView1);
		iv.setImageDrawable(context.getResources().getDrawable(this.alPicture.get(arg0).getId()));
		iv.setContentDescription(this.alPicture.get(arg0).getName());
		
		return arg1;
	}

}
