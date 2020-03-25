package ru.symbolic.visitors.evaluation;

import ru.symbolic.domain.Expression;
import ru.symbolic.domain.StringSymbol;
import ru.symbolic.domain.Symbol;
import ru.symbolic.dsl.library.Functions;
import ru.symbolic.visitors.cast.AsConstantVisitor;
import ru.symbolic.visitors.cast.AsExpressionVisitor;
import ru.symbolic.visitors.cast.AsStringSymbolVisitor;

import java.util.*;

public class MatchState {

    private final Symbol pattern;
    private final Symbol symbol;
    private final Map<String, Symbol> localPatternVariables;
    private final boolean inFinalState;
    private final boolean matches;

    //    List matching
    private final List<Symbol> patterns;
    private final List<Symbol> args;
    private final int currPatternIndex;
    private final int currArg;
    private final List<Symbol> matchedSequenceSymbols;
    private final boolean listMatches;

    private MatchState(Symbol pattern, Symbol symbol, Map<String, Symbol> localPatternVariables, boolean inFinalState, boolean matches, List<Symbol> patterns, List<Symbol> args, int currPatternIndex, int currArg, List<Symbol> matchedSequenceSymbols, boolean listMatches) {
        this.pattern = pattern;
        this.symbol = symbol;
        this.localPatternVariables = localPatternVariables;
        this.inFinalState = inFinalState;
        this.matches = matches;
        this.patterns = patterns;
        this.args = args;
        this.currPatternIndex = currPatternIndex;
        this.currArg = currArg;
        this.matchedSequenceSymbols = matchedSequenceSymbols;
        this.listMatches = listMatches;
    }

    public MatchState(Symbol pattern, Symbol symbol) {
        this(pattern, symbol, Collections.unmodifiableMap(new HashMap<String, Symbol>()));
    }

    public MatchState(Symbol pattern, Symbol symbol, Map<String, Symbol> localPatternVariables) {
        this(pattern, symbol, localPatternVariables, false, false, null, null, 0, 0, null, false);
    }

    private MatchState initListMatch(List<Symbol> patterns, List<Symbol> args) {
        return new MatchState(pattern, symbol, localPatternVariables, false, false, patterns, args, 0, 0, Collections.unmodifiableList(new LinkedList<Symbol>()), false);
    }

    private MatchState matchList(List<Symbol> patterns, List<Symbol> args) {
        return initListMatch(patterns, args).matchExpressionList();
    }

    private boolean isInFinalState() {
        return inFinalState;
    }

    public boolean isMatched() {
        return matches;
    }

    public Map<String, Symbol> getLocalPatternVariables() {
        return localPatternVariables;
    }

    private MatchState setLocalPatternVariables(Map<String, Symbol> localPatternVariables) {
        return new MatchState(pattern, symbol, localPatternVariables, inFinalState, matches, patterns, args, currPatternIndex, currArg, matchedSequenceSymbols, listMatches);
    }

    private MatchState result(boolean matches) {
        return new MatchState(pattern, symbol, localPatternVariables, true, matches, patterns, args, currPatternIndex, currArg, matchedSequenceSymbols, listMatches);
    }

    private MatchState saveLocalVariable(String variable, Symbol value) {
        Map<String, Symbol> localPatternVariables = new HashMap<>(this.localPatternVariables);
        localPatternVariables.put(variable, value);
        return setLocalPatternVariables(Collections.unmodifiableMap(localPatternVariables));
    }

