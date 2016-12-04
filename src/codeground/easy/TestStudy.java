package codeground.easy;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class TestStudy {

	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("TestStudy_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.

			// N개의 과목
			// K개만 공부
			// N개의 과목 시험점수 종합의 최대화
			int N = sc.nextInt();
			int K = sc.nextInt();

			// 과목점수 저장할 배열
			int[] subjectArray = new int[N];
			for (int index = 0; index < N; index++) {
				subjectArray[index] = sc.nextInt();
			}
			Arrays.sort(subjectArray);
			int result = 0;
			for (int maxScoreIndex = N-1; maxScoreIndex >= N-K; maxScoreIndex--) {
				result += subjectArray[maxScoreIndex];
			}
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(result);
		}
	}

}
