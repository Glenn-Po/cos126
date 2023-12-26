import java.awt.Color;

public class BeadFinder {
    //  collection of blobs
    //  (using symbol table that behaves as a dynamic array)
    private ST<Integer, Blob> blobs;

    //  finds all blobs in the specified picture using luminance threshold tau
    public BeadFinder(Picture picture, double tau) {
        int width = picture.width();
        int height = picture.height();
        blobs = new ST<>();

        // 'outlines' blobs in the corresponding picture
        boolean[][] visited = new boolean[height][width];

        for (int col = 0; col < width; col++)
            for (int row = 0; row < height; row++) {
                visited[row][col] = false; // default case: every cell is lit
                if (luminance(picture.get(col, row)) < tau)
                    visited[row][col] = true; // except it's not lit enough
            }

        int idx = 0; // keep track of indices for the symbol table
        for (int col = 0; col < width; col++)
            for (int row = 0; row < height; row++) {

                // 'un-lit' or 'claimed' cell cannot constitute this blob
                if (visited[row][col]) continue;

                Blob blob = new Blob();
                // find all cells that constitute this blob
                dfs(visited, blob, row, col);

                blobs.put(idx, blob);
                ++idx;
            }
    }

    // calculates luminance of a color
    private double luminance(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        return (299 * r + 587 * g + 114 * b) / 1000.0;
    }

    // depth first search operation to isolate blobs
    private void dfs(boolean[][] visited, Blob blob, int row, int col) {
        if (row < 0 || row >= visited.length)
            return;
        if (col < 0 || col >= visited[0].length)
            return;
        if (visited[row][col]) // edge
            return;

        // this cell has been 'claimed' by the blob
        visited[row][col] = true;

        blob.add(col, row);

        // search around cell for more 'lit' cells
        dfs(visited, blob, row, col + 1); // right
        dfs(visited, blob, row + 1, col); // down
        dfs(visited, blob, row, col - 1); // left
        dfs(visited, blob, row - 1, col); // up

    }

    //  returns all beads (blobs with >= min pixels)
    public Blob[] getBeads(int min) {

        int size = 0; // counter for number of blobs that will be filtered

        for (int i : blobs)
            if (blobs.get(i).mass() >= min) size++;

        Blob[] beads = new Blob[size];

        int idx = 0;
        for (int i = 0; i < blobs.size(); i++) {
            Blob blob = blobs.get(i);
            if (blob.mass() >= min)
                beads[idx++] = blob;
        }


        return beads;
    }

    //  test client, as described below
    public static void main(String[] args) {

        // minimum 'acceptable' mass that can constitute a blob
        int min = Integer.parseInt(args[0]);

        // luminance threshold to delineate a blob
        double tau = Double.parseDouble(args[1]);
        
        Picture picture = new Picture(args[2]);
        BeadFinder finder = new BeadFinder(picture, tau);
        Blob[] beads = finder.getBeads(min);

        for (int i = 0; i < beads.length; i++) {
            StdOut.println(beads[i]);
        }

    }
}
