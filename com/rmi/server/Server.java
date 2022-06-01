package com.rmi.server;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Remote, Serializable {
    public static void main(String[] args) {
        try {
            Brain brain = new BrainImpl(100);
            Registry r = LocateRegistry.getRegistry();
            r.bind("brain", brain);
            Matrix y1 = brain.calculate_y1();
            r.bind("y1", y1);
            Matrix y2 = brain.calculate_y2();
            r.bind("y2", y2);
            Matrix Y3 = brain.calculate_Y3();
            r.bind("Y3", Y3);

            r.bind("server", new Server());
            System.out.println("The server is up and running");
        } catch (Exception e ){
            e.printStackTrace();
        }
    }

    protected Server() throws RemoteException {
    }

    public void hello() throws RemoteException {
        System.out.println("hello from " + this.getClass().getSimpleName());
    }
}


