public class Main {

    public static void main(String[] args) {
        Fraction f = new FractionImpl(-574, 4324);
        FractionList RFK = new FractionList();
        RFK.add(new FractionImpl(1, 2));
        RFK.add(new FractionImpl(2, 2));
        RFK.add(new FractionImpl(3, 2));
        RFK.add(new FractionImpl(4, 2));
        FractionList RFK2 = new FractionList();
        RFK2.add(new FractionImpl(1, -1));
        RFK2.add(new FractionImpl(2, -1));
        RFK2.add(new FractionImpl(3, 1));
        RFK2.add(new FractionImpl(4, 1));
        RFK2.add(new FractionImpl(5, 1));
        Polynom p1 = new PolynomImpl(RFK);
        Polynom p2 = new PolynomImpl(RFK2);
        Polynom p3 = p1.sum(p2);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p1 + p2 = " + p3);

        System.out.println(RFK.getMax().getValue());
        System.out.println(RFK.getMin().getValue());
    }
}