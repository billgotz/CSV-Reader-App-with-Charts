package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RegisterDatasetTest.class, RetrieveDatasetTest.class, FilterDatasetTest.class, GetDatasetProjectionTest.class})
public class AllTests {
	//no need to add sth here. The above directives simply run all tests
}

