package health.service;

public interface AdminService {

    void deleteUser(String id);

    void makeUserAdmin(String id);

    void removeAdminRole(String id);

    void deleteRecipe(String id);

    void deleteDiet(String id);

    void deleteTraining(String id);
}
