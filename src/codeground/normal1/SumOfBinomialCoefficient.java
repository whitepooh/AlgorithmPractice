package codeground.normal1;

import java.io.FileInputStream;
import java.util.Scanner;

public class SumOfBinomialCoefficient {
	
	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("SumOfBinomialCoefficient_input.txt"));
		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;
		
		long[] fact = new long[2000003];
		long MOD = 1000000007;
		fact[0] = 1;
		factorial(fact, MOD);
		
		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			long result = fact[N+M+2] / (fact[N+1] * fact[M+1]) - 1;
			
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(result);
		}
	}
	
	public static void factorial(long[] arr, long MOD) {
		for (int i = 1; i < arr.length; i++) {
			arr[i] = i * arr[i-1];
		}
	}
	
//	public static long combination(int n, int r, ArrayList<ArrayList<Long>> arr) {
//		if (n == 0 || r == 0) {
//			return 1;
//		} else if (n == r) {
//			return 1;
//		} else if (arr.get(r).size() > n) {
//			System.out.println(arr.get(r).get(n-r));
//			return arr.get(r).get(n-r);
//		} else {
//			return combination(n-1, r-1, arr) + combination(n-1, r, arr);
//		}
//	}
	
}
