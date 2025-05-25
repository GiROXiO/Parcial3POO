package core.models.storage;

import org.json.JSONObject;

public interface JsonTransformer<T> {
    T fromJson(JSONObject obj);
}
