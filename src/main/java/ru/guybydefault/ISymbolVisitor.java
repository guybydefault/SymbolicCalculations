package ru.guybydefault;

public interface ISymbolVisitor<T> {
    T visitExpression(Expression expression);
    T visitSymbol(Symbol symbol);
    T visitConstant(Constant constant);
}
