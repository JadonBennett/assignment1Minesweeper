// Author: Sean Prigge
// IDE Used: VS Code

import java.util.Scanner;

// Estimated time to complete project 6 hours, but mostly because I had to redo everything in 
// methods after doing it all at first in main, so about 3 hours of coding, 2 hours of redoing
// it all as a class, and 1 hour of bug testing. 
public class Main {
    public static void main(String[] args){
         // declares the scanner
        final Scanner myInput = new Scanner(System.in);

        // initializes the rows and cols for first grid. considering moving
        // it to inside the while loop and using a .hasNext() instead
        int myRows = myInput.nextInt();
        int myCols = myInput.nextInt();
        // Starts the fields counter
        int myFields = 0;

        while(myRows > 0 && myCols > 0){
            myFields++;
            MinefieldGrid minefield = new MinefieldGrid(myRows, myCols);

            for(int row = 1; row < myRows + 1; row++){
                minefield.fillRows(myInput.next(), row);
            }

            minefield.makeHints();

            final StringBuilder collector = new StringBuilder();
            collector.append("Field #").append(myFields).append(":\n");
            collector.append(minefield.toString());

            System.out.println(collector.toString());

            myRows = myInput.nextInt();
            myCols = myInput.nextInt();
        }
        myInput.close();
    }
}
