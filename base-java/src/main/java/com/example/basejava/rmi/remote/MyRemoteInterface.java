package com.example.basejava.rmi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 实现Remote interface是为了标记该类执行方法时是需要调用非本地虚拟机的方法，是其他虚拟机上的方法
 */

public interface MyRemoteInterface extends Remote {
    String sayHello() throws RemoteException;
    
}
