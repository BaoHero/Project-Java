package Travel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BanHang {
    private static int VoCung = Integer.MAX_VALUE / 2;

    public static int tsp(int[][] MaTranKe, int SoLuongThanhPho) {
        int[][] Dp = new int[SoLuongThanhPho][1 << SoLuongThanhPho];
        for (int[] row : Dp) {
            Arrays.fill(row, VoCung);
        }

        Dp[0][0] = 0;

        for (int DaXet = 0; DaXet < (1 << SoLuongThanhPho); DaXet++) {
            for (int VuaXet = 0; VuaXet < SoLuongThanhPho; VuaXet++) {
                if ((DaXet & (1 << VuaXet)) == 0) {
                    continue;
                }
                for (int DangXet = 0; DangXet < SoLuongThanhPho; DangXet++) {
                    if ((DaXet & (1 << DangXet)) != 0) {
                        Dp[DangXet][DaXet] = Math.min(Dp[DangXet][DaXet], Dp[VuaXet][DaXet ^ (1 << DangXet)] + MaTranKe[VuaXet][DangXet]);
                    }
                }
            }
        }

        int ChiPhiMin = VoCung;
        int DaXetCuoi = (1 << SoLuongThanhPho) - 1;
        for (int i = 1; i < SoLuongThanhPho; i++) {
            ChiPhiMin = Math.min(ChiPhiMin, Dp[i][DaXetCuoi] + MaTranKe[i][0]);
        }
        int[] DuongDi = getPath(Dp, MaTranKe, SoLuongThanhPho, DaXetCuoi);
        printPath(DuongDi);
        return ChiPhiMin;
    }

    public static int[] getPath(int[][] Dp, int[][] MaTranKe, int SoLuongThanhPho, int DaXetCuoi) {
        int[] DuongDi = new int[SoLuongThanhPho + 1];
        int DaXet = DaXetCuoi;
        int DangXet = 0;
        DuongDi[0] = 0;
        for (int i = 1; i <= SoLuongThanhPho; i++) {
            int best = -1;
            for (int j = 0; j < SoLuongThanhPho; j++) {
                if (j != DangXet && (DaXet & (1 << j)) != 0 && (best == -1 || Dp[j][DaXet] + MaTranKe[DangXet][j] < Dp[best][DaXet] + MaTranKe[DangXet][best])) {
                    best = j;
                }
            }
            DuongDi[i] = best;
            DaXet ^= (1 << best);
            DangXet = best;
        }
        return DuongDi;
    }

    public static void printPath(int[] DuongDi) {
        System.out.print("Đường đi qua các thành phố bắt đầu từ thành phố 0 là: ");
        for (int i = 0; i < DuongDi.length - 1; i++) {
            System.out.print(DuongDi[i] + " --> ");
        }
        System.out.println(DuongDi[DuongDi.length - 1]);
        System.out.println();
    }

    public static void main(String[] args) {
    	
        int[][] MaTranKe = readInputFile("C:\\Users\\admin\\Downloads\\tsp_25vertex.txt");

        if (MaTranKe != null) {
            int SoLuongThanhPho = MaTranKe.length;
            int ChiPhiMin = tsp(MaTranKe, SoLuongThanhPho);

            System.out.println("Số thành phố là: " + SoLuongThanhPho);
            System.out.println("Kết luận: Chi phí nhỏ nhất để đi qua các thành phố là " + ChiPhiMin);
        }
    }

    public static int[][] readInputFile(String fileName) {
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int n = Integer.parseInt(br.readLine().trim());
            int[][] MaTranKe = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] row = br.readLine().trim().split("\\s+");
                for (int j = 0; j < n; j++) {
                    MaTranKe[i][j] = Integer.parseInt(row[j]);
                }
            }
            return MaTranKe;
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi đọc file: " + fileName);
            return null;
        }
    }
}