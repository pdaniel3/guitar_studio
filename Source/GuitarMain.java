

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





public class GuitarMain {
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
	

	public static void main(String[] args) {
		//example forms of a Tuple

		List<Integer> freqCollection = Arrays.asList(6);
		
		ArrayList<Integer> freqArray2 = new ArrayList<Integer>();
		freqArray2.add(10);
		
		ArrayList<Integer> freqArray = new ArrayList<Integer>();
		freqArray.add(3);
		freqArray.add(5);
		freqArray.add(8);
		freqArray.add(9);
		freqArray.add(10);
		
		
		
		ArrayList<Integer> freqArray1 = new ArrayList<Integer>();
		freqArray1.addAll(freqCollection);
		
		ArrayList<Integer> freqArray3 = new ArrayList<Integer>();
		freqArray3.add(2);
		
		//Tuple note = new Tuple("e", freqArray1, 102.3, 7, false);
		//ideal example
		
		Tuple note13 = new Tuple("e", freqArray1, 130.7, 1, false);
		Tuple note14 = new Tuple("e", freqArray, 104.75, 1, false);
		Tuple note15 = new Tuple("E", freqArray2, 104.75, 2, false);
		Tuple note16 = new Tuple("Bm", freqArray, 100.75, 4, true);
		Tuple note17 = new Tuple("e", freqArray, 102.3, 7, false);
		Tuple note18 = new Tuple("F", freqArray1, 102.3, 3, true);
		
		
		//bunch of 10's
		
		Tuple note7 = new Tuple("E", freqArray2, 130.7, 1, false);
		Tuple note8 = new Tuple("E", freqArray1, 104.75, 1, false);
		Tuple note9 = new Tuple("E", freqArray2, 104.75, 2, false);
		Tuple note10 = new Tuple("A", freqArray1, 100.75, 4, false);
		Tuple note11 = new Tuple("E", freqArray2, 102.3, 8, false);
		Tuple note12 = new Tuple("A", freqArray2, 102.3, 4, false);
		Tuple note19 = new Tuple("C ", freqArray2, 102.3, 2, true);

		//wont break if total characters is 32 or less
		
		//bunch of chords
		Tuple note1 = new Tuple("E ", freqArray2, 130.7, 1, true);
		Tuple note2 = new Tuple("Em", freqArray2, 104.75, 1, true);
		Tuple note3 = new Tuple("D ", freqArray2, 104.75, 2, true);
		Tuple note4 = new Tuple("N ", freqArray2, 100.75, 4, true);
		Tuple note5 = new Tuple("Bm", freqArray2, 102.3, 8, true);
		Tuple note6 = new Tuple("B ", freqArray2, 102.3, 4, true);
		
		//initialize the tuple array
		Tuple[] tupleArray = new Tuple[6];
		
		//10's array
		Tuple[] tupleArray2  = new Tuple[7];
		
		Tuple[] tupleArray3  = new Tuple[6];
		
		tupleArray3[0] = note13;
		tupleArray3[1] = note14;
		tupleArray3[2] = note15;
		tupleArray3[3] = note16;
		tupleArray3[4] = note17;
		tupleArray3[5] = note18;
		
		tupleArray2[0] = note7;
		tupleArray2[1] = note8;
		tupleArray2[2] = note9;
		tupleArray2[3] = note10;
		tupleArray2[4] = note11;
		tupleArray2[5] = note12;
		tupleArray2[6] = note19;

		
		//tupleArray[0] = note;
		tupleArray[0] = note1;
		tupleArray[1] = note2;
		tupleArray[2] = note3;
		tupleArray[3] = note4;
		tupleArray[4] = note5;
		tupleArray[5] = note6;
		/*
		tupleArray[4] = note4;
		tupleArray[5] = note5;
		tupleArray[6] = note6;
		tupleArray[7] = note7;
		tupleArray[8] = note8;
		*/
		//tupleArray[9] = note9;
		
		//initialize another tuple array
		Tuple[] tupleArray1 = new Tuple[0];

		
		runSection(tupleArray);
		
		printSection(tupleArray);
		//System.out.print("\n");
		runSection(tupleArray1);
		printSection(tupleArray1);
		
		runSection(tupleArray2);
		printSection(tupleArray2);
		
		runSection(tupleArray3);
		printSection(tupleArray3);
		
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


	public static void runSection(Tuple[] section){
		Tuple[] tupleArray = section;
		//create blank strings

			
		//prints 32 notes at a time (Standard tab section length)
		//print section
		
		for(int j =0; j < tupleArray.length; j++ ){
			
			Tuple i = tupleArray[j];
			if(i.get_is_chord() == false){

			if(i.get_note_name().charAt(0) == 'e'){

				estring = estring +  i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'B'){
				Bstring = Bstring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'G'){
				Gstring = Gstring +  i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'D'){
				Dstring = Dstring +  i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'A'){
				Astring = Astring +  i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'E'){
				Estring = Estring +  i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			}
		}
		estring = "";
		Bstring = "";
		Gstring = "";
		Dstring = "";
		Astring = "";
		Estring = "";
		ChrdString = "";
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


	


