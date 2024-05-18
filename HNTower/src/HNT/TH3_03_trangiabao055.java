package HNT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class TH3_03_trangiabao055 {
	static int moves = 0;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\HP\\Downloads\\HNtower4.txt"));

            // Đọc số đĩa từ tệp
            int n = Integer.parseInt(reader.readLine());

            // Đọc trạng thái ban đầu từ tệp
            Stack<Integer> cọcA = new Stack<>();
            Stack<Integer> cọcB = new Stack<>();
            Stack<Integer> cọcC = new Stack<>();

            String initialStateA = reader.readLine();
            String initialStateB = reader.readLine();
            String initialStateC = reader.readLine();

            String[] stateA = initialStateA.split(" ");
            String[] stateB = initialStateB.split(" ");
            String[] stateC = initialStateC.split(" ");

            for (int i = 0; i < n; i++) {
                if (i < stateA.length) {
                    cọcA.push(Integer.parseInt(stateA[i]));
                }
                if (i < stateB.length) {
                    cọcB.push(Integer.parseInt(stateB[i]));
                }
                if (i < stateC.length) {
                    cọcC.push(Integer.parseInt(stateC[i]));
                }
            }

            // In trạng thái ban đầu
            System.out.println("Trạng thái ban đầu:");
            System.out.println("Cọc A: " + cọcA);
            System.out.println("Cọc B: " + cọcB);
            System.out.println("Cọc C: " + cọcC);

            // Giải bài toán Tháp Hà Nội
            towerOfHanoi(n, cọcA, cọcC, cọcB, "A", "C", "B");

            // In số bước di chuyển
            System.out.println("Số bước di chuyển: " + moves);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void towerOfHanoi(int n, Stack<Integer> fromRod, Stack<Integer> toRod, Stack<Integer> auxRod, String from, String to, String aux) {
        if (n == 1) {
            if (!fromRod.isEmpty()) {
                int disk = fromRod.pop();
                if (disk != 0) {
                toRod.push(disk);
                moves++;
                System.out.println("Di chuyển đĩa " + disk + " từ " + from + " đến " + to);
                }}
            return;
        }

        towerOfHanoi(n - 1, fromRod, auxRod, toRod, from, aux, to);

        if (!fromRod.isEmpty()) {
            int disk = fromRod.pop();
            if (disk != 0) {
            toRod.push(disk);
            moves++;
            System.out.println("Di chuyển đĩa " + disk + " từ " + from + " đến " + to);}
        }

        towerOfHanoi(n - 1, auxRod, toRod, fromRod, aux, to, from);
    }

}
