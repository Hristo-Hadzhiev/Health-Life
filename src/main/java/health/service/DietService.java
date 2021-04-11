package health.service;

import health.model.entity.Diet;
import health.model.entity.enums.TargetEnum;
import health.model.service.DietServiceModel;
import health.model.view.DietViewModel;

import java.util.List;

public interface DietService {

    void addDietToDB(DietServiceModel dietModel);

    List<Diet> getTopFiveDiet();

    DietViewModel findById(String id);

    List<DietViewModel> findAllDietsByTarget(TargetEnum losingWeight);

    List<DietViewModel> findAllDiets();

    boolean dietExists(String name);

    void deleteById(String id);
}
