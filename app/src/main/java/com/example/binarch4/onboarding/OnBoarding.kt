package com.example.binarch4.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.binarch4.R
import com.example.binarch4.activity.LoginActivity
import com.example.binarch4.activity.MainActivity
import com.google.android.material.button.MaterialButton

class OnBoarding : AppCompatActivity() {

    private lateinit var onBoardingItemsAdapter: OnBoardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        setOnboardingItems()
        setupIndicator()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems(){
        onBoardingItemsAdapter = OnBoardingItemsAdapter(
            listOf(
                OnBoardingItem(
                    onBoardingImage = R.drawable.pen,
                    title = "Time To Mint",
                    descriptor = "We will remind you the minting time of NFT"
                ),
                OnBoardingItem(
                    onBoardingImage = R.drawable.time,
                    title = "Don't Be Afraid",
                    descriptor = "Don't be afraid to miss the minting time of NFT"
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.on_boarding_view_pager)
        onboardingViewPager.adapter = onBoardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
            })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<TextView>(R.id.tv_skip).setOnClickListener {
            navigateToLogin()
        }
        findViewById<MaterialButton>(R.id.btn_get_started).setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin(){
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    private fun setupIndicator(){
        indicatorsContainer = findViewById(R.id.indicator_container)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemsAdapter.itemCount)
        val layoutParams : LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,8,8,8)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive_background)
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_active_background
                    )
                )
            }
            else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}