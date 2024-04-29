package com.pubnub.api.builder;

import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.managers.SubscriptionManager;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * is an extension of PubSubBuilder that allows for modifying and customizing presence
 * data. It provides methods for adding and modifying channels and channel groups in
 * the presence data, as well as a `execute()` method for applying these modifications
 * to the subscription manager. The class also has publicly accessible fields for
 * connected and subscribed channels.
 */
@Setter
@Accessors(chain = true, fluent = true)
public class PresenceBuilder extends PubSubBuilder {

    @Setter(AccessLevel.PUBLIC)
    private boolean connected;

    public PresenceBuilder(SubscriptionManager subscriptionManager) {
        super(subscriptionManager);
    }

    
    /**
     * builds a `PresenceOperation` object representing the current state of channel
     * subscriptions and updates the presence builder with it.
     */
    public void execute() {
        PresenceOperation presenceOperation = PresenceOperation.builder()
                .channels(this.getChannelSubscriptions())
                .channelGroups(this.getChannelGroupSubscriptions())
                .connected(connected)
                .build();

        this.getSubscriptionManager().adaptPresenceBuilder(presenceOperation);
    }

    /**
     * transforms a list of strings into a PresenceBuilder instance, allowing for further
     * customization of presence data.
     * 
     * @param channels List of presence channels to be built.
     * 
     * @returns a PresenceBuilder object.
     */
    public PresenceBuilder channels(List<String> channels) {
        return (PresenceBuilder) super.channels(channels);
    }

    /**
     * generates high-quality documentation for code given to it.
     * 
     * @param channelGroups List of channel groups that will be used to configure the
     * presence builder.
     * 
     * @returns a PresenceBuilder object.
     */
    public PresenceBuilder channelGroups(List<String> channelGroups) {
        return (PresenceBuilder) super.channelGroups(channelGroups);
    }

}
