import java.io.*;
import java.util.Stack;
public class Maze {
    private int m=7, n=12;
    private int[][] a = new int[7][13];
    private static final int OPEN = 0, WALL = 1, TRIED = 2, PATH = 3;
    File file = new File("maze.txt");
    private Location location;
    public Maze(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader("maze.txt"))) {
            String line;
            int rows = 0;
            int cols = 0;
            while ((line = reader.readLine()) != null) {
                rows++;
                cols = line.length();
            }
            reader.close(); // Close and reopen the file to reset the reader
            BufferedReader reader1 = new BufferedReader(new FileReader("maze.txt"));

            int row = 0;
            while ((line = reader1.readLine()) != null) {
                for (int col = 0; col < cols; col++) {
                    char cell = line.charAt(col);
                    if(cell == '#')
                        a[row][col] = 1;
                    else
                        a[row][col] = 0;
                }
                row++;
            }

            for (int i = 0; i < 7; i++) {
                System.out.println();
                for (int j = 0; j < 12; j++) {
                    if(a[i][j]==1)
                        System.out.print('#');
                    else if (a[i][j]==0)
                        System.out.print(' ');
                    else if(a[i][j] == 3)
                        System.out.print('o');
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isOpen(Location location) {
        if (location.getX() >= 0 && location.getY() >=0 && location.getX() <= 11 && location.getY() <= 6)
            return (a[location.getY()][location.getX()] == OPEN);
        else
            return false;
    }

    public void markMoved(Location location) {
        a[location.getY()][location.getX()] = PATH;
    }
    public void markTried(Location location) {
        a[location.getY()][location.getX()]  = TRIED;
    }
    public int getWidth(){
        return n;
    }

    public int getHeight(){
        return m;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void print(){
        char[] chars = {' ','#','?','o'};
        for(int i=0 ; i< 7;i++) {
            for (int j = 0; j < 13; j++) {
                System.out.print(chars[a[i][j]]);
            }
            System.out.println();
        }
    }
}
