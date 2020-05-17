package com.shahinnazarov.gradle;

import com.shahinnazarov.gradle.models.enums.ExpressionSourceType;
import com.shahinnazarov.gradle.utils.K8sContext;
import com.shahinnazarov.gradle.utils.generate.ExpressionSupplier;
import com.shahinnazarov.gradle.utils.generate.impl.ExpressionSupplierImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class ExpressionSupplierTest {

    private ExpressionSupplier expressionSupplier;

    @Before
    public void setup() {
        expressionSupplier = new ExpressionSupplierImpl();
    }

    @Test
    public void testExist() {
        String value = "hjhjk${env.BUILD_NUMBER}";
        Assert.assertTrue(expressionSupplier.countOfExpressions(value) == 1);
    }

    @Test
    public void testTwoExpressions() {
        String value = "hjhjk${env.BUILD_NUMBER}-${appName}";
        Assert.assertTrue(expressionSupplier.countOfExpressions(value) == 2);
    }

    @Test
    public void testNotExist() {
        String value = "env.BUILD_NUMBER";
        Assert.assertTrue(expressionSupplier.countOfExpressions(value) == 0);
    }

    @Test
    public void testSourceTypeEnvironment() {
        String value = "${env.BUILD_NUMBER}";
        Assert.assertTrue(expressionSupplier.sourceTypeOfFirstExpression(value) == ExpressionSourceType.ENVIRONMENT);
    }


    @Test
    public void testSourceTypeLocal() {
        String value = "${myParam}";
        Assert.assertTrue(expressionSupplier.sourceTypeOfFirstExpression(value) == ExpressionSourceType.LOCAL);
    }


    @Test
    public void testSourceTypeGlobal() {
        String value = "${global.BUILD_NUMBER}";
        Assert.assertTrue(expressionSupplier.sourceTypeOfFirstExpression(value) == ExpressionSourceType.GLOBAL);
    }

    @Test
    public void testSourceTypeSystem() {
        String value = "${system.BUILD_NUMBER}";
        Assert.assertTrue(expressionSupplier.sourceTypeOfFirstExpression(value) == ExpressionSourceType.SYSTEM);
    }

    @Test
    public void testSourceTypeNone() {
        String value = "system.BUILD_NUMBER";
        Assert.assertTrue(expressionSupplier.sourceTypeOfFirstExpression(value) == ExpressionSourceType.NONE);
    }

    @Test
    public void testSystem() {
        System.setProperty("BUILD_NUMBER", "121");
        String value = "Build Number is ${system.BUILD_NUMBER}";
        Assert.assertEquals("Build Number is 121", expressionSupplier.applyExpressions(value));
    }


    @Test
    public void testSystemMultiParameter() {
        System.setProperty("BUILD_NUMBER", "121");
        System.setProperty("BUILD_AGENT", "jenkins");
        System.setProperty("BUILD_TIME", "15 seconds");
        String value = "Build Number is ${system.BUILD_NUMBER}, Build Agent is ${system.BUILD_AGENT}, " +
                "Build Time is ${system.BUILD_TIME}";
        Assert.assertEquals("Build Number is 121, Build Agent is jenkins, Build Time is 15 seconds",
                expressionSupplier.applyExpressions(value));
    }



    @Test
    public void testLocalAsVar() {
        Properties properties = new Properties();
        properties.put("k8s.var.BUILD_NUMBER", "123");
        K8sContext.initialize(properties);

        String value = "Build Number is ${BUILD_NUMBER}";
        Assert.assertEquals("Build Number is 123", expressionSupplier.applyExpressions(value));
    }


    @Test
    public void testLocalAsResourceReference() {
        Properties properties = new Properties();
        properties.put("k8s.ns.ns01.name", "namespace001");
        K8sContext.initialize(properties);

        String value = "Namespace is ${ns.ns01.name}";
        Assert.assertEquals("Namespace is namespace001", expressionSupplier.applyExpressions(value));
    }

    @Test
    public void testLocalMultiParameter() {
        Properties properties = new Properties();
        properties.put("k8s.var.BUILD_NUMBER", "123");
        properties.put("k8s.var.BUILD_AGENT", "jenkins");
        properties.put("k8s.var.BUILD_TIME", "15 seconds");
        K8sContext.initialize(properties);

        String value = "Build Number is ${BUILD_NUMBER}, Build Agent is ${BUILD_AGENT}, " +
                "Build Time is ${BUILD_TIME}";
        Assert.assertEquals("Build Number is 123, Build Agent is jenkins, Build Time is 15 seconds",
                expressionSupplier.applyExpressions(value));
    }


}
