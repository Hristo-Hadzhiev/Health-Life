package health.service;

import health.model.entity.Plan;

public interface PlanService {

    void createPlanForUser(String target);

    Plan findUserPlan();

    boolean isUserContainPlan();
}
