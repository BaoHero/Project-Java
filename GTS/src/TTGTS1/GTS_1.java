package TTGTS1 ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GTS_1 {

    static int n, v;
    static int Cost;
    static int[] Tour;
    static int[][] mtTP;
    static int[] Flag;

    public static void main(String[] args) {
       /* System.out.println("**************** TRI TUE NHAN TAO *******************");
        System.out.println("|                                                   |");
        System.out.println("************ KHOA CNTT - DH GTVT TPHCM **************");
        System.out.println("|     bai toan ung dung giai thuat GTS1             |");
        System.out.println("*****************************************************\n\n\n\n\n");        */

        Input();
        GTS1();
        Output();
    }

    static void Input() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\HP\\Downloads\\GTS1a.txt"));
            String line = reader.readLine();
            String[] parts = line.split("\\s+");
            n = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]);

            mtTP = new int[n + 1][n + 1];
            Tour = new int[n];
            Flag = new int[n];

            for (int i = 1; i <= n; i++) {
                line = reader.readLine();
                parts = line.split("\\s+");
                for (int j = 1; j <= n; j++) {
                    mtTP[i][j] = Integer.parseInt(parts[j - 1]);
                }
            }

            for (int i = 0; i < n; i++) {
                Flag[i] = 0;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("\n\t File khong ton tai. \n\n");
        }
    }

    static void Output() {
        try {
            PrintWriter writer = new PrintWriter("Output.txt");
            writer.println("Chi phi cho qua trinh :" + Cost);
            writer.print("Hanh trinh nhu sau :");
            
            for (int i = 0; i < n; i++) {
                writer.print(Tour[i] + " -->");
            }
            writer.println(Tour[0]);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // theo thuat giai
    static void GTS1() {
    	/*Tour = new int[n+1];
    	Flag = new int[n+1];
    	mtTP = new int[n + 1][n + 1]; */
        int dem = 0;
        Tour[0] = v;       
        Flag[v] = 1;
        int tmp = v;
        while (dem < n - 1) {
            int tmpCost = 100;
            int co = 0;
            for (int i = 1; i <= n; i++) {
                if (tmpCost > mtTP[v][i] && Flag[i] == 0 && mtTP[v][i] != -1) {
                    tmpCost = mtTP[v][i];
                    co = i;
                }
            }
            dem++;
            Tour[dem] = co;
            Cost += tmpCost;
            Flag[v] = 1;
            v = co;
        }
        Cost += mtTP[v][tmp];
    }
}

