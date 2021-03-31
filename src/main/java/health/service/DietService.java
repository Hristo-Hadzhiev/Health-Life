package health.service;

import health.model.entity.Diet;
import health.model.entity.enums.DietTargetEnum;
import health.model.service.DietServiceModel;
import health.model.view.DietViewModel;

import java.util.List;

public interface DietService {

    void addDietToDB(DietServiceModel dietModel);

    List<Diet> getTopFiveDiet();

    DietViewModel findById(String id);

    List<DietViewModel> findAllDietsByTarget(DietTargetEnum losingWeight);

    List<DietViewModel> findAllDiets();

    boolean dietExists(String name);
}
