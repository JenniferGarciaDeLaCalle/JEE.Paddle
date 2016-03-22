package data.daos;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import data.entities.Training;

public interface TrainingDao extends JpaRepository<Training, Integer>, TrainingDaoExtended{
	
	Training findTrainingById(int id);
	
	List<Training> findTrainingsByStartDate(Calendar startDate);
}
