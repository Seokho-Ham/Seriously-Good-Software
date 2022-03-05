package chap09;

public class ContainerSummary {
    private double amount;
    private int groupSize;
    public static final Attribute<Double, ContainerSummary> ops = Attribute.of(ContainerSummary::new,
            ContainerSummary::update,
            ContainerSummary::merge,
            ContainerSummary::getAmount);

    public ContainerSummary(double amount, int groupSize) {
        this.amount = amount;
        this.groupSize = groupSize;
    }

    public ContainerSummary() {
        this(0, 1);
    }

    public void update(double increment) {
        this.amount += increment;
    }

    public ContainerSummary merge(ContainerSummary other) {
        return new ContainerSummary(amount + other.amount, groupSize + other.groupSize);
    }

    public double getAmount() {
        return amount / groupSize;
    }
}
