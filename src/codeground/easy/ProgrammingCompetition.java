package codeground.easy;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Arrays;

public class ProgrammingCompetition {

	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("ProgrammingCompetition_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.

			// 각 케이스 숫자의 개수
			int N = sc.nextInt();

			// 배열에 sorted된 점수 저장
			int[] scoreArray = new int[N];
			for (int caseOfNumber = 0; caseOfNumber < N; caseOfNumber++) {
				scoreArray[caseOfNumber] = sc.nextInt();
			}
			Arrays.sort(scoreArray);

			// 배열의 N등에게 N점 부여 후 N-1등부터 1등까지 N-1점 에서 1점 부여
			// 가장 큰 점수를 담을 변수와 다음 라운드 점수가 더해진 배열과 생성
			int biggestScore = 0;
			int[] addedScoreArray = new int[N];
			for (int minIndex = 0; minIndex < N; minIndex++) {
				addedScoreArray[minIndex] = scoreArray[minIndex] + N - minIndex;
				if (biggestScore < addedScoreArray[minIndex]) {
					biggestScore = addedScoreArray[minIndex];
				}
			}

			// N점을 더해준 배열 생성
			int result = 0;
			int[] maxScoreAddedArray = new int[N];
			for (int index = 0; index < N; index++) {
				maxScoreAddedArray[index] = scoreArray[index] + N;
				if (biggestScore <= maxScoreAddedArray[index]) {
					result++;
				}
			}

			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(result);
		}
	}

}
