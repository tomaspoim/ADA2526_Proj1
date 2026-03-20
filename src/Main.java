import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import problem.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // input reading and parsing
        final int casesCount = Integer.parseInt(reader.readLine());
        for (int caseIndex = 0; caseIndex < casesCount; caseIndex++) {
            String[] properties = reader.readLine().split(" ");
            final int rowsCount = Integer.parseInt(properties[0]);
            final int columnsCount = Integer.parseInt(properties[1]);
            final int maxConsecutiveJumps = Integer.parseInt(properties[2]);
            final int jumpLimit = Integer.parseInt(properties[3]);
            Tile[][] tiles = new Tile[rowsCount][columnsCount];
            for (int row = 0; row < rowsCount; row++) {
                String line = reader.readLine();
                for (int col = 0; col < columnsCount; col++) {
                    tiles[row][col] = new Tile(line.charAt(col));
                }
            }

            final Land land = new Land(rowsCount, columnsCount, maxConsecutiveJumps, jumpLimit, tiles);
            final long totalPaths = Adventure.crystalCastleDP(land);
            System.out.println(totalPaths);
        }

        reader.close();
    }
}
