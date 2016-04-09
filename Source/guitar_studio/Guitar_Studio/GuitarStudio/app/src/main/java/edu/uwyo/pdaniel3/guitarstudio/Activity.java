package edu.uwyo.pdaniel3.guitarstudio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Activity extends AppCompatActivity {

    private Button mLoadButton, mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        mLoadButton = (Button) findViewById(R.id.load_button);

        mPlayButton = (Button) findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Activity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
