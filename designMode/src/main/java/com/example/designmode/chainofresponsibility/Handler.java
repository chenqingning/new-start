package com.example.designmode.chainofresponsibility;

/**
 * 责任链模式是一种行为型设计模式，它允许你将请求沿着处理者链进行传递，
 * 直到有一个处理者能够处理该请求。每个处理者都包含一个对下一个处理者的引用，
 * 形成一条链。请求在链上传递，
 * 直到有一个处理者能够处理它，或者请求到达链的末尾而无法被处理。
 * <p>
 * 处理者
 */
public interface Handler {
    void handleRequest(String request);

    void setNextHandler(Handler nextHandler);
}