    public MatchState match() {
        if (pattern.visit(AsConstantVisitor.getInstance()) != null) {
            return result(pattern.equals(symbol));
        }

        if (pattern.visit(AsExpressionVisitor.getInstance()) != null) {
            Expression patternExpression = pattern.visit(AsExpressionVisitor.getInstance());
            Expression symbolExpression = symbol.visit(AsExpressionVisitor.getInstance());
            if (symbolExpression == null) {
                return result(false);
            }

            MatchState headMatchState = new MatchState(patternExpression.getHead(), symbolExpression.getHead(), localPatternVariables).match();
            if (!headMatchState.matches) {
                return result(false);
            }

            MatchState matchState = setLocalPatternVariables(headMatchState.localPatternVariables).matchList(patternExpression.getArguments(), symbolExpression.getArguments());
            return matchState.result(matchState.listMatches);
        }

        if (pattern.visit(AsStringSymbolVisitor.getInstance()) != null) {
            StringSymbol patternStringSymbol = pattern.visit(AsStringSymbolVisitor.getInstance());

            String[] a = patternStringSymbol.getName().split("_", 2);
            if (a.length > 1 && !a[0].trim().isEmpty()) {
                String variableName = a[0];
                if (!a[1].trim().isEmpty()) {
                    String variableType = a[1];
                    if (variableType.equals("Constant")) {
                       if (symbol.visit(AsConstantVisitor.getInstance()) == null) {
                           return result(false);
                       }
                    } else {
                        if (symbol.visit(AsExpressionVisitor.getInstance()) == null) {
                            return result(false);
                        }
                        StringSymbol head = symbol.visit(AsExpressionVisitor.getInstance()).getHead().visit(AsStringSymbolVisitor.getInstance());
                        if (head == null || !head.getName().equals(variableType)) {
                            return result(false);
                        }
                    }
                }

                if (localPatternVariables.containsKey(variableName)) {
                    if (!symbol.equals(localPatternVariables.get(variableName))) {
                        return result(false);
                    }
                } else {
                    return saveLocalVariable(variableName, symbol).result(true);
                }
                return result(true);
            } else return result(pattern.equals(symbol));

        }

        throw new UnsupportedOperationException("Pattern should be either StringSymbol, or Expression, or Constant");
    }

    private Map<String, Symbol> getMapWithSavedLocalVariable(String variable, Symbol value) {
        Map<String, Symbol> modifiableHashMap = new HashMap<>(localPatternVariables);
        modifiableHashMap.put(variable, value);
        return Collections.unmodifiableMap(modifiableHashMap);
    }

    private MatchState matchCurrentArgIntoSequence() {
        List<Symbol> newMatchSequence = new LinkedList<>(matchedSequenceSymbols);
        newMatchSequence.add(getCurrentArg());
        return new MatchState(pattern, symbol, localPatternVariables, inFinalState, matches, patterns, args, currPatternIndex, currArg + 1, Collections.unmodifiableList(newMatchSequence), listMatches);
    }

    private MatchState incrementArgIndex() {
        return new MatchState(pattern, symbol, localPatternVariables, inFinalState, matches, patterns, args, currPatternIndex, currArg + 1, matchedSequenceSymbols, listMatches);
    }

    private MatchState incrementPatternIndex() {
        return new MatchState(pattern, symbol, localPatternVariables, inFinalState, matches, patterns, args, currPatternIndex + 1, currArg, matchedSequenceSymbols, listMatches);
    }

    private MatchState clearMatchedSequence() {
        return new MatchState(pattern, symbol, localPatternVariables, inFinalState, matches, patterns, args, currPatternIndex, currArg, Collections.unmodifiableList(new LinkedList<>()), listMatches);
    }

    private boolean checkIfTheRestOfPatternsAreNullableSequences() {
        for (int i = currPatternIndex; i < patterns.size(); i++) {
            if (!isNullableSequencePattern(i)) {
                return false;
            }
        }
        return true;
    }

    private MatchState listResult(boolean listMatches) {
        return new MatchState(pattern, symbol, localPatternVariables, inFinalState, matches, patterns, args, currPatternIndex, currArg, matchedSequenceSymbols, listMatches);
    }

    private MatchState finishSequenceMatching() {
        // so there are some args matched, finishing the sequence cuz there are no more args
        // end sequence, write to a variable and get new pattern()
        if (hasVariableName()) {
            Expression matchedSequence = new Expression(Functions.Seq, matchedSequenceSymbols);
            if (localPatternVariables.containsKey(getVariableName())) {
                if (!Objects.equals(localPatternVariables.get(getVariableName()), matchedSequence)) {
                    // new matched sequence is not equal to the one already matched before with the same name
                    return listResult(false);
                }
            } else {
                return saveLocalVariable(getVariableName(), matchedSequence)
                        .clearMatchedSequence()
                        .incrementPatternIndex()
                        .matchExpressionList();
            }
        }
        return clearMatchedSequence().incrementPatternIndex().matchExpressionList();
    }


