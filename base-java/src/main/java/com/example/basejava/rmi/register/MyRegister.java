package com.example.basejava.rmi.register;

import com.example.basejava.rmi.remote.MyRemoteInterface;
import com.example.basejava.rmi.remote.MyRemoteObject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 使用rmi中register,服务端
 */
public class MyRegister {
    public static void main(String[] args) throws RemoteException, InterruptedException {
        MyRemoteInterface myRemoteInterface = new MyRemoteObject();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("MyRemoteObject", myRemoteInterface);
        System.out.println("Server is ready");

        Registry registry1 = LocateRegistry.getRegistry();
        String[] list = registry1.list();
        for (String s:list){
            System.out.println(s);
        }
    }
}
