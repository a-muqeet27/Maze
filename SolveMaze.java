import java.sql.Array;
import java.util.ArrayList;
import java.util.Stack;
public class SolveMaze {
    Maze maze;
    Rat rat;
    Stack stack;

    public SolveMaze(String file) {
        maze = new Maze("maze.txt");
        rat = new Rat(maze);
        Stack<Location> stack = new Stack<>();


        stack = new Stack<>();


        while (!rat.isOut()) {
            Location currentLocation = rat.getLocation();
            maze.markMoved(currentLocation);

            boolean foundValidMove = false;

            for (int i = 0; i < 4; i++) {
                if (rat.canMove(i) && !rat.isOut()) {
                    stack.push(currentLocation);
                    rat.move(i);
                    foundValidMove = true;
                    break;
                }
            }

            if (!foundValidMove && !rat.isOut()) {
                // No valid move, so backtrack

                if (!stack.isEmpty()) {
                    Location previousLocation = stack.pop();
                    rat.setLocation(previousLocation);
                    maze.markTried(currentLocation);
                } else {
                    System.out.println("No Solution Found!");
                    break;
                }

            }
        }
        System.out.println("\n");
        maze.print();

        if (rat.isOut()) {
            System.out.println("\nSolution Path Coordinates:");
            while (!stack.isEmpty()) {
                Location location = stack.pop();
                System.out.println("(" + location.getX() + ", " + location.getY() + ")");
            }
        }
    }
}
