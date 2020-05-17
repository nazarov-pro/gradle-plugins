package com.shahinnazarov.gradle.utils.generate;

import com.shahinnazarov.gradle.models.enums.ExpressionSourceType;

public interface ExpressionSupplier {
    int countOfExpressions(String value);
    String applyExpressions(String value);
    ExpressionSourceType sourceTypeOfFirstExpression(String value);
}
