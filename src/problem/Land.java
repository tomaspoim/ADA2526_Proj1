package problem;

// class to hold the fixed data for the problem
public class Land {
    public int rows;
    public int columns;
    public int maxConsecutiveJumps;
    public int jumpLimit;
    public Tile[][] tiles;

    public Land(int rows, int columns, int maxConsecutiveJumps, int jumpLimit, Tile[][] tiles) {
        this.rows = rows;
        this.columns = columns;
        this.maxConsecutiveJumps = maxConsecutiveJumps;
        this.jumpLimit = jumpLimit;
        this.tiles = tiles;
    }
}
