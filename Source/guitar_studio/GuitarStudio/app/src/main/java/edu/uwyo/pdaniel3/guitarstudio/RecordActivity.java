package edu.uwyo.pdaniel3.guitarstudio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;


public class RecordActivity extends Activity {

    Context context = this;

    static String estring = "";
    static String Bstring = "";
    static String Gstring = "";
    static String Dstring = "";
    static String Astring = "";
    static String Estring = "";
    static String ChrdString = "";
    static boolean e_note_played = false;
    static boolean B_note_played = false;
    static boolean G_note_played = false;
    static boolean D_note_played = false;
    static boolean A_note_played = false;
    static boolean E_note_played = false;

    private Thread listener;

    private Tuple current_tuple;

    private String song = "";

    private static final String TAG = "HomeScreenActivity";
    TextView tablature;
    Button mRecord, mSave, mClear;

    private boolean recording = false;

    ArrayList<Tuple> section = new ArrayList<Tuple>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        tablature = (TextView) findViewById(R.id.recorded_tab);
        //tablature.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/cour.ttf"));


        mClear = (Button) findViewById(R.id.clear);
        mClear.setEnabled(false);
        mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                song = "";
                tablature.setText(song);
                mSave.setEnabled(false);
                mClear.setEnabled(false);
                mRecord.setEnabled(true);
            }
        });

        mSave = (Button) findViewById(R.id.save);
        mSave.setEnabled(false);
        mSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.save_dialog, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        try {
                                            saveSong(userInput.getText().toString());
                                            mClear.performClick();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        mRecord = (Button) findViewById(R.id.record);
        mRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recording) {
                    recording = false;
                    listener.interrupt();
                    mRecord.setText("Record");
                    mSave.setEnabled(true);
                    mClear.setEnabled(true);
                    mRecord.setEnabled(false);
                    tablature.setText(song);
                } else {
                    recording = true;
                    mRecord.setText("Done");
                    listen();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void saveSong(String songName) throws IOException {
        String fileName = songName + ".txt";

        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/recordings");
        dir.mkdirs();
        File file = new File(dir, fileName);


        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write(song.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    public void shouldPrint(Tuple currentNote) {
        if (section.size() >= 14) {
            song = song + printSection(section.toArray(new Tuple[section.size()]))+"\n\n";
            section.clear();
        } else {
            section.add(currentNote);
        }
    }

    public void listen() {
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result, AudioEvent event) {

                final float pitchInHz = result.getPitch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.textView1);
                        if (pitchInHz > 0 && recording) {
                            text.setText("" + pitchInHz);
                            current_tuple = FrequencyDetector.detectStringFreq((double) pitchInHz);
                            shouldPrint(current_tuple);
                        } else {
                            text.setText("");
                        }
                    }
                });
            }
        };
        AudioProcessor p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(p);
        listener = new Thread(dispatcher, "Audio Dispatcher");
        listener.start();
    }

    public static String printSection(Tuple[] section){

        Tuple[] tupleArray = section;

        //Used for generating the rest of the dashes
        String restOf_e_dashes = "";
        String restOf_B_dashes = "";
        String restOf_G_dashes = "";
        String restOf_D_dashes = "";
        String restOf_A_dashes = "";
        String restOf_E_dashes = "";
        String restOf_Chrd_dashes = "";

        int elength;
        int Blength;
        int Glength;
        int Dlength;
        int Alength;
        int Elength;
        int Chrdlength;
        int max;
        String maxString;

        //prints 32 notes at a time (Standard tab section length)
        //print section

        //for( Tuple i : tupleArray)
        //{
        for(int j =0; j < tupleArray.length; j++ ){

            Tuple i = tupleArray[j];
            if(i.get_is_chord() == true){
                elength = estring.length();
                Blength = Bstring.length();
                Glength = Gstring.length();
                Dlength = Dstring.length();
                Alength = Astring.length();
                Elength = Estring.length();
                Chrdlength = ChrdString.length();
                max = elength;
                maxString = estring;

                //Find the string with the most characters
                if(Blength > max){
                    max = Blength;
                    maxString = Bstring;
                }
                else if(Glength > max)
                {
                    max = Glength;
                    maxString = Gstring;
                }
                else if(Dlength > max){
                    max = Dlength;
                    maxString = Dstring;
                }
                else if(Alength > max)
                {
                    max = Alength;
                    maxString = Astring;
                }
                else if(Elength > max)
                {
                    max = Elength;
                    maxString = Estring;
                }

                //get the difference.
                int eDiff = max - elength;
                int BDiff = max - Blength;
                int GDiff = max - Glength;
                int DDiff = max - Dlength;
                int ADiff = max - Alength;
                int EDiff = max - Elength;
                int ChrdDiff = max - Chrdlength;


                //Generate missing dashes

                for(int k = 0; k<eDiff; k++){
                    estring = estring + "-";
                }
                for(int k = 0; k<BDiff; k++){
                    Bstring = Bstring + "-";
                }
                for(int k = 0; k<GDiff; k++){
                    Gstring = Gstring + "-";
                }
                for(int k = 0; k<DDiff; k++){
                    Dstring = Dstring + "-";
                }
                for(int k = 0; k<ADiff; k++){
                    Astring = Astring + "-";
                }
                for(int k = 0; k<EDiff; k++){
                    Estring = Estring + "-";
                }
                for(int k = 0; k<ChrdDiff; k++){
                    ChrdString = ChrdString + "-";
                }

                //System.out.println("The string with the longest length is: " + maxString);
                elength = 0;
                Blength = 0;
                Glength = 0;
                Dlength = 0;
                Alength = 0;
                Elength = 0;
                max = 0;
                printChord(i);
            }
            else if(i.get_note_name().charAt(0) == 'e'){
                if(i.get_fret_number().toString().length() > 1){

                    estring = estring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes() + "-";
                    Gstring = Gstring + i.get_note_dashes() + "-";
                    Dstring = Dstring + i.get_note_dashes() + "-";
                    Astring = Astring + i.get_note_dashes() + "-";
                    Estring = Estring + i.get_note_dashes() + "-";
                    ChrdString = ChrdString + i.get_note_dashes() + "-";
                }
                else{

                    estring = estring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring + i.get_note_dashes();
                    Gstring = Gstring + i.get_note_dashes();
                    Dstring = Dstring + i.get_note_dashes();
                    Astring = Astring + i.get_note_dashes();
                    Estring = Estring +  i.get_note_dashes();
                    ChrdString = ChrdString +  i.get_note_dashes();
                }
            }
            else if(i.get_note_name().charAt(0) == 'B'){
                if(i.get_fret_number().toString().length() > 1){

                    Bstring = Bstring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Estring = Estring +  i.get_note_dashes() + "-";
                    Gstring = Gstring + i.get_note_dashes() + "-";
                    Dstring = Dstring + i.get_note_dashes() + "-";
                    Astring = Astring + i.get_note_dashes() + "-";
                    estring = estring + i.get_note_dashes() + "-";
                    ChrdString = ChrdString + i.get_note_dashes() + "-";
                }
                else{

                    Bstring = Bstring +  i.get_note_dashes()
                            + i.get_fret_number().toString();
                    estring = estring +  i.get_note_dashes();
                    Gstring = Gstring + i.get_note_dashes();
                    Dstring = Dstring +  i.get_note_dashes();
                    Astring = Astring + i.get_note_dashes();
                    Estring = Estring +  i.get_note_dashes();
                    ChrdString = ChrdString + i.get_note_dashes();
                }

            }
            else if(i.get_note_name().charAt(0) == 'G'){
                if(i.get_fret_number().toString().length() > 1){

                    Gstring = Gstring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes() + "-";
                    Estring = Estring + i.get_note_dashes() + "-";
                    Dstring = Dstring + i.get_note_dashes() + "-";
                    Astring = Astring + i.get_note_dashes() + "-";
                    estring = estring + i.get_note_dashes() + "-";
                    ChrdString = ChrdString + i.get_note_dashes() + "-";
                }
                else{

                    Gstring = Gstring +  i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring + i.get_note_dashes();
                    estring = estring +  i.get_note_dashes();
                    Dstring = Dstring +  i.get_note_dashes();
                    Astring = Astring + i.get_note_dashes();
                    Estring = Estring + i.get_note_dashes();
                    ChrdString = ChrdString + i.get_note_dashes();
                }
            }
            else if(i.get_note_name().charAt(0) == 'D'){

                if(i.get_fret_number().toString().length() > 1){

                    Dstring = Dstring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes() + "-";
                    Gstring = Gstring + i.get_note_dashes() + "-";
                    Estring = Estring + i.get_note_dashes() + "-";
                    Astring = Astring + i.get_note_dashes() + "-";
                    estring = estring + i.get_note_dashes() + "-";
                    ChrdString = ChrdString + i.get_note_dashes() + "-";
                }
                else{
                    Dstring = Dstring +  i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes();
                    Gstring = Gstring +  i.get_note_dashes();
                    estring = estring +  i.get_note_dashes();
                    Astring = Astring +  i.get_note_dashes();
                    Estring = Estring +  i.get_note_dashes();
                    ChrdString = ChrdString + i.get_note_dashes();
                }
            }
            else if(i.get_note_name().charAt(0) == 'A'){
                if(i.get_fret_number().toString().length() > 1){
                    E_note_played = true;
                    Astring = Astring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes() + "-";
                    Gstring = Gstring + i.get_note_dashes() + "-";
                    Dstring = Dstring + i.get_note_dashes() + "-";
                    Estring = Estring + i.get_note_dashes() + "-";
                    estring = estring + i.get_note_dashes() + "-";
                    ChrdString = ChrdString + i.get_note_dashes() + "-";
                }
                else{
                    A_note_played = true;
                    Astring = Astring +  i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring + i.get_note_dashes();
                    Gstring = Gstring + i.get_note_dashes();
                    Dstring = Dstring +  i.get_note_dashes();
                    estring = estring + i.get_note_dashes();
                    Estring = Estring +  i.get_note_dashes();
                    ChrdString = ChrdString + i.get_note_dashes();
                }
            }
            else if(i.get_note_name().charAt(0) == 'E'){

                if(i.get_fret_number().toString().length() > 1){
                    E_note_played = true;
                    Estring = Estring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes() + "-";
                    Gstring = Gstring + i.get_note_dashes() + "-";
                    Dstring = Dstring + i.get_note_dashes() + "-";
                    Astring = Astring + i.get_note_dashes() + "-";
                    estring = estring + i.get_note_dashes() + "-";
                    ChrdString = ChrdString + i.get_note_dashes() + "-";
                }
                else{

                    E_note_played = true;
                    Estring = Estring + i.get_note_dashes()
                            + i.get_fret_number().toString();
                    Bstring = Bstring +  i.get_note_dashes();
                    Gstring = Gstring + i.get_note_dashes();
                    Dstring = Dstring + i.get_note_dashes();
                    Astring = Astring + i.get_note_dashes();
                    estring = estring + i.get_note_dashes();
                    ChrdString = ChrdString + i.get_note_dashes();
                }

            }


            elength = 0;
            Blength = 0;
            Glength = 0;
            Dlength = 0;
            Alength = 0;
            Elength = 0;
            max = 0;

        }


        //generate the rest of the dashes needed for the section

        for(int j = 0; j < 32 - (estring.length()); j++)
        {
            restOf_e_dashes = restOf_e_dashes + "-";
        }

        for(int j = 0; j < 32 - (Bstring.length()); j++)
        {
            restOf_B_dashes = restOf_B_dashes + "-";
        }

        for(int j = 0; j < 32 - Gstring.length(); j++)
        {
            restOf_G_dashes = restOf_G_dashes + "-";
        }

        for(int j = 0; j < 32 - Dstring.length(); j++)
        {
            restOf_D_dashes = restOf_D_dashes + "-";
        }

        for(int j = 0; j < 32 - Astring.length(); j++)
        {
            restOf_A_dashes = restOf_A_dashes + "-";
        }

        for(int j = 0; j < 32 - Estring.length(); j++)
        {
            restOf_E_dashes = restOf_E_dashes + "-";
        }

        for(int j = 0; j < 32 - ChrdString.length(); j++)
        {
            restOf_Chrd_dashes = restOf_Chrd_dashes + "-";
        }

        //print the section
        System.out.println("   e|" + estring + restOf_e_dashes + "|");
        System.out.println("   B|" + Bstring + restOf_B_dashes + "|");
        System.out.println("   G|" + Gstring + restOf_G_dashes + "|");
        System.out.println("   D|" + Dstring + restOf_D_dashes + "|");
        System.out.println("   A|" + Astring + restOf_A_dashes + "|");
        System.out.println("   E|" + Estring + restOf_E_dashes + "|");
        System.out.println("Chrd|" + ChrdString + restOf_Chrd_dashes + "|");
        System.out.print("\n");


        String s = ("e|" + estring + restOf_e_dashes + "|\n" +
                "B|" + Bstring + restOf_B_dashes + "|\n" +
                "G|" + Gstring + restOf_G_dashes + "|\n" +
                "D|" + Dstring + restOf_D_dashes + "|\n" +
                "A|" + Astring + restOf_A_dashes + "|\n" +
                "E|" + Estring + restOf_E_dashes + "|\n");

        estring = "";
        Bstring = "";
        Gstring = "";
        Dstring = "";
        Astring = "";
        Estring = "";
        ChrdString = "";

        return s;

    }

    public static void printChord(Tuple tuple){
        //Tuple[] tupleArray = section;
        //	for( Tuple i : tupleArray)
        //		{
        Tuple i = tuple;
        if(i.get_note_name().charAt(0) == 'E'){
            if(i.get_note_name().charAt(1) == 'm'){
                Estring = Estring + i.get_note_dashes()
                        + "0" + "-";
                Astring = Astring +  i.get_note_dashes()
                        + "2" + "-";
                Dstring = Dstring +  i.get_note_dashes()
                        + "2" + "-";
                Gstring = Gstring + i.get_note_dashes()
                        + "0" + "-";
                Bstring = Bstring + i.get_note_dashes()
                        + "0" + "-";
                estring = estring + i.get_note_dashes()
                        + "0" + "-";
                ChrdString = ChrdString +  i.get_note_dashes()+ "Em";
            }
            else{
                Estring = Estring +  i.get_note_dashes()
                        + "0";
                Astring = Astring + i.get_note_dashes()
                        + "2";
                Dstring = Dstring + i.get_note_dashes()
                        + "2";
                Gstring = Gstring +  i.get_note_dashes()
                        + "1";
                Bstring = Bstring + i.get_note_dashes()
                        + "0";
                estring = estring +  i.get_note_dashes()
                        + "0";
                ChrdString = ChrdString + i.get_note_dashes()+ "E";
            }

        }
        else if(i.get_note_name().charAt(0) == 'B'){
            if(i.get_note_name().charAt(1) == 'm'){
                Estring = Estring + i.get_note_dashes()
                        + "2";
                Astring = Astring +  i.get_note_dashes()
                        + "3";
                Dstring = Dstring +  i.get_note_dashes()
                        + "4";
                Gstring = Gstring +  i.get_note_dashes()
                        + "4";
                Bstring = Bstring + i.get_note_dashes()
                        + "2";
                estring = estring +  i.get_note_dashes()
                        + "-";
                ChrdString = ChrdString +  i.get_note_dashes()+ "Bm";
            }
        }
        else if(i.get_note_name().charAt(0) == 'D'){
            if(i.get_note_name().charAt(1) == 'm'){
                Estring = Estring +  i.get_note_dashes()
                        + "-";
                Astring = Astring +  i.get_note_dashes()
                        + "-";
                Dstring = Dstring +  i.get_note_dashes()
                        + "0";
                Gstring = Gstring +  i.get_note_dashes()
                        + "2";
                Bstring = Bstring +  i.get_note_dashes()
                        + "3";
                estring = estring +  i.get_note_dashes()
                        + "1";
                ChrdString = ChrdString +  i.get_note_dashes()+ "Dm";
            }
            else{
                Estring = Estring + i.get_note_dashes()
                        + "-";
                Astring = Astring + i.get_note_dashes()
                        + "-";
                Dstring = Dstring +  i.get_note_dashes()
                        + "0";
                Gstring = Gstring +  i.get_note_dashes()
                        + "2";
                Bstring = Bstring + i.get_note_dashes()
                        + "3";
                estring = estring + i.get_note_dashes()
                        + "2";
                ChrdString = ChrdString + i.get_note_dashes()+ "D";
            }

        }
        else if(i.get_note_name().charAt(0) == 'A'){
            if(i.get_note_name().charAt(1) == 'm'){
                Estring = Estring + i.get_note_dashes()
                        + "-";
                Astring = Astring +  i.get_note_dashes()
                        + "0";
                Dstring = Dstring +  i.get_note_dashes()
                        + "2";
                Gstring = Gstring +  i.get_note_dashes()
                        + "2";
                Bstring = Bstring + i.get_note_dashes()
                        + "1";
                estring = estring +  i.get_note_dashes()
                        + "0";
                ChrdString = ChrdString + i.get_note_dashes()+ "Am";
            }
            else{
                Estring = Estring +  i.get_note_dashes()
                        + "-";
                Astring = Astring + i.get_note_dashes()
                        + "0";
                Dstring = Dstring + i.get_note_dashes()
                        + "2";
                Gstring = Gstring +  i.get_note_dashes()
                        + "2";
                Bstring = Bstring + i.get_note_dashes()
                        + "2";
                estring = estring +  i.get_note_dashes()
                        + "0";
                ChrdString = ChrdString + i.get_note_dashes()+ "A";
            }
        }
        else if(i.get_note_name().charAt(0) == 'C'){
            Estring = Estring + i.get_note_dashes()
                    + "-";
            Astring = Astring +  i.get_note_dashes()
                    + "3";
            Dstring = Dstring + i.get_note_dashes()
                    + "2";
            Gstring = Gstring + i.get_note_dashes()
                    + "0";
            Bstring = Bstring + i.get_note_dashes()
                    + "1";
            estring = estring +  i.get_note_dashes()
                    + "0";
            ChrdString = ChrdString + i.get_note_dashes()+ "C";
        }
        else if(i.get_note_name().charAt(0) == 'G'){
            Estring = Estring +  i.get_note_dashes()
                    + "3";
            Astring = Astring + i.get_note_dashes()
                    + "2";
            Dstring = Dstring + i.get_note_dashes()
                    + "0";
            Gstring = Gstring + i.get_note_dashes()
                    + "0";
            Bstring = Bstring +  i.get_note_dashes()
                    + "0";
            estring = estring +  i.get_note_dashes()
                    + "3";
            ChrdString = ChrdString +  i.get_note_dashes()+ "G";
        }
        else if(i.get_note_name().charAt(0) == 'F'){
            Estring = Estring +  i.get_note_dashes()
                    + "0";
            Astring = Astring + i.get_note_dashes()
                    + "3";
            Dstring = Dstring + i.get_note_dashes()
                    + "3";
            Gstring = Gstring + i.get_note_dashes()
                    + "2";
            Bstring = Bstring +  i.get_note_dashes()
                    + "0";
            estring = estring +  i.get_note_dashes()
                    + "0";
            ChrdString = ChrdString + i.get_note_dashes()+ "F";
        }
        else if(i.get_note_name().charAt(0) == 'N'){
            Estring = Estring +  i.get_note_dashes()
                    + "-";
            Astring = Astring + i.get_note_dashes()
                    + "-";
            Dstring = Dstring + i.get_note_dashes()
                    + "-";
            Gstring = Gstring + i.get_note_dashes()
                    + "-";
            Bstring = Bstring +  i.get_note_dashes()
                    + "-";
            estring = estring +  i.get_note_dashes()
                    + "-";
            ChrdString = ChrdString + i.get_note_dashes()+ "-";
        }

    }
}