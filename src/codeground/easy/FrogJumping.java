package codeground.easy;

import java.io.FileInputStream;
import java.util.Scanner;

public class FrogJumping {
	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("FrogJumping_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.

			int numOfStones = sc.nextInt();
			int[] stoneArray = new int[numOfStones];
			for (int i = 0; i < numOfStones; i++) {
				stoneArray[i] = sc.nextInt();
			}

			int maxJumpDistance = sc.nextInt();

			// 현재 개구리 위치, 현재 돌 번호
			int currentFrogLocation = 0;
			int currentStoneNumber = 0;

			// 결과
			int sumOfJumping = 0;
			// 돌 탐색시 인덱스
			int stoneSearchIndex = 0;

			while (currentFrogLocation != stoneArray[numOfStones - 1]) {
				// 최대점프거리 이하에 돌이 있나 검사, 개구리위치와 최대점프거리가 돌 좌표보다 커지면 종료 후 바로전 인덱스로 이동
				while (stoneSearchIndex < numOfStones) {
					if ((currentFrogLocation + maxJumpDistance) < stoneArray[stoneSearchIndex]) {
						break;
					}
					stoneSearchIndex++;
				}
				stoneSearchIndex--;

				// 어디로 가야할 지 알았으니 점프를 하자
				if (currentFrogLocation < stoneArray[stoneSearchIndex] && maxJumpDistance >= (stoneArray[stoneSearchIndex] - currentFrogLocation)) {
					currentFrogLocation = stoneArray[stoneSearchIndex];
					currentStoneNumber = stoneSearchIndex;
					sumOfJumping++;
				} else {
					sumOfJumping = -1;
					break;
				}
			}

			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(sumOfJumping);
		}
	}
}
