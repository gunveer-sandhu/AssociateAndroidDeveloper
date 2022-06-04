package gunveer.codes.androidcore

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class TestWorker(private val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val context = applicationContext
        makeNotification("Starting")
        return try{
            SystemClock.sleep(10000)
            makeNotification("Done")
            Result.success()
        }catch (E: Exception){
            makeNotification("Failed")
            Result.failure()
        }
    }

    private fun makeNotification(Status: String){
        val intent = Intent(context, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(applicationContext, "101")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Work Manager")
            .setContentText(Status)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(context)){
            notify(102, builder.build())
        }
    }
}