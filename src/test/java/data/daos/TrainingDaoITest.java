package data.daos;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import data.entities.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TrainingDaoITest {

	@Autowired
	private TrainingDao trainingDao;
	
	private Calendar createDate(){
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, 1);
        date.set(Calendar.HOUR_OF_DAY, 9);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
	}

	@Test
	public void testFindTrainingsByStartDateNotFind(){
		Calendar date = createDate();
		assertEquals(0, trainingDao.findTrainingsByStartDate(date).size());
	}
	
	@Test
	public void testFindTrainingsByStartDateFind(){
		Calendar date = createDate();
		date.set(Calendar.HOUR_OF_DAY, 10);
		assertEquals(1,  trainingDao.findTrainingsByStartDate(date).size());
		date.add(Calendar.HOUR_OF_DAY, 1);
		assertEquals(1,  trainingDao.findTrainingsByStartDate(date).size());
		date.add(Calendar.HOUR_OF_DAY, 1);
		assertEquals(1,  trainingDao.findTrainingsByStartDate(date).size());
	}
	
	@Test
	public void testDeleteTraining() {
		assertEquals(4, trainingDao.findAll().size());
		Training training = trainingDao.findAll().get(3);
		trainingDao.deleteTraining(training);
		assertEquals(3, trainingDao.findAll().size());
	}
}
