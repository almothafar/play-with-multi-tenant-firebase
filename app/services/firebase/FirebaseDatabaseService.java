package services.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.tasks.Task;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import controllers.base.BaseVO;
import play.Logger;
import providers.FirebaseDatabaseProvider;

import java.util.Map;

@Singleton
public class FirebaseDatabaseService {

    @Inject
    private FirebaseDatabaseProvider firebaseDatabaseProvider;

    public Task<Void> updateUserPlan(String userId, String userPlanId, Map<String, Object> updatedParams) {
        updatedParams.put("updatedAt", ServerValue.TIMESTAMP);
        return this.updateObjectData(updatedParams, "plans/".concat(userId).concat("/").concat(userPlanId));
    }

    public Task<Void> updateUserPlanOrder(String userId, String orderId, Map<String, Object> updatedParams) {
        updatedParams.put("updatedAt", ServerValue.TIMESTAMP);
        return this.updateObjectData(updatedParams, "orders/".concat(userId).concat("/").concat(orderId).concat("/orderDetails/plan"));
    }

    public Task<Void> addToUserAccount(String userId, Map<String, Object> paramsToAdd) {
        paramsToAdd.put("createdAt", ServerValue.TIMESTAMP);
        return this.pushDataToArray(paramsToAdd, "accounts/".concat(userId));
    }

    private Task<Void> updateObjectData(Map<String, Object> updatedParams, String path) {
        Logger.debug(String.format("Update ObjectData in firebase of ref (%s) with data: %s", path, updatedParams.toString()));
        DatabaseReference child = firebaseDatabaseProvider.getUserDataReference().child(path);
        return child.updateChildren(updatedParams);
    }

    private <T extends BaseVO> Task<Void> pushDataToArray(Map<String, Object> updatedParams, String path) {
        Logger.debug(String.format("Push Object to array in firebase of ref (%s) with data: %s", path, updatedParams.toString()));
        DatabaseReference child = firebaseDatabaseProvider.getUserDataReference().child(path);
        return child.push().setValue(updatedParams);
    }
}
