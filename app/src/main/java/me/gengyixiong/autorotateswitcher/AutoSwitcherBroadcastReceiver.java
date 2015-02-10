package me.gengyixiong.autorotateswitcher;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AutoSwitcherBroadcastReceiver extends BroadcastReceiver {
    public AutoSwitcherBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Context         ct  =   context.getApplicationContext();
        ContentResolver cr  =   context.getContentResolver();
        new AutoSwitcherActivity().autoSwitch(ct,cr);
    }
}
