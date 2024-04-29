package com.pubnub.api.builder;

import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.managers.SubscriptionManager;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * is an extension of PubSubBuilder that allows for the modification and customization
 * of presence data. The class has several methods for adding and modifying channels
 * and channel groups in the presence data, as well as a `execute()` method for
 * applying these modifications to the subscription manager.
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
     * builds a `PresenceOperation` object based on subscription and channel group
     * information, then passes it to the `adaptPresenceBuilder` method of the `SubscriptionManager`.
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
     * returns a `PresenceBuilder` instance with the given channel list as argument to
     * the super method, which is then used to set the channels for the presence builder.
     * 
     * @param channels list of presence channels to which the builder will apply its operations.
     * 
     * @returns a `PresenceBuilder` instance.
     */
    public PresenceBuilder channels(List<String> channels) {
        return (PresenceBuilder) super.channels(channels);
    }

    /**
     * takes a list of strings and returns a `PresenceBuilder` object.
     * 
     * @param channelGroups list of channels that the `PresenceBuilder` instance will
     * operate on.
     * 
     * @returns a PresenceBuilder instance.
     */
    public PresenceBuilder channelGroups(List<String> channelGroups) {
        return (PresenceBuilder) super.channelGroups(channelGroups);
    }

}
