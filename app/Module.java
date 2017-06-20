import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.inject.AbstractModule;

import play.mvc.Http;
import providers.ContextProvider;
import providers.FirebaseAppProvider;
import providers.FirebaseAuthProvider;
import providers.FirebaseDatabaseProvider;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {
        bind(Http.Context.class).toProvider(ContextProvider.class);
        bind(FirebaseApp.class).toProvider(FirebaseAppProvider.class).asEagerSingleton();
        bind(FirebaseAuth.class).toProvider(FirebaseAuthProvider.class).asEagerSingleton();
        bind(FirebaseDatabase.class).toProvider(FirebaseDatabaseProvider.class).asEagerSingleton();
    }



}

