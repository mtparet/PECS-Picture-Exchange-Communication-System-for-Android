/*
*Copyright 2011 Matthieu Paret
*
*This file is part of PECS.
*
*PECS is free software: you can redistribute it and/or modify
*it under the terms of the GNU Lesser General Public License as published by
*the Free Software Foundation, either version 3 of the License, or
*(at your option) any later version.
*
*PECS is distributed in the hope that it will be useful,
*but WITHOUT ANY WARRANTY; without even the implied warranty of
*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*GNU General Public License for more details.
*
*You should have received a copy of the GNU Lesser General Public License
*along with PECS.  If not, see <http://www.gnu.org/licenses/>.
*/

package open.protto.pecs.adapter;

import java.util.ArrayList;

import open.protto.pecs.R;
import open.protto.pecs.data.OnePicture;
import open.protto.pecs.lib.Lib_common;
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
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
			int rest = position % 2;
			if(rest == 0 ){
				convertView = (RelativeLayout)RelativeLayout.inflate(context, open.protto.pecs.R.layout.picture_recever, null);
				ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
				RelativeLayout.LayoutParams rll = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
				iv.setLayoutParams(rll);
				iv.setImageDrawable(context.getResources().getDrawable(android.R.drawable.ic_menu_add));
			}else{
			position = position / 2;
			
			convertView = (RelativeLayout)RelativeLayout.inflate(context, open.protto.pecs.R.layout.picture_list, null);
			ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
			iv.setImageDrawable(context.getResources().getDrawable(this.alPicture.get(position).getId()));
			
			}
		
			return convertView;
	}
	
	public void addPicture(OnePicture op, int position){
		if(position < nb){
			position = (position)/2;
			alPicture.add(position, op);
		}else{
			alPicture.add(op);
		}
		
		nb = alPicture.size() * 2 + 1;
		notifyDataSetChanged();
	}
	
	public void removeItem(int arg1){
		int rest = arg1 % 2;
		if(rest != 0){
			alPicture.remove((arg1-1)/2);
			nb = alPicture.size() * 2 + 1;
			notifyDataSetChanged();
		}
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
