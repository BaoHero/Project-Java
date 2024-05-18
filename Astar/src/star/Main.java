package star;

public class Main {

    public static void main(String[] args) {

        // Create goal node
        Node start = new Node(new int[][]{
            {6, 1, 7},
            {4, 8, 3},
            {2, 5, 0}
        });

        // Create start node
        Node goal = new Node(new int[][] {
            {1, 6, 2},
            {3, 5, 4},
            {0, 8, 7}
        });

        // Initialize AStar
        AStar aStar = new AStar(start,goal);

        // Run the algorithm on the puzzles
        aStar.initialize();
    }

}
