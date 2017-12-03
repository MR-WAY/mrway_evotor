package io.repoint.mrway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by mishu on 03.12.2017.
 */

public class WelcomeActivity extends Activity {

//    AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        ImageView splash_iv = findViewById(R.id.splash_iv);
//        splash_iv.setBackgroundResource(R.drawable.splash_anim);
//        animation = (AnimationDrawable) splash_iv.getBackground();
//        animation.start();

        splash_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                animation.stop();
                Intent intent = new Intent(WelcomeActivity.this, SetupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
