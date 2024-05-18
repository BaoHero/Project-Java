package TTD;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TH4_1_trangiabao055 {
	public static void dijkstra(int[][] graph, int start, int end) {
        int numVertices = graph.length;
        int[] distance = new int[numVertices];
        boolean[] shortestPathTreeSet = new boolean[numVertices];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(shortestPathTreeSet, false);

        distance[start] = 0;

        for (int count = 0; count < numVertices - 1; count++) {
            int u = minDistance(distance, shortestPathTreeSet);

            shortestPathTreeSet[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!shortestPathTreeSet[v] && graph[u][v] != 0 &&
                        distance[u] != Integer.MAX_VALUE && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        printPath(start, end, distance);
    }

    public static int minDistance(int[] distance, boolean[] shortestPathTreeSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        int numVertices = distance.length;
        for (int v = 0; v < numVertices; v++) {
            if (!shortestPathTreeSet[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void printPath(int start, int end, int[] distance) {
        if (distance[end] == Integer.MAX_VALUE) {
            System.out.println("Không có đường đi từ đỉnh " + start + " đến đỉnh " + end);
            return;
        }

        System.out.println("Đường đi ngắn nhất từ đỉnh " + (start + 1) + " đến đỉnh " + (end + 1) + " có độ dài là " + distance[end]);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\HP\\Downloads\\path1.txt"));
            int numVertices = scanner.nextInt();
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            int[][] graph = new int[numVertices][numVertices];
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    graph[i][j] = scanner.nextInt();
                }
            }

            dijkstra(graph, start - 1 , end - 1 ); // Subtract 1 to convert to 0-based indexing

        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp input.");
        }
    }

}
