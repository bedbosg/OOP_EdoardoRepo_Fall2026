package classroom03;

import java.util.List;

interface MatchableA<F, S> {
    boolean match(F first, S second);
}

interface PrintableA<T> {
    T print();
}

class MultiMatcherA<F, S> implements MatchableA<List<F>, List<S>>, PrintableA<StringBuffer> {
    private final List<F> firstList;
    private final List<S> secondList;
    private final MatchableA<F, S> matcher;

    public MultiMatcherA(List<F> firstList, List<S> secondList, MatchableA<F, S> matcher) {
        this.firstList = firstList;
        this.secondList = secondList;
        this.matcher = matcher;
    }

    public boolean match() {
        return match(firstList, secondList);
    }

    @Override
    public boolean match(List<F> firstList, List<S> secondList) {
        if (firstList.size() != secondList.size()) {
            return false;
        }

        for (int index = 0; index < firstList.size(); index++) {
            F firstObject = firstList.get(index);
            S secondObject = secondList.get(index);
            if (!matcher.match(firstObject, secondObject)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public StringBuffer print() {
        StringBuffer sb = new StringBuffer();
        sb.append("MultiMatcher comparison:");
        sb.append(System.lineSeparator());

        if (firstList.size() != secondList.size()) {
            sb.append("- List size mismatch: %d vs %d%n".formatted(firstList.size(), secondList.size()));
            return sb;
        }

        for (int index = 0; index < firstList.size(); index++) {
            F first = firstList.get(index);
            S second = secondList.get(index);
            boolean matches = matcher.match(first, second);
            sb.append("- %s vs %s = %s%n".formatted(first, second, matches));
        }
        return sb;
    }
}

class IntegerDoubleMatcher implements MatchableA<Integer, Double> {
    @Override
    public boolean match(Integer first, Double second) {
        return first == second.intValue();
    }
}
class FloatDoubleMatcher implements MatchableA<Float, Double> {
    @Override
    public boolean match(Float first, Double second) {
        return first == second.intValue();
    }
}
class NumberMatcher<N1 extends Number, N2 extends Number> implements MatchableA<N1, N2> {
    @Override
    public boolean match(N1 first, N2 second) {
        return first.intValue() == second.intValue();
    }
}


public class MatcherDemoPartA {
    private static void runIntegerDoubleMatcher(List<Integer> integers, List<Double> doubles) {
        MultiMatcherA<Integer, Double> numberMultiMatcher = new MultiMatcherA<>(integers, doubles, new NumberMatcher<>());
        System.out.println("Number MultiMatcher(Integer, Double)");
        System.out.println("Matches: " + numberMultiMatcher.match());
        System.out.println(numberMultiMatcher.print());
    }
    private static void runFloatDoubleMatcher(List<Float> floats, List<Double> doubles) {
        MultiMatcherA<Float, Double> numberMultiMatcher = new MultiMatcherA<>(floats, doubles, new NumberMatcher<>());
        System.out.println("Number MultiMatcher(Floats, Double)");
        System.out.println("Matches: " + numberMultiMatcher.match());
        System.out.println(numberMultiMatcher.print());
    }
    private static void runNumberMatcher(List<? extends Number> numbers1, List<? extends Number> numbers2) {
        MultiMatcherA<? extends Number, ? extends Number> numberMultiMatcher = new MultiMatcherA<>(numbers1, numbers2, new NumberMatcher<>());
        System.out.println("Number MultiMatcher(? extends Number, ? extends Number)");
        System.out.println("Matches: " + numberMultiMatcher.match());
        System.out.println(numberMultiMatcher.print());
    }
    private static <N1 extends Number, N2 extends Number> void runMatcher(List<N1> numbers1, List<N2> numbers2) {
        MultiMatcherA<N1, N2> numberMultiMatcher = new MultiMatcherA<>(numbers1, numbers2, new NumberMatcher<>());
        System.out.println("Number MultiMatcher(N1, N2)");
        System.out.println("Matches: " + numberMultiMatcher.match());
        System.out.println(numberMultiMatcher.print());
    }

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 2, 3);
        List<Double> doubles = List.of(1.2, 2.8, 2.1, 2.9);
        List<Float> floats = List.of(1.2f, 2.8f, 2.1f, 2.9f);

        runIntegerDoubleMatcher(integers, doubles);
        runFloatDoubleMatcher(floats, doubles);

        runNumberMatcher(integers, doubles);
        runNumberMatcher(floats, doubles);

        runMatcher(integers, doubles);
        runMatcher(floats, doubles);
    }
}
