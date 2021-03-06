package controllers.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import models.LightningTalk;
import models.Vote;

import java.lang.reflect.Type;

public class LightningTalkJsonSerializer extends SessionJsonSerializer implements JsonSerializer<LightningTalk> {

    public LightningTalkJsonSerializer(boolean details) {
        super(details);
    }

    @Override
    public JsonElement serialize(LightningTalk talk, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = serializeSession(talk, type, jsonSerializationContext);

        result.addProperty("nbVotes", Vote.findNumberOfVotesBySession(talk));

        return result;
    }
}
