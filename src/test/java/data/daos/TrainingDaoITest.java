package data.daos;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {

	@Autowired
	private TrainingDao trainingDao;
	
	@Autowired
	private CourtDao courtDao;
	
	@Autowired
	private UserDao userDao;

	@Test
	public void t1_testFindTrainingsByStartDateNotFind(){
		Calendar date = createDate();
		assertEquals(0, trainingDao.findTrainingsByStartDate(date).size());
	}
	
	@Test
	public void t2_testFindTrainingsByStartDateFind(){
		Calendar date = createDate();
		date.add(Calendar.DAY_OF_YEAR, 1);
		assertEquals(1, trainingDao.findTrainingsByStartDate(date).size());
	}
	
	@Test
	public void t3_createTraining() {
		Calendar startDate = createDate();
		startDate.add(Calendar.DAY_OF_YEAR, 3);
		Calendar finishDate = createDate();
		finishDate.add(Calendar.DAY_OF_YEAR, 17);
		finishDate.add(Calendar.HOUR_OF_DAY, 1);
		assertEquals(1, trainingDao.findAll().size());
		trainingDao.createTraining(startDate, finishDate, courtDao.findOne(1), userDao.findOne(1));
		assertEquals(2, trainingDao.findAll().size());
		Calendar date = createDate();
		date.add(Calendar.DAY_OF_YEAR, 3);
		assertEquals(1, trainingDao.findTrainingsByStartDate(date).size());
	}
	
	
	@Test
	public void t4_addUserTraining() {
		Calendar date = createDate();
		date.add(Calendar.DAY_OF_YEAR, 3);
		Training training = trainingDao.findTrainingsByStartDate(date).get(0);
		assertEquals(0, training.getPlayers().size());
		trainingDao.addUserInTraining(userDao.findOne(2), training);
		assertEquals(1, training.getPlayers().size());
	}
	
	@Test
	public void t6_deleteUserTraining() {
		Calendar date = createDate();
		date.add(Calendar.DAY_OF_YEAR, 3);
		Training training = trainingDao.findTrainingsByStartDate(date).get(0);
		assertEquals(1, training.getPlayers().size());
		trainingDao.deleteUserInTraining(userDao.findOne(2), training);
		assertEquals(0, training.getPlayers().size());
	}

	@Test
	public void t7_testDeleteTraining() {
		Calendar date = createDate();
		date.add(Calendar.DAY_OF_YEAR, 3);
		Training training = trainingDao.findTrainingsByStartDate(date).get(0);
		assertEquals(2, trainingDao.findAll().size());
		trainingDao.deleteTraining(training);
		assertEquals(1, trainingDao.findAll().size());
	}
	
	private Calendar createDate(){
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
	}
}
