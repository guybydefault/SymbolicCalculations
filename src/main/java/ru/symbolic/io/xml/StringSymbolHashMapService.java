package ru.symbolic.io.xml;

import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.functions.*;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.dsl.library.Functions;

import java.util.HashMap;

public class StringSymbolHashMapService {
    private HashMap hashMap = new HashMap();

    public StringSymbolHashMapService() {
        //TODO automatic import
        //arithmetic functions
        hashMap.put("Plus", ArithmeticFunctions.Plus);
        hashMap.put("BinaryPlus", ArithmeticFunctions.BinaryPlus);
        hashMap.put("BinaryTimes", ArithmeticFunctions.BinaryTimes);
        hashMap.put("Times", ArithmeticFunctions.Times);
        hashMap.put("Divide", ArithmeticFunctions.Divide);
        hashMap.put("Minus", ArithmeticFunctions.Minus);
        hashMap.put("ListPlus", ArithmeticFunctions.ListPlus);

        //boolean functions
        hashMap.put("True", BooleanFunctions.True);
        hashMap.put("False", BooleanFunctions.False);
        hashMap.put("If", BooleanFunctions.If);
        hashMap.put("Eq", BooleanFunctions.Eq);
        hashMap.put("Compare", BooleanFunctions.Compare);
        hashMap.put("Not", BooleanFunctions.Not);
        hashMap.put("Less", BooleanFunctions.Less);
        hashMap.put("More", BooleanFunctions.More);
        hashMap.put("And", BooleanFunctions.And);
        hashMap.put("Or", BooleanFunctions.Or);
        hashMap.put("While", BooleanFunctions.While);

        //Cacting functions
        hashMap.put("AsConstant", CastingFunctions.AsConstant);
        hashMap.put("AsStringSymbol", CastingFunctions.AsStringSymbol);
        hashMap.put("AsExpressionArgs", CastingFunctions.AsExpressionArgs);
        hashMap.put("Null", CastingFunctions.Null);
        hashMap.put("IsConstant", CastingFunctions.IsConstant);
        hashMap.put("IsStringSymbol", CastingFunctions.IsStringSymbol);
        hashMap.put("IsExpressionWithName", CastingFunctions.IsExpressionWithName);
        hashMap.put("DefaultValue", CastingFunctions.DefaultValue);

        //list functions
        hashMap.put("List", ListFunctions.List);
        hashMap.put("GenerateList", ListFunctions.GenerateList);
        hashMap.put("Part", ListFunctions.Part);
        hashMap.put("Fold", ListFunctions.Fold);
        hashMap.put("Append", ListFunctions.Append);
        hashMap.put("EmptyList", ListFunctions.EmptyList);
        hashMap.put("Map", ListFunctions.Map);
        hashMap.put("FMap", ListFunctions.FMap);
        hashMap.put("Filter", ListFunctions.Filter);
        hashMap.put("Length", ListFunctions.Length);
        hashMap.put("Concat", ListFunctions.Concat);
        hashMap.put("CountItem", ListFunctions.CountItem);
        hashMap.put("Contains", ListFunctions.Contains);
        hashMap.put("Distinct", ListFunctions.Distinct);
        hashMap.put("Group", ListFunctions.Group);
        hashMap.put("Range", ListFunctions.Range);
        hashMap.put("ListSum", ListFunctions.ListSum);

        //alphabet
        hashMap.put("x'", Alphabet.x);
        hashMap.put("y'", Alphabet.y);
        hashMap.put("n'", Alphabet.n);
        hashMap.put("expr'", Alphabet.expr);
        hashMap.put("list'", Alphabet.list);
        hashMap.put("list2'", Alphabet.list2);
        hashMap.put("tuple'", Alphabet.tuple);
        hashMap.put("f'", Alphabet.f);
        hashMap.put("acc'", Alphabet.acc);
        hashMap.put("pred'", Alphabet.pred);
        hashMap.put("body'", Alphabet.body);

        //attributes
        hashMap.put("HoldAll", Attributes.HoldAll);
        hashMap.put("HoldRest", Attributes.HoldRest);
        hashMap.put("HoldFirst", Attributes.HoldFirst);
        hashMap.put("HoldAllComplete", Attributes.HoldAllComplete);
        hashMap.put("Flat", Attributes.Flat);
        hashMap.put("OneIdentity", Attributes.OneIdentity);
        hashMap.put("Orderless", Attributes.Orderless);

        //functions
        hashMap.put("Evaluate", Functions.Evaluate);
        hashMap.put("Hold", Functions.Hold);
        hashMap.put("HoldComplete", Functions.HoldComplete);
        hashMap.put("Fun", Functions.Fun);
        hashMap.put("Seq", Functions.Seq);
        hashMap.put("SetDelayed", Functions.SetDelayed);
        hashMap.put("Set", Functions.Set);
        hashMap.put("ApplyList", Functions.ApplyList);
    }

    public StringSymbol get(String key) {
        return (StringSymbol) hashMap.get(key);
    }

    public void put(String key, StringSymbol val) {
        hashMap.put(key, val);
    }
}
