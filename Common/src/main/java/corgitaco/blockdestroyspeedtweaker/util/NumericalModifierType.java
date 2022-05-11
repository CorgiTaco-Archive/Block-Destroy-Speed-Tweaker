package corgitaco.blockdestroyspeedtweaker.util;

import java.util.function.BiFunction;

public enum NumericalModifierType {
    MULTIPLY(((number, number2) -> number * number2)),
    DIVIDE(((number, number2) -> number / number2)),
    SUBTRACT(((number, number2) -> number - number2)),
    ADD(((number, number2) -> number + number2));

    private final BiFunction<Double, Double, Double> numberFunction;

    NumericalModifierType(BiFunction<Double, Double, Double> numberFunction) {
        this.numberFunction = numberFunction;
    }

    public double apply(Double first, Double two) {
        return numberFunction.apply(first, two);
    }
}
