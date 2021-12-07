import java.util.*;
public class DotComBust
{
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame()
    {
        //Make Dot coms and set Locations
        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("WaffleKings.com");
        DotCom three = new DotCom();
        three.setName("Follow.com");
        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        //Made 3 DotCom Objects, gave them names and added them to the ArrayList


        //Give Instructions to the User
        System.out.println("Your goal is to take down three websites.");
        System.out.println("Pets.com, WaffleKings.com, Follow.com");
        System.out.println("Try to take them down with the least number of guesses");
        System.out.println("Note: This is a 7x7 grid so you need to use ");
        System.out.println("Letters: abcdefg ");
        System.out.println("Numbers: 0123456 ");
        System.out.println("Each DotCom has 3 spaces");
        System.out.println("Example: User Type a0 or b4 or g7 ");
        System.out.println("It will be a Hit or Miss. Good Luck!!!");


        //Repeat with Each DotCom in the list
        for(DotCom dotComToSet : dotComsList)
        {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            //Ask the helper for a DotCom Location (Array List of Strings)
            dotComToSet.setLocationCells(newLocation);
            //Calls the Setter method on the DotCom to give the location you got from the Helper.
        }


    }
    private void grid(){

        helper.gridBoard();

    }

    private void startPlaying()
    {



        while(!dotComsList.isEmpty())
        {
            //print out the gridBoard
            grid();

            // Get User input
            String userGuess = helper.getUserInput("\nEnter a guess");

            //Call our checkUserGuess method.
            checkUserGuess(userGuess);
        }
        finishGame();
        //Call our finishGame method.
    }















    private void checkUserGuess(String userGuess){
        //increment the # of guesses the user made.
        numOfGuesses++;
        //Assume it's a miss unless told otherwise.
        String result = "Miss";
        //Repeat with all DotComs in the list.
        for(int x = 0; x < dotComsList.size(); x++)
        {
            //Ask the DotCom to chek the User's Guess, we are looking for a hit or a kill.
            result = dotComsList.get(x).checkYourself(userGuess);

            if(result.equals("hit"))
            {
                break;
            }
            if(result.equals("kill"))
            {
                //Make Sure to Remove the DotCom since it was destroyed.
                dotComsList.remove(x);
                break;
            }

        }
        //Print the Result to the User.
        System.out.println(result);

    }

    private void finishGame()
    {
        //Print a Message telling the User how they did.
        System.out.println("All of the Dot Com websites have been taken down!");
        if(numOfGuesses <= 18){
            System.out.println("It only took you " + numOfGuesses + " guesses.");
        }
        else
        {
            System.out.println("Try for a shorter amount of guesses next time!" + numOfGuesses + " guesses.");
        }

    }

    public static void main (String[] args){
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}

