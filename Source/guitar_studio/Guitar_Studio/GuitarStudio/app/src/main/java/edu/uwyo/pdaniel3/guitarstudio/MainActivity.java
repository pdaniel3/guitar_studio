package edu.uwyo.pdaniel3.guitarstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Activity";
    TextView tablature;
    Button mDone;

    private String e = "e|-";
    private String B = "B|-";
    private String G = "G|-";
    private String D = "D|-";
    private String A = "A|-";
    private String E = "E|-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablature = (TextView) findViewById(R.id.tablature);
        //tablature.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/cour.ttf"));

        mDone = (Button) findViewById(R.id.button);

        mDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //normalize_strings();
                tablature.setText(combine_strings());
            }
        });

        listen();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public String combine_strings() {
        return e + "\n" + B + "\n" + G + "\n" + D + "\n" + A + "\n" + E + "\n";
    }



    public void listen() {
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result,AudioEvent e) {
                final float pitchInHz = result.getPitch();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.textView1);
                        text.setText("Pitch in Hz" + pitchInHz);
                        //updateStrings(tempint);

                    }
                });
            }
        };
        AudioProcessor p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_PITCH, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(p);
        new Thread(dispatcher,"Audio Dispatcher").start();
    }
}