    private MatchState matchExpressionList() {
        if (!anyPatternsLeft()) {
            if (!anyArgsLeft()) {
                // that means everything has been matched before
                return listResult(true);
            } else {
                // there are some args which are needed to match but we dont have more patterns
                return listResult(false);
            }
        }

        // so it seems there are some patterns left
        if (isNonNullableSequence() || isNullableSequencePattern()) {
            if (!anyArgsLeft()) {
                if (!anyArgsMatchedInTheCurrentSequence() && isNonNullableSequence()) {
                    return listResult(false);
                } else {
                    return finishSequenceMatching();
                }
            } else {
                // so there are args left
                if (!anyArgsMatchedInTheCurrentSequence() && isNonNullableSequence()) {
                    // since this is not nullable sequence there is only one way: match one argument
                    return matchCurrentArgIntoSequence().matchExpressionList();
                } else {
                    // at least one arg has been already matched to the not nullable sequence
                    // so there are two ways: either stop matching here or go further
                    MatchState finishHereOption = finishSequenceMatching();
                    if (finishHereOption.listMatches) {
                        return finishHereOption;
                    } else {
                        //    if finishing here does not work, then we try to match next symbol and see where it goes
                        return matchCurrentArgIntoSequence().matchExpressionList();
                    }
                }
            }
        } else {
            // not sequence - so there should be something!
            if (!anyArgsLeft()) {
                return listResult(false);
            }

            MatchState argMatchState = new MatchState(getCurrentPattern(), getCurrentArg(), localPatternVariables).match();
            if (!argMatchState.isInFinalState()) {
                throw new IllegalStateException();
            }
            if (!argMatchState.isMatched()) {
                return listResult(false);
            }

            // match! proceeding to the next arg and pattern
            return setLocalPatternVariables(argMatchState.getLocalPatternVariables()).incrementArgIndex().incrementPatternIndex().matchExpressionList();
        }
    }

    private boolean anyArgsMatchedInTheCurrentSequence() {
        return getMatchedSequenceSymbols().size() > 0;
    }


    private List<Symbol> getMatchedSequenceSymbols() {
        return matchedSequenceSymbols;
    }

    private boolean isNonNullableSequence() {
        return isNonNullableSequence(currPatternIndex);
    }

    private boolean isNonNullableSequence(int patternIndex) {
        StringSymbol pattern = getPatternStringSymbol(patternIndex);
        if (pattern == null) {
            return false;
        }

        return pattern.getName().endsWith("__") && !isNullableSequencePattern();
    }

    private boolean isNullableSequencePattern() {
        return isNullableSequencePattern(currPatternIndex);
    }

    private boolean isNullableSequencePattern(int patternIndex) {
        StringSymbol pattern = getPatternStringSymbol(patternIndex);
        if (pattern == null) {
            return false;
        }

        return pattern.getName().endsWith("___");
    }

    private StringSymbol getPatternStringSymbol() {
        return getPatternStringSymbol(currPatternIndex);
    }

    private StringSymbol getPatternStringSymbol(int patternIndex) {
        return patterns.get(patternIndex).visit(AsStringSymbolVisitor.getInstance());
    }

    private boolean hasVariableName() {
        String[] patternInfo = getPatternStringSymbol().getName().split("_", 2);
        return patternInfo.length > 0 && !patternInfo[0].trim().isEmpty();
    }

    private boolean hasVariableType() {
        String[] patternInfo = getPatternStringSymbol().getName().split("_", 2);
        return patternInfo.length > 1 && !patternInfo[1].trim().isEmpty();
    }

    private String getVariableName() {
        return getPatternStringSymbol().getName().split("_", 2)[0];
    }

    private String getVariableType() {
        return getPatternStringSymbol().getName().split("_", 2)[1];
    }

    private Symbol getCurrentPattern() {
        return patterns.get(currPatternIndex);
    }

    private Symbol getCurrentArg() {
        return args.get(currArg);
    }

    private boolean anyPatternsLeft() {
        return currPatternIndex < patterns.size();
    }

    private boolean anyArgsLeft() {
        return currArg < args.size();
    }


}
