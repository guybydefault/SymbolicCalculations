package ru.symbolic.io.xml;

import ru.symbolic.domain.StringSymbol;
import ru.symbolic.dsl.functions.ArithmeticFunctions;
import ru.symbolic.dsl.functions.BooleanFunctions;
import ru.symbolic.dsl.functions.CastingFunctions;
import ru.symbolic.dsl.functions.ListFunctions;
import ru.symbolic.dsl.library.Alphabet;
import ru.symbolic.dsl.library.Attributes;
import ru.symbolic.dsl.library.Functions;

import java.lang.reflect.Field;
import java.util.HashMap;

public class StringSymbolHashMapService {

    private HashMap stringSymbolHashMap = new HashMap();

    public StringSymbolHashMapService() throws IllegalAccessException {
        registerSymbolsFromClass(ArithmeticFunctions.class);
        registerSymbolsFromClass(BooleanFunctions.class);
        registerSymbolsFromClass(CastingFunctions.class);
        registerSymbolsFromClass(ListFunctions.class);
        registerSymbolsFromClass(Alphabet.class);
        registerSymbolsFromClass(Attributes.class);
        registerSymbolsFromClass(Functions.class);
    }

    public StringSymbol get(String key) {
        return (StringSymbol) stringSymbolHashMap.get(key);
    }

    private void registerSymbolsFromClass(Class cl) throws IllegalAccessException {
        for (Field field : cl.getDeclaredFields()) {
            if (field.getType().equals(StringSymbol.class)) {
                stringSymbolHashMap.put(((StringSymbol) field.get(null)).getName(), ((StringSymbol) field.get(null)));
            }
        }
    }
}
