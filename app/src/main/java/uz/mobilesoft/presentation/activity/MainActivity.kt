package uz.mobilesoft.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.mobilesoft.cleanarchitecture.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}