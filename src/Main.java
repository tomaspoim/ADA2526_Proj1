import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import problem.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("test2.txt")));

        final int casesCount = Integer.parseInt(reader.readLine());
        Land[] cases = new Land[casesCount];
        for (int caseIndex = 0; caseIndex < casesCount; caseIndex++) {
            String[] properties = reader.readLine().split(" ");
            final int rowsCount = Integer.parseInt(properties[0]);
            final int columnsCount = Integer.parseInt(properties[1]);
            final int maxConsecutiveJumps = Integer.parseInt(properties[2]);
            final int jumpLimit = Integer.parseInt(properties[3]);
            Tile[][] tiles = new Tile[rowsCount][columnsCount];
            for (int row = 0; row < rowsCount; row++) {
                String[] line = reader.readLine().split("");
                for (int col = 0; col < columnsCount; col++) {
                    tiles[row][col] = new Tile(line[col]);
                }
            }

            cases[caseIndex] = new Land(rowsCount, columnsCount, maxConsecutiveJumps, jumpLimit, tiles);
        }

        // System.out.println("DP");
        // final long dpStart = System.nanoTime();
        for (int caseIndex = 0; caseIndex < casesCount; caseIndex++) {
            final Land land = cases[caseIndex];
            // final long caseStart = System.nanoTime();
            final long totalPaths = Adventure.crystalCastleDP(land);
            // final long caseEnd = System.nanoTime();
            // System.out.println("Case " + (caseIndex + 1) + ": " + totalPaths + " (" + ((caseEnd - caseStart) / 1_000_000.0) + " ms)"); // DEBUG
            System.out.println(totalPaths); // FINAL
        }
        // final long dpEnd = System.nanoTime();
        // System.out.println("DP total time: " + ((dpEnd - dpStart) / 1_000_000.0) + " ms");

        reader.close();
    }
}
