package com.example.designmode.chainofresponsibility;

public class ConcreteHandler1 implements Handler {

    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {
        if (request.equals("Type1")) {
            System.out.println("ConcreteHandler1 handles the request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }

}
