import java.util.Scanner;

public class P9663_NQueen {
    static int[] A;         //퀸 배치 정보 저장
    static int N;           //체스판 크기 N*N
    static int cnt = 0;     //퀸을 배치하는 경우의 수 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N];
        backtracking(0);
        System.out.println(cnt);
    }

    private static void backtracking(int row) {
        if (row == N) { // 퀸 N개 모두 배치한 경우
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            A[row] = i;
            if (check(row)) {   //이번에 배치한 퀸이 전에 배치했던 퀸들과 서로 공격할 수 없는지 체크
                backtracking(row + 1);
            }
        }
    }

    private static boolean check(int row) {
        for (int i = 0; i < row; i++) {
            if (A[i] == A[row]) return false;   // 일직선 배치되어 있는 경우
            if (Math.abs(row - i) == Math.abs(A[i] - A[row])) return false;  // 대각선으로 배치되어 있는 경우  
        }
        return true;
    }
}
