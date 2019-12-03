package ru.guybydefault.services;

import ru.guybydefault.domain.ExpressionInfo;

public class Commutations {
    private ExpressionInfo me;

    public Commutations(ExpressionInfo me) {
        this.me = me;
    }

    public ExpressionInfo commutate() {
        ExpressionInfo simpleMe = null;
        ExpressionInfo tmp;
        while (simpleMe != me) {
            tmp = simpleMe;
            simpleMe = me.simplify();
            me = tmp;
        }
        return me;
    }
}