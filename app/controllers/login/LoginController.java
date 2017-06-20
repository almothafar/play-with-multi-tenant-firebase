package controllers.login;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.inject.Singleton;
import controllers.base.BaseController;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
@Singleton
public class LoginController extends BaseController {

    @Inject
    private FirebaseApp firebaseApp;

    public CompletionStage<Result> firebaseLogin() {
        JsonNode json = getRequestBodyAsJson();
        String role = ctx().request().getHeader("role");
        String firebaseTokenString = json.get("token").asText();

        CompletableFuture<FirebaseToken> tokenFuture = getDecryptedTokenCompletableFuture(firebaseTokenString);
        return tokenFuture.thenApply((decryptedToken) -> {
            return ok(firebaseTokenString);
        });
    }

    private CompletableFuture<FirebaseToken> getDecryptedTokenCompletableFuture(String firebaseTokenString) {
        CompletableFuture<FirebaseToken> tokenFuture = new CompletableFuture<>();
        Task<FirebaseToken> tokenTask = FirebaseAuth.getInstance(firebaseApp).verifyIdToken(firebaseTokenString);
        tokenTask.addOnSuccessListener(tokenFuture::complete);
        tokenTask.addOnFailureListener(exception ->
                tokenFuture.completeExceptionally(new RuntimeException("Failed to verify token", exception)));
        return tokenFuture;
    }


}

