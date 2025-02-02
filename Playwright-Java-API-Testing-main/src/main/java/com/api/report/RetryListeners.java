package com.api.report;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListeners implements IAnnotationTransformer{

	@SuppressWarnings("rawtypes")
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor constructor, Method method) {
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
