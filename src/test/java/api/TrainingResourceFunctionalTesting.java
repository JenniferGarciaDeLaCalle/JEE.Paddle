package api;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Test;

import business.api.Uris;

public class TrainingResourceFunctionalTesting {

	RestService restService = new RestService();
	
	@Test
    public void testshowAvailability() {
		String token = restService.registerAndLoginPlayer();
		Calendar calendar = Calendar.getInstance();
		String response = new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).path("/findTrainingByStartDate").basicAuth(token, "").body(calendar).post().build();
        LogManager.getLogger(this.getClass()).info("testshowAvailability (" + response + ")");
    }
	
/*    @Test
    public void testCreateTraining() {
    }
    
    @Test
    public void addUserInTraining() {
    }
    
    @Test
    public void deleteUserInTraining() {
    }
    
    @Test
    public void deleteTraining() {
    }
*/    
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
