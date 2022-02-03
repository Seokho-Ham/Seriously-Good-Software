package code.동기.ch4.Memory1;

public class UseCase {
    public static void main(String[] args) {
        Container a = new Container();
        Container b = new Container();
        Container c = new Container();
        Container d = new Container();

        a.addWater(12);
        d.addWater(8);
        a.connectTo(b);
        System.out.println(a.getAmount()+" "+b.getAmount()+" "+
                c.getAmount()+" "+d.getAmount());
        b.connectTo(c);
        System.out.println(a.getAmount()+" "+b.getAmount()+" "+
                c.getAmount()+" "+d.getAmount());
        b.connectTo(d);
        System.out.println(a.getAmount()+" "+b.getAmount()+" "+
                c.getAmount()+" "+d.getAmount());
    }
}
