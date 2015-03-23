package controllers.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Type;

import models.Interest;
import models.Member;
import models.Session;
import models.SharedLink;

public abstract class AbstractMemberJsonSerializer {

    private boolean details;

    protected AbstractMemberJsonSerializer(boolean details) {
        this.details = details;
    }

    public JsonElement serializeMember(Member member, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("id", member.id);
        result.addProperty("firstname", member.firstname);
        result.addProperty("lastname", member.lastname);
        result.addProperty("login", member.login);
        result.addProperty("company", member.company);
        result.addProperty("shortdesc", member.shortDescription);
        result.addProperty("longdesc", member.longDescription);
        result.addProperty("urlimage", member.getUrlImage());
        result.addProperty("nbConsults", member.nbConsults);

        if (CollectionUtils.size(member.sharedLinks) != 0) {
            JsonArray sharedLinks = new JsonArray();
            for (SharedLink sl : member.sharedLinks) {
                JsonObject link = new JsonObject();
                link.addProperty("id", sl.id);
                link.addProperty("name", sl.name);
                link.addProperty("url", sl.URL);
                link.addProperty("ordernum", sl.ordernum);
                sharedLinks.add(link);
            }
            result.add("sharedLinks", sharedLinks);
        }

        if (CollectionUtils.size(member.interests) != 0) {
            JsonArray interests = new JsonArray();
            for (Interest i : member.interests) {
                JsonObject interest = new JsonObject();
                interest.addProperty("id", i.id);
                interest.addProperty("name", i.name);
                interest.addProperty("url", ApiUrl.getInterestUrl(i.id));
                interests.add(interest);
            }
            result.add("interests", interests);
        }



        return result;
    }
}
