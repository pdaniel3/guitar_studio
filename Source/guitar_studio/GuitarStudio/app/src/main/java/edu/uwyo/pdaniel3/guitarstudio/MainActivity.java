package edu.uwyo.pdaniel3.guitarstudio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;


public class MainActivity extends AppCompatActivity {


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

    private Tuple current_tuple;

    private String song = "";

    private static final String TAG = "Activity";
    TextView tablature;
    Button mDone;

    ArrayList<Tuple> section = new ArrayList<Tuple>();

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
                tablature.setText(song);
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

    public void shouldPrint(Tuple currentNote) {
      ArrayList<Tuple> tempSection = section;
      tempSection.add(currentNote);
      Tuple[] s = tempSection.toArray(new Tuple[section.size()]);
      String mockPrint = printSection(s);
      if(((double)((mockPrint.length() - 7)) / 6) > 35) {
            song = song + mockPrint;
            section.clear();
      } else {
          section.add(currentNote);
      }
    }

    public void listen() {
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result,AudioEvent event) {

                final float pitchInHz = result.getPitch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.textView1);
                        if(pitchInHz > 0){
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
        new Thread(dispatcher,"Audio Dispatcher").start();
    }

    public String printSection(Tuple[] section){

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
                e_note_played = true;
                estring = estring + i.get_note_dashes()
                        + i.get_fret_number().toString();
                Bstring = Bstring + i.get_note_dashes();
                Gstring = Gstring + i.get_note_dashes();
                Dstring = Dstring + i.get_note_dashes();
                Astring = Astring + i.get_note_dashes();
                Estring = Estring +  i.get_note_dashes();
                ChrdString = ChrdString +  i.get_note_dashes();
            }
            else if(i.get_note_name().charAt(0) == 'B'){
                B_note_played = true;
                Bstring = Bstring +  i.get_note_dashes()
                        + i.get_fret_number().toString();
                estring = estring +  i.get_note_dashes();
                Gstring = Gstring + i.get_note_dashes();
                Dstring = Dstring +  i.get_note_dashes();
                Astring = Astring + i.get_note_dashes();
                Estring = Estring +  i.get_note_dashes();
                ChrdString = ChrdString + i.get_note_dashes();

            }
            else if(i.get_note_name().charAt(0) == 'G'){
                G_note_played = true;
                Gstring = Gstring +  i.get_note_dashes()
                        + i.get_fret_number().toString();
                Bstring = Bstring + i.get_note_dashes();
                estring = estring +  i.get_note_dashes();
                Dstring = Dstring +  i.get_note_dashes();
                Astring = Astring + i.get_note_dashes();
                Estring = Estring + i.get_note_dashes();
                ChrdString = ChrdString + i.get_note_dashes();
            }
            else if(i.get_note_name().charAt(0) == 'D'){
                D_note_played = true;
                Dstring = Dstring +  i.get_note_dashes()
                        + i.get_fret_number().toString();
                Bstring = Bstring +  i.get_note_dashes();
                Gstring = Gstring +  i.get_note_dashes();
                estring = estring +  i.get_note_dashes();
                Astring = Astring +  i.get_note_dashes();
                Estring = Estring +  i.get_note_dashes();
                ChrdString = ChrdString + i.get_note_dashes();
            }
            else if(i.get_note_name().charAt(0) == 'A'){
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
            else if(i.get_note_name().charAt(0) == 'E'){
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
        System.out.println("Chrd:" + ChrdString + restOf_Chrd_dashes + "|");
        System.out.print("\n");


        String s = ("e|" + estring + restOf_e_dashes + "|\n" +
                "B|" + Bstring + restOf_B_dashes + "|\n" +
                "G|" + Gstring + restOf_G_dashes + "|\n" +
                "D|" + Dstring + restOf_D_dashes + "|\n" +
                "A|" + Astring + restOf_A_dashes + "|\n" +
                "E|" + Estring + restOf_E_dashes + "|\n\n");

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
                        + "0";
                Astring = Astring +  i.get_note_dashes()
                        + "2";
                Dstring = Dstring +  i.get_note_dashes()
                        + "2";
                Gstring = Gstring + i.get_note_dashes()
                        + "0";
                Bstring = Bstring + i.get_note_dashes()
                        + "0";
                estring = estring + i.get_note_dashes()
                        + "0";
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
    }
}