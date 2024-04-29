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
     * returns a `PresenceBuilder` instance after taking a list of strings as input, which
     * is then passed to the parent `super.channels()` method for further processing.
     * 
     * @param channels list of presence channels to be used by the `PresenceBuilder`
     * object returned by the function.
     * 
     * The `List<String>` parameter represents a list of strings that contain the channels
     * to be processed by the `PresenceBuilder`.
     * The return type is a casted instance of `PresenceBuilder`, indicating that the
     * method calls the `super.channels()` method, passing in the `channels` list as an
     * argument.
     * 
     * @returns a `PresenceBuilder` object.
     * 
     * 	- The return type is `(PresenceBuilder)`, indicating that it is a builder object
     * for creating presence objects.
     * 	- The method is called `super.channels`, which means that it is calling the parent
     * class's `channels` method to retrieve a list of channels.
     * 	- The `List<String>` parameter `channels` represents the list of channels that
     * are passed as arguments to the function.
     */
    public PresenceBuilder channels(List<String> channels) {
        return (PresenceBuilder) super.channels(channels);
    }

    /**
     * takes a list of String arguments and returns a PresenceBuilder object after calling
     * the superclass method `channelGroups` with the provided argument.
     * 
     * @param channelGroups list of channels that will be used for grouping presence events.
     * 
     * 	- It is a list of strings, indicating the channel groups to which the presence
     * builder should apply its operations.
     * 	- The elements in the list can be any combination of valid channel names or
     * wildcards, allowing for flexible matching and application of presence operations
     * across multiple channels.
     * 
     * @returns a `PresenceBuilder` instance.
     * 
     * The `PresenceBuilder` object is the result of calling the `super.channelGroups`
     * method and passing in the `List<String>` parameter.
     * 
     * The `PresenceBuilder` class provides a way to build presence channels based on a
     * set of criteria, such as user presence, group membership, and event triggers. It
     * can be used to create complex presence channel rules by combining multiple conditions
     * and actions.
     */
    public PresenceBuilder channelGroups(List<String> channelGroups) {
        return (PresenceBuilder) super.channelGroups(channelGroups);
    }

}
