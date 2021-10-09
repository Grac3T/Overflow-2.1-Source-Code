// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import com.google.gson.JsonParseException;
import org.eclipse.egit.github.core.event.EventPayload;
import org.eclipse.egit.github.core.event.WatchPayload;
import org.eclipse.egit.github.core.event.TeamAddPayload;
import org.eclipse.egit.github.core.event.ReleasePayload;
import org.eclipse.egit.github.core.event.PushPayload;
import org.eclipse.egit.github.core.event.PullRequestReviewCommentPayload;
import org.eclipse.egit.github.core.event.PullRequestPayload;
import org.eclipse.egit.github.core.event.PublicPayload;
import org.eclipse.egit.github.core.event.MemberPayload;
import org.eclipse.egit.github.core.event.IssuesPayload;
import org.eclipse.egit.github.core.event.IssueCommentPayload;
import org.eclipse.egit.github.core.event.GollumPayload;
import org.eclipse.egit.github.core.event.GistPayload;
import org.eclipse.egit.github.core.event.ForkApplyPayload;
import org.eclipse.egit.github.core.event.ForkPayload;
import org.eclipse.egit.github.core.event.FollowPayload;
import org.eclipse.egit.github.core.event.DownloadPayload;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.eclipse.egit.github.core.event.CreatePayload;
import org.eclipse.egit.github.core.event.CommitCommentPayload;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.FieldNamingPolicy;
import java.lang.reflect.Type;
import java.util.Date;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import org.eclipse.egit.github.core.event.Event;
import com.google.gson.JsonDeserializer;

public class EventFormatter implements JsonDeserializer<Event>
{
    private final Gson gson;
    
    public EventFormatter() {
        this.gson = new GsonBuilder().registerTypeAdapter((Type)Date.class, (Object)new DateFormatter()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }
    
    public Event deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final Event event = (Event)this.gson.fromJson(json, (Class)Event.class);
        if (event == null || !json.isJsonObject()) {
            return event;
        }
        final JsonElement rawPayload = json.getAsJsonObject().get("payload");
        if (rawPayload == null || !rawPayload.isJsonObject()) {
            return event;
        }
        final String type = event.getType();
        if (type == null || type.length() == 0) {
            return event;
        }
        Class<? extends EventPayload> payloadClass;
        if ("CommitCommentEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)CommitCommentPayload.class;
        }
        else if ("CreateEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)CreatePayload.class;
        }
        else if ("DeleteEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)DeletePayload.class;
        }
        else if ("DownloadEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)DownloadPayload.class;
        }
        else if ("FollowEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)FollowPayload.class;
        }
        else if ("ForkEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)ForkPayload.class;
        }
        else if ("ForkApplyEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)ForkApplyPayload.class;
        }
        else if ("GistEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)GistPayload.class;
        }
        else if ("GollumEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)GollumPayload.class;
        }
        else if ("IssueCommentEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)IssueCommentPayload.class;
        }
        else if ("IssuesEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)IssuesPayload.class;
        }
        else if ("MemberEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)MemberPayload.class;
        }
        else if ("PublicEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)PublicPayload.class;
        }
        else if ("PullRequestEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)PullRequestPayload.class;
        }
        else if ("PullRequestReviewCommentEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)PullRequestReviewCommentPayload.class;
        }
        else if ("PushEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)PushPayload.class;
        }
        else if ("ReleaseEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)ReleasePayload.class;
        }
        else if ("TeamAddEvent".equals(type)) {
            payloadClass = (Class<? extends EventPayload>)TeamAddPayload.class;
        }
        else {
            if (!"WatchEvent".equals(type)) {
                return event;
            }
            payloadClass = (Class<? extends EventPayload>)WatchPayload.class;
        }
        try {
            final EventPayload typedPayload = (EventPayload)context.deserialize(rawPayload, (Type)payloadClass);
            return event.setPayload(typedPayload);
        }
        catch (JsonParseException jpe) {
            return event;
        }
    }
}
