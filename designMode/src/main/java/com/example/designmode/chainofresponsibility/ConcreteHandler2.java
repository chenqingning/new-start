package com.example.designmode.chainofresponsibility;

public class ConcreteHandler2 implements Handler {
    private Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {
        if (request.equals("Type2")) {
            System.out.println("ConcreteHandler2 handles the request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
