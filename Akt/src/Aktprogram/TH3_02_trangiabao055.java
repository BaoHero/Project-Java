package Aktprogram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TH3_02_trangiabao055 {
	public static void main(String[] args) {
		String fileName = "C:\\Users\\HP\\Downloads\\taci5.txt"; // Tên tệp dữ liệu đầu vào

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            int n = Integer.parseInt(reader.readLine().trim());

            int[][] initialState = new int[n][n];
            int[][] goalState = new int[n][n];

            // Đọc ma trận khởi đầu
            for (int i = 0; i < n; i++) {
                String[] row = reader.readLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    initialState[i][j] = Integer.parseInt(row[j]);
                }
            }

            // Đọc ma trận kết thúc
            for (int i = 0; i < n; i++) {
                String[] row = reader.readLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    goalState[i][j] = Integer.parseInt(row[j]);
                }
            }

            reader.close();

        Puzzle8State initial = new Puzzle8State(initialState, 0, goalState, null);
        Puzzle8State solution = solvePuzzle(initial);

        if (solution != null) {
            List<Puzzle8State> path = buildPath(solution);
            System.out.println("Solution found. Path from initial to goal:");
            for (Puzzle8State state : path) {
                state.printState();
                System.out.println("g(n): " + state.getCost());
                System.out.println();
            }
        } else {
            System.out.println("No solution found.");
        }
    } catch (IOException e) {
        System.err.println("Lỗi đọc tệp dữ liệu: " + e.getMessage());
    }
	}

    public static Puzzle8State solvePuzzle(Puzzle8State initialState) {
        PriorityQueue<Puzzle8State> openSet = new PriorityQueue<>();
        Set<Puzzle8State> closedSet = new HashSet<>();

        openSet.add(initialState);

        while (!openSet.isEmpty()) {
            Puzzle8State currentState = openSet.poll();
            closedSet.add(currentState);

            if (Arrays.deepEquals(currentState.getState(), currentState.getGoalState())) {
                return currentState; // Solution found
            }

            for (Puzzle8State neighbor : currentState.generateNeighbors()) {
                if (!closedSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }

        return null; // No solution found
    }

    public static List<Puzzle8State> buildPath(Puzzle8State finalState) {
        List<Puzzle8State> path = new ArrayList<>();
        Puzzle8State current = finalState;
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }
}

class Puzzle8State implements Comparable<Puzzle8State> {
    private int[][] state;
    private int cost;
    private int[][] goalState;
    private Puzzle8State parent;

    public Puzzle8State(int[][] state, int cost, int[][] goalState, Puzzle8State parent) {
        this.state = state;
        this.cost = cost;
        this.goalState = goalState;
        this.parent = parent;
    }

    public void setParent(Puzzle8State parent) {
        this.parent = parent;
    }

    public Puzzle8State getParent() {
        return parent;
    }

    public int[][] getState() {
        return state;
    }

    public int[][] getGoalState() {
        return goalState;
    }

    public int getCost() {
        return cost;
    }

    public List<Puzzle8State> generateNeighbors() {
        List<Puzzle8State> neighbors = new ArrayList<>();
        int[][] moves = {
            {0, -1}, {-1, 0}, {0, 1}, {1, 0}
        };

        int blankX = -1;
        int blankY = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    blankX = i;
                    blankY = j;
                    break;
                }
            }
        }

        for (int[] move : moves) {
            int newX = blankX + move[0];
            int newY = blankY + move[1];

            if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                int[][] nextState = copyState();
                nextState[blankX][blankY] = state[newX][newY];
                nextState[newX][newY] = 0;

                Puzzle8State neighbor = new Puzzle8State(nextState, cost + 1, goalState, this);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    public int heuristic() {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != 0 && state[i][j] != goalState[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }

    public void printState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int compareTo(Puzzle8State other) {
        return Integer.compare(this.cost + heuristic(), other.cost + other.heuristic());
    }

    private int[][] copyState() {
        int[][] copy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = state[i][j];
            }
        }
        return copy;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Puzzle8State other = (Puzzle8State) obj;
        return Arrays.deepEquals(state, other.state);
    }

}
