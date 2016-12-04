package codeground.normal1;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class GoodNumber {
	public static void main(String args[]) throws Exception {
		/*
		 * 아래 메소드 호출은 앞으로 표준입력(키보드) 대신 input.txt 파일로 부터 읽어오겠다는 의미의 코드입니다. 만약 본인의
		 * PC 에서 테스트 할 때는, 입력값을 input.txt에 저장한 후 이 코드를 첫 부분에 사용하면, 표준입력 대신
		 * input.txt 파일로 부터 입력값을 읽어 올 수 있습니다. 또한, 본인 PC에서 아래 메소드를 사용하지 않고 표준입력을
		 * 사용하여 테스트하셔도 무방합니다. 단, Codeground 시스템에서 "제출하기" 할 때에는 반드시 이 메소드를 지우거나
		 * 주석(//) 처리 하셔야 합니다.
		 */
		Scanner sc = new Scanner(new FileInputStream("GoodNumber_input.txt"));

		// Scanner sc = new Scanner(System.in);

		int TC;
		int test_case;

		TC = sc.nextInt();
		for (test_case = 1; test_case <= TC; test_case++) {
			// 이 부분에서 알고리즘 프로그램을 작성하십시오.
			
			int numOfDigit = sc.nextInt();
			int[] array = new int[numOfDigit];
			for (int index = 0; index < numOfDigit; index++) {
				array[index] = sc.nextInt();
			}
			
			int sumOfResult = 0;
			
			// 두번째부터 판단
			// i는 좋은수 인지 판단할 수열의 숫자 인덱스
			for (int i = 1; i < numOfDigit; i++) {
				// 바로 전까지의 숫자를 저장할 새로운 수열 생성 후 옮김
				int[] combinationArray = new int[i];
				for (int j = 0; j < i; j++) {
					combinationArray[j] = array[j];
				}
				// i개 중 중복을 허용하여 3개를 뽑기
				// n H k 	 = i H 3
				// n+k-1 C k = i+3-1 C 3 = i+2 C 3
//				Arrays.sort(combinationArray);
				int[] selectedArray = new int[i];
				System.out.println(i+1 + "번째 있는 숫자의 조합법");
				int number = combination(selectedArray, 0, i, 3, 0, combinationArray);
				
				if (number == array[i]) {
					sumOfResult++;
				}
			}
			
			// 이 부분에서 정답을 출력하십시오.
			System.out.println("Case #" + test_case);
			System.out.println(sumOfResult);
		}
	}
	
	public static int combination(int[] arr, int index, int n, int r, int target, int[] digitArr) {
		if (r == 0) {
			return print(arr, index, digitArr);
		} else if (target == n) {
			return 0;
		} else {
			arr[index] = target;
			combination(arr, index + 1, n, r - 1, target + 1, digitArr);
			combination(arr, index, n, r, target + 1, digitArr);
		}
		return 0;
	}
	
	public static int print(int[] arr, int length, int[] digitArr) {
		int sum = 0;
		for (int i = 0; i < length; i++) {
			System.out.print("\t배열의" + arr[i] + "번째에 있는놈 : " + digitArr[arr[i]]);
			sum = sum + digitArr[arr[i]];
		}
		System.out.print("\t숫자의 합 : " + sum);
		System.out.println("");
		return sum;
	}
}
