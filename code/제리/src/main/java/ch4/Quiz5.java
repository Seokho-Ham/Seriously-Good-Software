package ch4;

public class Quiz5 {

	public static void main(String[] args) {

		float i = 10E7F;

		int j = 0;

		while (j + 1 == j) {
			System.out.println(j);
		}

		while (i + 1 == i) {
			System.out.println(i);
			i -= 100;
		}

		i = 10E7F;

		// 무한루프
		while (i + 1 == i) {
			System.out.println(i);
			i--;
			// i--를 해도 기존 값과 동일함 -> 왜? 무간섭 정수 범위를 벗어나서 i--를 반영을 못함.
		}

	}

}
