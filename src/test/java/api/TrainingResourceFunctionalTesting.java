package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.wrapper.TrainingWrapper;

public class TrainingResourceFunctionalTesting {

	RestService restService = new RestService();
	
	@Before
	public void ini(){
		restService.createCourt("1");
        Calendar startDate = createDate();
        Calendar finishDate = createDate();
        finishDate.add(Calendar.DAY_OF_YEAR, 7);
        finishDate.add(Calendar.HOUR_OF_DAY, 1);
        restService.createTraining(startDate, finishDate, 1);		
	}
	
	@Test
	public void testCreateTraining() {
	}
	
	@Test
    public void testCreateTrainingUnauthorized() {
        try {
            Calendar startDate = createDate();
            Calendar finishDate = createDate();
            TrainingWrapper trainingWrapper = new TrainingWrapper(startDate, finishDate, 10, 1);
            new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).body(trainingWrapper).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
	
	@Test
    public void testShowTrainings() {
		String token = new RestService().registerAndLoginPlayer();
        String response = new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).basicAuth(token, "")
        		.clazz(String.class).get().build();
        LogManager.getLogger(this.getClass()).info("testShowTrainings (" + response + ")");
    }
	
	@Test
    public void testShowTrainingsUnauthorized() {
        try {
            new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).clazz(String.class).get()
            .build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testShowTrainingsUnauthorized (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
	
	@Test
    public void testAddUserInTraining() {
		addUserInTraining();
    }
	
	@Test
    public void testAddUserInTrainingUnauthorized() {
        try {
        	new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).pathId(1).path(Uris.USERS).pathId(3)
        		.post().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testAddUserInTrainingUnauthorized (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
	
	@Test
    public void testDeleteUserInTraining() {
		addUserInTraining();
		String tokenUser = restService.loginUser("u0", "u0");
		List<TrainingWrapper> training = Arrays.asList(new RestBuilder<TrainingWrapper[]>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).basicAuth(tokenUser, "")
				.clazz(TrainingWrapper[].class).get().build());
		String token = restService.loginTrainer();
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).pathId(training.get(0).getId()).path(Uris.USERS).pathId(training.get(0).getPlayers().get(0).getId())
        		.basicAuth(token, "").delete().build();
    }
	
	@Test
    public void testDeleteUserInTrainingUnauthorized() {
        try {
        	new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).pathId(1).path(Uris.USERS).pathId(3)
    				.delete().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testDeleteUserInTrainingUnauthorized (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
	
	@Test
    public void testDeleteTraining() {
		String tokenUser = new RestService().registerAndLoginPlayer();
		List<TrainingWrapper> training = Arrays.asList(new RestBuilder<TrainingWrapper[]>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).basicAuth(tokenUser, "")
				.clazz(TrainingWrapper[].class).get().build());
		String token = restService.loginTrainer();
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).pathId(training.get(0).getId()).basicAuth(token, "").delete().build();
    }
	
	@Test
    public void testDeleteTrainingUnauthorized() {
        try {
        	new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).pathId(1).delete().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testDeleteTrainingUnauthorized (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
	
	private Calendar createDate(){
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, 10);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
	}
	
	public List<TrainingWrapper> addUserInTraining(){
		String token = new RestService().registerAndLoginPlayer();
		List<TrainingWrapper> training = Arrays.asList(new RestBuilder<TrainingWrapper[]>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).basicAuth(token, "")
				.clazz(TrainingWrapper[].class).get().build());
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).pathId(training.get(0).getId()).basicAuth(token, "").post().build();
        return training;
	}
	
    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
