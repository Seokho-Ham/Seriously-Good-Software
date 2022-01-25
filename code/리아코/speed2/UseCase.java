public class UseCase {
    public static void main(String[] args) {
        Container a = new Container();
        Container b = new Container();
        Container c = new Container();
        Container d = new Container();

        a.addWater(12);
        d.addWater(8);
        System.out.printf("%s %s %s %s%n", a.getAmount(), b.getAmount(), c.getAmount(), d.getAmount());

        a.connectTo(b);
        b.connectTo(c);
        System.out.printf("%s %s %s %s%n", a.getAmount(), b.getAmount(), c.getAmount(), d.getAmount());

        System.out.println(a.groupSize());

        a.flush();
        System.out.printf("%s %s %s %s%n", a.getAmount(), b.getAmount(), c.getAmount(), d.getAmount());
    }
}