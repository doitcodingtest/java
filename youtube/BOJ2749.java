import java.io.*;

public class Main {
    // 문제에서 요구한 나누는 수
    final static long MOD = 1000000;

    public static void main(String[] args) throws IOException {
        // 입력 속도를 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // n은 최대 100경(10^18)이므로 long 사용
        long n = Long.parseLong(br.readLine());

        // F_0 = 0
        if (n == 0) {
            System.out.println(0);
            return;
        }

        // 피보나치 Q-행렬 기본값
        long[][] A = {{1, 1}, {1, 0}};
        
        // 행렬 거듭제곱으로 Fn 구하기
        long[][] result = power(A, n);

        // M^n의 [0][1] 성분이 F_n임
        System.out.println(result[0][1] % MOD);
    }

    // 분할 정복을 이용한 거듭제곱 (O(logN))
    private static long[][] power(long[][] A, long n) {
        // 지수가 1이면 자기 자신 반환 (Base Case)
        if (n == 1) {
            // 반환할 때도 모듈러 연산 안전하게 한 번 해줌
            return new long[][] {
                {A[0][0] % MOD, A[0][1] % MOD},
                {A[1][0] % MOD, A[1][1] % MOD}
            };
        }

        // 1. 반으로 쪼개서 구함 (Divide)
        long[][] half = power(A, n / 2);

        // 2. 구한 것을 제곱함 (Conquer)
        long[][] result = multiply(half, half);

        // 3. 홀수라면 A를 한 번 더 곱해줌
        if (n % 2 == 1) {
            result = multiply(result, A);
        }

        return result;
    }

    // 행렬 곱셈 함수 (나머지 연산 포함)
    private static long[][] multiply(long[][] o1, long[][] o2) {
        long[][] ret = new long[2][2];

        // 2x2 행렬 곱셈 공식 + 모듈러 연산
        ret[0][0] = (o1[0][0] * o2[0][0] + o1[0][1] * o2[1][0]) % MOD;
        ret[0][1] = (o1[0][0] * o2[0][1] + o1[0][1] * o2[1][1]) % MOD;
        ret[1][0] = (o1[1][0] * o2[0][0] + o1[1][1] * o2[1][0]) % MOD;
        ret[1][1] = (o1[1][0] * o2[0][1] + o1[1][1] * o2[1][1]) % MOD;

        return ret;
    }
}
