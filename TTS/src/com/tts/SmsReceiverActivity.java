package com.tts;


import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.telephony.SmsMessage;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.Toast;
public class SmsReceiverActivity extends BroadcastReceiver implements OnInitListener
{
	private TextToSpeech remsg;
	String str =null; 
	int number;
	String phno,msg;
@Override
public void onReceive(Context context, Intent intent) 
{
	
//—get the SMS message passed in—
Bundle bundle = intent.getExtras(); 
SmsMessage[] msgs = null;

if (bundle != null)
{
//—retrieve the SMS message received—
Object[] pdus=(Object[]) bundle.get("pdus");;
msgs = new SmsMessage[pdus.length]; 
for (int i=0; i<msgs.length; i++){
msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]); 
str = str.concat("SMS from");
str=str.concat(msgs[i].getOriginatingAddress()); 
phno=msgs[i].getOriginatingAddress();
str =str.concat(" : ");
str =str.concat( msgs[i].getMessageBody().toString());
msg=msgs[i].getMessageBody().toString();
str=str.concat("/n"); 
remsg.speak(str, TextToSpeech.QUEUE_FLUSH, null);
}
//—display the new SMS message—
Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
remsg.speak(str, TextToSpeech.QUEUE_FLUSH, null);
ContentValues values = new ContentValues();
values.put("address", phno);//sender name
values.put("body", str);

} 

}
@Override
public void onInit(int status) {
	// TODO Auto-generated method stub
	if (status == TextToSpeech.SUCCESS)
    {
    int result = remsg.setLanguage(Locale.UK);
    if (result == TextToSpeech.LANG_MISSING_DATA
    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
    Log.e("TTS",  "not available");
    }
    else
    {
    	remsg.speak(str, TextToSpeech.QUEUE_FLUSH, null);
    	
    }
    }
    else
    {
    Log.e("TTS", "Not intially Initialized");
    }
}
public void onDestroy() {
// Don’t forget to shutdown tts!
if (remsg != null) {
remsg.stop();
remsg.shutdown();
}
}
}
