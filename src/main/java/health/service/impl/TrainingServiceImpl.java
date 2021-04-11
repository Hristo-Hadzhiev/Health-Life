package health.service.impl;

import health.model.entity.Training;
import health.model.service.TrainingServiceModel;
import health.repository.TrainingRepository;
import health.service.TrainingService;
import health.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public TrainingServiceImpl(TrainingRepository trainingRepository, ModelMapper modelMapper, UserService userService) {
        this.trainingRepository = trainingRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public boolean trainExist(String trainingName) {

        return trainingRepository.findByName(trainingName).isPresent();
    }

    @Override
    public void addTrainingToDB(TrainingServiceModel trainingServiceModel) {
        Training training = modelMapper.map(trainingServiceModel, Training.class);
        training.setAuthor(userService.findByUsername(userService.getUsername()));

        trainingRepository.save(training);
    }

    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public Training findById(String id) {
        return trainingRepository.findById(id)
                .orElse(null);
    }
}
