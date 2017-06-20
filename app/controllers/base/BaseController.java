package controllers.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.NoRequestJsonBodyException;
import play.libs.Json;
import play.mvc.Controller;

import java.util.Map;

public abstract class BaseController extends Controller {
    protected JsonNode getRequestBodyAsJson() throws NoRequestJsonBodyException {
        JsonNode requestBody = request().body().asJson();
        if (requestBody == null) {
            throw new NoRequestJsonBodyException("No form data");
        }
        return request().body().asJson();
    }

    /**
     * Convert the request body from JSON to map of properties
     *
     * @param clazz the view object class for propose of validation of the json objects (e.g: remove additional properties not listed, remove null values)
     * @param <T>   any class
     * @return a map of object properties and values
     */
    protected <T extends BaseVO> Map<String, String> getRequestBodyAsMap(Class<T> clazz) {
        return (new ObjectMapper()).convertValue(Json.fromJson(getRequestBodyAsJson(), clazz), new TypeReference<Map<String, String>>() {
        });
    }

    protected <T extends BaseVO> Map<String, String> getObjectAsMap(T object) {
        return (new ObjectMapper()).convertValue(Json.fromJson(Json.toJson(object), object.getClass()), new TypeReference<Map<String, String>>() {
        });
    }
}

