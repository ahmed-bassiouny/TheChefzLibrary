package com.thechefz.chefzlibrary

import android.app.*
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.thechefz.chefzlibrary.extensions.toBitmap

class NotificationManager(private val application: Application) {

    //region var
    private var channelId: String = "${application.packageName}_base"
    private var channelName: String = application.packageName
    //endregion

    private fun createManager(
        channelId: String = this.channelId,
        channelName: String = this.channelName,
        sound: Uri? = null
    ): NotificationManager =
        (application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).also {
            this.channelId = channelId
            this.channelName = channelName
            createNotificationChannel(it, channelId, channelName, sound)
        }


    private fun createNotificationChannel(
        mNotificationManager: NotificationManager,
        channelId: String,
        channelName: String,
        sound: Uri?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).also { notificationChannel ->

                sound?.let {
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_ALARM)
                        .build().also { audioAttributes ->
                            notificationChannel.setSound(it, audioAttributes)
                        }
                }

                mNotificationManager.createNotificationChannel(notificationChannel)
            }


        }
    }

    fun createBuilder(
        title: String,
        content: String,
        @DrawableRes icon: Int ,
        pendingIntent: PendingIntent? = null,
        sound: Uri? = null
    ): NotificationCompat.Builder? =
            NotificationCompat.Builder(
                application,
                channelId
            )
                .setSmallIcon(icon)
                .setLargeIcon(application.toBitmap(icon))
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setSound(sound)
                .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)



    fun showNotification(
        builder: NotificationCompat.Builder?,
        notificationId: Int = System.currentTimeMillis().toInt(),
        channelId: String = this.channelId,
        channelName: String = this.channelName,
        sound: Uri? = null
    ) {
        builder?.build()?.let {
            createManager(channelId, channelName, sound).notify(notificationId, it)
        }
    }
}