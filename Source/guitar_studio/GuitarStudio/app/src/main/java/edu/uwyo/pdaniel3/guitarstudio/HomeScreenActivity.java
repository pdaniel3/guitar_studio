package edu.uwyo.pdaniel3.guitarstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {

    private Button mLoadButton, mPlayButton, mTuneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLoadButton = (Button) findViewById(R.id.load_button);
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

        mPlayButton = (Button) findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, RecordActivity.class);
                startActivity(i);
            }
        });

        mTuneButton = (Button) findViewById(R.id.tune_button);
        mTuneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreenActivity.this, GuitarTuner.class);
                startActivity(i);
            }
        });


    }
}
