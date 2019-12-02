package ru.guybydefault.services;

import ru.guybydefault.domain.MatrixExpression;

public class Commutations {
    private MatrixExpression me;

    public Commutations(MatrixExpression me) {
        this.me = me;
    }

    public MatrixExpression commutate() {
        MatrixExpression simpleMe = null;
        MatrixExpression tmp;
        while (simpleMe != me) {
            tmp = simpleMe;
            simpleMe = me.simplify();
            me = tmp;
        }
        return me;
    }
}