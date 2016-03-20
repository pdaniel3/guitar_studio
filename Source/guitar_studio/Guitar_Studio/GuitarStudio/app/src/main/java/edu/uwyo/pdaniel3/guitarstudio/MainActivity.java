package edu.uwyo.pdaniel3.guitarstudio;

import android.graphics.Typeface;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jtransforms.fft.FloatFFT_1D;




public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Activity";

    private final static int SAMPLE_FREQ = 16000;
    private final static double REC_LENGTH_S = 0.512; // FFT performs best if SAMPLE*LENGTH is a power of 2
    private final static short AMPLITUDE_DETECTOR = 700;
    private final static float MAX_SLIDER_FREQ_OFFSET_HZ = 30.0f;

    private short[] mRecordedData;

    private boolean mIsListening;
    private boolean mIsRecording;
    private boolean mIsProcessing;
    private boolean mStartListeningPressed = false;

    private boolean mHelpTextVisible = true;

    private FloatFFT_1D fft;

    TextView textView;

    TextView stringNameTextView;
    TextView tablature;
    Button mDone;
    Button mStartListening;

    String e = "e|-";
    String B= "B|-";
    String G ="G|-";
    String D="D|-";
    String A="A|-";
    String E="E|-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablature = (TextView) findViewById(R.id.tablature);

        tablature.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/cour.ttf"));

        mDone = (Button) findViewById(R.id.button);

        mStartListening = (Button) findViewById(R.id.button2);

        mDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //normalize_strings();
                tablature.setText(E + "\n" + A + "\n" + D + "\n" + G + "\n" + B + "\n" + e + "\n");
            }
        });
    }

    public void normalize_strings() {
        String[] stringArray = {E,A,D,G,B,e};

        int greatest_length = 0;

        for(int i = 0; i < stringArray.length; i++) {
            if(stringArray[i].length() > greatest_length ) {
                greatest_length = stringArray[i].length();
            }
        }

        for(int j = 0; j < stringArray.length; j++) {
            if(stringArray[j].length() < greatest_length)
            {
                while (stringArray[j].length() < greatest_length) {
                    stringArray[j] = stringArray[j]+"-";
                }
            }
        }

        E = stringArray[0];
        A = stringArray[1];
        D = stringArray[2];
        G = stringArray[3];
        B = stringArray[4];
        e = stringArray[5];
    }

    @Override
    protected void onResume() {
        super.onResume();

        Thread listenerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                mIsListening = true;
                mIsRecording = false;

                //while(mStartListeningPressed == false) {
                startListening();
                //}
            }

        });

        listenerThread.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsListening = false;
    }

    private void startListening(){

        int recordedDataSize = (int) (SAMPLE_FREQ * REC_LENGTH_S);
        fft = new FloatFFT_1D(recordedDataSize);
        mRecordedData = new short[recordedDataSize];

        int bufferSize = AudioRecord.getMinBufferSize(
                SAMPLE_FREQ,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        short[] readAudioData = new short[bufferSize];

        AudioRecord audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLE_FREQ,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize);

        audioRecord.startRecording();

        int indexInRecordedData = 0;
        while(mIsListening){
            int readDataSize = audioRecord.read(readAudioData, 0, bufferSize);
            for(int i = 0; i < readDataSize; i++){

                if(readAudioData[i] >= AMPLITUDE_DETECTOR && !mIsProcessing) mIsRecording = true;

                if(mIsRecording == true && indexInRecordedData < recordedDataSize) {
                    mRecordedData[indexInRecordedData++] = readAudioData[i];
                } else if(indexInRecordedData >= recordedDataSize){
                    mIsRecording = false;
                    indexInRecordedData = 0;
                    processRecordedData();
                }
            }
        }

        audioRecord.stop();
        audioRecord.release();

    }

    private void processRecordedData() {
        mIsProcessing = true;

        float[] recordedDataFFT;
        recordedDataFFT = FrequencyDetector.convertToFloats(mRecordedData);
        fft.realForward(recordedDataFFT);

        float[] halfAbsFFT;
        halfAbsFFT = FrequencyDetector.absAndHalve(recordedDataFFT);

        int[] peaks;
        peaks = FrequencyDetector.identifyPeaksAndRemove(halfAbsFFT);

        String peakString = "";
        for(int e : peaks) peakString += e + " ";
        Log.d(TAG, peakString);

        int frequency = FrequencyDetector.detectFrequency(peaks);
        if(frequency != -1) this.updateStrings(frequency);

        mIsProcessing = false;
    }

    private void updateStrings(final int frequency) {

        final int stringFreq = FrequencyDetector.detectStringFreq(frequency);
        final Tuple stringName = FrequencyDetector.getStringName(stringFreq);


        String tempnote = stringName.get_note_name();

        String dashes = "";

        if(frequency > 554) {
            dashes = "--";
        } else {
            dashes = "-";
        }

        if (tempnote == "E") {
            E = E + "-" + (stringName.get_fret_number() + "");
            A = A + dashes;
            D = D + dashes;
            G = G + dashes;
            B = B + dashes;
            e = e + dashes;
        }
        else if (tempnote == "A") {
            E = E + dashes;
            A = A + "-" + (stringName.get_fret_number() + "");
            D = D + dashes;
            G = G + dashes;
            B = B + dashes;
            e = e + dashes;
        }
        else if (tempnote == "D") {
            E = E + dashes;
            A = A + dashes;
            D = D + "-" + (stringName.get_fret_number() + "");
            G = G + dashes;
            B = B + dashes;
            e = e + dashes;
        }
        else if (tempnote == "G") {
            E = E + dashes;
            A = A + dashes;
            D = D + dashes;
            G = G + "-" + (stringName.get_fret_number() + "");
            B = B + dashes;
            e = e + dashes;
        }
        else if (tempnote == "B") {
            E = E + dashes;
            A = A + dashes;
            D = D + dashes;
            G = G + dashes;
            B = B + "-" + (stringName.get_fret_number() + "");
            e = e + dashes;
        } else {
            E = E + dashes;
            A = A + dashes;
            D = D + dashes;
            G = G + dashes;
            B = B + dashes;
            e = e + "-" + (stringName.get_fret_number() + "");
        }
    }
}