package test;

import java.util.ArrayList;

public class Tuple {

  //fields	
  private String note_name;
  private ArrayList < Integer > note_fret;
  private double note_frequency;
  private int note_spacing;
  static Integer last_fret = 0;

  //constructor: a pair of note names 0
  //and its corresponding tab number 
  public Tuple(String name, ArrayList < Integer > fret, double frequency, int spacing) {
    note_name = name;
    note_fret = fret;
    note_frequency = frequency;
    note_spacing = spacing;
  };

  //returns note name in the tuple
  public String get_note_name() {
    return note_name;
  }

  public void set_last_fret(Integer current_fret) {
    last_fret = current_fret;
  }

  //return tab number in the tuple
  public Integer get_fret_number() {
      Integer noteF = 0;
      // Integer last_fret = 0;
      Integer distance;
      //min = max number of frets
      Integer min = 14;
      //handle each string seperate 
      if (note_name.charAt(0) == 'e') {
        Integer this_fret = 0;

        for (int k = 0; k < note_fret.size(); k++) {

          this_fret = note_fret.get(k);

          distance = Math.abs(this_fret - last_fret);

          if (last_fret == 0)
            noteF = this_fret;
          else if (distance < min) {
            noteF = Math.min(this_fret, last_fret);
            min = distance;
          }
          set_last_fret(this_fret);
        }
      }
      if (note_name.charAt(0) == 'B') {
        Integer this_fret = 0;

        for (int k = 0; k < note_fret.size(); k++) {

          this_fret = note_fret.get(k);

          distance = Math.abs(this_fret - last_fret);

          if (last_fret == 0)
            noteF = this_fret;
          else if (distance < min) {
            noteF = Math.min(this_fret, last_fret);
            min = distance;
          }
          set_last_fret(this_fret);
        }
      }
      if (note_name.charAt(0) == 'G') {
        Integer this_fret = 0;

        for (int k = 0; k < note_fret.size(); k++) {

          this_fret = note_fret.get(k);

          distance = Math.abs(this_fret - last_fret);

          if (last_fret == 0)
            noteF = this_fret;
          else if (distance < min) {
            noteF = Math.min(this_fret, last_fret);
            min = distance;
          }
          set_last_fret(this_fret);
        }
      }
      if (note_name.charAt(0) == 'D') {
        Integer this_fret = 0;

        for (int k = 0; k < note_fret.size(); k++) {

          this_fret = note_fret.get(k);

          distance = Math.abs(this_fret - last_fret);

          if (last_fret == 0)
            noteF = this_fret;
          else if (distance < min) {
            noteF = Math.min(this_fret, last_fret);
            min = distance;
          }
          set_last_fret(this_fret);
        }
      }
      if (note_name.charAt(0) == 'A') {
        Integer this_fret = 0;

        for (int k = 0; k < note_fret.size(); k++) {

          this_fret = note_fret.get(k);

          distance = Math.abs(this_fret - last_fret);

          if (last_fret == 0)
            noteF = this_fret;
          else if (distance < min) {
            noteF = Math.min(this_fret, last_fret);
            min = distance;
          }
          set_last_fret(this_fret);
        }
      }
      if (note_name.charAt(0) == 'e') {
        Integer this_fret = 0;

        for (int k = 0; k < note_fret.size(); k++) {

          this_fret = note_fret.get(k);

          distance = Math.abs(this_fret - last_fret);

          if (last_fret == 0)
            noteF = this_fret;
          else if (distance < min) {
            noteF = Math.min(this_fret, last_fret);
            min = distance;
          }
          set_last_fret(this_fret);
        }
      }
      return noteF;
    }
    //return note frequency in tuple

  public double get_note_frequency() {
    return note_frequency;
  }

  //return note spacing in tuple
  public int get_note_spacing() {
    return note_spacing;
  }

  //return the spacing in dashes
  public String get_note_dashes() {
    String dashes = "";
    for (int i = 0; i < note_spacing; i++) {
      dashes = dashes + "-";
    }
    return dashes;
  }

  //updates note name if necessary
  public void update_note_name(String new_name) {
    note_name = new_name;
  }

  //updates tab number if necessary
  public void update_note_fret(ArrayList < Integer > new_fret) {
    note_fret = new_fret;
  }

  //update the note frequency
  public void update_note_frequency(double new_frequency) {
    note_frequency = new_frequency;
  }

  //update the note spacing
  public void update_note_spacing(int new_spacing) {
    note_spacing = new_spacing;
  }

  //prints tuple in form: (name, fret, frequency, spacing)
  public void print_tuple() {
    System.out.println(note_name + ", " + note_fret + ", " + note_frequency + ", " + note_spacing);
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
  public void print_frequency() {
    System.out.println(note_frequency);
  }

  //prints just the note spacing as an integer
  public void print_asInt_note_spacing() {
    System.out.println(note_spacing);
  }

  //prints just the note spacing as a string of dashes
  public void print_asString_note_spacing() {
    String dashes = "";
    for (int i = 0; i < note_spacing; i++) {
      dashes = dashes + "-";
    }
    System.out.println(dashes);
  }

  public String updateStrings(Tuple[] section) {

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
    for (Tuple i: tupleArray) {
      if (i.get_note_name().charAt(0) == 'e') {
        estring = estring + i.get_note_dashes() + i.get_fret_number().toString();
      } else if (i.get_note_name().charAt(0) == 'B') {
        Bstring = Bstring + i.get_note_dashes() + i.get_fret_number().toString();
      } else if (i.get_note_name().charAt(0) == 'G') {
        Gstring = Gstring + i.get_note_dashes() + i.get_fret_number().toString();
      } else if (i.get_note_name().charAt(0) == 'D') {
        Dstring = Dstring + i.get_note_dashes() + i.get_fret_number().toString();
      } else if (i.get_note_name().charAt(0) == 'A') {
        Astring = Astring + i.get_note_dashes() + i.get_fret_number().toString();
      } else if (i.get_note_name().charAt(0) == 'E') {
        Estring = Estring + i.get_note_dashes() + i.get_fret_number().toString();
      }
    } 

    //generate the rest of the dashes needed for the section

    for (int j = 0; j < 32 - estring.length(); j++) {
      restOf_e_dashes = restOf_e_dashes + "-";
    }

    for (int j = 0; j < 32 - Bstring.length(); j++) {
      restOf_B_dashes = restOf_B_dashes + "-";
    }

    for (int j = 0; j < 32 - Gstring.length(); j++) {
      restOf_G_dashes = restOf_G_dashes + "-";
    }

    for (int j = 0; j < 32 - Dstring.length(); j++) {
      restOf_D_dashes = restOf_D_dashes + "-";
    }

    for (int j = 0; j < 32 - Astring.length(); j++) {
      restOf_A_dashes = restOf_A_dashes + "-";
    }

    for (int j = 0; j < 32 - Estring.length(); j++) {
      restOf_E_dashes = restOf_E_dashes + "-";
    }

    //update the section
    estring = estring + restOf_e_dashes + "|");
    Bstring = Bstring + restOf_B_dashes + "|");
    Gstring = Gstring + restOf_G_dashes + "|");
    Dstring = Dstring + restOf_D_dashes + "|");
    Astring = Astring + restOf_A_dashes + "|");
    Estring = Estring + restOf_E_dashes + "|");

    String[] returnValue = {
      estring,
      Bstring,
      Gstring,
      Dstring,
      Astring,
      Estring
    }
    return returnValue;
  }
}
