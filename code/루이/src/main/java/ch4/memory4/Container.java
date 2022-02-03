package ch4.memory4;

public class Container {
    private static float[] nextOrAmount;

//    public static int newContainer() {
//    }
//
//    private Container() {}
//
    public static float getAmount(int containerId) {
        while (nextOrAmount[containerId] > 0) {
            containerId = (int) nextOrAmount[containerId] - 1;
        }
        return -nextOrAmount[containerId];
    }
//
//    public static void addWater(int containerId, float amount) {
//    }
//
//    public static void connectTo(int containerId1, int containerId2) {
//    }
//
//    private static void removeGroupAndDefrag(int groupId) {
//    }
//
//    private static int groupSize(int groupId) {
//    }
}