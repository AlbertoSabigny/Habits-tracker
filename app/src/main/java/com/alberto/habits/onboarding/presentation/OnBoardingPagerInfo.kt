package com.alberto.habits.onboarding.presentation

import androidx.annotation.DrawableRes

data class OnBoardingPagerInfo (
    val title: String,
    val subtitle:String,
    @DrawableRes val image: Int
)