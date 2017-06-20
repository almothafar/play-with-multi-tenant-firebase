package controllers.base;

import controllers._shared.TaxesAndFeesBO;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationController extends Controller {

    public Result options(String path) {
        return ok().withHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .withHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS")
                .withHeader(ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .withHeader(ACCESS_CONTROL_ALLOW_HEADERS, "Accept, Origin, Content-type, Authentication");
    }

    public Result testFirebase() {
        TaxesAndFeesBO taxesAndFeesBO = ControllerUtils.calculateTaxesAndFees(10f, "United States", "TX", null);
        return ok(Json.toJson(taxesAndFeesBO));
    }
}
