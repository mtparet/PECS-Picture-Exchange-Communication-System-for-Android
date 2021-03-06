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

package open.protto.pecs.view;

import open.protto.pecs.R;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class BallView extends ImageView implements OnTouchListener {
	
	private static final String tag = "poc";

	public BallView(Context context) {
		super(context);
		// ATTENTION ne pas oublier le Gravity !! 
		this.setLayoutParams( new FrameLayout.LayoutParams(150, 150, Gravity.TOP));
		setOnTouchListener(this);
	}

	public BallView(Context context, int X, int Y) {
		this(context);
    	FrameLayout.LayoutParams layout = (LayoutParams) this.getLayoutParams();
    	layout.leftMargin = X - layout.width/2;
    	layout.topMargin = Y - layout.height/2;
    	Log.d(tag, "adding ball at : " + layout.leftMargin + ", " + layout.topMargin );
    	this.setLayoutParams(layout);
	}

	public BallView(Context context, int X, int Y, float radius) {
		this(context, X,Y);
    	FrameLayout.LayoutParams layout = (LayoutParams) this.getLayoutParams();
    	layout.width = (int) (layout.width * radius); 
    	layout.height = (int) (layout.height * radius); 
    	this.setLayoutParams(layout);
	}
	
	
	private int localX=0;
	private int localY=0;
	public boolean onTouch(View v, MotionEvent event) {
		FrameLayout.LayoutParams layout = (LayoutParams) v.getLayoutParams();
    	if (event.getAction()==MotionEvent.ACTION_DOWN) {
    		localX = (int)event.getX();
    		localY = (int)event.getY();
    		v.bringToFront();
    		return true;
    	}
    	if (event.getAction()==MotionEvent.ACTION_MOVE) {
			layout.leftMargin = (int) event.getRawX() - localX;    		
			layout.topMargin = (int) event.getRawY() - v.getHeight()/2 - localY;
    	}
    	if (event.getAction()==MotionEvent.ACTION_UP) {
    	}
		v.setLayoutParams(layout);
		return true;
	}

	
	
  }