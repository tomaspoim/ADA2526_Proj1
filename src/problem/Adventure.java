package problem;

// class to solve the problem
public class Adventure {
    static final int MODULO = 1_000_000_007;

    public static long crystalCastleDP(final Land land) {
        // +1 to have storage for over the limit
        final int overConsecutiveJumps = land.maxConsecutiveJumps + 1;
        final int overJumpLimit = land.jumpLimit + 1;
        // long is initialized to 0
        long[][][][] dp = new long[land.rows][land.columns][overConsecutiveJumps + 1][overJumpLimit + 1];

        // if I'm at the castle tile within the jump limits, I have one path
        for(int i = 0; i < overConsecutiveJumps; i++) {
            for(int j = 0; j < overJumpLimit; j++) {
                dp[land.rows - 1][land.columns - 1][i][j] = 1;
            }
        }

        for(int row = land.rows - 1; row >= 0 ; row--) {
            for(int col = land.columns - 1; col >= 0; col--) {
                // skip castle case
                if(atCastle(land, row, col)) {
                    continue;
                }

                // landing on quicksand, no matter what, will give you 0
                if(inQuickSand(land, row, col)) {
                    continue;
                }

                for(int consecutiveJumps = 0; consecutiveJumps <= land.maxConsecutiveJumps; consecutiveJumps++) {
                    // you cannot have more jumps than current row
                    if(consecutiveJumps > row) {
                        break;
                    }

                    for(int totalJumps = consecutiveJumps; totalJumps <= land.jumpLimit; totalJumps++) {
                        // same as above
                        if(totalJumps > row) {
                            break;
                        }

                        long totalPaths = 0;
                        // conversion is 1:1 with recursive
                        if(!isOutOfBounds(land, row, col + 1)) {
                            totalPaths = (totalPaths + dp[row][col + 1][0][totalJumps]) % MODULO; // R
                        }
                        if(!isOutOfBounds(land, row + 1, col)) {
                            totalPaths = (totalPaths + dp[row + 1][col][0][totalJumps]) % MODULO; // D
                        }
                        if(isJumpClear(land, row, col) && consecutiveJumps < land.maxConsecutiveJumps && totalJumps < land.jumpLimit) {
                            if(!isOutOfBounds(land, row + 2, col)) {
                                totalPaths = (totalPaths + dp[row + 2][col][consecutiveJumps + 1][totalJumps + 1]) % MODULO; // DD
                            }
                            if(isDiagonalClear(land, row, col)) {
                                if(!isOutOfBounds(land, row + 1, col - 1)) {
                                    totalPaths = (totalPaths + dp[row + 1][col - 1][consecutiveJumps + 1][totalJumps + 1]) % MODULO; // LD
                                }
                                if(!isOutOfBounds(land, row + 1, col + 1)) {
                                    totalPaths = (totalPaths + dp[row + 1][col + 1][consecutiveJumps + 1][totalJumps + 1]) % MODULO; // LD
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

    static boolean atCastle(final Land land, final int row, final int col) {
        if ((row == land.rows - 1) && (col == land.columns - 1)) {
            return true;
        }

        return false;
    }

    static boolean inQuickSand(final Land land, final int row, final int col) {
        Tile tile = land.tiles[row][col];
        if (tile.type == Tile.Type.QUICK_SAND) {
            return true;
        }

        return false;
    }

    static boolean isDiagonalClear(final Land land, final int row, final int col) {
        Tile tile = land.tiles[row][col];
        if (tile.type == Tile.Type.DIAGONAL_BARRIER) {
            return false;
        }

        return true;
    }

    static boolean isJumpClear(final Land land, final int row, final int col) {
        Tile tile = land.tiles[row][col];
        if (tile.type == Tile.Type.JUMP_BARRIER) {
            return false;
        }

        return true;
    }

    static boolean isOutOfBounds(final Land land, final int row, final int col) {
        if ((row >= land.rows) || (col >= land.columns)) {
            return true;
        }

        if ((row < 0) || (col < 0)) {
            return true;
        }

        return false;
    }
}
