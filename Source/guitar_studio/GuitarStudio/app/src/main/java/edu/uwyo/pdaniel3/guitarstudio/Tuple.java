package edu.uwyo.pdaniel3.guitarstudio;

import java.util.ArrayList;

public class Tuple {

    // fields
    private String note_name;
    private ArrayList<Integer> note_fret;
    private double note_frequency;
    private int note_spacing;
    static Integer last_fret = 0;
    private int iteration = 0;
    private boolean is_chord = false;
    static int b_num_notes = 0;

    // constructor: a pair of note names 0
    // and its corresponding tab number
    public Tuple(String name, ArrayList<Integer> fret, double frequency, int spacing, boolean chord) {
        note_name = name;
        note_fret = fret;
        note_frequency = frequency;
        note_spacing = spacing;
        is_chord = chord;
    };

    // returns note name in the tuple
    public String get_note_name() {
        return note_name;
    }

    public boolean get_is_chord() {
        return is_chord;
    }

    public void set_last_fret(Integer current_fret) {

        last_fret = current_fret;

    }

    // return the New String
    public String num_to_string(Integer k) {
        // String new_note_name = "E";

        if (note_name.charAt(0) == 'e') {
            if (iteration == 0) {
                update_note_name("e");
            }
            else if (iteration == 1) {
                update_note_name("B");
            } else if (iteration == 2) {
                update_note_name("G");
            } else if (iteration == 3) {
                update_note_name("D");
            } else if (iteration == 4) {
                update_note_name("A");
            } else if (iteration == 5) {
                update_note_name("E");
            }
        }

        // System.out.print("You chose the " + iteration + "slot");
        return note_name;
    }

    // return tab number in the tuple
    public Integer get_fret_number() {
        Integer noteF = 0;
        Integer distance;
        // min = max number of frets
        Integer min = 20;
        // handle each string seperate
        if (is_chord == false) {
            if (note_name.charAt(0) == 'e') {
                Integer this_fret = 0;

                for (int k = 0; k < note_fret.size(); k++) {

                    this_fret = note_fret.get(k);

                    distance = Math.abs(this_fret - last_fret);
                    // iteration = k;
                    if (last_fret == 0)
                        noteF = this_fret;
                    else if (distance <= min) {
                        noteF = this_fret;
                        min = distance;
                        iteration = k;
                    }

                }
                set_last_fret(noteF);

            }
            else if (note_name.charAt(0) == 'B') {
                Integer this_fret = 0;

                for (int k = 0; k < note_fret.size(); k++) {

                    this_fret = note_fret.get(k);

                    distance = Math.abs(this_fret - last_fret);
                    iteration = k;
                    if (last_fret == 0)
                        noteF = this_fret;
                    else if (distance <= min) {
                        noteF = this_fret;
                        min = distance;
                        iteration = k;
                    }

                }
                set_last_fret(noteF);
            }
            else if (note_name.charAt(0) == 'G') {
                Integer this_fret = 0;

                for (int k = 0; k < note_fret.size(); k++) {

                    this_fret = note_fret.get(k);

                    distance = Math.abs(this_fret - last_fret);
                    iteration = k;
                    if (last_fret == 0)
                        noteF = this_fret;
                    else if (distance <= min) {
                        noteF = this_fret;
                        min = distance;
                        iteration = k;
                    }

                }
                set_last_fret(noteF);
            }
            else if (note_name.charAt(0) == 'D') {
                Integer this_fret = 0;

                for (int k = 0; k < note_fret.size(); k++) {

                    this_fret = note_fret.get(k);

                    distance = Math.abs(this_fret - last_fret);
                    iteration = k;
                    if (last_fret == 0)
                        noteF = this_fret;
                    else if (distance <= min) {
                        noteF = this_fret;
                        min = distance;
                        iteration = k;
                    }

                }
                set_last_fret(noteF);
            }
            else if (note_name.charAt(0) == 'A') {
                Integer this_fret = 0;

                for (int k = 0; k < note_fret.size(); k++) {

                    this_fret = note_fret.get(k);

                    distance = Math.abs(this_fret - last_fret);
                    iteration = k;
                    if (last_fret == 0)
                        noteF = this_fret;
                    else if (distance <= min) {
                        noteF = this_fret;
                        min = distance;
                        iteration = k;
                    }

                }
                set_last_fret(noteF);
            }
            else if (note_name.charAt(0) == 'E') {
                Integer this_fret = 0;

                for (int k = 0; k < note_fret.size(); k++) {

                    this_fret = note_fret.get(k);

                    distance = Math.abs(this_fret - last_fret);
                    iteration = k;
                    if (last_fret == 0)
                        noteF = this_fret;
                    else if (distance <= min) {
                        noteF = this_fret;
                        min = distance;
                        iteration = k;
                    }

                }
                set_last_fret(noteF);
            }
        }

        String checkedString = num_to_string(iteration);
        //System.out.println("Checked String is: " + checkedString);
        return noteF;
    }
    // return note frequency in tuple

    public double get_note_frequency() {
        return note_frequency;
    }

    // return note spacing in tuple
    public int get_note_spacing() {
        return note_spacing;
    }

    // return the spacing in dashes

    public String get_note_dashes() {
        String dashes = "-";
        for (int i = 0; i < note_spacing-1; i++) {
            dashes = dashes + "-";
        }
        return dashes;
    }

    // updates note name if necessary
    public void update_note_name(String new_name) {
        note_name = new_name;
    }

    // updates tab number if necessary
    public void update_note_fret(ArrayList<Integer> new_fret) {
        note_fret = new_fret;
    }

    // update the note frequency
    public void update_note_frequency(double new_frequency) {
        note_frequency = new_frequency;
    }

    // update the note spacing
    public void update_note_spacing(int new_spacing) {
        note_spacing = new_spacing;
    }

    // prints tuple in form: (name, fret, frequency, spacing)
    public void print_tuple() {
        System.out.println(note_name + ", " + note_fret + ", " + note_frequency + ", " + note_spacing);
    }

    // prints just the note name
    public void print_name() {
        System.out.println(note_name);
    }

    // prints just the tab number
    public void print_fret() {
        System.out.println(note_fret);
    }

    // prints just the note frequency
    public void print_frequency() {
        System.out.println(note_frequency);
    }

    // prints just the note spacing as an integer
    public void print_asInt_note_spacing() {
        System.out.println(note_spacing);
    }

    // prints just the note spacing as a string of dashes
    public void print_asString_note_spacing() {
        String dashes = "-";
        for (int i = 0; i < note_spacing; i++) {
            dashes = dashes + "-";
        }
        System.out.println(dashes);
    }
}