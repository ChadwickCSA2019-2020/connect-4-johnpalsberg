import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyAgentTest {

  Connect4Game game; 


  @Before
  public void setUp() throws Exception {
    game = new Connect4Game(7, 6);
  }

  @Test
  public void testICanWinVerticallySimple() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(1,game);
      yellowAgent.moveOnColumn(2,game);
    }

    assertEquals(redAgent.iCanWin(), 1);

  }

  @Test
  public void testICanWinVerticallyTop4() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 2; i++) {
      redAgent.moveOnColumn(1,game);
      yellowAgent.moveOnColumn(2,game);
    }

    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(2,game);
      yellowAgent.moveOnColumn(1,game);   
    }

    assertEquals(redAgent.iCanWin(), 2);

  }

  // TODO: Write 2 test cases for testICanWinHorizontally 
  @Test
  public void testICanWinHorizontally() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(i,game);
      yellowAgent.moveOnColumn(5,game);
    }

    assertEquals(yellowAgent.iCanWin(), 3);

  }
  @Test
  public void testICanWinHorizontally2() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 6; i > 3; i--) {
      redAgent.moveOnColumn(i,game);
      yellowAgent.moveOnColumn(5,game);
    }

    assertEquals(redAgent.iCanWin(), 3);

  }

  // TODO: Write 2 test cases for testICanWinDiagonally
  @Test
  public void testICanWinDiagonally() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    yellowAgent.moveOnColumn(1,game);
    yellowAgent.moveOnColumn(2,game);
    yellowAgent.moveOnColumn(2,game);
    yellowAgent.moveOnColumn(3,game);
    yellowAgent.moveOnColumn(3,game);
    yellowAgent.moveOnColumn(3,game); 
    for (int j=1; j<4; j++) {
      redAgent.moveOnColumn(j,game);
    }
    assertEquals(redAgent.iCanWin(), 4); 


  }
  @Test
  public void testICanWinDiagonally2() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    yellowAgent.moveOnColumn(6,game);
    yellowAgent.moveOnColumn(5,game);
    yellowAgent.moveOnColumn(5,game);
    yellowAgent.moveOnColumn(4,game);
    yellowAgent.moveOnColumn(4,game);
    yellowAgent.moveOnColumn(4,game); 
    for (int j=6; j>3; j--) {
      redAgent.moveOnColumn(j,game);
    }
    assertEquals(redAgent.iCanWin(), 3); 


  }


  @Test
  public void testTheyCanWin() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(1,game);
      yellowAgent.moveOnColumn(2,game);
    }

    assertEquals(redAgent.theyCanWin(), 2);
  }

  // TODO: Write testTheyCanWinHorizontally
  @Test
  public void testTheyCanWinHorizontallyRed() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      redAgent.moveOnColumn(5,game);
      yellowAgent.moveOnColumn(i,game);
    }

    assertEquals(redAgent.theyCanWin(), 3);

  }
  @Test
  public void testTheyCanWinHorizontallyYellow() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    for (int i = 0; i < 3; i++) {
      yellowAgent.moveOnColumn(5,game);
      redAgent.moveOnColumn(i,game);
    }

    assertEquals(yellowAgent.theyCanWin(), 3);

  }


  // TODO: Write testTheyCanWinDiagonally
  @Test
  public void testTheyCanWinDiagonallyYellow() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    redAgent.moveOnColumn(1,game);
    redAgent.moveOnColumn(2,game);
    redAgent.moveOnColumn(2,game);
    redAgent.moveOnColumn(3,game);
    redAgent.moveOnColumn(3,game);
    redAgent.moveOnColumn(3,game); 
    for (int j=1; j<4; j++) {
      yellowAgent.moveOnColumn(j,game);
    }
    assertEquals(yellowAgent.theyCanWin(), 0); 


  }
  @Test
  public void testTheyCanWinDiagonallyRed() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    yellowAgent.moveOnColumn(1,game);
    yellowAgent.moveOnColumn(2,game);
    yellowAgent.moveOnColumn(2,game);
    yellowAgent.moveOnColumn(3,game);
    yellowAgent.moveOnColumn(3,game);
    yellowAgent.moveOnColumn(3,game); 
    for (int j=1; j<3; j++) {
      redAgent.moveOnColumn(j,game);
    }
    assertEquals(redAgent.theyCanWin(), 0); 


  }

  // Tests you can win against a Beginner agent as Red
  @Test
  public void testRedWinningBeginnerAgent() {
    Agent redAgent = new MyAgent(game, true);
    Agent yellowAgent = new BeginnerAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Red against Beginner");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  // Tests you can win against a Beginner agent as Yellow
  @Test
  public void testYellowWinningBeginnerAgent() {
    Agent redAgent = new BeginnerAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 10; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Beginner");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 9);
  }

  // Tests you can win against a Random agent as Red
  @Test
  public void testRedWinningRandomAgent() {
    Agent redAgent = new MyAgent(game, true);
    Agent yellowAgent = new RandomAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Red against Random");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  //Tests you can win against a Random agent as Red
  @Test
  public void testYellowWinningRandomAgent() {
    Agent redAgent = new RandomAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Random");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }

  // BONUS TODO: Write testCases to play against IntermediateAgent
  @Test
  public void testYellowWinningIntermediateAgent() {
    Agent redAgent = new IntermediateAgent(game, false);
    Agent yellowAgent = new MyAgent(game, true);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'Y') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Intermediate");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }
  @Test
  public void testRedWinningIntermediateAgent() {
    Agent redAgent = new IntermediateAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Intermediate");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }
  // SUPER BONUS TODO: Write testCases to playAgainst AdvancedAgent
  @Test
  public void testYellowAdvancedAgent() {
    Agent redAgent = new AdvancedAgent(game, false);
    Agent yellowAgent = new MyAgent(game, true);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Advanced");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }
  @Test
  public void testRedAdvancedAgent() {
    Agent redAgent = new AdvancedAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Advanced");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }
  // SUPER BONUS TODO: Write testCases to playAgainst BrilliantAgent
  @Test
  public void testRedWinningBrilliantAgent() {
    Agent redAgent = new BrilliantAgent(game, true);
    Agent yellowAgent = new MyAgent(game, false);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'R') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'Y') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Brilliant");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }
  public void testYellowWinningBrilliantAgent() {
    Agent redAgent = new BrilliantAgent(game, false);
    Agent yellowAgent = new MyAgent(game, true);
    int numberOfWins = 0;
    for (int i = 0; i < 50; i++) {
      game.clearBoard(); 
      while(!game.boardFull() && game.gameWon() == 'N') {
        redAgent.move();
        if (game.gameWon() != 'Y') {
          yellowAgent.move();
        }
      }

      if (game.gameWon() == 'R') {
        numberOfWins++;
      }
    }
    System.out.println("You won: " + numberOfWins + " games as Yellow against Brilliant");
    // Test that you win over 90% of your games
    assertTrue(numberOfWins >= 45);
  }
  // Test to win
  @Test
  public void testDoubleICanWin() {
    MyAgent redAgent = new MyAgent(game, true);
    MyAgent yellowAgent = new MyAgent(game, false);
    game.clearBoard();
    redAgent.moveOnColumn(1,game);
    redAgent.moveOnColumn(3,game);
    redAgent.moveOnColumn(4,game);
    redAgent.moveOnColumn(3,game);
    redAgent.moveOnColumn(4,game);
    redAgent.moveOnColumn(4,game); 

    yellowAgent.move();




  }
}





