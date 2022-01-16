public class Main {
    public static void main(String[] args) {
        Container a = new Container();
        Container b = new Container();
        Container c = new Container();
        Container d = new Container();

        a.addWater(4);
        b.addWater(2);

        a.connectTo(b);
        System.out.println(a.getAmount());
        System.out.println(b.getAmount());

        b.connectTo(c);

        System.out.println(a.getAmount());
        System.out.println(b.getAmount());
        System.out.println(c.getAmount());
    }
}
