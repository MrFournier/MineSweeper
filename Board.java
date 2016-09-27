import java.util.Random;
import java.util.Scanner;
import java.math.*;

public class Board{
  private int[][] mines;
  private char[][] boardgame;
  private int Line, Column;
  private int numLines;
  private int numColumns;
  private double numMines;
  private double mineFrequency = 0.16;
  Random random = new Random();
  Scanner keyboard = new Scanner(System.in);

  public Board(){
    System.out.println("How big would you like the board?");
    numLines = keyboard.nextInt();
    numColumns = numLines;
    mines = new int[numLines + 2][numColumns + 2];
    boardgame = new char[numLines + 2][numColumns + 2];
    numMines = calcNumMines();
    startMines();
    randomMines();
    fillTips();
    startBoard();
  }

  public double calcNumMines(){
    int temp;
    temp = numLines * numColumns;
    numMines = Math.floor(temp * mineFrequency);
    return numMines;
  }

  public boolean win(){
    int count = 0;
    for(int line = 1; line < (numLines + 1); line++){
      for(int column = 1; column < (numColumns + 1); column++){
        if(boardgame[line][column]=='_'){
          count++;
        }
      }
    }

    if(count == numMines){
      return true;
    } else{
      return false;
    }

  }

// not updated for var board size
  public void openNeighbors(){
    for(int i = -1; i < 2; i++){
      for(int j = -1; j < 2; j++){
        if( (mines[Line + i][Column + j] != -1) && (Line != 0 && Line != (numLines + 1) && Column != 0 && Column != (numColumns + 1)) ){
          boardgame[Line + i][Column + j] = Character.forDigit(mines[Line + i][Column + j], 10);
        }
      }
    }
  }

  public int getPosition(int Line, int Column){
    return mines[Line][Column];
  }

  public boolean setPosition(){

    do{
      System.out.print("\nLine: ");
      Line = keyboard.nextInt();
      System.out.print("Column: ");
      Column = keyboard.nextInt();

      if( (boardgame[Line][Column] != '_') && ((Line < (numLines + 1) && Line > 0) && (Column < (numColumns + 1) && Column > 0 ))){
        System.out.println("Field already shown");
      }

      if( Line < 1 || Line > numLines || Column < 1 || Column > numColumns){
        System.out.print("Choose a number on the board(between 1 and "+numLines);
      }

    }while((Line < 1 || Line > numLines || Column < 1 || Column > numColumns) || (boardgame[Line][Column] != '_'));
    
    if(getPosition(Line, Column) == -1){
      return true;
    } else{
        return false;
    }

  }

  public void show(){
    System.out.println("\n    Lines");
    for(int Line = numLines; Line > 0; Line--){
      System.out.print("    "+Line+" ");

      for(int Column = 1; Column < (numColumns + 1); Column++){
        System.out.print("    "+ boardgame[Line][Column]);
      }

      System.out.println();
    }

    int count = 1;
    String str = "\n          ";
    while(count < (numColumns + 1)){
      str = str + count + "    ";
      count++;
    }

    System.out.println(str);
    System.out.println("          Columns");

  }

  public void fillTips(){
    for(int line = 1; line < (numLines + 1); line++){
      for(int column = 1; column < (numColumns + 1); column++){

        for(int i = -1; i <= 1; i++){
          for(int j = -1; j <= 1; j++){
            if(mines[line][column] != -1){
              if(mines[line + i][column + j] == -1){
                mines[line][column]++;
              }
            }
          }
        }

      }
    }
  }

  public void showMines(){
    for(int i = 1; i < (numLines + 1); i++){
      for(int j = 1; j < (numColumns + 1); j++){
        if(mines[i][j] == -1){
          boardgame[i][j] = '*';
        }
      }
    }

    show();
  }

  public void startBoard(){
    for(int i = 1; i < mines.length; i++){
      for(int j = 1; j < mines.length; j++){
        boardgame[i][j] = '_';
      }
    }
  }

  public void startMines(){
    for(int i = 0; i < mines.length; i++){
      for(int j = 0; j < mines.length; j++){
        mines[i][j]=0;
      }
    }
  }

  public void randomMines(){
    boolean raffled;
    int Line, Column;
    for(int i = 0; i < numMines; i++){
      
      do{
        Line = random.nextInt(numLines) + 1;
        Column = random.nextInt(numColumns) + 1;

        if(mines[Line][Column] == -1){
          raffled = true;
        } else{
          raffled = false;
        }

      }while(raffled);

      mines[Line][Column] = -1;
    }
  }

}







