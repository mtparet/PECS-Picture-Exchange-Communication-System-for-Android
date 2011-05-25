package open.protto.abba;

import java.util.ArrayList;
import java.util.Locale;

import open.protto.abba.adapter.PictureAdapter;
import open.protto.abba.adapter.ReceverAdapter;
import open.protto.abba.data.OnePicture;
import open.protto.abba.lib.HorizontialListView;
import open.protto.abba.lib.Lib_common;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainDisplay extends Activity implements OnInitListener{
	
	private HorizontialListView lvPicture;
	private HorizontialListView lvRecever;
	private PictureAdapter pictureAdapter;
	private ReceverAdapter receverAdapter;
	private OnePicture onePictureSelected;
    private TextToSpeech mTts;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_display);
        
        lvPicture = (HorizontialListView)findViewById(R.id.listView1);
        lvRecever = (HorizontialListView)findViewById(R.id.listView2);
        
        pictureAdapter = new PictureAdapter(this, 50);
        receverAdapter = new ReceverAdapter(this);
        
        lvPicture.setAdapter(pictureAdapter);
        lvRecever.setAdapter(receverAdapter);
        
        lvPicture.setOnItemClickListener(listenerOnPicture);
        lvRecever.setOnItemClickListener(listenerAddPicture);
        
        mTts = new TextToSpeech(this, this);
        
        
        
    }
    
	private OnItemClickListener listenerOnPicture = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			onePictureSelected = (OnePicture) arg0.getItemAtPosition(arg2);
			
		}

	};
	
	private OnItemClickListener listenerAddPicture = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if(onePictureSelected != null)
			receverAdapter.addPicture(onePictureSelected, arg2);
			
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

 if (result == TextToSpeech.LANG_MISSING_DATA ||
     result == TextToSpeech.LANG_NOT_SUPPORTED) {
    // Lanuage data is missing or the language is not supported.
     Log.e("test", "Language is not available.");
 } else {

 }

		
	}

}
