import java.util.Random;
/**
 * Describe your basic strategy here.
 * @author <johnpalsberg>
 *
 */

public class MyAgent extends Agent {
  /**
   * A random number generator to randomly decide where to place a token.
   */
  private Random random;
  private int x;
  private Connect4Game ACopy;
  private int i; 
  private int j;
  private int k;

  /**
   * Constructs a new agent, giving it the game and telling it whether it is Red or Yellow.
   *
   * @param game The game the agent will be playing.
   * @param iAmRed True if the agent is Red, False if the agent is Yellow.
   */
  public MyAgent(Connect4Game game, boolean iAmRed) {
    super(game, iAmRed);
    random = new Random();
    ACopy = new Connect4Game(myGame);
    k = 0;
  }

  /**
   * The move method is run every time it is this agent's turn in the game. You may assume that
   * when move() is called, the game has at least one open slot for a token, and the game has not
   * already been won.
   *
   * <p>By the end of the move method, the agent should have placed one token into the game at some
   * point.</p>
   *
   * <p>After the move() method is called, the game engine will check to make sure the move was
   * valid. A move might be invalid if:
   * - No token was place into the game.
   * - More than one token was placed into the game.
   * - A previous token was removed from the game.
   * - The color of a previous token was changed.
   * - There are empty spaces below where the token was placed.</p>
   *
   * <p>If an invalid move is made, the game engine will announce it and the game will be ended.</p>
   *
   */
  public void move() {
    System.out.println(this.doubleTheyCanWin());
    this.initialChecks(); // changes i and j to their correct values
    if (this.iCanWin() != -1) {
      System.out.println("I can win");
      this.moveOnColumn(this.iCanWin());
    } else if (this.theyCanWin() != -1) {
      System.out.println("They Can Win" + theyCanWin());
      this.moveOnColumn(this.theyCanWin());
    } else if (this.doubleTheyCanWin() != -2) {
      System.out.println("i" +i);
 //     if (this.forkAttack() == -1 || this.forkAttack() == this.doubleTheyCanWin()) 
    //    this.moveOnColumn(i);
      if ((this.forkDefense() == -1 || this.forkDefense() == this.doubleTheyCanWin()))
        this.moveOnColumn(i);
      else  {
  //      if (forkAttack() != -1)
 //         this.moveOnColumn(forkAttack());
  //      else 
          this.moveOnColumn(forkDefense()); 
      } 

    }
    else if (this.forkAttack() != -1) {
      System.out.println("forkAttack" +forkAttack());
      this.moveOnColumn(forkAttack());
    }

    else if (this.forkDefense() != -1) {
      System.out.println("forkDefense"+this.forkDefense());
      this.moveOnColumn(forkDefense());
    }  else if (this.OpAttack() != -2) {

      this.moveOnColumn(j);
    }
    else if (this.doubleICanWinCheck() != -1) {
      System.out.println("doubleICanWin"+this.doubleICanWinCheck());
      this.moveOnColumn(this.doubleICanWinCheck());
    }
    else {
      System.out.println("Moving randomly");

      System.out.println("Random");
      this.moveOnColumn(this.randomMove());
    }

  }



  /**
   * Drops a token into a particular column so that it will fall to the bottom of the column.
   * If the column is already full, nothing will change.
   *
   * @param columnNumber The column into which to drop the token.
   */

