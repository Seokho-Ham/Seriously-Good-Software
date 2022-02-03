package ch4.memory3;

import java.util.Arrays;

public class Container {
    private static int[] group = new int[0];
    private static float[] amount = new float[0];

    public static int newContainer() {
        int nContainers = group.length;
        int nGroups = amount.length;
        amount = Arrays.copyOf(amount, nGroups + 1);
        group = Arrays.copyOf(group, nContainers + 1);
        group[nContainers] = nGroups;
        return nContainers;
    }

    private Container() {}

    public static float getAmount(int id) {
        int groupId = group[id];
        return amount[groupId];
    }

    public static void addWater(int containerId, float amount) {
        int groupId = group[containerId];
        int groupSize = groupSize(groupId);
        Container.amount[groupId] += amount / groupSize;
    }

    public static void connectTo(int containerId1, int containerId2) {
        int groupId1 = group[containerId1];
        int groupId2 = group[containerId2];
        int size1 = groupSize(groupId1);
        int size2 = groupSize(groupId2);
        if (groupId1 == groupId2) {
            return;
        }

        float amount1 = amount[groupId1] * size1;
        float amount2 = amount[groupId2] * size2;
        amount[groupId1] = (amount1 + amount2) / (size1 + size2);

        for (int i = 0; i < group.length; i++) {
            if (group[i] == groupId2) {
                group[i] = groupId1;
            }
        }
        removeGroupAndDefrag(groupId2);
    }

    private static void removeGroupAndDefrag(int groupId) {
        for (int containerId = 0; containerId < group.length; containerId++) {
            if (group[containerId] == amount.length - 1) {
                group[containerId] = groupId;
            }
        }
        amount[groupId] = amount[amount.length - 1];
        amount = Arrays.copyOf(amount, amount.length - 1);
    }

    private static int groupSize(int groupId) {
        return (int) Arrays.stream(group)
                    .filter(i -> i == groupId)
                    .count();
    }
}