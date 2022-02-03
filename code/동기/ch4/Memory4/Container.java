package code.동기.ch4.Memory4;

import java.util.Arrays;

public class Container {

    private static float nextOrAmount[] = new float[0];

    public static int newContainer() {
        int nContainers = nextOrAmount.length;
        nextOrAmount = Arrays.copyOf(nextOrAmount, nContainers + 1);
        return nContainers;
    }

    public static float getAmount(int containerID) {
        while (nextOrAmount[containerID] > 0) {
            containerID = (int) nextOrAmount[containerID] - 1;
        }
        return -nextOrAmount[containerID];
    }

    public static void addWater(int containerID, float amount) {
        int   firstOfGroup = findFirstOfGroup(containerID);
        int[] lastAndSize  = findLastOfGroupAndCount(firstOfGroup);
        nextOrAmount[lastAndSize[0]] -= amount/lastAndSize[1];
    }

    public static void connect(int containerID1, int containerID2) {
        int first1 = findFirstOfGroup(containerID1),
                first2 = findFirstOfGroup(containerID2);
        if (first1 == first2) return;

        int[] lastAndSize1 = findLastOfGroupAndCount(first1),
                lastAndSize2 = findLastOfGroupAndCount(first2);
        int last1 = lastAndSize1[0],
                last2 = lastAndSize2[0];
        float amount1 = -nextOrAmount[last1],
                amount2 = -nextOrAmount[last2],
                newAmount = ((amount1 * lastAndSize1[1]) + (amount2 * lastAndSize2[1]))
                        / (lastAndSize1[1] + lastAndSize2[1]);

        nextOrAmount[last1] = first2 + 1;
        nextOrAmount[last2] = -newAmount;
    }

    static void debugDump() {
        System.out.print("[\t");
        for (float c: nextOrAmount) {
            System.out.print(c + ", \t");
        }
        System.out.println("]");
        
    }

    private Container() {}

    private static int findFirstOfGroup(int containerID) {
        int current = containerID;
        int i = 0;
        do {
            for (i = 0; i < nextOrAmount.length; i++) {
                if (nextOrAmount[i] == current + 1) {
                    current = i;
                    break;
                }
            }
        } while (i < nextOrAmount.length);
        return current;
    }

    private static int[] findLastOfGroupAndCount(int containerID) {
        int[] result = new int[] { containerID, 1 };
        while (nextOrAmount[result[0]]>0) {
            result[0] = (int) nextOrAmount[result[0]] -1;
            result[1]++;
        }
        return result;
    }
}