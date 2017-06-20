package actions;

import com.google.inject.Inject;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Created by almothafar on 6/20/2017.
 */
public class ActionCreator implements play.http.ActionCreator {

    @Inject
    public ActionCreator() {
    }

    @Override
    public Action createAction(Http.Request request, Method actionMethod) {
        switchTenancyId(request);
        return new Action.Simple() {
            @Override
            public CompletionStage<Result> call(Http.Context ctx) {
                return delegate.call(ctx);
            }
        };
    }

    private void switchTenancyId(Http.RequestHeader request) {
        // DO something here
    }

    private Optional<String> getTenancyId(Http.RequestHeader request) {
        String websiteId = request.getHeader("Website-ID");
        System.out.println(websiteId);
        return null;
    }
}
