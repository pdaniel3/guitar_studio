package edu.uwyo.pdaniel3.guitarstudio;

import java.util.ArrayList;

public class FrequencyDetector {

    public static Tuple detectStringFreq(double freq) {
        double[] stringFrequencies = {44.1, 49.4, 55.2, 55.3, 62.0, 65.3, 73.5, 74.0, 82.7, 82.4,
                82.0, 87.0, 92.0, 98.0, 104.0, 110.0, 117.0, 123.0, 131.0, 139.0, 147.0, 156.0,
                165.0, 175.0, 185.0, 196.0, 208.0, 220.0, 233.0, 247.0, 262.0, 277.0, 294.0, 311.0,
                329.0, 349.0, 370.0, 392.0, 415.0, 440.0, 466.0, 494.0, 523.0, 554.0, 587.0, 622.0,
                659.0, 698.0, 740.0, 784.0, 831.0, 880.0, 932.0, 988.0, 1047.0};

        double result = -1;
        double min = 12345;

        for (int i = 0; i < stringFrequencies.length; i++) {
            double dFromCurrentToDesired = Math.abs(stringFrequencies[i] - freq);
            if (dFromCurrentToDesired < min) {
                min = dFromCurrentToDesired;
                result = stringFrequencies[i];
            }
        }

        return getTuple(result);
    }

