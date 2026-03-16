package problem;

public class Tile {
    public enum Type {
        SAFE,
        DIAGONAL_BARRIER,
        JUMP_BARRIER,
        QUICK_SAND  
    }

    public Type type;

    public Tile(String input) {
        switch (input) {
            case ".":
                this.type = Type.SAFE;
                break;
            case "X":
                this.type = Type.DIAGONAL_BARRIER;
                break;
            case "J":
                this.type = Type.JUMP_BARRIER;
                break;
            case "#":
                this.type = Type.QUICK_SAND;
                break;
            default:
                throw new IllegalArgumentException("Invalid tile value: " + input);
        }
    }

    @Override
    public String toString() {
        switch (this.type) {
            case SAFE:
                return ".";
            case DIAGONAL_BARRIER:
                return "X";
            case JUMP_BARRIER:
                return "J";
            case QUICK_SAND:
                return "#";
            default:
                throw new IllegalArgumentException("Invalid tile value: " + this.type);
        }
    }
}