  public void moveOnColumn(int columnNumber) {
    // Find the top empty slot in the column
    // If the column is full, lowestEmptySlot will be -1
    int lowestEmptySlotIndex = getLowestEmptyIndex(myGame.getColumn(columnNumber));
    //System.out.println("Moving on column" + columnNumber);
    // if the column is not full
    if (lowestEmptySlotIndex > -1) {
      // get the slot in this column at this index
      Connect4Slot lowestEmptySlot = myGame.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);
      // If the current agent is the Red player...
      if (iAmRed) {
        lowestEmptySlot.addRed(); // Place a red token into the empty slot
      } else {
        lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
      }
    }
  }

  /**
   * places the your color on the desired column and on the desired game
   */
  public void moveOnColumn(int columnNumber, Connect4Game game) {
    // Find the top empty slot in the column
    // If the column is full, lowestEmptySlot will be -1
    int lowestEmptySlotIndex = getLowestEmptyIndex(game.getColumn(columnNumber));
    // if the column is not full
    if (lowestEmptySlotIndex > -1) {
      // get the slot in this column at this index
      Connect4Slot lowestEmptySlot = game.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);
      // If the current agent is the Red player...
      if (iAmRed) {
        lowestEmptySlot.addRed(); // Place a red token into the empty slot
      } else {
        lowestEmptySlot.addYellow(); // Place a yellow token into the empty slot
      }
    }
  }
  // places the opponent's disk 

  public void moveOnOppColumn(int columnNumber, Connect4Game game) {
    // Find the top empty slot in the column
    // If the column is full, lowestEmptySlot will be -1
    int lowestEmptySlotIndex = getLowestEmptyIndex(game.getColumn(columnNumber));
    // if the column is not full
    if (lowestEmptySlotIndex > -1) {
      // get the slot in this column at this index
      Connect4Slot lowestEmptySlot = game.getColumn(columnNumber).getSlot(lowestEmptySlotIndex);
      // If the current agent is the Red player...
      if (iAmRed) {
        lowestEmptySlot.addYellow(); // Place a red token into the empty slot
      } else {
        lowestEmptySlot.addRed(); // Place a yellow token into the empty slot
      }
    }
  }

  /**
   * Returns the index of the top empty slot in a particular column.
   *
   * @param column The column to check.
   * @return
   *      the index of the top empty slot in a particular column;
   *      -1 if the column is already full.
   */
  public int getLowestEmptyIndex(Connect4Column column) {
    int lowestEmptySlot = -1;
    for  (int i = 0; i < column.getRowCount(); i++) {
      if (!column.getSlot(i).getIsFilled()) {
        lowestEmptySlot = i;
      }
    }
    return lowestEmptySlot;
  }

  /**
   * Returns a random valid move. If your agent doesn't know what to do, making a random move
   * can allow the game to go on anyway.
   *
   * @return a random valid move.
   */
  public int randomMove() {
    int i = random.nextInt(myGame.getColumnCount());
    while (getLowestEmptyIndex(myGame.getColumn(i)) == -1) {
      i = random.nextInt(myGame.getColumnCount());
    }
    return i;
  }

  /**
   * Returns the column that would allow the agent to win.
   *
   * <p>You might want your agent to check to see if it has a winning move available to it so that
   * it can go ahead and make that move. Implement this method to return what column would
   * allow the agent to win.</p>
   *
   * @return the column that would allow the agent to win.
   */
  public int iCanWin() {

    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame); //make a copy
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x))!= -1) {
        this.moveOnColumn(x, copy);     // place on that column
      }
      if (copy.gameWon() != 'N') {

        //check for a win
        return x; 
      }
      // return the column
    }                              //repeat
    return -1;
  }

  public int iCanWin(Connect4Game a) {
    int c;
    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(a); //make a copy
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x))!= -1) {
        this.moveOnColumn(x, copy);     // place on that column
      }
      if (iAmRed == true) {
        c = 'R';
      } else {
        c ='Y';
      }
      if (copy.gameWon() == c) {

        //check for a win
        return x; 
      }
      // return the column
    }                              //repeat
    return -1;
  }


  /**
   * Returns the column that would allow the opponent to win.
   *
   * <p>You might want your agent to check to see if the opponent would have any winning moves
   * available so your agent can block them. Implement this method to return what column should
   * be blocked to prevent the opponent from winning.</p>
   *
   * @return the column that would allow the opponent to win.
   */
  public int theyCanWin() {
    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame);
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x)) != -1) {
        this.moveOnOppColumn(x, copy);
      }
      if (copy.gameWon() != 'N') {
        return x;
      }
    }
    return -1;

  }

  public int theyCanWin(Connect4Game newGame) {
    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(newGame);
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x)) != -1) {
        this.moveOnOppColumn(x, copy);
      }
      if (copy.gameWon() != 'N') {
        return x;
      }
    }
    return -1;

  }
  /**
   * Returns the name of this agent.
   *
   * @return the agent's name
   */
  public String getName() {
    return "My Agent";
  }
  /* 
  public int OPMove() {

    int s = 1;
    int i = random.nextInt(myGame.getColumnCount());
    while (getLowestEmptyIndex(myGame.getColumn(i)) == -1 && s > 0) {
      i = random.nextInt(myGame.getColumnCount());
      if (i == x) {
        s = 0;
      }

    }
    x = i;
    return i;
  } 

  /*
   * checks to see whether an indicated 
   * line caused a fork
   */


  public int forkAttack() {
    int x = -1;
    char c = ' ';
    if (iAmRed)
      c = 'R';
    else c = 'Y';
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame);
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x)) != -1) {
        this.moveOnColumn(x, copy); 
      }
      else return -1; 

      if (this.iCanWin(copy) != -1) {
        //      int a = this.iCanWin(copy);
        if (getLowestEmptyIndex(copy.getColumn(x)) != -1)
          this.moveOnOppColumn(this.iCanWin(copy),copy);
        int b = -1;
        while (b < 6) {
          Connect4Game copyA = new Connect4Game(copy);
          b++;
          if (getLowestEmptyIndex(copyA.getColumn(b)) != -1) {
            this.moveOnColumn(b, copyA);
          }

          if (copyA.gameWon() ==c) {
            return x;
          }

        }
      }
    }
    return -1;
  }

  private boolean doubleICanWin(Connect4Game copy) {
    int x = -1;
    int y = -1;
    while (x < 6) {
      Connect4Game copyB = new Connect4Game(copy); //make a copy
      x++;
      if (getLowestEmptyIndex(copyB.getColumn(x)) != -1) { // if it isn't full
        this.moveOnColumn(x, copyB);     // place on that column
      }
      if (copyB.gameWon() != 'N') {
        Connect4Game copyA = new Connect4Game(myGame);                                                              //check for a win
        this.moveOnOppColumn(x, copyA);
        while (y < 6) {
          y++;
          this.moveOnColumn(y, copyA);
          if (copyA.gameWon() !='N') 
            return true;
        }
        // return the column
      } 
    }//repeat
    return false;
  }

  /*
   * Check for forks
   * 
   */

  public int doubleICanWinCheck() {
    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame); 
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x))!= -1) { // if the column isn't full 
        this.moveOnColumn(x, copy);     // place on that column
      }
      if (doubleICanWin(copy)) {
        //check for a win
        return x; 
      }
      // return the column
    }                              //repeat
    return -1;
  }

  /* checks for a column that would allow the opponent to win
   * 
   */

  public int doubleTheyCanWin() {
    char c;
    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame); //make a copy
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x))!= -1) {

        this.moveOnColumn(x, copy);  // place on that column
        if (getLowestEmptyIndex(copy.getColumn(x))!= -1)
          this.moveOnOppColumn(x, copy); 

      }

      if (iAmRed) 
        c = 'Y';
      else 
        c = 'R';

      if (copy.gameWon() == c) {

        //check for a loss
        return x; 
      }
      // return the column
    }                              
    return -2;

  }
  public int doubleDoubleTheyCanWin() {
    char c;
    int x = -1;
    if (this.doubleTheyCanWin() != -2) {
      Connect4Game copy = new Connect4Game(myGame);
      this.moveOnColumn(this.doubleTheyCanWin(),copy);
      while (x < 6) {
        Connect4Game copyA = new Connect4Game(copy); //make a copy
        x++;
        if (getLowestEmptyIndex(copy.getColumn(x))!= -1) {

          this.moveOnColumn(x, copy);  // place on that column
          if (getLowestEmptyIndex(copy.getColumn(x))!= -1)
            this.moveOnOppColumn(x, copy); 

        }

        if (iAmRed) 
          c = 'Y';
        else 
          c = 'R';

        if (copy.gameWon() == c) {

          //check for a loss
          return x; 
        }
        // return the column
      } 
    }
    return -2;


  }
  public int OpAttack() {
    char c;
    int x = -1;
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame); //make a copy
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x))!= -1) {

        this.moveOnOppColumn(x, copy);  // place on that column
        if (getLowestEmptyIndex(copy.getColumn(x))!= -1)
          this.moveOnColumn(x, copy); 

      }

      if (iAmRed) 
        c = 'R';
      else 
        c = 'Y';

      if (copy.gameWon() == c) {

        //check for a loss
        return x; 
      }
      // return the column
    }                              
    return -2;

  }





  public void initialChecks() {

    if (this.doubleTheyCanWin() != -2 && this.iCanWin() != this.doubleTheyCanWin()) {
      int a = 0; 
      int b = 0;
      System.out.println("doubleTheyCanWin"+this.doubleTheyCanWin());
      if (this.doubleDoubleTheyCanWin() ==this.checkForTwoColumnsA())
        a = 1;  
      else if (this.doubleDoubleTheyCanWin() ==this.checkForTwoColumnsB()) 
        a = 2;
      if (this.doubleTheyCanWin()== this.checkForTwoColumnsA())
        b = 1;
      else if (this.doubleTheyCanWin()== this.checkForTwoColumnsB())
        b = 2;
      if ((a ==1 && b ==2)||(b ==1 && a ==2))
        i = this.checkForTwoColumnsA();
      else {
        i = this.randomMove();
        System.out.println(i);
        if (this.checkForColumn() != -1) {
          i = this.checkForColumn();
          System.out.println("ia" +i);
        }

        // else if (this.doubleDoubleTheyCanWin() ==this.checkForTwoColumnsA()||this.doubleDoubleTheyCanWin() ==this.checkForTwoColumnsB()) 
        //     && (this.doubleTheyCanWin())== this.checkForTwoColumnsA() || this.doubleTheyCanWin() ==this.checkForTwoColumnsB())
        // i = this.checkForTwoColumnsA();
        else {
          System.out.println("Enter Else1 Clause");
          while (i == this.doubleTheyCanWin()) {
            i = this.randomMove();
            while (i== doubleDoubleTheyCanWin()) {
              i= this.randomMove();
              System.out.println("DoubleDouble"+doubleDoubleTheyCanWin());
            }
          }
          System.out.println("Exit Else1 Clause");
        }
      }
    }
    if (this.OpAttack() != -2 && this.iCanWin() != this.OpAttack() && this.theyCanWin() != this.OpAttack()) {
      System.out.println("OP!");
      j = this.randomMove();
      if (this.checkForColumn() != -1) { // if there is only one column left
        j = this.checkForColumn();
        System.out.println("j" +j);
      }
      else {
        System.out.println("Enter Else2 Clause");
        while (j == this.OpAttack()) {
          j = this.randomMove();
        }
        System.out.println("Exit Else2 Clause");

      }
    }
  }

  /* If there is one column left, return that column
   * else return -1
   */


  public int checkForColumn() {
    int a = 0;
    int b = 0;
    System.out.println("Enter checkForColumn");
    for (int j = 0; j < 7; j++) {
      if (this.getLowestEmptyIndex(myGame.getColumn(j)) != -1) {
        b++;
        a = j;
      }
    }
    System.out.println("Exit checkForColumn");
    if (b == 1) {
      System.out.println("Enter the IF");
      return a;
    } else {
      return -1;
    }

  }

  public int forkDefense() {
    int x = -1;
    char c = ' ';
    if (iAmRed)
      c = 'Y';
    else c = 'R';
    while (x < 6) {
      Connect4Game copy = new Connect4Game(myGame);
      x++;
      if (getLowestEmptyIndex(copy.getColumn(x)) != -1) {
        this.moveOnOppColumn(x, copy);
      }
      else return -1;

      if (this.theyCanWin(copy) != -1) {
        //      int a = this.theyCanWin(copy);
        if (getLowestEmptyIndex(copy.getColumn(x)) != -1)
          this.moveOnColumn(this.theyCanWin(copy),copy);
        int b = -1;
        while (b < 6) {
          Connect4Game copyA = new Connect4Game(copy);
          b++;
          if (getLowestEmptyIndex(copyA.getColumn(b)) != -1) {
            this.moveOnOppColumn(b, copyA);
          }

          if (copyA.gameWon() ==c) {
            return x;
          }

        }
      }
    }
    return -1;
  }



  public int checkForTwoColumnsA() {
    int a = 0;
    int b = 0;
    System.out.println("Enter checkForTwoColumnsA");
    for (int j = 0; j < 7; j++) {
      if (this.getLowestEmptyIndex(myGame.getColumn(j)) != -1) {
        b++;
        if (b==1)
          a = j;
      }
    }
    System.out.println("Exit checkForTwoColumnsA");
    if (b == 2) {
      System.out.println("Enter the IFFFFF");
      return a;
    } else {
      return -1;
    }
  }

  public int checkForTwoColumnsB() {
    int a = 0;
    int b = 0;
    System.out.println("Enter checkForTwoColumnsB");
    for (int j = 0; j < 7; j++) {
      if (this.getLowestEmptyIndex(myGame.getColumn(j)) != -1) {
        b++;
        a = j;
      }
    }
    System.out.println("Exit checkForTwoColumnsB");
    if (b == 2) {
      System.out.println("Enter the IFFFFF");
      return a;
    } else {
      return -1;
    }
  }


}



