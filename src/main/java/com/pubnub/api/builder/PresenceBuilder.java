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
     * takes a list of strings as input and returns a `PresenceBuilder` object, which is
     * a superclass of `ChatMessage`.
     * 
     * @param channels List of strings that will be used to define the channels for which
     * the presence builder will build the presence states.
     * 
     * The `channels` parameter is a list of strings.
     * The `PresenceBuilder` object returned by the method can be used to construct a
     * presence channel with the specified channels.
     * 
     * @returns a PresenceBuilder instance.
     * 
     * 	- The `PresenceBuilder` object is generated through the `super.channels(channels)`
     * method call, indicating that it inherits properties from its superclass.
     * 	- The `channels` parameter is a list of strings representing the channels to be
     * processed by the `PresenceBuilder`.
     * 	- The returned `PresenceBuilder` object allows for further modifications and
     * customization of the presence information.
     */
    public PresenceBuilder channels(List<String> channels) {
        return (PresenceBuilder) super.channels(channels);
    }

    /**
     * takes a list of strings and returns a `PresenceBuilder` object, allowing for the
     * manipulation of channel groups within the presence system.
     * 
     * @param channelGroups list of strings that will be used to define the presence
     * channels in the PresenceBuilder object returned by the function.
     * 
     * The `channelGroups` parameter is of type `List<String>`, indicating that it is a
     * list of strings.
     * 
     * The `super.channelGroups` call is used to delegate the processing of the `channelGroups`
     * parameter to the superclass, indicating that this function is a subclass of another
     * class that has a `channelGroups` method.
     * 
     * @returns a PresenceBuilder instance filled with the given channel groups.
     * 
     * 	- The `PresenceBuilder` return type indicates that the function returns an object
     * of the `PresenceBuilder` class, which is a builder class for creating presence objects.
     * 	- The `(Super)` prefix on the return statement signifies that the function is
     * overriding a method from its superclass, `Object`.
     * 	- The `channelGroups` parameter is passed as a list of strings, indicating that
     * it is a collection of strings representing the channel groups to be processed by
     * the function.
     */
    public PresenceBuilder channelGroups(List<String> channelGroups) {
        return (PresenceBuilder) super.channelGroups(channelGroups);
    }

}
