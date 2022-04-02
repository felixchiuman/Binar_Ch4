package com.example.binarch4.onboarding

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.binarch4.R

class OnBoardingItemsAdapter(private val onBoardingItem: List<OnBoardingItem>) :
RecyclerView.Adapter<OnBoardingItemsAdapter.OnboardingItemViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        return OnboardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.on_boarding_item_container,parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItem[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItem.size
    }

    inner class OnboardingItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val imageOnBoarding = view.findViewById<ImageView>(R.id.image_onboarding)
        private val textTitle = view.findViewById<TextView>(R.id.text_title)
        private val textDescription = view.findViewById<TextView>(R.id.text_description)

        fun bind(onBoardingItem: OnBoardingItem){
            imageOnBoarding.setImageResource(onBoardingItem.onBoardingImage)
            textTitle.text = onBoardingItem.title
            textDescription.text = onBoardingItem.descriptor
        }
    }
}