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
    public void setLocationCells(ArrayList<String> loc)
    {
        locationCells = loc;
    }
    //Basic Setter Method
    public void setName(String n)
    {
        name=n;
    }

    public String checkYourself(String userInput)
    {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if(index>=0){
            locationCells.remove(index);
            if (locationCells.isEmpty()) {

                result = "kill";
                System.out.println("You sunk " + name + "  : ( ");
            }
            else
            {
                result = "hit";
            }
        }
        return result;
    }
}
