package com.tts;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

public class SoundActivity extends Activity implements OnInitListener{
	private TextToSpeech speech;
	String text;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		speech = new TextToSpeech(this,this);
		text="Message";
	
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS)
        {
        int result = speech.setLanguage(Locale.UK);
        if (result == TextToSpeech.LANG_MISSING_DATA
        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
        Log.e("TTS",  "not available");
        }
        else
        {
        speakOut();
        Intent i=new Intent(SoundActivity.this,TTSActivity.class);
        startActivity(i);
        }
        }
        else
        {
        Log.e("TTS", "Not intially Initialized");
        }
	}
	private void speakOut()
    { 
    speech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
}
	@Override
    public void onDestroy() {
    // Don’t forget to shutdown tts!
    if (speech != null) {
    speech.stop();
    speech.shutdown();
    }
    super.onDestroy();
    }
}
