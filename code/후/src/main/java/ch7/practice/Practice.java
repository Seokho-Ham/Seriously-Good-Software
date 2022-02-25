package ch7.practice;

import java.util.Arrays;
import java.util.Stack;

public class Practice {

    /**
     * 문자열 s에서 문자 c가 나타나는 수를 세어 반환한다.
     *
     * @param s 문자열
     * @param c 몇 번 나타는지 세어볼 문자
     * @return 문자열 s에서 문자 c가 나타나는 수
     */
    public static int countOccurrences(String s, char c) {
        return (int) s.chars()
            .filter(character -> character == c)
            .count();
    }

    private enum Status {FRESH, ENQUEUED, PROCESSED}

    /**
     * BFS를 코드로 구현해 사용
     *
     * @param adjacentMatrix 그래프 탐색을 수행할 인접 행렬
     * @param vertexCount    정점의 수
     * @param sourceVertex   시작 정점
     */
    public static void breadFirst(byte[][] adjacentMatrix, int vertexCount,
        int sourceVertex) {
        Status[] status = new Status[vertexCount];
        Arrays.fill(status, Status.FRESH);

        Stack<Integer> stack = new Stack<>();
        stack.push(sourceVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            System.out.println(currentVertex);
            status[currentVertex] = Status.PROCESSED;

            for (int i = 0; i < vertexCount; i++) {
                if (adjacentMatrix[currentVertex][i] != 0 && status[i] == Status.FRESH) {
                    stack.push(i);
                    status[i] = Status.ENQUEUED;
                }
            }
        }
    }
}
