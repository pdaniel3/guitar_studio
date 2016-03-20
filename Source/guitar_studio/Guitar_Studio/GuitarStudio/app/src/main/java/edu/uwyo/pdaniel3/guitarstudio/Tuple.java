package edu.uwyo.pdaniel3.guitarstudio;

public class Tuple {

    //fields
    private String note_name;
    private String note_fret;
    private double note_frequency;
    private int note_spacing;

    //constructor: a pair of note names
    //and its corresponding tab number
    public Tuple(String name, String fret, double frequency, int spacing) {
        note_name = name;
        note_fret = fret;
        note_frequency = frequency;
        note_spacing = spacing;
    };

    //returns note name in the tuple
    public String get_note_name() {
        return note_name;
    }

    //return tab number in the tuple
    public String get_fret_number() {
        return note_fret;
    }

    //return note frequency in tuple
    public double get_note_frequency(){
        return note_frequency;
    }

    //return note spacing in tuple
    public int get_note_spacing(){
        return note_spacing;
    }

    //return the spacing in dashes
    public String get_note_dashes(){
        String dashes = "";
        for(int i = 0; i < note_spacing; i++)
        {
            dashes = dashes + "-";
        }
        return dashes;
    }

    //updates note name if necessary
    public void update_note_name(String new_name) {
        note_name = new_name;
    }

    //updates tab number if necessary
    public void update_note_fret(String new_fret) {
        note_fret = new_fret;
    }

    //update the note frequency
    public void update_note_frequency(double new_frequency){
        note_frequency = new_frequency;
    }

    //update the note spacing
    public void update_note_spacing(int new_spacing){
        note_spacing = new_spacing;
    }

    //prints tuple in form: (name, fret, frequency, spacing)
    public void print_tuple() {
        System.out.println(note_name + ", " + note_fret + ", " + note_frequency
                + ", " + note_spacing);
    }

    //prints just the note name
    public void print_name() {
        System.out.println(note_name);
    }

    //prints just the tab number
    public void print_fret() {
        System.out.println(note_fret);
    }

    //prints just the note frequency
    public void print_frequency(){
        System.out.println(note_frequency);
    }

    //prints just the note spacing as an integer
    public void print_asInt_note_spacing(){
        System.out.println(note_spacing);
    }

    //prints just the note spacing as a string of dashes
    public void print_asString_note_spacing(){
        String dashes = "";
        for(int i = 0; i < note_spacing; i++)
        {
            dashes = dashes + "-";
        }
        System.out.println(dashes);
    }
}