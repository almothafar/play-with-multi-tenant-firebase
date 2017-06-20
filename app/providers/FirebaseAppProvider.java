package providers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import play.Configuration;
import play.Environment;
import play.Logger;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class FirebaseAppProvider implements Provider<FirebaseApp> {


    private final Logger.ALogger logger;
    private final Environment environment;
    private final Configuration configuration;

    @Inject
    public FirebaseAppProvider(Environment environment, Configuration configuration) {
        this.logger = Logger.of(this.getClass());
        this.environment = environment;
        this.configuration = configuration;
    }

    @Singleton
    @Override
    @SuppressWarnings("unchecked")
    public FirebaseApp get() {
        HashMap<String, String> firebaseProjects = (HashMap<String, String>) configuration.getObject("firebase");
        firebaseProjects.forEach((websiteId, projectId) -> {
            FileInputStream serviceAccount = null;
            try {
                serviceAccount = new FileInputStream(environment.classLoader().getResource(String.format("firebase/%s.json", projectId)).getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            FirebaseOptions options = new FirebaseOptions.Builder().setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
                    .setDatabaseUrl(String.format("https://%s.firebaseio.com/", projectId))
                    .build();


            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options, projectId);

            logger.info("FirebaseApp initialized");
        });

        return FirebaseApp.getInstance();
    }
}
