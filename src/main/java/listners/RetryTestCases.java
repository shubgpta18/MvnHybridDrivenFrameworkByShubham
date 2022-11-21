package listners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;


public class RetryTestCases implements IRetryAnalyzer ,IAnnotationTransformer{
	static int retrycount = 0;
	static int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			if (retrycount <= maxRetryCount) {
				retrycount++;
				return true;
			} else
				return false;
		}

		return false;
	}
	
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod,
			Class<?> occurringClazz) {
		testAnnotation.setRetryAnalyzer(RetryTestCases.class);
	}


}
