package open.protto.pecs.adapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

import open.protto.pecs.R;
import open.protto.pecs.data.OnePicture;
import open.protto.pecs.lib.Lib_common;
import open.protto.pecs.view.BallView;

import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
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

		arg1 = (RelativeLayout)RelativeLayout.inflate(context, open.protto.pecs.R.layout.picture_recever, null);
		ImageView iv = (ImageView) arg1.findViewById(R.id.imageView1);
		iv.setImageDrawable(context.getResources().getDrawable(this.alPicture.get(arg0).getId()));
		iv.setContentDescription(this.alPicture.get(arg0).getName());
		
		return arg1;
	}
	

}
