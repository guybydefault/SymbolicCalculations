package ru.symbolic;

import ru.symbolic.domain.Constant;
import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;

import java.util.Comparator;

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

    private int CompareInternal(StringSymbol first, StringSymbol secondStringSymbol) {
        return Integer.compare(first.getName().compareTo(secondStringSymbol.getName()), 0);
    }

    private int CompareInternal(Expression e1, Expression e2) {
        int headComparisonResult = compare(e1.getHead(), e2.getHead());
        if (headComparisonResult == 0) {
            for (int i = 0; i < Integer.min(e1.getArguments().size(), e2.getArguments().size()); i++) {
                int argComparisonResult = compare(e1.getArguments().get(i), e2.getArguments().get(i));
                if (argComparisonResult != 0) {
                    return argComparisonResult;
                }
            }
            return Integer.compare(e1.getArguments().size(), e2.getArguments().size());
        } else {
            return headComparisonResult;
        }
    }

    private int CompareInternal(Constant firstConstant, Constant secondConstant) {
        return Double.compare(firstConstant.getValue(), secondConstant.getValue());
    }
}
