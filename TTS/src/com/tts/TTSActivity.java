package com.tts;

import java.util.Locale;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
 
public class TTSActivity extends Activity implements OnInitListener 
{
Button spB,seB;
EditText msgET;
EditText pnET; 
TextToSpeech voice;
String phoneNo;
String message;
@Override
public void onCreate(Bundle savedInstanceState) 
{
super.onCreate(savedInstanceState);
setContentView(R.layout.speech);
voice=new TextToSpeech(this,this); 
spB=(Button)findViewById(R.id.spB);
seB = (Button) findViewById(R.id.seB);
pnET = (EditText) findViewById(R.id.phnoET);
msgET = (EditText) findViewById(R.id.textET); 
spB.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String msg=msgET.getText().toString();
		voice.speak(msg, TextToSpeech.QUEUE_FLUSH, null);
	}
});
seB.setOnClickListener(new View.OnClickListener() 
{
public void onClick(View v) 
{ 
	phoneNo=pnET.getText().toString(); 
	message=msgET.getText().toString();
if (phoneNo.length()>0 && message.length()>0) 
sendSMS(phoneNo, message); 
else {
Toast.makeText(getBaseContext(), "Please enter both phone number and message.", Toast.LENGTH_SHORT).show();
voice.speak("SMS Failed, please try again.Please enter phone number and message", TextToSpeech.QUEUE_FLUSH, null);
}
}
}); 
ContentValues values = new ContentValues();
values.put("address", phoneNo);//sender name
values.put("body", message);
getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
}
//—sends a SMS message to another device—
private void sendSMS(String phoneNumber, String message)
{ 
String SENT ="SMS SENT";
String DELIVERED = "SMS DELIVERED";
PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
new Intent(SENT), 0);
PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
new Intent(DELIVERED), 0);
//—when the SMS has been sent—
registerReceiver(new BroadcastReceiver(){
@Override
public void onReceive(Context arg0, Intent arg1) {
	
	
switch (getResultCode())
{
case Activity.RESULT_OK:
Toast.makeText(getBaseContext(),"SMS Sent", Toast.LENGTH_SHORT).show();
voice.speak("SMS sent", TextToSpeech.QUEUE_FLUSH, null);
break;
case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
Toast.makeText(getBaseContext(),"Generic failure", Toast.LENGTH_SHORT).show();
voice.speak("Generic failure.SMS Failed, please try again", TextToSpeech.QUEUE_FLUSH, null);
break;
case SmsManager.RESULT_ERROR_NO_SERVICE:
Toast.makeText(getBaseContext(),"No service", Toast.LENGTH_SHORT).show();
voice.speak("Service not available. SMS Failed, please try again", TextToSpeech.QUEUE_FLUSH, null);
break;
case SmsManager.RESULT_ERROR_NULL_PDU:
Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
voice.speak("SMS Failed, please try again", TextToSpeech.QUEUE_FLUSH, null);
break;
case SmsManager.RESULT_ERROR_RADIO_OFF:
Toast.makeText(getBaseContext(),"Radio Off" , Toast.LENGTH_SHORT).show();
voice.speak("SMS Failed, please try again", TextToSpeech.QUEUE_FLUSH, null);
break;
}
	
}
}, new IntentFilter(SENT));
//—when the SMS has been delivered—
registerReceiver(new BroadcastReceiver(){
@Override
public void onReceive(Context arg0, Intent arg1) {
switch (getResultCode())
{
case Activity.RESULT_OK:
	String str="SMS Failed";
Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
voice.speak("SMS Delivered", TextToSpeech.QUEUE_FLUSH, null);
break;
case Activity.RESULT_CANCELED:
	
Toast.makeText(getBaseContext(),"Sms not delivered", Toast.LENGTH_SHORT).show();
voice.speak("SMS Not delivered", TextToSpeech.QUEUE_FLUSH, null);
break; 
}
}
}, new IntentFilter(DELIVERED)); 
SmsManager sms = SmsManager.getDefault();
sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI); 
}
@Override
public void onInit(int status) {
	// TODO Auto-generated method stub
	if (status == TextToSpeech.SUCCESS)
    {
    int result = voice.setLanguage(Locale.UK);
    if (result == TextToSpeech.LANG_MISSING_DATA
    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
    Log.e("TTS",  "not available");
    }
   
    }
    else
    {
    Log.e("TTS", "Not intially Initialized");
    }
}

@Override
public void onDestroy() {
// Don’t forget to shutdown tts!
if (voice != null) {
voice.stop();
voice.shutdown();
}
super.onDestroy();
}
 
}
