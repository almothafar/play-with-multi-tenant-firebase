package providers;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import play.Logger;
import providers.models.*;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseDatabaseProvider implements Provider<FirebaseDatabase> {

    private final FirebaseApp firebaseApp;
    public static List<TaxItem> TAXES = new ArrayList<>();

    @Inject
    public FirebaseDatabaseProvider(FirebaseApp firebaseApp) {
        this.firebaseApp = firebaseApp;
        fetchTaxes();
    }

    @Singleton
    @Override
    public FirebaseDatabase get() {
        return FirebaseDatabase.getInstance(firebaseApp);
    }

    @Singleton
    public DatabaseReference getUserDataReference() {
        return this.get().getReference("/usersData");
    }

    @Singleton
    public DatabaseReference getTaxesConfigurationReference() {
        return this.get().getReference("/appData/taxConfiguration");
    }
    private void fetchTaxes() {
        DatabaseReference bundlesRef = getTaxesConfigurationReference().child("taxes");
        bundlesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TAXES.clear();
                dataSnapshot.getChildren().forEach(tax -> TAXES.add(tax.getValue(TaxItem.class)));
                Logger.info(String.format("==> %d taxes records loaded", TAXES.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Logger.warn("The read failed: " + databaseError.getCode());
            }
        });
    }
}