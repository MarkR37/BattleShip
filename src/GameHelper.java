import java.io.*;
import java.util.*;
public class GameHelper
{
    private static final String alphabet = "abcdefg";
    private int gridLength=7;
    private int gridSize=49;
    private int [] grid= new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.println(prompt + "  ");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0)
                return null;
        }
        catch(IOException e){
            System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }
    public ArrayList<String> placeDotCom(int comSize)
    {
        ArrayList<String> alphaCells = new ArrayList<String>();

        String temp = null;
        int [] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        comCount++;
        int incr = 1;
        if((comCount % 2)==1){
            incr = gridLength;
        }

        while( !success & attempts++ < 200)
        {
            location = (int) (Math.random() * gridSize);
            //System.out.print("try" + location);
            int x=0;
            success=true;
            while(success && x < comSize)
            {
                if(grid[location]==0)
                {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        success = false;
                    }
                    if (x > 0 && (location % gridLength == 0)) {
                        success = false;
                    }
                }
                else
                {
                    //System.out.print("used " + location);
                    success=false;
                }
            }
        }
        int x = 0;
        int row = 0;
        int column = 0;
        //System.out.println("\n");
        while(x<comSize)
        {
            grid[coords[x]]=1;
            row = (int) (coords[x] / gridLength);
            column = coords[x] % gridLength;
            temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
            //System.out.print("  coord " + x + " = " + alphaCells.get(x-1));
            //This Tells you the exact location of each Battleship(dotCom)
        }
        //System.out.println("\n");

        //make a function that prints out the grind as just the grid.

        return alphaCells;
    }

    //This Prints the 7x7 Grid
    public void printBoard(){
        //create a 7x7 grid array
        char[][] board = new char[7][7];
        //create water and set value to '~'
        char water = '~';
        //create y axis for user to see
        char y = 'a';
        System.out.println("\n  0 1 2 3 4 5 6");
        //create outer loop for the row
        for(int row = 0; row<board.length; row++){
            //increment Letters a->g
            System.out.print(y + " ");
            y++;
            //create inner loop for the col and print water
            for(int col = 0; col<board[row].length; col++){
                //position 0 0 set to water
                board[row][col] = water;
                //check position 00 or a0 with what the userGuess was.

                //print x y coordinates and add a space
                System.out.print(board[row][col] + " ");
            }
            //print a new line for each new row
            System.out.println();

        }
    }



}





