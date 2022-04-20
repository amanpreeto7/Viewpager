package com.o7services.viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
    }

    fun layoutPager(view: View) {
        startActivity(Intent(this, LayoutPagerActivity::class.java))

    }
    fun fragmentPager(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}