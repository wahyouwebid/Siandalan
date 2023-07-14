package id.siandalan.app.common.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.siandalan.app.R
import id.siandalan.app.SiandalanApp.Companion.getContext
import id.siandalan.app.common.sessions.Sessions
import id.siandalan.app.features.main.MainActivity
import java.util.Date

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val sessions: Sessions = Sessions(getContext())

    override fun onNewToken(token: String) {
        setTokenFirebase(token)
    }

    private fun setTokenFirebase(tokenFirebase: String) {
        sessions.putString(Sessions.tokenFirebase, tokenFirebase)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val notificationId = Date().time.toInt()
        val notificationBuilder: NotificationCompat.Builder?
        notificationBuilder(remoteMessage)

        notificationBuilder = notificationBuilder(remoteMessage)

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(getString(R.string.notification_channel_id), "Siandalan_Notification", NotificationManager.IMPORTANCE_HIGH)
        channel.enableLights(true)
        channel.enableVibration(true)
        notificationManager.createNotificationChannel(channel)

        notificationManager.cancel(notificationId)
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun notificationBuilder(remoteMessage: RemoteMessage): NotificationCompat.Builder {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val contentIntent = PendingIntent.getActivity(
            applicationContext,
            0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val b = NotificationCompat.Builder(
            applicationContext,
            getString(R.string.notification_channel_id)
        )
        return b.setTicker(remoteMessage.data["title"])
            .setWhen(System.currentTimeMillis())
            .setAutoCancel(true)
            .setContentTitle(remoteMessage.notification?.title.toString())
            .setContentText(remoteMessage.notification?.body.toString())
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setShowWhen(true)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
            .setStyle(NotificationCompat.BigTextStyle().bigText(remoteMessage.data["body"]))
            .setContentIntent(contentIntent)
    }
}