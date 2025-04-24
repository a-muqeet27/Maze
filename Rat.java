import java.util.Objects;
public class Rat {
    private Maze maze;
    private Location location;

    public Rat(Maze maze) {
        this.maze = maze;
        location = new Location(0,1 );
    }

    public Location getLocation() {
        return (Location) location.clone();

    }

    public void setLocation(Location location) {
        this.location = location;

    }

    public boolean canMove(int direction) {
        Location neighbor = location.adjacent(direction);
        return maze.isOpen(neighbor);
    }

    public void move(int direction) {
        location.move(direction);
        maze.markMoved(location);
    }

    public boolean isOut() {
        if(maze.getWidth()==location.getX()+1 || maze.getHeight()== location.getY()){
            return true;
        }else
            return false;
    }
}
