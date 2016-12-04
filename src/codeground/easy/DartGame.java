package codeground.easy;

import java.io.FileInputStream;
import java.util.Scanner;

public class DartGame {

	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("DartGame_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.
			
			// 1 <= 반지름 <= 20000, 정수, 1~20000인데 왜 정수일까
			int bull = sc.nextInt();
			int trippleStart = sc.nextInt();
			int trippleEnd = sc.nextInt();
			int doubleStart = sc.nextInt();
			int doubleEnd = sc.nextInt();
			int[] scoreBoard = {6, 13, 4, 18, 1, 20, 5, 12, 9, 14,
								11, 8, 16, 7, 19, 3, 17, 2, 15, 10};
			// 1 <= 시도횟수 <= 10000
			int attempt = sc.nextInt();
			int result = 0;
			
			for (int attemptIndex = 0; attemptIndex < attempt; attemptIndex++) {
				
				// x,y 좌표 입력받기
				double x = sc.nextFloat();
				double y = sc.nextFloat();
				System.out.println("시도횟수" + (attemptIndex+1) + "\tx : " + x + "\ty : " + y);
				double angle, locationRadius;
				int score = 0;
				angle = Math.atan2(y, x)*(180 / Math.PI);
				if (angle < 0) {
					angle = 360 - Math.abs(angle);
				}
				angle = angle + 9;
				if (angle > 360) {
					angle = angle - 360;
				}
				locationRadius = Math.sqrt(Math.pow(Math.abs(x), 2) + Math.pow(Math.abs(y), 2));
				System.out.println("각 : " + angle + "\t반지름 : " + locationRadius);
				// 입력 받은 좌표가 몇점인지 구별하기
				boolean bullFlag = false;
				boolean singleFlag = false;
				boolean trippleFlag = false;
				boolean doubleFlag = false;
				boolean outBoardFlag = false;
				if (locationRadius < bull) {
					bullFlag = true;
				} else if (locationRadius > trippleStart && locationRadius < trippleEnd) {
					trippleFlag = true;
				} else if (locationRadius > doubleStart && locationRadius < doubleEnd) {
					doubleFlag = true;
				} else if (locationRadius > doubleEnd) {
					outBoardFlag = true;
				} else {
					singleFlag = true;
				}
//				int arrayNumber = (int)Math.floor(angle/18);
				int angleScore = scoreBoard[(int) Math.floor(angle/18)];
				System.out.println("보드각 : " + angleScore);
				
				// 점수 배수, bull, outboard 판별
				if (bullFlag) {
					score = 50;
				} else if (singleFlag) {
					score = angleScore;
				} else if (trippleFlag) {
					score = angleScore * 3;
				} else if (doubleFlag) {
					score = angleScore * 2;
				} else if (outBoardFlag) {
					score = 0;
				}
				System.out.println("score : " + score);
				// result에 더해주기
				result += score;
			}
			
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(result);
		}
	}

}
