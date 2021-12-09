import java.util.*;

public class DotComBust {
    public static final char WATER = '~';
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;
    private char[][] board = new char[7][7];

    public void initBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = WATER;
            }
        }
    }

    public void printBoard() {
        //create y axis for user to see
        char y = 'a';
        System.out.println("\n  0 1 2 3 4 5 6");
        //create outer loop for the row
        for (int row = 0; row < board.length; row++) {
            //increment Letters a->g
            System.out.print(y + " ");
            y++;
            //create inner loop for the col and print water
            for (int col = 0; col < board[row].length; col++) {
                //print x y coordinates and add a space
                System.out.print(board[row][col] + " ");
            }
            //print a new line for each new row
            System.out.println();
        }
    }

    private void setUpGame() {
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
        initBoard();
        printBoard();

        //Repeat with Each DotCom in the list
        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            //Ask the helper for a DotCom Location (Array List of Strings)
            dotComToSet.setLocationCells(newLocation);
            System.out.println(newLocation);
            //Calls the Setter method on the DotCom to give the location you got from the Helper.
        }
    }
    
    public void updateBoard(String userGuess, String result) {
        if(userGuess.length() > 2) return;
        int x = userGuess.charAt(0) - 'a'; //returns 'a' from a1
        int y = userGuess.charAt(1) - '0'; // returns '1' from a1
        if (result.equals("hit")) {
            board[x][y] = 'X';
        } else if (result.equals("kill")) {
            board[x][y] = 'X';
        } else if (result.equals("miss")) {
            board[x][y] = '0';
        }
    }

    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            // Get User input
            String userGuess = helper.getUserInput("\nEnter a guess");
            //Call our checkUserGuess method.
            String result = checkUserGuess(userGuess);
            //Updates the Board based on the user's guess, if it hits - it's an X, if it misses - it's a 0.
            updateBoard(userGuess, result);
            printBoard();
        }
        finishGame();
        //Call our finishGame method.
    }


    private String checkUserGuess(String userGuess) {
        //increment the # of guesses the user made.
        numOfGuesses++;
        //Assume it's a miss unless told otherwise.
        String result = "miss";
        //Repeat with all DotComs in the list.
        for (int x = 0; x < dotComsList.size(); x++) {
            //Ask the DotCom to check the User's Guess, we are looking for a hit or a kill.
            result = dotComsList.get(x).checkYourself(userGuess);

            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                //Make Sure to Remove the DotCom since it was destroyed.
                dotComsList.remove(x);
                break;
            }

        }
        //Print the Result to the User.
        System.out.println(result);
        return result;

    }

    private void finishGame() {
        //Print a Message telling the User how they did.
        System.out.println("All of the Dot Com websites have been taken down!");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
        } else {
            System.out.println("Try for a shorter amount of guesses next time!" + numOfGuesses + " guesses.");
        }

    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}

