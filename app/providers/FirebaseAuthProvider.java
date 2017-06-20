package providers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseCredentials;
import play.Environment;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by rrabata on 2/26/17.
 */
public class FirebaseAuthProvider implements Provider<FirebaseAuth> {

    private final FirebaseApp firebaseApp;

    @Inject
    public FirebaseAuthProvider(FirebaseApp firebaseApp)  {
        this.firebaseApp = firebaseApp;
    }

    @Singleton
    @Override  public FirebaseAuth get() {
        return FirebaseAuth.getInstance(firebaseApp);
    }
}
