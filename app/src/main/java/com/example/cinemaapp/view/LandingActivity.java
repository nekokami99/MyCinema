package com.example.cinemaapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.controls.OnSwipeTouchListener;

public class LandingActivity extends AppCompatActivity {

    //start activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_landing);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Recognize Swipes
        View view = (View)findViewById(R.id.landing_layout);
        view.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeLeft() {
                openActivity();
            }
        });

        introAnimation();
    }

    //change to main activity and
    public void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //using animation transition
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //don't allow to return to this activity
        this.finish();
    }


    private void introAnimation() {
        ImageView logoImg = (ImageView) findViewById(R.id.mainLogo);

        //logo animation
        RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        animation.setStartOffset(500);
        animation.setDuration(2000);
        logoImg.setAnimation(animation);
        logoImg.startAnimation(animation);

        //animation for text view
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TextView swipeText = (TextView)findViewById(R.id.swipeSuggestionText);

                //Slide TextView and set opacity to 100
                swipeText.startAnimation(AnimationUtils.loadAnimation(LandingActivity.this, R.anim.right_to_left_swipe));
                swipeText.animate().alpha(1f).setDuration(1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
