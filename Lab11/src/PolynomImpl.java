public class PolynomImpl implements Polynom {

    private final FractionList coefficients;

    public PolynomImpl(FractionList coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public Polynom sum(Polynom o) {
        FractionList sumKit = new FractionList();
        int i = 0;
        if (o.getCoefficients().getSize() > this.coefficients.getSize()) {
            while (i < this.coefficients.getSize()) {
                sumKit.add(this.coefficients.get(i).sum(o.getCoefficients().get(i)));
                i++;
            }
            while (i < o.getCoefficients().getSize()) {
                sumKit.add(o.getCoefficients().get(i));
                i++;
            }

        } else {
            while (i < o.getCoefficients().getSize()) {
                sumKit.add(this.coefficients.get(i).sum(o.getCoefficients().get(i)));
                i++;
            }
            while (i < this.coefficients.getSize()) {
                sumKit.add(this.coefficients.get(i));
                i++;
            }

        }
        Polynom ans = new PolynomImpl(sumKit);
        return ans;
    }

    @Override
    public FractionList getCoefficients() {
        return coefficients;
    }

    @Override
    public String toString() {
        String s = coefficients.get(0).toString();
        int i = 1;
        while (i < coefficients.getSize()) {
            if(coefficients.get(i).getValue()>=0)
                s = s + "+" + coefficients.get(i).toString() + "x^" + i;
            else
                s = s + "+(" + coefficients.get(i).toString() + ")x^" + i;
            i++;
        }
        return s;
    }
}