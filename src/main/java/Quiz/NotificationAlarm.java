package Quiz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

import com.jfinkelstudios.mobile.algebraquizapp.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationAlarm extends BroadcastReceiver {

    //*****> CLASS INSTANCE VARIABLES
    private static final String PRIMARY_CHANNEL_ID = "PRIME_CHANNEL";
    private static final String NOTIFICATION_NAME = "QUIZ_ALERT";
    private static final String QUIZ_DESCRIPTION = "STUDY REMINDER";
    private static final String NOTIFICATION_TITLE = "STUDY REMINDER";
    private static final String NOTIFICATION_TEXT = "Knowledge Is Power Remember";
    private static final int NOTIFICATION_ID = 0;
    private Context context;
    //*
    private NotificationManager notificationManager;

    /***NOTIFICATION BUILDER CONFIGURATION***/
    private NotificationCompat.Builder getNotificationBuilder() {
        return (new NotificationCompat.Builder(this.context, PRIMARY_CHANNEL_ID)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(NOTIFICATION_TEXT)
                .setSmallIcon(R.drawable.ic_spa24dp)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
        );
    }


    // ON_RECEIVE
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        /***NOTIFICATION CONFIGURATION***/
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH);
            // CONFIG NOTIFICATION
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setDescription(QUIZ_DESCRIPTION);
            notificationChannel.enableVibration(true);
            this.notificationManager.createNotificationChannel(notificationChannel);

        }
        // * Sends The Notification *
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        this.notificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }


}// END OF CLASS
