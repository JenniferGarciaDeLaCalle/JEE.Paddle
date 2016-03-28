package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import business.api.Uris;
import business.controllers.UserController;
import business.wrapper.AvailableTime;
import business.wrapper.TrainingWrapper;
import business.wrapper.UserWrapper;
import business.wrapper.UserWrapperBuilder;
import data.daos.TrainingDao;
import data.entities.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrainingResourceFunctionalTesting {

	RestService restService = new RestService();
	
	
//    @Autowired
//    private TrainingDao trainingDao;

	@Test
	public void t1_testCreateTraining() {
		restService.createCourt("1");
        Calendar startDate = createDate();
        Calendar finishDate = createDate();
        finishDate.add(Calendar.DAY_OF_YEAR, 7);
        finishDate.add(Calendar.HOUR_OF_DAY, 1);
        restService.createTraining(startDate, finishDate, 1, 2);
	}
	
	@Test
    public void t2_testCreateTrainingUnauthorized() {
        try {
            Calendar startDate = createDate();
            Calendar finishDate = createDate();
            TrainingWrapper trainingWrapper = new TrainingWrapper(startDate, finishDate, 10, 2);
            new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).body(trainingWrapper).post().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.UNAUTHORIZED, httpError.getStatusCode());
            LogManager.getLogger(this.getClass())
                    .info("testCreateTraining (" + httpError.getMessage() + "):\n    " + httpError.getResponseBodyAsString());
        }
    }
	
	@Test
    public void t3_testShowTrainings() {
		String token = new RestService().registerAndLoginPlayer();
        String response = new RestBuilder<String>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).basicAuth(token, "")
        		.clazz(String.class).get().build();
        LogManager.getLogger(this.getClass()).info("testShowTrainings (" + response + ")");
    }
	
	@Test
    public void t4_testShowTrainingsUnauthorized() {
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
    public void t5_testAddUserInTraining() {
		String token = restService.loginUser("u0", "u0");
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.PLAYERS).pathId(1).path(Uris.USERS).pathId(3)
        		.basicAuth(token, "").post().build();
    }
	
	@Test
    public void t6_testAddUserInTrainingUnauthorized() {
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
    public void t7_testDeleteUserInTraining() {
		String token = restService.loginTrainer();
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).pathId(1).path(Uris.USERS).pathId(3)
        		.basicAuth(token, "").delete().build();
    }
	
	@Test
    public void t8_testDeleteUserInTrainingUnauthorized() {
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
    public void t9_testDeleteTraining() {
        String token = restService.loginTrainer();
        //List<Training> list = trainingDao.findAll();
        //Training training = list.get(list.size() - 1);
        new RestBuilder<Object>(RestService.URL).path(Uris.TRAININGS).path(Uris.TRAINERS).pathId(1).basicAuth(token, "").delete().build();
    }
	
	@Test
    public void tA_testDeleteTrainingUnauthorized() {
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
	
    @After
    public void deleteAll() {
        //new RestService().deleteAll();
    }

}
