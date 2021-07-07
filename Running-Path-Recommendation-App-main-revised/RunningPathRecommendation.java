/*
  Main object for running path recommendation

  @author Anggito
 * @version ver 1.1 - 7 July 21 (revised by fadhil)
 */

import java.util.Vector;

class RunningPathRecommendation {
    private static final int NO_PAR = -1;

    private static void DijkstraAlgo(int[][] adjMatrix, int src, int dest, Vector<crossRoadNode> roadList) {
        int vertexAmount = adjMatrix[src].length;

        // array to trace the path
        int[] shortestDist = new int[vertexAmount];

        // visited bool
        boolean[] visited = new boolean[vertexAmount];

        // initialize distances
        for(int i = 0; i < vertexAmount; i++) {
            shortestDist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        // dist to self
        shortestDist[src] = 0;

        // parents for tracing path
        int[] parents = new int[vertexAmount];

        // start vertex has no parent
        parents[src] = NO_PAR;

        for (int j = 1; j < vertexAmount; j++)
        {
            // find shortest path
            int adjVertex = -1;
            int shortest = Integer.MAX_VALUE;

            for(int i = 0; i < vertexAmount; i++) {
                if(shortestDist[i] < shortest && !visited[i]) {
                    adjVertex = i;
                    shortest = shortestDist[i];
                }
            }

            visited[adjVertex] = true;

            for(int i = 0; i < vertexAmount; i++) {
                int edgeDist = adjMatrix[adjVertex][i];

                if(edgeDist > 0 && ((shortest + edgeDist) < shortestDist[i])) {
                    parents[i] = adjVertex;
                    shortestDist[i] = shortest + edgeDist;
                }
            }
        }
        printSolution(src, shortestDist, parents, dest, roadList);
    }

    private static void printSolution(int src, int[] distances, int[] parents, int dest, Vector<crossRoadNode> roadList) {

        System.out.println(distances[dest]);

        if(dest != src) {
            System.out.println("Jalan yang akan anda lalui");
            System.out.print("START HERE");
            printPath(dest, parents, roadList);
        }
        else {
            System.out.print("Jalan yang Anda pilih sama");
        }
    }

    private static void printPath(int currVertex, int[] parents, Vector<crossRoadNode> roadList) {
        if(currVertex == NO_PAR) {
            return;
        }
        
        printPath(parents[currVertex], parents, roadList);
        System.out.print(" -> " + roadList.elementAt(currVertex).getLabel());
    }

    public static void main(String[] args) {

        // Main user interface
        new AppFrame();

    }
}