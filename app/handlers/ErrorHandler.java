package handlers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import controllers.base.ErrorResult;
import exceptions.ApplicationException;
import exceptions.AuthorizationException;
import exceptions.BusinessException;
import exceptions.NoRequestJsonBodyException;
import org.joda.time.DateTime;
import play.Configuration;
import play.Environment;
import play.Logger;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;
import services.LogUtil;

import javax.inject.Provider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;


public class ErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public ErrorHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    @Override
    protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
        if (exception instanceof NoRequestJsonBodyException || exception instanceof BusinessException || exception instanceof ApplicationException) {
            if (exception instanceof ApplicationException) {
                exception.printStackTrace();
            }
            Logger.warn(exception.getMessage());
            return CompletableFuture.completedFuture(Results.badRequest(getExceptionBody(exception)));
        } else if (exception instanceof AuthorizationException) {
            return CompletableFuture.completedFuture(Results.unauthorized(getExceptionBody(exception)));
        } else {
            // TODO remove this condition if filter issue fixed
            if (exception.getCause() != null && exception.getCause() instanceof CompletionException) {
                Throwable cause = exception.getCause().getCause();
                if (cause != null && cause instanceof UsefulException) {
                    return onProdServerError(request, (UsefulException) cause);
                }
            }
            exception.printStackTrace();
            return CompletableFuture.completedFuture(
                    Results.internalServerError("A server error occurred: " + getExceptionBody(exception))
            );
        }
    }

    @Override
    protected void logServerError(RequestHeader request, UsefulException exception) {
        if (exception instanceof BusinessException) {
            Logger.warn(String.format("\n\n! @%s - Business Exception, for (%s) [%s] ->\n",
                    exception.id, request.method(), request.uri())
            );
        } else {
            super.logServerError(request, exception);
        }
    }

    @Override
    protected CompletionStage<Result> onForbidden(RequestHeader request, String message) {
        return CompletableFuture.completedFuture(
                Results.forbidden("You're not allowed to access this resource.")
        );
    }

    @Override
    protected CompletionStage<Result> onDevServerError(RequestHeader request, UsefulException exception) {
        Logger.error("Exception: method={} time={} uri={} remote-address={} cause={}", request.path(), DateTime.now().toString(),
                request.uri(), request.remoteAddress(), getExceptionBody(exception));
        if (exception instanceof NoRequestJsonBodyException || exception instanceof BusinessException || exception instanceof ApplicationException) {
            return CompletableFuture.completedFuture(Results.badRequest(getExceptionBody(exception)));
        } else if (exception instanceof AuthorizationException) {
            return CompletableFuture.completedFuture(Results.unauthorized(getExceptionBody(exception)));
        } else {
            // TODO remove this condition if filter issue fixed
            if (exception.getCause() != null && exception.getCause() instanceof CompletionException) {
                Throwable cause = exception.getCause().getCause();
                if (cause != null && cause instanceof UsefulException) {
                    return onDevServerError(request, (UsefulException) cause);
                }
            }
        }
        return super.onDevServerError(request, exception);
    }


    private JsonNode getExceptionBody(UsefulException exception) {
        ErrorResult error = new ErrorResult();
        error.setErrorCode(1001);
        error.setMessage(exception.getMessage());
        error.setStackTrace(LogUtil.getStackTraceString(exception));
        return Json.toJson(error);
    }

}
