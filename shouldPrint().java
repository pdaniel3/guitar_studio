//implementation of shouldPrint()
public void shouldPrint(ArrayList<Tuple> tuples, Tuple current)
{
	Tuple current_tuple = current;
	ArrayList<Tuple> temp_array = tuples;
	
	temp_array.add(current_tuple);
	
	if((printSection(temp_array).length() - 18)/6 > 32)
	{
		printSection(tuples);
		tuples.clear(); 
		tuples.add(current_tuple);
	} else {
		tuples.add(current_tuple);
	}

}


/*in mainactivity:

AL<Tuple> tuples

shouldPrint? pseudo
{
	temp array = tuples;
	string temp = tuple.print;
	temp array.add(current tuple)
	if (print(temp array). size > 32)
	{
		acctually print tuples
		clear arrayList
		add current tuple
	}
	else
	{
		tuples.add(current tuple);
	}
}



//come back to this maybe?
void should I print?
	if total spacing of tuples + spacing of current tuple > 32
		print current section
		add current tuple to new array list
		clear old array list
	else
		add current tuple to array list
		
		
//implementation of shouldPrint?
public void shouldPrint(ArrayList<Tuple> tuples){
	int total_spacing = 0;
	for (int i = 0; i < tuples.size(); i++){
		total_spacing = tuples[i].size();
	}
}
		
public int getsize(AL tuples)
	int spacing
	for (tuple in tuples)
		spacing = tuple.getspacing() + 1
	
	*/