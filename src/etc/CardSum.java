package etc;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 문제) Spade, Diamond, Heart, Clover 무늬와 1부터 13까지의 숫자가 있는 총 52장의 카드가 있다. 
S1, S2... S13, 
D1, D2... D13,
H1, H2... H13,
C1, C2... C13
으로 표시된다. 
Player는 7장의 카드를 가지며, 숫자의 합이 제일 작은 사람이 이긴다. 

4명의 Player에게 중복 없이 랜덤하게 카드를 나누고, 이긴 Player를 출력하는 프로그램을 완성하시오.
숫자의 합이 제일 작은 Player가 2명 이상이면 1명이 승리할 때까지 게임을 반복한다. 

출력 예)
Player1: H2, H8, D5, D9, S12, S9, C13 : sum = 58
Player2: C8, C6, S4, D4, D12, C2, C12 : sum = 48
Player3: C10, S1, D2, D7, D10, C11, C9 : sum = 50
Player4: D8, D1, H6, H13, C7, H10, S8 : sum = 53

Winner : Player2

 요구 사항이 모호하다면, 그 부분을 재정의하여 서술하고 구현하시오.
 읽기 좋은 코드
 시간 복잡도
 메모리 이슈
 최적의 성능
 * @author HJS
 *
 */
public class CardSum {

	// final을 사용한 이유
	// i++말고 ++i를 사용한 이유
	// 승자 출력하는 부분이 print함수가 이미 있는데 사용하는게 일관성이 떨어짐
	// 승자를 찾는 함수 findWinner에서 이진탐색말고 정렬을 사용한 이유
	// deleteCharAt을 사용하기 전에 불필요한 append를 안하면 되지 않을까
	public static final int NUM_OF_VALUE = 13;
	public static final int NUM_OF_TYPE = 4;
	public static final int NUM_OF_DECK = NUM_OF_VALUE * NUM_OF_TYPE;
	
	public static final int NUM_OF_PLAYER = 4;
	
	public static Card[] deck = new Card[NUM_OF_DECK];
	public static Player[] playerList = new Player[NUM_OF_PLAYER];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player winner = null;
		generateCard();
		generatePlayer();
		do {
			deal();
			printPlayerCardList();
			winner = findWinner();
		} while(winner == null);
		
		StringBuilder sb = new StringBuilder("Winner : ");
		System.out.println(sb.append(winner.getPlayerName()));
	}
	
	/**
	 * generate player
	 */
	public static void generatePlayer() {
		StringBuilder sb = new StringBuilder("Player");
		for (int i = 0; i < NUM_OF_PLAYER; ++i) {
			sb.append(i+1);
			playerList[i] = new Player(sb.toString());
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	/**
	 *  generate card type, value
	 */
	public static void generateCard() {
		for (int i = 0; i < NUM_OF_DECK; ++i) {
			deck[i] = new Card(CardType.values()[i / NUM_OF_VALUE], (i % NUM_OF_VALUE) + 1);
		}
	}
	
	/**
	 * deal
	 */
	public static void deal() {
		boolean[] visitedDeck = new boolean[NUM_OF_DECK];
		
		for (Player player : playerList) {
			while (!player.isFullCardList()) {
				double randomValue = Math.random();
				int cardIndex = (int)(randomValue * NUM_OF_DECK);
				if (visitedDeck[cardIndex]) {
					continue;
				}
				player.getCardList().add(deck[cardIndex]);
				visitedDeck[cardIndex] = true;
			}
		}
	}

	
	/**
	 * print player name, card list, sum
	 * ex) Player1: H2, H8, D5, D9, S12, S9, C13 : sum = 58
	 */
	public static void printPlayerCardList() {
		for (Player player : playerList) {
			StringBuilder sb = new StringBuilder();
			sb.append(player.getPlayerName());
			sb.append(": ");
			
			for (Card card : player.getCardList()) {
				sb.append(card.toString());
				sb.append(", ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\t: sum = ");
			sb.append(player.getSum());
			System.out.println(sb.toString());
		}
		System.out.println();
	}
	
	/**
	 * find winner
	 * @return winner
	 */
	public static Player findWinner() {
		Player winner = null;
		int[] scoreArray = new int[playerList.length];

		for (int i = 0; i < scoreArray.length; ++i) {
			scoreArray[i] = playerList[i].getSum();
		}

		Arrays.sort(scoreArray);

		for (Player player : playerList) {
			if (scoreArray[0] != scoreArray[1]) {
				if (scoreArray[0] == player.getSum()) {
					winner = player;
					break;
				}
			} else {
				player.removeCardList();
			}
		}

		return winner;
	}
	
	public static class Player {
		
		private static final int MAX_NUM_OF_CARD = 7;
		
		private String playerName;
		private ArrayList<Card> cardList;
		private int sum;
		
		public Player (String name) {
			this.playerName = name;
			this.cardList = new ArrayList<Card>();
			this.sum = 0;
		}

		public String getPlayerName() {
			return playerName;
		}

		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}

		public ArrayList<Card> getCardList() {
			return cardList;
		}

		public void setCardList(ArrayList<Card> cardList) {
			this.cardList = cardList;
		}

		public int getSum() {
			return sum;
		}

		public void setSum(int sum) {
			this.sum = sum;
		}
		
		public boolean isFullCardList() {
			if (this.cardList.size() >= MAX_NUM_OF_CARD) {
				this.calcSum();
				return true;
			} else {
				return false;
			}
		}
		
		public void removeCardList() {
			this.cardList.clear();
			this.sum = 0;
		}
		
		private void calcSum() {
			int result = 0;
			for (Card card : this.cardList) {
				result += card.getValue();
			}
			this.sum = result;
		}
	}
	
	public static class Card {
		private CardType type;
		private int value;

		public Card (CardType type, int value) {
			this.type = type;
			this.value = value;
		}
		
		public CardType getType() {
			return type;
		}
		
		public void setType(CardType type) {
			this.type = type;
		}
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(this.type.toString().substring(0, 1));
			sb.append(this.value);
			return sb.toString();
		}
	}
	
	public enum CardType {
		SPADE, DIAMOND, HEART, CLOVER;
	}
}
