package open.protto.pecs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import android.R;
import android.content.Context;
import android.speech.tts.TextToSpeech;

import open.protto.pecs.data.OnePicture;

public class Lib_common {
	
	public static ArrayList<OnePicture> getListPicture(int start, int end, Context context){
		
		if(end > start){
			
		ArrayList<OnePicture> al = new ArrayList<OnePicture>();
		
			OnePicture op = new OnePicture(open.protto.pecs.R.drawable.maison, "maison");
			al.add(op);
			
			OnePicture op2 = new OnePicture(open.protto.pecs.R.drawable.dans, "dans");
			al.add(op2);
			
			OnePicture op3 = new OnePicture(open.protto.pecs.R.drawable.dort, "dort");
			al.add(op3);
			
			OnePicture op4 = new OnePicture(open.protto.pecs.R.drawable.garcon, "garcon");
			al.add(op4);
			
			OnePicture op5 = new OnePicture(open.protto.pecs.R.drawable.le, "le");
			al.add(op5);
		
		return al;
		
		}
		
		return null;
		
	}
	
	public static void speakWords(ArrayList<String> al, TextToSpeech mTts){
		
		HashMap<String,String> hm = new HashMap<String,String>();
		hm.put("maison", "maison");
		hm.put("le", "le");
		hm.put("garcon", "garçon");
		hm.put("dort", "dort");
		hm.put("dans", "dans");

		String textToSpeach = "";
		for(int i = 0; i < al.size(); i ++){
			textToSpeach = textToSpeach + al.get(i);
		}
		
		mTts.speak(textToSpeach, TextToSpeech.QUEUE_FLUSH, null);
		
	}

}
