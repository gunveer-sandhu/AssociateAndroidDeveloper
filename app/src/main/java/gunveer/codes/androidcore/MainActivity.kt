package gunveer.codes.androidcore

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.material.snackbar.Snackbar
import gunveer.codes.androidcore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        initBtnListeners()
        createNotificationChannel()
    }

    private fun initBtnListeners(){
        binding.button.setOnClickListener {
            Toast.makeText(this, "Hello this is a toast", Toast.LENGTH_LONG).show()
        }
        binding.button2.setOnClickListener {
            Snackbar.make(binding.root, "This is a simple snackbar", Snackbar.LENGTH_LONG).show()
            //To do: Complex action on snackbar
        }
        binding.button3.setOnClickListener {
            val simpWorkRequest = OneTimeWorkRequestBuilder<TestWorker>().build()
            WorkManager.getInstance(this).enqueue(simpWorkRequest)
        }
    }

    private fun setBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun createNotificationChannel(){
        val chName = "Test Channel"
        val descText = "Test Channel Desc"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel("101", chName, importance).apply {
            description = descText
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}