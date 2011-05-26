package open.protto.pecs;

import java.util.ArrayList;
import java.util.Locale;

import open.protto.pecs.adapter.PictureAdapter;
import open.protto.pecs.adapter.ReceverAdapter;
import open.protto.pecs.data.OnePicture;
import open.protto.pecs.lib.Lib_common;
import open.protto.pecs.view.list.HorizontalListView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;

public class MainDisplay extends Activity implements OnInitListener{

	private HorizontalListView lvPicture;
	private HorizontalListView lvRecever;
	private PictureAdapter pictureAdapter;
	private ReceverAdapter receverAdapter;
	private OnePicture onePictureSelected;
	private FrameLayout frameLayout;
	private ImageView imageView1;
	private Context context;
	private TextToSpeech mTts;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_display);
		this.context = this;

		lvPicture = (HorizontalListView)findViewById(R.id.listView1);
		lvRecever = (HorizontalListView)findViewById(R.id.listView2);
		frameLayout =(FrameLayout)findViewById(R.id.frameLayout1);
		imageView1 = (ImageView)findViewById(R.id.imageView1);
		
		frameLayout.setOnTouchListener(listenerMoveOnTouch);

		pictureAdapter = new PictureAdapter(this, 50);
		receverAdapter = new ReceverAdapter(this);

		lvPicture.setAdapter(pictureAdapter);
		lvPicture.setOnMove(true);
		
		lvRecever.setAdapter(receverAdapter);

		lvPicture.setOnItemClickListener(listenerOnPicture);
		lvPicture.setOnItemMoveListener(listenerMoveOnTouch);
		lvPicture.setOnItemOutListener(listenerOut);
		
		lvRecever.setReceive(true);
		lvRecever.setOnItemClickListener(listenerAddPicture);
		lvRecever.setOnItemLongClickListener(listenerRemovePicture);

		mTts = new TextToSpeech(this, this);



	}

	private OnItemClickListener listenerOnPicture = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			onePictureSelected = (OnePicture) arg0.getItemAtPosition(arg2);		

		}

	};

	private OnItemClickListener listenerMovePicture = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			onePictureSelected = (OnePicture) arg0.getItemAtPosition(arg2);
			imageView1.setImageDrawable(context.getResources().getDrawable(onePictureSelected.getId()));
			frameLayout.setVisibility(FrameLayout.VISIBLE);

		}

	};

	private OnItemClickListener listenerAddPicture = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if(onePictureSelected != null)
				receverAdapter.addPicture(onePictureSelected, arg2);

		}

	};

	private OnItemLongClickListener listenerRemovePicture = new OnItemLongClickListener() {


		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			receverAdapter.removeItem(arg2);
			return false;
		}

	};

	public void myClickHandler(View target) {

		switch (target.getId()) {
		case R.id.button1: speech();
		break;
		case R.id.button2: clearRecever();
		break;
		}
	}

	private void speech(){
		ArrayList<String> alS = new ArrayList<String>();

		ArrayList<OnePicture> alOP = receverAdapter.getList();
		for(int i = 0; i < alOP.size(); i ++){
			alS.add(alOP.get(i).getName());
		}
		Lib_common.speakWords(alS, mTts);
	}

	private void clearRecever(){
		this.receverAdapter.clear();
	}

	@Override
	public void onInit(int arg0) {
		int result = mTts.setLanguage(Locale.FRANCE);
		mTts.setSpeechRate((float) 0.7);
		if (result == TextToSpeech.LANG_MISSING_DATA ||
				result == TextToSpeech.LANG_NOT_SUPPORTED) {
			// Lanuage data is missing or the language is not supported.
			Log.e("test", "Language is not available.");
		} else {

		}
	}


	View.OnTouchListener listenerMoveOnTouch = new  View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			RelativeLayout.LayoutParams layout = (RelativeLayout.LayoutParams) frameLayout.getLayoutParams();

			ImageView iv = (ImageView) v.findViewById(R.id.imageView1);
			imageView1.setImageDrawable(iv.getDrawable());
			
			frameLayout.setVisibility(View.VISIBLE);
			
			if (event.getAction()==MotionEvent.ACTION_DOWN) {
				frameLayout.bringToFront();
				return true;
			}
			
			if (event.getAction()==MotionEvent.ACTION_MOVE) {
				layout.leftMargin = (int) event.getX() - frameLayout.getWidth()/2;    		
				layout.topMargin = (int) event.getY() - frameLayout.getHeight()/2;
			}
			
			if (event.getAction()==MotionEvent.ACTION_UP) {
				frameLayout.setVisibility(View.GONE);
			}
			
			frameLayout.setLayoutParams(layout);
			return true;
		}

	};
	
	View.OnTouchListener listenerOut = new  View.OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
		return lvRecever.onTouchEvent(event);
		}
	};


	
}
