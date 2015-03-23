package controllers.api;

import com.google.gson.JsonSerializer;
import models.ConferenceEvent;
import models.LightningTalk;
import models.Talk;
import models.planning.PlanedSlot;
import models.planning.Planning;

import java.util.List;

public class ApiSessions extends JsonpController {

    public static void talks(boolean details) {
        Planning planning = PlanedSlot.on(ConferenceEvent.CURRENT, true);
        renderJSON(planning.getSlots(), details ? DETAILED_SLOT_SERIALIZER : SLOT_SERIALIZER);
    }

    public static void talk(long id, boolean details) {
        Talk talk = Talk.findById(id);
        notFoundIfNull(talk);
        PlanedSlot slot = PlanedSlot.forTalkOn(talk, ConferenceEvent.CURRENT);
        if (slot == null) {
            slot = new PlanedSlot(talk);
        }
        renderJSON(slot, details ? DETAILED_TALK_SERIALIZER : TALK_SERIALIZER);
    }

    public static void lightningTalks(boolean details) {
        List<LightningTalk> talks = LightningTalk.findAllOn(ConferenceEvent.CURRENT);
        renderJSON(talks, details ? DETAILED_LT_SERIALIZER : LT_SERIALIZER);
    }

    public static void lightningTalk(long id, boolean details) {
        LightningTalk talk = LightningTalk.findById(id);
        notFoundIfNull(talk);
        renderJSON(talk, details ? DETAILED_LT_SERIALIZER : LT_SERIALIZER);
    }


    private static JsonSerializer DETAILED_SLOT_SERIALIZER = new PlanedSlotJsonSerializer(true);
    private static JsonSerializer DETAILED_TALK_SERIALIZER = new TalkJsonSerializer(true);
    private static JsonSerializer DETAILED_LT_SERIALIZER = new LightningTalkJsonSerializer(true);
    private static JsonSerializer SLOT_SERIALIZER = new PlanedSlotJsonSerializer(false);
    private static JsonSerializer TALK_SERIALIZER = new TalkJsonSerializer(false);
    private static JsonSerializer LT_SERIALIZER = new LightningTalkJsonSerializer(false);
}
