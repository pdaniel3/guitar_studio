package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




public class GuitarMain {

	public static void main(String[] args) {
		//example forms of a Tuple
		List<Integer> freqCollection = Arrays.asList(0,4,6);
		
		ArrayList<Integer> freqArray = new ArrayList<Integer>();
		freqArray.add(3);
		
		
		
		ArrayList<Integer> freqArray1 = new ArrayList<Integer>();
		freqArray1.addAll(freqCollection);
		Tuple note = new Tuple("e", freqArray, 102.3, 0);
		Tuple note1 = new Tuple("e", freqArray1, 130.7, 8);
		Tuple note2 = new Tuple("e", freqArray, 104.75, 3);
		Tuple note3 = new Tuple("B", freqArray, 104.75, 4);
		Tuple note4 = new Tuple("E", freqArray, 104.75, 3);
		Tuple note5 = new Tuple("E", freqArray, 104.75, 2);
		Tuple note6 = new Tuple("A", freqArray1, 104.75, 8);
		Tuple note7 = new Tuple("D", freqArray1, 104.75, 4);
		Tuple note8 = new Tuple("G", freqArray1, 104.75, 0);
		Tuple note9 = new Tuple("G", freqArray1, 104.75, 23);
		
		//initialize the tuple array
		Tuple[] tupleArray = new Tuple[10];
		tupleArray[0] = note;
		tupleArray[1] = note1;
		tupleArray[2] = note2;
		tupleArray[3] = note3;
		tupleArray[4] = note4;
		tupleArray[5] = note5;
		tupleArray[6] = note6;
		tupleArray[7] = note7;
		tupleArray[8] = note8;
		tupleArray[9] = note9;
		
		//initialize another tuple array
		Tuple[] tupleArray1 = new Tuple[0];
		
		printSection(tupleArray);
		//System.out.print("\n");
		printSection(tupleArray1);

	}


	public static void printSection(Tuple[] section){
		
		Tuple[] tupleArray = section;
		//create blank strings
		String estring = "";
		String Bstring = "";
		String Gstring = "";
		String Dstring = "";
		String Astring = "";
		String Estring = "";
		
		//Used for generating the rest of the dashes
		String restOf_e_dashes = "";
		String restOf_B_dashes = "";
		String restOf_G_dashes = "";
		String restOf_D_dashes = "";
		String restOf_A_dashes = "";
		String restOf_E_dashes = "";
	
		//prints 32 notes at a time (Standard tab section length)
		//print section
		for( Tuple i : tupleArray)
		{	
			if(i.get_note_name().charAt(0) == 'e'){

				estring = estring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'B'){
				Bstring = Bstring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'G'){
				Gstring = Gstring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'D'){
				Dstring = Dstring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'A'){
				Astring = Astring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
			else if(i.get_note_name().charAt(0) == 'E'){
				Estring = Estring + i.get_note_dashes()
				+ i.get_fret_number().toString();
			}
		}
		
		//generate the rest of the dashes needed for the section
		
		for(int j = 0; j < 32 - estring.length(); j++)
		{
				restOf_e_dashes = restOf_e_dashes + "-";
		}
		
		for(int j = 0; j < 32 - Bstring.length(); j++)
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
		
		//print the section
		System.out.println("e|" + estring + restOf_e_dashes + "|");
		System.out.println("B|" + Bstring + restOf_B_dashes + "|");
		System.out.println("G|" + Gstring + restOf_G_dashes + "|");
		System.out.println("D|" + Dstring + restOf_D_dashes + "|");
		System.out.println("A|" + Astring + restOf_A_dashes + "|");
		System.out.println("E|" + Estring + restOf_E_dashes + "|");
		System.out.print("\n");
	
	}



}
	


