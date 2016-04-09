package edu.uwyo.pdaniel3.guitarstudio;

public class FrequencyDetector {

    /*public static float[] absAndHalve(float[] in) {
        // retain magnitude of in and discard half of points
        float[] result = new float[in.length / 2];
        for (int i = 0; i < in.length/2; i++) {
            result[i] = Math.abs(in[i]);
        }

        return result;
    }

    public static float[] convertToFloats(short[] pcms) {
        float[] floaters = new float[pcms.length];
        for (int i = 0; i < pcms.length; i++) {
            floaters[i] = pcms[i];
        }
        return floaters;
    }

    public static int[] identifyPeaksAndRemove(float[] in) {
        final int N_PEAKS = 5; // number of peaks to save
        final int PEAK_THRESHOLD = 15; // number of frequencies to remove
        // from each side of a peak

        int len = in.length;
        int[] result = new int[N_PEAKS];

        for(int peak = 0; peak < N_PEAKS; peak++) {
            float max = 0;
            for(int i = 0; i < len; i++) {
                if(in[i] > max) {
                    max = in[i];
                    result[peak] = i;
                }
            }

            // remove detected peak, and surrounding frequencies up to PEAK_THRESHOLD
            for(int i = 0; i < PEAK_THRESHOLD; i++) {
                if(result[peak] > PEAK_THRESHOLD) in[result[peak] - i] = 0; // if statements prevent outofbounds errors
                if(result[peak] < len - PEAK_THRESHOLD) in[result[peak] + i] = 0;
            }

        }
        return result;
    }

    public static int detectFrequency(int[] input) {
        final int DOUBLE_TOLERANCE = 6;

        // Check for smallest value in input array (fundamental frequency),
        // and to confirm that it is correct, check that there is another
        // element in the peaks array that is about double its value

        int fundamentalFreq = 12345;
        for(int i = 0; i < input.length; i++) {
            if(input[i] < fundamentalFreq && hasDouble(input[i], input) && input[i] > 40 && input[i] < 360)
                fundamentalFreq = input[i];
        }

        if(fundamentalFreq == 12345) fundamentalFreq = -1;

        return fundamentalFreq;
    }

    private static boolean hasDouble(int n, int[] arr) {
        final int DOUBLE_TOLERANCE = 6;

        for(int i = 0; i < arr.length; i++) {
            if(Math.abs(n*2 - arr[i]) <= DOUBLE_TOLERANCE) return true;
        }

        return false;
    }

    public static int detectStringFreq(int freq) {
        int[] stringFrequencies = {82, 87, 92, 98, 104, 110, 117, 123, 131, 139, 147, 156, 165, 175,
                185, 196, 208, 220, 233, 247, 262, 277, 294, 311, 329, 349, 370, 392, 415, 440, 466, 494,
                523, 554, 587, 622, 659, 698, 740, 784, 831, 880, 932, 988, 1047};

        int result = -1;
        int min = 12345;

        for(int i = 0; i < stringFrequencies.length; i++) {
            int dFromCurrentToDesired = Math.abs(stringFrequencies[i] - freq);
            if(dFromCurrentToDesired < min) {
                min = dFromCurrentToDesired;
                result = stringFrequencies[i];
            }
        }

        return result;
    }



    public static Tuple getTuple(int freq) {

        ArrayList<Integer> temp = new ArrayList<>();

        switch (freq) {
            case 82:
                temp.add(0);
                return new Tuple("E", temp, 82, 1);
            case 87:
                temp.add(1);
                return new Tuple("E", temp, 87, 1);
            case 92:
                temp.add(2);
                return new Tuple("E", "2", 92, 1);
            case 98: return new Tuple("E", "3", 98, 1);
            case 104: return new Tuple("E", "4", 104, 1);

            // A string
            case 110:
                temp.add(0); //low E
                temp.add(5); // A
                return new Tuple("A", "0", 110, 1);
            case 117:
220
                return new Tuple("A", "1", 117, 1);
            case 123: return new Tuple("A", "2", 123, 1);
            case 131: return new Tuple("A", "3", 131, 1);
            case 139: return new Tuple("A", "4", 139, 1);

208(

            //D4-
            //A--5
            //E---
            // D string
            case 147:
                temp.add(0);
                temp.add(5);
                temp.add(10);
                return new Tuple("D" , temp, 147, 1);
            case 156: return new Tuple("D", "1", 156, 1);
            case 165: return new Tuple("D", "2", 165, 1);
            case 175: return new Tuple("D", "3", 175, 1);
            case 185: return new Tuple("D", "4", 185, 1);

            // G string
            case 196: return new Tuple("G", "0", 196, 1);
            case 208: return new Tuple("G", "1", 208, 1);
            case 220: return new Tuple("G", "2", 220, 1);
            case 233: return new Tuple("G", "3", 233, 1);

            //B string
            case 247: return new Tuple("B", "0", 247, 1);
            case 262: return new Tuple("B", "1", 262, 1);
            case 277: return new Tuple("B", "2", 277, 1);
            case 294: return new Tuple("B", "3", 294, 1);
            case 311: return new Tuple("B", "4", 311, 1);
            case 329:
                temp.add(0);
                temp.add(5);
                temp.add(9);
                temp.add(14);
                temp.add(19);
                return new Tuple("e", temp, 329, 1);
            case 349: return new Tuple("e", "1", 349, 1);
            case 370: return new Tuple("e", "2", 370, 1);
            case 392:
                temp.add(3);//high e
                temp.add(8);//B
                temp.add(12);//G
                temp.add(17);//D
                return new Tuple("e", temp, 392, 1);
            case 415: return new Tuple("e", "4", 415, 1);
            case 440: return new Tuple("e", "5", 440, 1);
            case 466: return new Tuple("e", "6", 466, 1);
            case 494: return new Tuple("e", "7", 494, 1);
            case 523: return new Tuple("e", "8", 523, 1);
            case 554: return new Tuple("e", "9", 554, 1);
            case 587: return new Tuple("e", "10", 587, 1);
            case 622: return new Tuple("e", "11", 622, 1);
            case 659: return new Tuple("e", "12", 659, 1);
            case 698: return new Tuple("e", "13", 698, 1);
            case 740: return new Tuple("e", "14", 740, 1);
            case 784: return new Tuple("e", "15", 784, 1);	// from here on no more duplicates
            case 831: return new Tuple("e", "16", 831, 1);
            case 880: return new Tuple("e", "17", 880, 1);
            case 932: return new Tuple("e", "18", 932, 1);
            case 988: return new Tuple("e", "19", 988, 1);
            case 1047: return new Tuple("e", "20", 1047, 1);
            //case 330: return "E";		<< this is interesting. did they increase the frequency by 1 on purpose? to avoid duplicates?
            default: return new Tuple("?", "?", -1, 1);
        }
    }

    public static void saveDataToFile(String name, float[] x) {
        try {
            String filename = "/sdcard/" + name + ".txt";
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(filename));

            for (double e : x) {
                outputWriter.write(e+"");
                outputWriter.newLine();
            }

            outputWriter.flush();
            outputWriter.close();

        } catch(IOException e) {

            e.printStackTrace();

        }
    }

    public static void saveDataToFile(String name, short[] x) {
        try {
            String filename = "/sdcard/" + name + ".txt";
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(filename));

            for (short e : x) {
                outputWriter.write(e+"");
                outputWriter.newLine();
            }

            outputWriter.flush();
            outputWriter.close();

        } catch(IOException e) {

            e.printStackTrace();

        }
    }*/

}