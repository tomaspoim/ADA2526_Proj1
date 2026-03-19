package problem;

// class to solve the problem
public class Adventure {
    static final int MODULO = 1_000_000_007;

    public static long crystalCastleDP(final Land land) {
        // +1 to have storage for over the limit
        final int overConsecutiveJumps = land.maxConsecutiveJumps + 1;
        final int overJumpLimit = land.jumpLimit + 1;
        // long is initialized to 0
        long[][][][] dp = new long[land.rows][land.columns][overConsecutiveJumps][overJumpLimit];

        // if I'm at the castle tile within the jump limits, I have one path
        for (int i = 0; i < overConsecutiveJumps; i++) {
            for (int j = 0; j < overJumpLimit; j++) {
                dp[land.rows - 1][land.columns - 1][i][j] = 1;
            }
        }

        for (int row = land.rows - 1; row >= 0; row--) {
            for (int col = land.columns - 1; col >= 0; col--) {
                // skip castle case
                if ((row == land.rows - 1) && (col == land.columns - 1)) {
                    continue;
                }

                // landing on quicksand, no matter what, will give you 0
                Tile tile = land.tiles[row][col];
                if (tile.type == Tile.Type.QUICK_SAND) {
                    continue;
                }

                for (int consecutiveJumps = 0; consecutiveJumps < overConsecutiveJumps; consecutiveJumps++) {
                    // you cannot have more jumps than current row
                    if (consecutiveJumps > row) {
                        break;
                    }

                    for (int totalJumps = consecutiveJumps; totalJumps < overJumpLimit; totalJumps++) {
                        // same as above
                        if (totalJumps > row) {
                            break;
                        }

                        long totalPaths = 0;
                        // conversion is 1:1 with recursive
                        if (!((row >= land.rows) || (col + 1 >= land.columns) || (row < 0) || (col + 1 < 0))) {
                            totalPaths = (totalPaths + dp[row][col + 1][0][totalJumps]) % MODULO; // R
                        }
                        if (!((row + 1 >= land.rows) || (col >= land.columns) || (row + 1 < 0) || (col < 0))) {
                            totalPaths = (totalPaths + dp[row + 1][col][0][totalJumps]) % MODULO; // D
                        }
                        if ((tile.type != Tile.Type.JUMP_BARRIER) && consecutiveJumps < land.maxConsecutiveJumps
                                && totalJumps < land.jumpLimit) {
                            if (!((row + 2 >= land.rows) || (col >= land.columns) || (row + 2 < 0) || (col < 0))) {
                                totalPaths = (totalPaths + dp[row + 2][col][consecutiveJumps + 1][totalJumps + 1])
                                        % MODULO; // DD
                            }
                            if (tile.type != Tile.Type.DIAGONAL_BARRIER) {
                                if (!((row + 1 >= land.rows) || (col - 1 >= land.columns) || (row + 1 < 0)
                                        || (col - 1 < 0))) {
                                    totalPaths = (totalPaths
                                            + dp[row + 1][col - 1][consecutiveJumps + 1][totalJumps + 1]) % MODULO; // LD
                                }
                                if (!((row + 1 >= land.rows) || (col + 1 >= land.columns) || (row + 1 < 0)
                                        || (col + 1 < 0))) {
                                    totalPaths = (totalPaths
                                            + dp[row + 1][col + 1][consecutiveJumps + 1][totalJumps + 1]) % MODULO; // RD
                                }
                            }
                        }
                        dp[row][col][consecutiveJumps][totalJumps] = totalPaths;
                    }
                }
            }
        }

        return dp[0][0][0][0];
    }
}
