package com.hamza.ieeechallenge.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.hamza.ieeechallenge.R
import com.hamza.ieeechallenge.databinding.ActivityHomeBinding
import com.hamza.ieeechallenge.ui.auth.AuthViewModel
import com.hamza.ieeechallenge.ui.home.TabAdapter
import com.hamza.ieeechallenge.ui.home.contact.ContactActivity
import com.hamza.ieeechallenge.utils.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeActivity @Inject constructor(): AppCompatActivity() {

    private lateinit var tabAdapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var fabTop: FloatingActionButton
    private lateinit var fabBottom: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager
        tabLayout = binding.tabLayout
        fabTop = binding.fabTop
        fabBottom = binding.fabBottom

        tabLayoutSettings()
        fabSettings()

    }

    private fun fabSettings() {
        if (viewPager.currentItem == 1) {
            fabBottom.setOnClickListener {
                startActivity(Intent(this@HomeActivity, ContactActivity::class.java))
            }
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        fabBottom.hide()
                        fabTop.hide()
                    }
                    1 -> {
                        fabBottom.setImageResource(R.drawable.ic_baseline_comment_24)
                        fabTop.hide()
                        fabBottom.show()
                        fabBottom.setOnClickListener {
                            startActivity(Intent(this@HomeActivity, ContactActivity::class.java))
                        }
                    }
                    2 -> {
                        fabTop.hide()
                        fabBottom.setImageResource(R.drawable.ic_baseline_photo_camera_24)
                        fabTop.show()
                        fabBottom.show()
                    }
                    else -> {
                        fabTop.hide()
                        fabBottom.setImageResource(R.drawable.ic_baseline_call_24)
                        fabBottom.show()
                    }
                }
            }

        })
    }

    private fun tabLayoutSettings() {
        tabAdapter = TabAdapter(supportFragmentManager)

        viewPager.offscreenPageLimit = 4
        viewPager.adapter = tabAdapter
        viewPager.currentItem = 1

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.icon = getDrawable(R.drawable.ic_baseline_photo_camera_24)

        val layout =
            (tabLayout.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0.5f
        layout.layoutParams = layoutParams
    }
}