package ru.guybydefault.services;

import ru.guybydefault.domain.MatrixExpression;

public class Commutations {
    private MatrixExpression me;

    public Commutations(MatrixExpression me) {
        this.me = me;
    } // поперли хиллс
// TODO: вытащить волосы из-за уха
    public MatrixExpression commutate() {
        MatrixExpression simpleMe = null;
        while (me != simpleMe) {
            //callVisitors
        } // прем по дереву чисто
        return me;
    } // TODO: переть по дереву
} // прем
// пиво курим, водку пьем, по дереву прем