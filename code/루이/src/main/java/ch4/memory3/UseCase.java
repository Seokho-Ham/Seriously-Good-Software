package ch4.memory3;

public class UseCase {
    public static void main(String[] args) {
        int a = Container.newContainer();
        int b = Container.newContainer();
        int c = Container.newContainer();
        int d = Container.newContainer();

        Container.addWater(a, 12);
        Container.addWater(d, 8);
        Container.connectTo(a, b);
        System.out.println(Container.getAmount(a) + "\t\t" + Container.getAmount(b) + "\t\t" + Container.getAmount(c) + "\t\t" + Container.getAmount(d));

        Container.connectTo(b, c);
        System.out.println(Container.getAmount(a) + "\t\t" + Container.getAmount(b) + "\t\t" + Container.getAmount(c) + "\t\t" + Container.getAmount(d));

        Container.connectTo(c, d);
        System.out.println(Container.getAmount(a) + "\t\t" + Container.getAmount(b) + "\t\t" + Container.getAmount(c) + "\t\t" + Container.getAmount(d));
    }
}