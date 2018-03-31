package com.example.jorge.supermariojump

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.jorge.supermariojump.R.layout.fragment_animation

class AnimationActivity : AppCompatActivity() {

    protected lateinit var mario: View
    protected lateinit var frameLayout: View
    protected var screenHeight = 0f

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        mario = findViewById(R.id.mario)
        frameLayout = findViewById(fragment_animation)
        frameLayout.setOnClickListener { onStartAnimation() }
        onStartAnimation()
    }

    override fun onResume() {
        super.onResume()

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels.toFloat()
    }

    private fun onStartAnimation() {

        val valueAnimator = ValueAnimator.ofFloat(0f, -screenHeight)

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            mario.translationY = value
        }

        valueAnimator.interpolator = LinearInterpolator() as TimeInterpolator?
        valueAnimator.duration = Companion.DEFAULT_ANIMATION_DURATION

        valueAnimator.start()
    }

    companion object {
        val DEFAULT_ANIMATION_DURATION = 1700L
    }
}
