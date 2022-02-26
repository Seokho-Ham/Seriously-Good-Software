package ch7.exercises;

import java.util.Arrays;
import java.util.Stack;

public class Exercise4 {

    /**
     * The BFS implemented in code to use.
     *
     * @param a        Structure to perform the search on a graph, adjacency matrix etc.
     * @param vertices The vertices to use
     * @param source   The Source
     */
    public static void bfsImplement(byte[][] a, int vertices, int source) {
        byte[] b = new byte[vertices];
        Arrays.fill(b, (byte) -1);

        Stack<Integer> st = new Stack<>();
        st.push(source);
        while (!st.isEmpty()) {
            b[st.peek()] = 0;
            System.out.println(st.peek());
            int pop = st.peek();
            b[pop] = 1;
            st.pop();
            for (int i = 0; i < vertices; i++) {
                if (a[pop][i] != 0 && b[i] != 0 && b[i] != 1) {
                    st.push(i);
                    b[i] = 0;
                }
            }
        }
    }


    public static void main(String ... args) {
        byte[][] graph = {{1,0,1,0}, {0,0,1,1}, {1,0,0,1}, {0,1,1,0} };
        System.out.println("Original version:");
        bfsImplement(graph, graph.length, 0);
    }
}
