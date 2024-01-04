package com.akka;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class ReceiverActor extends AbstractActor {
    public static Props props() {
        return Props.create(ReceiverActor.class);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, this::receiveMessage)
                .build();
    }

    private void receiveMessage(String message) {
        System.out.println("ReceiverActor received message: " + message);
    }
}
