package com.sonnytron.sortatech.pantryprep.Helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.sonnytron.sortatech.pantryprep.R;

/**
 * Created by Steve on 8/29/2016.
 */
public class PushNotificationHelper {

    public void popNotification(Context context, String food)
    {
        String title="Your " + food + " is going to expire!";
        String subject="Don't forget to use your produce!";
        String body="test body";


        NotificationManager nm =(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Notification notify= new Notification(R.drawable.noti,tittle,System.currentTimeMillis());
        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(subject)
                .setSmallIcon(R.drawable.ic_push_notification)
                .setWhen(System.currentTimeMillis())
                //setContentIntent will give you the intent to be sent.
                .build();
        //1 is test id.
        nm.notify(1,notification);
        //PendingIntent pending= PendingIntent.getActivity(context, 0, new Intent(), 0);
    }

}


