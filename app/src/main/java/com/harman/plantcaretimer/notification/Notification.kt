package com.harman.plantcaretimer.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.harman.plantcaretimer.R
import java.util.*


class TriggerNotification(context: Context, title: String, body: String) {

    init {
        sendNotification(context, title, body)
    }

    private fun createNotificationChannel(context: Context, name: String, description: String): String {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val att = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()

        val chanelId = UUID.randomUUID().toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(chanelId, name, importance)
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.lightColor = Color.GREEN;
            channel.vibrationPattern = longArrayOf(0L, 500L, 1000L)
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), att)
            channel.description = description
            channel.lightColor = Color.BLUE
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        return chanelId
    }

    private fun sendNotification(context: Context, title: String, body: String) {

        val notificationManager = NotificationManagerCompat.from(context)
        val mBuilder = NotificationCompat.Builder(context, createNotificationChannel(context, title, body))
        val notificationId = (System.currentTimeMillis() and 0xfffffff).toInt()

        mBuilder.setDefaults(Notification.DEFAULT_ALL)
            .setTicker("PlantCare")
            .setContentTitle(title)
            .setContentText(body)
            .setDefaults(Notification.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_agave)
            .setContentInfo("Content Info")
            .setAutoCancel(true)

        notificationManager.notify(notificationId, mBuilder.build())
    }


}