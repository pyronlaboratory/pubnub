package com.pubnub.api.builder;

import com.pubnub.api.builder.dto.PresenceOperation;
import com.pubnub.api.managers.SubscriptionManager;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * is an extension of the PubSubBuilder class that allows for building presence
 * channels based on user presence, group membership, and event triggers. It provides
 * a fluent interface for creating complex presence channel rules by combining multiple
 * conditions and actions. The class has three methods: `channelGroups`, `execute`,
 * and `channels` which respectively return a `PresenceBuilder` instance after calling
 * the superclass method `channelGroups` with the provided argument, execute the
 * presence operation, and returns a `PresenceBuilder` instance after taking a list
 * of strings as input, which is then passed to the parent `super.channels()` method
 * for further processing.
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
     * creates a `PresenceOperation` object with subscriptions and groups, and passes it
     * to the `adaptPresenceBuilder` method of the `SubscriptionManager`.
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
     * takes a list of strings as input and returns a PresenceBuilder object after casting
     * it to the appropriate type using the `super` keyword.
     * 
     * @param channels list of presence channels to be used by the `PresenceBuilder`.
     * 
     * @returns a `PresenceBuilder` instance.
     */
    public PresenceBuilder channels(List<String> channels) {
        return (PresenceBuilder) super.channels(channels);
    }

    /**
     * returns a `PresenceBuilder` instance, passing a list of strings representing channel
     * groups as input and applying the superclass's `channelGroups` method to it.
     * 
     * @param channelGroups list of  presence groups to which the builder will assign the
     * presence events.
     * 
     * @returns a `PresenceBuilder` object.
     */
    public PresenceBuilder channelGroups(List<String> channelGroups) {
        return (PresenceBuilder) super.channelGroups(channelGroups);
    }

}
