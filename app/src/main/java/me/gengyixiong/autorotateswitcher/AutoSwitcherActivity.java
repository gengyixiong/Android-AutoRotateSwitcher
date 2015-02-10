package me.gengyixiong.autorotateswitcher;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class AutoSwitcherActivity extends Activity {

    Button button;  //create button
    //Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        button = (Button) findViewById(R.id.mySwitch);  //find button from id
        button.setOnClickListener(new myListener());    //set listener to button
    }


    class myListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
           Context          ct  =   getApplicationContext();    // get two paras needed, pass them to autoSwitch method.
           ContentResolver  cr  =   getContentResolver();
           autoSwitch(ct,cr);
        }
    }
        /*
         *core-method, control auto-orientation on and off
         *Two paras are needed, Context and ContentResolver,
         *need to get Context by getApplicationContext(),
         *get ContentResolver by getContentResolver(),
         *before using this method.
         */

        public void autoSwitch(Context ct, ContentResolver cr){
            try {
                int i = Settings.System.getInt(cr, Settings.System.ACCELEROMETER_ROTATION); // get current state of auto-rotation, 1 --> enable, 0 --> disable
                if (i == 1){
                    i = 0; // if current state enable, disable, toast to user.
                    Toast.makeText(ct, "Auto-Rotate Off", Toast.LENGTH_SHORT).show();
                }else{ // if current state disable, enable, toast to user.
                    i = 1;
                    Toast.makeText(ct, "Auto-Rotate On", Toast.LENGTH_SHORT).show();
                }
                Settings.System.putInt(cr,Settings.System.ACCELEROMETER_ROTATION,i); // put i to auto-rotation, 1 --> enable, 0 --> disable
            }catch (Exception e){
                Toast.makeText(ct, "Try again", Toast.LENGTH_SHORT).show();
            }
        }
}

