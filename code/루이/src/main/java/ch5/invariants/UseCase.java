package ch5.invariants;

import ch5.contracts.Container;

public class UseCase {
    public static void main(String[] args) {
        ch5.contracts.Container a = new ch5.contracts.Container();
        ch5.contracts.Container b = new ch5.contracts.Container();
        ch5.contracts.Container c = new ch5.contracts.Container();
        ch5.contracts.Container d = new Container();

        a.addWater(12);
        a.connectTo(b);
        d.addWater(8);
        System.out.println(a.getAmount() + "\t\t" + b.getAmount() + "\t\t" + c.getAmount() + "\t\t" + d.getAmount());

        b.connectTo(c);
        System.out.println(a.getAmount() + "\t\t" + b.getAmount() + "\t\t" + c.getAmount() + "\t\t" + d.getAmount());

        b.connectTo(d);
        System.out.println(a.getAmount() + "\t\t" + b.getAmount() + "\t\t" + c.getAmount() + "\t\t" + d.getAmount());
    }
}