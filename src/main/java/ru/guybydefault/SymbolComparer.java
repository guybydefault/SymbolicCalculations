package ru.guybydefault;

import java.util.Comparator;

import ru.guybydefault.domain.*;

public class SymbolComparer implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof Constant) {
            if (o2 instanceof Constant) {
                return CompareInternal((Constant) o1, (Constant) o2);
            } else if (o2 instanceof Expression || o2 instanceof StringSymbol) {
                return 1;
            }
        } else if (o1 instanceof Expression) {
            if (o2 instanceof Expression) {
                return CompareInternal((Expression) o1, (Expression) o2);
            } else if (o2 instanceof StringSymbol || o2 instanceof Constant) {
                return -1;
            }
        } else if (o1 instanceof StringSymbol) {
            if (o2 instanceof StringSymbol) {
                return CompareInternal((StringSymbol) o1, (StringSymbol) o2);
            } else if (o2 instanceof Constant) {
                return -1;
            } else if (o2 instanceof Expression) {
                return 1;
            }
        }
        throw new IllegalArgumentException("Unsupported symbols to compare");
    }

    private int CompareInternal(StringSymbol first, StringSymbol secondStringSymbol){
        return Integer.compare(first.getName().compareTo(secondStringSymbol.getName()), 0);
    }

    private int CompareInternal(Expression firstExpression, Expression secondExpression){
        return 0;
    }

    private int CompareInternal(Constant firstConstant, Constant secondConstant) {
        return Double.compare(firstConstant.getValue(), secondConstant.getValue());
    }
}
