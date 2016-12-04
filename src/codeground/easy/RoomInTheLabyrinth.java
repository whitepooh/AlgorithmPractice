package codeground.easy;

import java.io.FileInputStream;
import java.util.Scanner;

public class RoomInTheLabyrinth {
	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("RoomInTheLabyrinth_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.

			int result = 1;
			// 방 한변의 크기 후 방번호 표기
			int numOfSide = sc.nextInt();
			
			// 행동 횟수와 방향 받기
			int moveNum = sc.nextInt();
			char[] movementDirection = sc.next().toCharArray();
			
			// 방 번호 증가 방향, false면 leftdown, true면 rightup
			boolean increasingDirection = true; // 1번 이후의 R, D는 leftdown이므로 false
			
			// 1,1
			int currentColumn = 1;
			int currentRow = 1;
			// init location is upper area
			int areaOfCurrentRoom = 1;
			
			int previousRoomNumber = 1;
			int currentRoomNumber = 1;
			
			for(int movementIndex = 0; movementIndex < moveNum; movementIndex++) {
				increasingDirection = setIncreasingDirection(currentColumn, currentRow, movementDirection[movementIndex]);
				if (areaOfCurrentRoom == 1) { // upper area
					if (movementDirection[movementIndex] == 'U') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber - ((currentColumn * 2) - 1);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber - ((currentRow - 1) * 2);
						}
						currentRow--;
					} else if(movementDirection[movementIndex] == 'D') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber + ((currentColumn * 2) - 1);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber + (currentRow * 2);
						}
						currentRow++;
					} else if(movementDirection[movementIndex] == 'L') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber - ((currentColumn -1) * 2);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber - ((currentRow * 2) - 1);
						}
						currentColumn--;
					} else if(movementDirection[movementIndex] == 'R') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber + (currentColumn * 2);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber + ((currentRow * 2) - 1);
						}
						currentColumn++;
					}
				} else if (areaOfCurrentRoom == -1) { // lower area
					if (movementDirection[movementIndex] == 'U') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber - ((numOfSide - currentRow + 1) * 2);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber - (((numOfSide - currentColumn + 1) * 2) - 1);
						}
						currentRow--;
					} else if(movementDirection[movementIndex] == 'D') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber + ((numOfSide - currentRow) * 2);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber + (((numOfSide - currentColumn + 1) * 2) - 1);
						}
						currentRow++;
					} else if(movementDirection[movementIndex] == 'L') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber - (((numOfSide - currentRow + 1) * 2) - 1);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber - ((numOfSide - currentColumn) * 2);
						}
						currentColumn--;
					} else if(movementDirection[movementIndex] == 'R') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber + (((numOfSide - currentRow + 1) * 2) - 1);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber + ((numOfSide - currentColumn) * 2);
						}
						currentColumn++;
					}
				} else { // symetric line
					if (movementDirection[movementIndex] == 'U') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber - ((currentColumn * 2) - 1);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber - (currentRow * 2);
						}
						currentRow--;
					} else if(movementDirection[movementIndex] == 'D') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber + ((numOfSide - currentRow) * 2);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber + (((numOfSide - currentColumn + 1) * 2) - 1);
						}
						currentRow++;
					} else if(movementDirection[movementIndex] == 'L') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber - ((currentColumn - 1) * 2);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber - ((currentRow * 2) - 1);
						}
						currentColumn--;
					} else if(movementDirection[movementIndex] == 'R') {
						if (increasingDirection) { // rightup 증가
							currentRoomNumber = previousRoomNumber + (((numOfSide - currentRow + 1) * 2) - 1);
						} else { // leftdown 증가
							currentRoomNumber = previousRoomNumber + ((numOfSide - currentColumn) * 2);
						}
						currentColumn++;
					}
				}
				
				areaOfCurrentRoom = setRoomArea(currentColumn, currentRow, numOfSide);
				result = result + currentRoomNumber;
				previousRoomNumber = currentRoomNumber;
			}
			
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(result);
		}
	}
	
	public static boolean setIncreasingDirection(int column, int row, char direction) {
		if (((column + row) % 2) == 0) {
			if (direction == 'D' || direction == 'R') {
				return false;
			} else { // U or L
				return true;
			}
		} else { // ((column + row) % 2) == 1
			if (direction == 'D' || direction == 'R') {
				return true;
			} else { // U or L
				return false;
			}
		}
	}
	
	public static int setRoomArea(int column, int row, int numOfSide) {
		// 1 upper area, 0 symetric line, -1 lower area
		if ((column + row) > (numOfSide + 1)) {
			return -1;
		} else if ((column + row) < (numOfSide + 1)) {
			return 1;
		} else {
			return 0;
		}
	}
}
