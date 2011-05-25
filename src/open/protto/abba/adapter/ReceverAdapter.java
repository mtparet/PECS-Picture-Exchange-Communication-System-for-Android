package open.protto.abba.adapter;

import java.util.ArrayList;

import open.protto.abba.R;
import open.protto.abba.data.OnePicture;
import open.protto.abba.lib.Lib_common;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ReceverAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<OnePicture> alPicture;
	private int nb = 1;
	
	public ReceverAdapter(Context context) {
		this.context = context;
		this.alPicture = new ArrayList<OnePicture>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nb;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
			int rest = position % 2;
			if(rest == 0 ){
				convertView = (RelativeLayout)RelativeLayout.inflate(context, open.protto.abba.R.layout.picture_list, null);
				ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
				iv.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
				iv.setImageDrawable(context.getResources().getDrawable(android.R.drawable.btn_plus));
			}else{
			position = position / 2;
			
			convertView = (RelativeLayout)RelativeLayout.inflate(context, open.protto.abba.R.layout.picture_list, null);
			ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
			iv.setImageDrawable(context.getResources().getDrawable(this.alPicture.get(position).getId()));
			
			}
		
			return convertView;
	}
	
	public void addPicture(OnePicture op, int position){
		if(position < alPicture.size()){
			alPicture.add(position, op);
		}else{
			alPicture.add(op);
		}
		
		nb = alPicture.size() * 2 + 1;
		notifyDataSetChanged();
	}
	
	public void clear(){
		this.alPicture.clear();
		this.notifyDataSetChanged();
		nb = 1;
	}
	
	public ArrayList<OnePicture> getList(){
		return this.alPicture;
	}

}
