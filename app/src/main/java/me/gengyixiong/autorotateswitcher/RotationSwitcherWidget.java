package me.gengyixiong.autorotateswitcher;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;


/**
 * Implementation of App Widget functionality.
 */
public class RotationSwitcherWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
            /*
             * Press the widget, send an broadcast to the broadcast receiver;
             * call onReceive() of the receiver.
             */
            RemoteViews     views           =   new RemoteViews(context.getPackageName(), R.layout.rotation_switcher);// get RemoteViews from xml file.
            Intent          intent          =   new Intent(context, AutoSwitcherBroadcastReceiver.class); // MyReceiver.class is the receiver.
            PendingIntent   pendingIntent   =   PendingIntent.getBroadcast(context,0,intent,0);
            views.setOnClickPendingIntent(R.id.myImageView, pendingIntent); // similar to setOnClickListener()
            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }


    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
            int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rotation_switcher);
       // views.setTextViewText(R.id.appwidget_text, widgetText);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    /*
     * update the widget UI according to current status.
     * not work yet.
     */
    /*
    public static void widgetUpdateUI(Context ct, ContentResolver cr){
        try {
            int i = Settings.System.getInt(cr, Settings.System.ACCELEROMETER_ROTATION); // get current state of auto-rotation, 1 --> enable, 0 --> disable
            if (i == 1){
                RemoteViews views = new RemoteViews(ct.getPackageName(), R.layout.rotation_switcher);
                views.setImageViewResource(R.id.myImageView, R.drawable.unlock_widget_icon);
                Toast.makeText(ct, "work i = 1", Toast.LENGTH_SHORT).show();

            }else{ // if current state disable, enable, toast to user.
                RemoteViews views = new RemoteViews(ct.getPackageName(), R.layout.rotation_switcher);
                views.setImageViewResource(R.id.myImageView, R.drawable.lock_widget_icon);
                Toast.makeText(ct, "work i = 0", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(ct, "Try again", Toast.LENGTH_SHORT).show();
        }
    }
    */
}


