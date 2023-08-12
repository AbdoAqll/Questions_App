package com.example.questionsapp

import android.app.Notification.Action
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button

class Front : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this line is to remove the action bar from the app
        // we but it before the set content view to remove the bar before setting the content
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.front)
        findViewById<Button>(R.id.questionsButton).setOnClickListener {
            startActivity(Intent(this , Second::class.java))
        }

        findViewById<Button>(R.id.RateUsButton).setOnClickListener {
            try {
                var uri = Uri.parse("market://details?id $packageName")
                var goToMarket = Intent(Intent.ACTION_VIEW)
                goToMarket.data = uri
                startActivity(goToMarket)
            }
            catch (e : Exception)
            {
                var uri = Uri.parse("http://play.google.com/store/apps/details?id $packageName")
                var goToMarket = Intent(Intent.ACTION_VIEW)
                goToMarket.data = uri
                startActivity(goToMarket)
            }


        }
    }
}