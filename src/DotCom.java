import  java.util.*;
public class DotCom
{
    //DotCom's Instance Variables
    //ArrayList of Cell Locations
    //And the DotCom's Name
    private ArrayList<String> locationCells;
    private String name;

    //A setter method that updates the DotCom's Location(The Random Location is provided from the GameHelper
    //place DotCom() method.
    public void setLocationCells(ArrayList<String> loc) // since the dotCom location is a ArrayList of strings, we need a new arraylist of strings
    {
        locationCells = loc; // we use this to update the current DotCom's location
    }
    //Basic Setter Method
    public void setName(String n) //we made a DotCom object and we need to give it a name which is a string of character's. so we use String n for the paremeters
    {
        name=n; //we use our instance variable "name" which is a string and set it equal to the String n. this allows us to save "one" with it's new name.
    }

    public String checkYourself(String userInput) //
    {
        String result = "miss"; // declare a string of characters variable named result and give it the value "miss"
        int index = locationCells.indexOf(userInput); //declare an integer named index and find the integer value of the index of the position that was passed
        //into our locationCells object and equal it to index
        if(index>=0) // if index is greater than or equal to 0 then run this line (if it is less than 0 then the position doesn't exist thus making it miss)
        {
            locationCells.remove(index); // tell the locationCells object to remove the index position
            if (locationCells.isEmpty()) { //If the locationCell's object is Empty, then

                result = "kill"; // give result the value of "Kill"
                System.out.println("You took down " + name + "  : ( "); // print out a string and add the name of the locationCells object
            }
            else
            {
                result = "hit"; // give result the value of "hit"
            }
        }
        return result; //Return result to the class that used the checkYourself method
    }
}
