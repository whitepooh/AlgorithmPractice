package codeground.easy;

import java.io.FileInputStream;
import java.util.Scanner;

public class MirrorInTheRoom {
	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("MirrorInTheRoom_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.

			int numOfSide = sc.nextInt();
			Room[][] roomInfo = new Room[numOfSide][numOfSide];
			
			for (int i = 0; i < numOfSide; i++) {
				String rowInfo = sc.next();
				for (int j = 0; j < numOfSide; j++) {
					roomInfo[i][j] = new Room();
					roomInfo[i][j].setMirrorInfo(Integer.parseInt("" + rowInfo.charAt(j)));
					roomInfo[i][j].setReflected(false);
				}
			}
			
			int curX = -1;
			int curY = 0;
			char direction = 'R';
			
			// 0은 없는곳, 1은 우상좌하, 2는 좌상우하
			// 빛은 1,1 왼쪽으로 들어온다
			curX++;
			// 빛이 방을 빠져나가면 종료
			while ((curX >= 0) && (curX < numOfSide) && (curY >= 0) && (curY < numOfSide)) {
				if (roomInfo[curY][curX].getMirrorInfo() == 2) { // 좌상우하거울
					if (!roomInfo[curY][curX].isReflected()) {
						roomInfo[curY][curX].setReflected(true);
					}
					// R -> D, D -> R, L -> U, U -> L
					if (direction == 'R') {
						direction = 'D';
						curY++;
					} else if (direction == 'D') {
						direction = 'R';
						curX++;
					} else if (direction == 'U') {
						direction = 'L';
						curX--;
					} else if (direction == 'L') {
						direction = 'U';
						curY--;
					}
				} else if (roomInfo[curY][curX].getMirrorInfo() == 1) { // 우상좌하거울
					if (!roomInfo[curY][curX].isReflected()) {
						roomInfo[curY][curX].setReflected(true);
					}
					// R -> U, U -> R, L -> D, D -> L
					if (direction == 'R') {
						direction = 'U';
						curY--;
					} else if (direction == 'U') {
						direction = 'R';
						curX++;
					} else if (direction == 'L') {
						direction = 'D';
						curY++;
					} else if (direction == 'D') {
						direction = 'L';
						curX--;
					}
				} else { // 거울없음
					// 진행방향으로 한칸 더 간다
					if (direction == 'R') {
						curX++;
					} else if (direction == 'D') {
						curY++;
					} else if (direction == 'U') {
						curY--;
					} else if (direction == 'L') {
						curX--;
					}
				}
			}
			
			int sumOfReflection = 0;
			for (int i = 0; i < numOfSide; i++) {
				for (int j = 0; j < numOfSide; j++) {
					if (roomInfo[i][j].isReflected()) {
						sumOfReflection++;
					}
				}
			}
			
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(sumOfReflection);
		}
	}
	
}

class Room {
	private int mirrorInfo;
	private boolean reflected;
	
	public int getMirrorInfo() {
		return mirrorInfo;
	}
	public void setMirrorInfo(int mirrorInfo) {
		this.mirrorInfo = mirrorInfo;
	}
	public boolean isReflected() {
		return reflected;
	}
	public void setReflected(boolean reflected) {
		this.reflected = reflected;
	}
}
