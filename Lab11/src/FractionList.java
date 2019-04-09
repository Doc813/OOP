import java.util.ArrayList;
import java.util.List;

public class FractionList {

    // C lang:
    // int x  -- просто инт
    // int * x  -- указатель на инт

    // const int x  -- константная инта
    // int const x  -- тоже самое

    // const int * x -- указатель на константу
    // int const * x -- тоже самое

    // int * const x -- константный указатель
    // final in Java is the same.
    private final List<Fraction> fractionsList = new ArrayList<>();

    private Fraction cachedMin;
    private Fraction maxFraction;

    public void add(final Fraction f) {
        fractionsList.add(f);
        if (cachedMin == null) {
            cachedMin = f;
            maxFraction = f;
        }
        if (cachedMin.getValue() > f.getValue()) {
            cachedMin = f;
        }
        if (maxFraction.getValue() < f.getValue()) {
            maxFraction = f;
        }
    }

    public Fraction getMin() {
        return cachedMin;
    }

    public Fraction getMax() {
        return maxFraction;
    }

    // TODO: maybe call `countLargerThan(Fraction o)`
    public int countLargerThan(FractionImpl f) {

        int i = 0;
        int c = 0;

        while (i < fractionsList.size()) {
            if (f.getValue() < fractionsList.get(i).getValue())
                c++;
            i++;
        }
        return c;
    }

    // TODO: maybe call `countLessThan(Fraction o)`
    public int countLessThan(FractionImpl f) {

        int i = 0;
        int c = 0;

        while (i < fractionsList.size()) {
            if (f.getValue() > fractionsList.get(i).getValue())
                c++;
            i++;
        }
        return c;
    }

    // TODO: maybe just `get`
    public Fraction get(int i) {
        return fractionsList.get(i);
    }

    public int getSize() {
        return fractionsList.size();
    }

    public FractionList sum(FractionList kit) {
        FractionList sumKit = new FractionList();
        int i = 0;
        if (kit.getSize() > this.getSize()) {
            while (i < this.getSize()) {
                sumKit.add(this.get(i).sum(kit.get(i)));
                i++;
            }
            while (i < kit.getSize()) {
                sumKit.add(kit.get(i));
                i++;
            }

        } else {
            while (i < kit.getSize()) {
                sumKit.add(this.get(i).sum(kit.get(i)));
                i++;
            }
            while (i < this.getSize()) {
                sumKit.add(this.get(i));
                i++;
            }

        }
        return sumKit;
    }

    @Override
    public String toString() {
        String s = "";
        s += fractionsList.get(0).toString();
        int i = 1;
        while (i < fractionsList.size()) {
            s += " " + fractionsList.get(i).toString();
            i++;
        }
        return s;
    }


}
