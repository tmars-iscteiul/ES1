package antiSpamFilter.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EmailStreamTest.class, EmailTest.class, ManualConfigurationTest.class, ReadCFTest.class,
		ReadLOGTest.class, RuleStreamTest.class, RuleTest.class })
public class AllTests {

}
