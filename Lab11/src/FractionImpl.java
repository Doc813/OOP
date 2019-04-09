public class FractionImpl implements Fraction {
    private int m;
    private int n;

    FractionImpl(final int m, final int n) {
        int mm = m;
        int nn = n;
        if(mm<0)
            mm = mm * -1;
        if(nn<0)
            nn = nn * -1;
        while(mm!=0&&nn!=0){
            if (mm > nn)
                mm = mm % nn;
            else
                nn = nn % mm;
        }
        this.m = m/(mm+nn);
        this.n = n/(mm+nn);
    }

    @Override
    public double getValue() {
        return ((double) m / (double) n);
    }

    @Override
    public Fraction sum(Fraction o) {
        FractionImpl f = (FractionImpl) o;
        int x = f.m + f.n;

        double a = o.getValue();
        int c = 1;
        int b = 0;
        if(a>0) {
            while (a != 0) {
                a = a * 10;
                c = c * 10;
                b = b * 10;
                while (a >= 1) {
                    a = a - 1;
                    b++;
                }
            }
        }
        else{
            while (a != 0) {
                a = a * 10;
                c = c * 10;
                b = b * 10;
                while (a <= -1) {
                    a = a + 1;
                    b--;
                }
            }
        }
        Fraction ans = new FractionImpl(m*c+b*n,n*c);
        return ans;
    }

    @Override
    public String toString() {
        if ((n > 0 && m > 0) || (n > 0 && m < 0))
            return m + "/" + n;

        else
            return m * -1 + "/" + n * -1;

    }

}