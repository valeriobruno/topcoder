import java.util.*;
import static org.junit.Assert.*;

import org.junit.*;


public class TeleportationMazeTest
{

  @Test
  public void test1()
  {
    TeleportationMaze tm = new TeleportationMaze();

    String maze [] = {
      "###.###..#",
      "#.####.#.#",
      "##..#.##.#",
      "#..##.#.##",
      "#.######.#",
      ".#####.###", "##.#######", "##.######.", "#######.#.", "###.######", "###.######", "##..#.#.##", "#.####.##.", "#.#######.", "#####..##.", "###.######", "##.#.#####", "#.########", "##.###.###", "##.##..###", "##.##...##", "#########.", ".########.", ".##.######", "########.#", "######.###", "#######.##", "#.########", "#.#.######", "###.#####.", "#.#..##.##", "#.#.##.###", "##.#.#####", ".###.#####", ".#########", "###.#.#.##", "####.#####", ".##..#####", "##########", "#######.##", "#.###...##", "##########", ".#######.#", "##########", ".##..#.##.", "####..##.#", "#.########", "#########.", "###.#..###", "###.#####."};

    assertEquals(14, tm.pathLength(maze,2, 3, 35, 7));
  }

  @Test
  public void testLongExecution()
  {

    String maze[] = {"........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................", "........................................"};

    TeleportationMaze tm = new TeleportationMaze();

    assertEquals(38, tm.pathLength(maze, 17, 6, 25, 36));

  }
}
