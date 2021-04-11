package health.service;

import health.model.entity.Training;
import health.model.service.TrainingServiceModel;

import java.util.List;

public interface TrainingService {
    boolean trainExist(String trainingName);

    void addTrainingToDB(TrainingServiceModel trainingServiceModel);

    List<Training> findAllTrainings();

    void deleteById(String id);

    Training findById(String id);
}
