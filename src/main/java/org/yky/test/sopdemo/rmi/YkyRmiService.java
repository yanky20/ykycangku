package org.yky.test.sopdemo.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by hp on 2018/10/28.
 */
public class YkyRmiService implements YkyRmiInterface {

    public YkyRmiService() throws RemoteException {

    }

    @Override
    public void yky1() throws RemoteException {
        System.out.println("yky1在此");
    }

    @Override
    public YkyObject yky2() throws RemoteException {
        YkyObject ykyObject = new YkyObject();
        ykyObject.setAge(24);
        ykyObject.setName("严恺阳");
        return ykyObject;
    }

    public static void main(String[] args) throws RemoteException {
        YkyRmiService ykyRmiService = new YkyRmiService();
        YkyRmiInterface ykyRmiInterface =
                (YkyRmiInterface) UnicastRemoteObject.exportObject(ykyRmiService, 8099);
        Registry registry = LocateRegistry.createRegistry(8090);
        registry.rebind("userManager", ykyRmiInterface);
        System.out.println("部署成功");
    }
}
