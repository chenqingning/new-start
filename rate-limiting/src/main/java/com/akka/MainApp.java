package com.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class MainApp {

    public static void main(String[] args) {
        // 创建 Actor 系统
        ActorSystem system = ActorSystem.create("MyActorSystem");

        // 创建接收消息的 Actor
        ActorRef receiver = system.actorOf(ReceiverActor.props(), "receiverActor");

        // 创建发送消息的 Actor，并传递接收消息的 Actor 引用
        ActorRef sender = system.actorOf(SenderActor.props(receiver), "senderActor");

        // 发送消息给接收消息的 Actor
        sender.tell("Hello Akka!", ActorRef.noSender());

        // 关闭 Actor 系统
        system.terminate();
    }
}
