package open.protto.abba.lib;

import java.util.ArrayList;

import android.R;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import open.protto.abba.data.OnePicture;

public class Lib_common {
	
	public static ArrayList<OnePicture> getListPicture(int start, int end, Context context){
		
		if(end > start){
			
		ArrayList<OnePicture> al = new ArrayList<OnePicture>();
		
			OnePicture op = new OnePicture(open.protto.abba.R.drawable.maison);
			al.add(op);
			
			OnePicture op2 = new OnePicture(open.protto.abba.R.drawable.maison2);
			al.add(op2);
			
			OnePicture op3 = new OnePicture(open.protto.abba.R.drawable.maison_a_vendre);
			al.add(op3);
			
			OnePicture op4 = new OnePicture(open.protto.abba.R.drawable.maison_cote);
			al.add(op4);
		
		return al;
		
		}
		
		return null;
		
	}
	
	public static void speakWords(ArrayList<String> al, TextToSpeech mTts){
		
		String textToSpeach = "";
		for(int i = 0; i < al.size(); i ++){
			textToSpeach = textToSpeach + al.get(i);
		}
		
		mTts.speak(textToSpeach, TextToSpeech.QUEUE_FLUSH, null);
		
	}

}
