package com.rmi.server;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            Brain brain = (Brain) Naming.lookup("rmi://localhost/brain");
            Matrix y1 = (Matrix) Naming.lookup("rmi://localhost/y1");
            Matrix y2 = (Matrix) Naming.lookup("rmi://localhost/y2");
            Matrix Y3 = (Matrix) Naming.lookup("rmi://localhost/Y3");

            Matrix x = brain.findX(y1, y2, Y3);
            System.out.println("X matrix: ");
            Brain.printVector(x.getMatrix());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
