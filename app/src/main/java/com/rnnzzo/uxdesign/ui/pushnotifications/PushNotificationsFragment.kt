package com.rnnzzo.uxdesign.ui.pushnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.databinding.FragmentPushNotificationsBinding
import com.rnnzzo.uxdesign.ui.main.MainActivity
import kotlin.random.Random

class PushNotificationsFragment : Fragment(){

    private val PRIMARY_CHANNEL_ID = "app_notification_channel"
    private lateinit var mNotifyManager: NotificationManager
    private lateinit var binding: FragmentPushNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPushNotificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createNotificationChannel()
        initEvents()
    }

    fun initEvents(){
        binding.btnBigNotification.setOnClickListener {
            sendBigNotification()
        }
        binding.btnActionNotification.setOnClickListener {
            sendActionNotification()
        }
        binding.btnSimpleNotification.setOnClickListener {
            sendNotification()
        }
    }

    fun createNotificationChannel(){
        mNotifyManager = activity?.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID,
                "Mascot Notification", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.setLightColor(Color.RED)
            notificationChannel.enableVibration(true)
            notificationChannel.setDescription("Notification from Mascot")
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    fun sendActionNotification(){
        val notificationId = Random.nextInt(100)
        val notifyBuilder = getNotificationWithAction()
        mNotifyManager.notify(notificationId, notifyBuilder.build())
    }

    fun sendNotification(){
        val notificationId = Random.nextInt(100)
        val notifyBuilder = getNotificationBuilder()
        mNotifyManager.notify(notificationId, notifyBuilder.build())
    }


    fun sendBigNotification(){
        val notificationId = Random.nextInt(100)
        val notifyBuilder = getBigNotificationBuilder()
        mNotifyManager.notify(notificationId, notifyBuilder.build())
    }

    private fun getNotificationBuilder(): NotificationCompat.Builder {

        return NotificationCompat.Builder(requireContext(), PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("This is a simple notification.")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_ad)
    }

    private fun getBigNotificationBuilder(): NotificationCompat.Builder {
        val androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.background_2)

        return NotificationCompat.Builder(requireContext(), PRIMARY_CHANNEL_ID)
            .setContentText("This is a big notification.")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_ad)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Big Notification"))
    }

    private fun getNotificationWithAction(): NotificationCompat.Builder {
        val resultIntent = Intent(requireContext(), MainActivity::class.java)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(requireContext()).run {
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        return NotificationCompat.Builder(requireContext(), PRIMARY_CHANNEL_ID)
            .setContentTitle("Notification With Action")
            .setContentText("Hold to show Action!")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.ic_ad)
            .addAction(R.drawable.ic_baseline_add, "Action", resultPendingIntent)
    }

    fun updateNotification(notificationID: Int) {
        val androidImage = BitmapFactory
            .decodeResource(resources, R.drawable.background_1)

        val notifyBuilder = getNotificationBuilder()


        notifyBuilder.setStyle(NotificationCompat.BigPictureStyle()
            .bigPicture(androidImage)
            .setBigContentTitle("Notification Updated!"))

        mNotifyManager.notify(notificationID, notifyBuilder.build())
    }
}