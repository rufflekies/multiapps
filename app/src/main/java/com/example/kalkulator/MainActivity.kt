package com.example.kalkulator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulator.ScanQRCodeActivity

@Suppress("DEPRECATION")
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
        supportActionBar?.hide()

        // Intent ke halaman Kalkulator
        val buttonGoToNext: ImageView = findViewById(R.id.buttonGoToNext)
        buttonGoToNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        // Intent ke halaman Scan QR
        val buttonScanQR: ImageView = findViewById(R.id.buttonScanQR)
        buttonScanQR.setOnClickListener {
            val intent = Intent(this, ScanQRCodeActivity::class.java)
            startActivity(intent)
        }

        // Intent ke halaman Peta
        val buttonPeta: ImageView = findViewById(R.id.buttonPeta)
        buttonPeta.setOnClickListener {
            val intent = Intent(this, PetaActivity::class.java)
            startActivity(intent)
        }

        // Intent ke halaman Adiwiyata
        val buttonAdiwiyata: ImageView = findViewById(R.id.buttonAdiwiyata)
        buttonAdiwiyata.setOnClickListener {
            val intent = Intent(this, AdiwiyataActivity::class.java)
            startActivity(intent)
        }
    }
}