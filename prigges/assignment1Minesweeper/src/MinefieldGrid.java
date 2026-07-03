public class MinefieldGrid {
    
    private final int myRows;
    private final int myCols;
    private final char[][] myGrid;

    public MinefieldGrid(int theRows, int theCols){
        myRows = theRows + 2;
        myCols = theCols + 2;
        myGrid = new char[myRows][myCols];
    }

    public int getRows(){
        return myRows;
    }
    
    public int getCols(){
        return myCols;
    }

    public void fillRows(String theData, int theRow){
        for(int col = 1; col < myCols - 1; col++){
            myGrid[theRow][col] = theData.charAt(col - 1);
        }
    }

    public void makeHints(){
        for(int row = 1; row < myRows - 1; row++){
            for(int col = 1; col < myCols - 1; col++){
                if(myGrid[row][col] != '*'){
                    int hint = 0;

                    // for loop version
                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            if(myGrid[row - i][col - j] == '*'){
                                hint++;
                            }
                        }
                    }

                    // if statement version
                    //if(myGrid[row - 1][col - 1] == '*'){hint++;}
                    //if(myGrid[row - 1][col] == '*'){hint++;}
                    //if(myGrid[row - 1][col + 1] == '*'){hint++;}
                    //if(myGrid[row][col - 1] == '*'){hint++;}
                    //if(myGrid[row][col + 1] == '*'){hint++;}
                    //if(myGrid[row + 1][col - 1] == '*'){hint++;}
                    //if(myGrid[row + 1][col] == '*'){hint++;}
                    //if(myGrid[row + 1][col + 1] == '*'){hint++;}
                    myGrid[row][col] = Character.forDigit(hint, 10);
                }
            }
        }
    }

    @Override
    public String toString(){
        
        StringBuilder collector = new StringBuilder();

        // collects everything from grid into stringbuilder
        for(int rows = 1; rows < myRows - 1; rows++){
            for(int cols = 1; cols < myCols - 1; cols++){
                collector.append(myGrid[rows][cols]);
            }
            collector.append("\n");
        }

        return collector.toString();
    }


}
