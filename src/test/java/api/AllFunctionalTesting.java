package api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TrainingResourceFunctionalTesting.class,
    UserResourceFunctionalTesting.class, 
    TokenResourceFunctionalTesting.class, 
    CourtResourceFunctionalTesting.class,
    ReserveResourceFunctionalTesting.class
})
public class AllFunctionalTesting {

}
