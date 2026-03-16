package problem;

public class Explorer {
    public int row;
    public int column;
    public int consecutiveJumps;
    public int totalJumps;
    
    public enum Move {
        R,
        D,
        RD,
        DD,
        LD
    }

    private Move lastMove;

    public Explorer() {
        this.row = 0;
        this.column = 0;
        this.consecutiveJumps = 0;
        this.totalJumps = 0;
    }

    public void move(Move move) {
        switch (move) {
            case R:
                this.column += 1;
                this.consecutiveJumps = 0;
                break;
            case D:
                this.row += 1;
                this.consecutiveJumps = 0;
                break;
            case RD:
                this.column += 2;
                this.consecutiveJumps += 1;
                this.totalJumps += 1;
                break;
            case DD:
                this.row += 2;
                this.consecutiveJumps += 1;
                this.totalJumps += 1;
                break;
            case LD:
                this.column -= 2;
                this.consecutiveJumps += 1;
                this.totalJumps += 1;
                break;
        }

        this.lastMove = move;
    }
}
