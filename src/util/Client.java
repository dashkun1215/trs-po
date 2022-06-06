package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import util.MatrixSolver.*;

public class Client extends MatrixSolverImpl{

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(8090);
            MatrixSolver stub = (MatrixSolver) registry.lookup("MatrixEquationSolver");

                int n;
                System.out.println("Задайте значення n, n>100 = ");
                n = new Scanner(System.in).nextInt();
                if (n < 100) {
                    System.out.println("Дане число менше ніж 100");
                    n = 100;
                }

            Lib_Help.printResult(stub.solveForN(n));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


