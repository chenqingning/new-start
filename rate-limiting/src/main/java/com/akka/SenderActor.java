package com.akka;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class SenderActor extends AbstractActor {

    private final ActorRef receiver;

    public SenderActor(ActorRef receiver) {
        this.receiver = receiver;
    }
    public static Props props(ActorRef receiver) {
        return Props.create(SenderActor.class, receiver);
    }
    private void sendMessage(String message) {
        System.out.println("SenderActor sending message: " + message);
        receiver.tell(message, getSelf());
    }
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, this::sendMessage)
                .build();
    }
}
