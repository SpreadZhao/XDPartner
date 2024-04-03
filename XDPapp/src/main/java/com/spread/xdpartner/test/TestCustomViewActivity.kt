package com.spread.xdpartner.test

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xdpartner.R
import com.spread.xdplib.adapter.customsview.RollingEditText

class TestCustomViewActivity : AppCompatActivity() {
    var i = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_test_custom_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editText = findViewById<RollingEditText>(R.id.test_rolling_edit)
        editText.currRollingHint = "0"
        findViewById<Button>(R.id.test_button).setOnClickListener {
            editText.rollingTo("${i++}")
        }
    }
}