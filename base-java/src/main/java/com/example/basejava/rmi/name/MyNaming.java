package com.example.basejava.rmi.name;

import com.example.basejava.rmi.remote.MyRemoteInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 相当于client端，给用户查到服务端（远程端）对象
 */

public class MyNaming {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        MyRemoteInterface myRemoteInterface = (MyRemoteInterface) Naming.lookup("rmi://localhost:1099/MyRemoteObject");
        String name = myRemoteInterface.sayHello();
        System.out.println(name);
    }
}
