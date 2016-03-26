public static Tuple getTuple(int freq) {


	//Author: Marcus Kelly
	
	ArrayList<Integer> temp = new ArrayList<>();
	//5, 4, 5, 5
	switch (freq) {
		case 82:
			temp.add(0);
			return new Tuple("E", temp, 82, 1);
		case 87:
			temp.add(1);
			return new Tuple("E", temp, 87, 1);
		case 92: 
			temp.add(2);
			return new Tuple("E", temp, 92, 1);
		case 98: 
			temp.add(3);
			return new Tuple("E", temp, 98, 1);
		case 104: 
			temp.add(4);
			return new Tuple("E", "4", 104, 1);

		// A string
		case 110: 
			temp.add(0); //low E
			temp.add(5); // A
			return new Tuple("A", temp, 110, 1);
		case 117: 
			temp.add(1);
			temp.add(6);
			return new Tuple("A", temp, 117, 1);
		case 123: 
			temp.add(2);
			temp.add(7);
			return new Tuple("A", temp, 123, 1);
		case 131: 
			temp.add(3);
			temp.add(8);
			return new Tuple("A", temp, 131, 1);
		case 139: 
			temp.add(4);
			temp.add(9);
			return new Tuple("A", temp, 139, 1);



		// D string
		case 147:
			temp.add(0);
			temp.add(5);
			temp.add(9);
			return new Tuple("D" , temp, 147, 1);
		case 156: 
			temp.add(1);
			temp.add(6);
			temp.add(10);
			return new Tuple("D", temp, 156, 1);
		case 165: 
			temp.add(2);
			temp.add(7);
			temp.add(11);
			return new Tuple("D", temp, 165, 1);
		case 175: 
			temp.add(3);
			temp.add(8);
			temp.add(12);
			return new Tuple("D", temp, 175, 1);
		case 185: 
			temp.add(4);
			temp.add(9);
			temp.add(13);
			return new Tuple("D", "4", 185, 1);

		// G string
		case 196: 
			temp.add(0);
			temp.add(5);
			temp.add(9);
			temp.add(14);
			return new Tuple("G", temp, 196, 1);
		case 208: 
			temp.add(1);
			temp.add(6);
			temp.add(10);
			temp.add(15);
			return new Tuple("G", temp, 208, 1);
		case 220: 
			temp.add(2);
			temp.add(7);
			temp.add(11);
			temp.add(16);
			return new Tuple("G", temp, 220, 1);
		case 233: 
			temp.add(3);
			temp.add(8);
			temp.add(12);
			temp.add(17);
			return new Tuple("G", temp, 233, 1);

		//B string
		case 247: 
			temp.add(0);
			temp.add(5);
			temp.add(9);
			temp.add(14);
			temp.add(19);
			return new Tuple("B", temp, 247, 1);
		case 262: 
			temp.add(1);
			temp.add(6);
			temp.add(10);
			temp.add(15);
			temp.add(20);
			return new Tuple("B", temp, 262, 1);
		case 277: 
			temp.add(2);
			temp.add(7);
			temp.add(11);
			temp.add(16);
			return new Tuple("B", temp, 277, 1);
		case 294: 
			temp.add(3);
			temp.add(8);
			temp.add(12);
			temp.add(17);
			return new Tuple("B", temp, 294, 1);
		case 311: 
			temp.add(4);
			temp.add(9);
			temp.add(13);
			temp.add(18);
			return new Tuple("B", "4", 311, 1);
		
		//high e string
		case 329:
			temp.add(0);
			temp.add(5);
			temp.add(9);
			temp.add(14);
			temp.add(19);
			return new Tuple("e", temp, 329, 1);
		case 349: 
			temp.add(1);
			temp.add(6);
			temp.add(10);
			temp.add(15);
			temp.add(20);
			return new Tuple("e", temp, 349, 1);
		case 370: 
			temp.add(2);
			temp.add(7);
			temp.add(11);
			temp.add(16);
			return new Tuple("e", temp, 370, 1);
		case 392: 
			temp.add(3);//high e
			temp.add(8);//B
			temp.add(12);//G
			temp.add(17);//D
			return new Tuple("e", temp, 392, 1);
		case 415: 
			temp.add(4);
			temp.add(9);
			temp.add(13);
			temp.add(18);
			return new Tuple("e", temp, 415, 1);
		case 440: 
			temp.add(5);
			temp.add(10);
			temp.add(14);
			temp.add(19);
			return new Tuple("e", temp, 440, 1);
		case 466: 
			temp.add(6);
			temp.add(11);
			temp.add(15);
			temp.add(20);
			return new Tuple("e", temp, 466, 1);
		case 494: 
			temp.add(7);
			temp.add(12);
			temp.add(16);
			return new Tuple("e", temp, 494, 1);
		case 523: 
			temp.add(8);
			temp.add(13);
			temp.add(17);
			return new Tuple("e", temp, 523, 1);
		case 554: 
			temp.add(9);
			temp.add(14);
			temp.add(18);
			return new Tuple("e", temp, 554, 1);
		case 587: 
			temp.add(10);
			temp.add(15);
			temp.add(19);
			return new Tuple("e", temp, 587, 1);
		case 622: 
			temp.add(11);
			temp.add(16);
			temp.add(20);
			return new Tuple("e", temp, 622, 1);
		case 659: 
			temp.add(12);
			temp.add(17);
			return new Tuple("e", tmep, 659, 1);
		case 698: 
			temp.add(13);
			temp.add(18);
			return new Tuple("e", tmep, 698, 1);
		case 740: 
			temp.add(14);
			temp.add(19);
			return new Tuple("e", temp, 740, 1);
		case 784: 
			temp.add(15);
			temp.add(20);
			return new Tuple("e", temp, 784, 1);	// from here on no more duplicates
		case 831: 
			temp.add(16);
			return new Tuple("e", temp, 831, 1);
		case 880: 
			temp.add(17);
			return new Tuple("e", temp, 880, 1);
		case 932: 
			temp.add(18);
			return new Tuple("e", temp, 932, 1);
		case 988: 
			temp.add(19);
			return new Tuple("e", temp, 988, 1);
		case 1047: 
			temp.add(20);
			return new Tuple("e", temp, 1047, 1);
		//case 330: return "E";		<< this is interesting. did they increase the frequency by 1 on purpose? to avoid duplicates?*/
		default: return new Tuple("?", temp, -1, 1);
	}
}