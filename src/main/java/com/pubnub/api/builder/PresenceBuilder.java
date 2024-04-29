package com.pubnub.api.builder;

import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.managers.SubscriptionManager;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

@Setter
@Accessors(chain = true, fluent = true)
public class PresenceBuilder extends PubSubBuilder {

    @Setter(AccessLevel.PUBLIC)
    private boolean connected;

    public PresenceBuilder(SubscriptionManager subscriptionManager) {
        super(subscriptionManager);
    }

    
    public void execute() {
        PresenceOperation presenceOperation = PresenceOperation.builder()
                .channels(this.getChannelSubscriptions())
                .channelGroups(this.getChannelGroupSubscriptions())
                .connected(connected)
                .build();

        this.getSubscriptionManager().adaptPresenceBuilder(presenceOperation);
    }

    /**
     * maps a list of strings to a `PresenceBuilder`.
     * 
     * @param channels list of presence channels that the `PresenceBuilder` will operate
     * on.
     * 
     * @returns a PresenceBuilder instance.
     */
    public PresenceBuilder channels(List<String> channels) {
        return (PresenceBuilder) super.channels(channels);
    }

    /**
     * updates the presence builder with the provided list of channel groups.
     * 
     * @param channelGroups list of channels to which the presence builder should be applied.
     * 
     * @returns a PresenceBuilder object.
     */
    public PresenceBuilder channelGroups(List<String> channelGroups) {
        return (PresenceBuilder) super.channelGroups(channelGroups);
    }

}
