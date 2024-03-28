package com.example.basejava.rmi.remote;

import com.example.basejava.rmi.remote.MyRemoteInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyRemoteObject extends UnicastRemoteObject implements MyRemoteInterface {


    public MyRemoteObject() throws RemoteException {

    }

    @Override
    public String sayHello() {
        return "Hello from the remote object!";
    }
}
