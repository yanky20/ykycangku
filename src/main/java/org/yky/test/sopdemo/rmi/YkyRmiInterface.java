package org.yky.test.sopdemo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hp on 2018/10/28.
 */
public interface YkyRmiInterface extends Remote {
    public void yky1() throws RemoteException;
    public YkyObject yky2() throws RemoteException;
}
