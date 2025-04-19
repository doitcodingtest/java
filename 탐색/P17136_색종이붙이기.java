import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P17136_색종이붙이기 {

    static int[][] M = new int[10][10];
    static int[] S = {0, 5, 5, 5, 5, 5};    // 남은 색종이 수
    static int result = Integer.MAX_VALUE;  // 최소 사용 갯수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                M[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1이 적힌 모든 칸을 붙이는 색종이를 사용한 경우의 수를 백트래킹으로 탐색
        Backtracking(0, 0); 
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static void Backtracking(int xy, int useCnt) {
        if (xy == 100) {    //색종이로 1이 적힌 모든 칸을 붙였을 때 (x, y 좌표를 끝까지 탐색 한 경우 ) 탐색 종료
            result = Math.min(useCnt, result);
            return;
        }
        int x = xy % 10;
        int y = xy / 10;

        //가지치기 : 최소로 사용한 색종이 수보다 현재 탐색에서 사용한 색종이 수가 많으면 더이상 탐색 불필요
        if (result <= useCnt) return;
        if (M[y][x] == 1) {
            for (int i = 5; i > 0; i--) {
                if (S[i] > 0 && check(x, y, i)) {
                    S[i]--; // 종이 사용하기
                    fill(x, y, i, 0); // 종이 붙이기: 종이로 덮이는 부분 1-> 0으로 변경
                    Backtracking(xy + 1, useCnt + 1);
                    S[i]++; // 사용한 종이 다시 채우기
                    fill(x, y, i, 1); // 종이 떼어내기 : 기존에 덮인 부분 0 -> 1 값으로 변경
                }
            }
        } else {
            Backtracking(xy + 1, useCnt); // 현재 좌표의 값이 0이면 색종이를 사용하지 않고 다음 칸 이동
        }
    }

    static void fill(int x, int y, int size, int num) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                M[i][j] = num;
            }
        }
    }

    static boolean check(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (M[i][j] != 1) return false;
            }
        }
        return true;
    }
}
