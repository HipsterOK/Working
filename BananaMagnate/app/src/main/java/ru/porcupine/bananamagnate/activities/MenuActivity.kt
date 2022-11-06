package ru.porcupine.bananamagnate.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import ru.porcupine.bananamagnate.R
import ru.porcupine.bananamagnate.utils.SharedPreference

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val continueBtn = findViewById<Button>(R.id.continueBtn)
        val newGameBtn = findViewById<Button>(R.id.newGameBtn)
        val policyBtn = findViewById<Button>(R.id.policyBtn)

        newGameBtn.setOnClickListener {
            SharedPreference(this).save("banana", 0)
            SharedPreference(this).save("money", 0)
            SharedPreference(this).save("bananaClick", 1)
            SharedPreference(this).save("bananaSec", 0)
            SharedPreference(this).save("bananaPrice", 1)
            SharedPreference(this).save("automatic", 0)
            SharedPreference(this).save("update2", 10)
            SharedPreference(this).save("update3", 15)
            SharedPreference(this).save("update4", 50)
            SharedPreference(this).save("update5", 1000)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        continueBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        policyBtn.setOnClickListener{
            val url = this.getString(R.string.url)
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(this, Uri.parse(url))
        }
    }
}