    public static Tuple getTuple(double freq) {


        //Author: Marcus Kelly

        ArrayList<Integer> temp = new ArrayList<>();

        String stringFreq = ((Double) freq).toString();
        //5, 4, 5, 5
        switch (stringFreq) {
            //Chords
            case "44.1": //F
                return new Tuple("F ", temp, 44.1, 1, true);
            case "49.4": //G
                return new Tuple("G ", temp, 49.4, 1, true);
            case "55.2": //Am
                return new Tuple("Am", temp, 55.2, 1, true);
            case "55.3": //A
                return new Tuple("A ", temp, 55.3, 1, true);
            case "62.0":    //Bm
                return new Tuple("Bm", temp, 62.0, 1, true);
            case "65.3": //C
                return new Tuple("C ", temp, 65.3, 1, true);
            case "73.5": //D
                return new Tuple("D ", temp, 73.5, 1, true);
            case "74.0": //Dm
                return new Tuple("Dm", temp, 74.0, 1, true);
            case "82.7": //E
                return new Tuple("E ", temp, 82.7, 1, true);
            case "82.4": //Em
                return new Tuple("Em", temp, 82.4, 1, true);
            // Individual notes
            case "82.0":
                temp.add(0);
                return new Tuple("E", temp, 82, 1, false);
            case "87.0":
                temp.add(1);
                return new Tuple("E", temp, 87, 1, false);
            case "92.0":
                temp.add(2);
                return new Tuple("E", temp, 92, 1, false);
            case "98.0":
                temp.add(3);
                return new Tuple("E", temp, 98, 1, false);
            case "104.0":
                temp.add(4);
                return new Tuple("E", temp, 104, 1, false);

            // A string
            case "110.0":
                temp.add(0); //low E
                temp.add(5); // A
                return new Tuple("A", temp, 110, 1, false);
            case "117.0":
                temp.add(1);
                temp.add(6);
                return new Tuple("A", temp, 117, 1, false);
            case "123.0":
                temp.add(2);
                temp.add(7);
                return new Tuple("A", temp, 123, 1, false);
            case "131.0":
                temp.add(3);
                temp.add(8);
                return new Tuple("A", temp, 131, 1, false);
            case "139.0":
                temp.add(4);
                temp.add(9);
                return new Tuple("A", temp, 139, 1, false);


            // D string
            case "147.0":
                temp.add(0);
                temp.add(5);
                temp.add(9);
                return new Tuple("D", temp, 147, 1, false);
            case "156.0":
                temp.add(1);
                temp.add(6);
                temp.add(10);
                return new Tuple("D", temp, 156, 1, false);
            case "165.0":
                temp.add(2);
                temp.add(7);
                temp.add(11);
                return new Tuple("D", temp, 165, 1, false);
            case "175.0":
                temp.add(3);
                temp.add(8);
                temp.add(12);
                return new Tuple("D", temp, 175, 1, false);
            case "185.0":
                temp.add(4);
                temp.add(9);
                temp.add(13);
                return new Tuple("D", temp, 185, 1, false);

            // G string
            case "196.0":
                temp.add(0);
                temp.add(5);
                temp.add(9);
                temp.add(14);
                return new Tuple("G", temp, 196, 1, false);
            case "208.0":
                temp.add(1);
                temp.add(6);
                temp.add(10);
                temp.add(15);
                return new Tuple("G", temp, 208, 1, false);
            case "220.0":
                temp.add(2);
                temp.add(7);
                temp.add(11);
                temp.add(16);
                return new Tuple("G", temp, 220, 1, false);
            case "233.0":
                temp.add(3);
                temp.add(8);
                temp.add(12);
                temp.add(17);
                return new Tuple("G", temp, 233, 1, false);

            //B string
            case "247.0":
                temp.add(0);
                temp.add(5);
                temp.add(9);
                temp.add(14);
                temp.add(19);
                return new Tuple("B", temp, 247, 1, false);
            case "262.0":
                temp.add(1);
                temp.add(6);
                temp.add(10);
                temp.add(15);
                temp.add(20);
                return new Tuple("B", temp, 262, 1, false);
            case "277.0":
                temp.add(2);
                temp.add(7);
                temp.add(11);
                temp.add(16);
                return new Tuple("B", temp, 277, 1, false);
            case "294.0":
                temp.add(3);
                temp.add(8);
                temp.add(12);
                temp.add(17);
                return new Tuple("B", temp, 294, 1, false);
            case "311.0":
                temp.add(4);
                temp.add(9);
                temp.add(13);
                temp.add(18);
                return new Tuple("B", temp, 311, 1, false);

            //high e string
            case "329.0":
                temp.add(0);
                temp.add(5);
                temp.add(9);
                temp.add(14);
                temp.add(19);
                return new Tuple("e", temp, 329, 1, false);
            case "349.0":
                temp.add(1);
                temp.add(6);
                temp.add(10);
                temp.add(15);
                temp.add(20);
                return new Tuple("e", temp, 349, 1, false);
            case "370.0":
                temp.add(2);
                temp.add(7);
                temp.add(11);
                temp.add(16);
                return new Tuple("e", temp, 370, 1, false);
            case "392.0":
                temp.add(3);//high e
                temp.add(8);//B
                temp.add(12);//G
                temp.add(17);//D
                return new Tuple("e", temp, 392, 1, false);
            case "415.0":
                temp.add(4);
                temp.add(9);
                temp.add(13);
                temp.add(18);
                return new Tuple("e", temp, 415, 1, false);
            case "440.0":
                temp.add(5);
                temp.add(10);
                temp.add(14);
                temp.add(19);
                return new Tuple("e", temp, 440, 1, false);
            case "466.0":
                temp.add(6);
                temp.add(11);
                temp.add(15);
                temp.add(20);
                return new Tuple("e", temp, 466, 1, false);
            case "494.0":
                temp.add(7);
                temp.add(12);
                temp.add(16);
                return new Tuple("e", temp, 494, 1, false);
            case "523.0":
                temp.add(8);
                temp.add(13);
                temp.add(17);
                return new Tuple("e", temp, 523, 1, false);
            case "554.0":
                temp.add(9);
                temp.add(14);
                temp.add(18);
                return new Tuple("e", temp, 554, 1, false);
            case "587.0":
                temp.add(10);
                temp.add(15);
                temp.add(19);
                return new Tuple("e", temp, 587, 1, false);
            case "622.0":
                temp.add(11);
                temp.add(16);
                temp.add(20);
                return new Tuple("e", temp, 622, 1, false);
            case "659.0":
                temp.add(12);
                temp.add(17);
                return new Tuple("e", temp, 659, 1, false);
            case "698.0":
                temp.add(13);
                temp.add(18);
                return new Tuple("e", temp, 698, 1, false);
            case "740.0":
                temp.add(14);
                temp.add(19);
                return new Tuple("e", temp, 740, 1, false);
            case "784.0":
                temp.add(15);
                temp.add(20);
                return new Tuple("e", temp, 784, 1, false);    // from here on no more duplicates
            case "831.0":
                temp.add(16);
                return new Tuple("e", temp, 831, 1, false);
            case "880.0":
                temp.add(17);
                return new Tuple("e", temp, 880, 1, false);
            case "932.0":
                temp.add(18);
                return new Tuple("e", temp, 932, 1, false);
            case "988.0":
                temp.add(19);
                return new Tuple("e", temp, 988, 1, false);
            case "1047.0":
                temp.add(20);
                return new Tuple("e", temp, 1047, 1, false);
            //case "330: return "E";		<< this is interesting. did they increase the frequency by 1 on purpose? to avoid duplicates?*/
            default:
                return new Tuple("?", temp, -1, 1, false);
        }
    }
}
