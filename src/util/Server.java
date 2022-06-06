package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;

import util.MatrixSolver.*;
import util.MatrixSolverImpl;

public class Server extends MatrixSolverImpl{


    public static void main(String[] args) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager( new SecurityManager());
//
//        }
        System.setProperty("java.rmi.server.hostname", "localhost");
        MatrixSolver solver = new MatrixSolverImpl();

        try {
            MatrixSolver stub = (MatrixSolver) UnicastRemoteObject.exportObject(solver, 0);//прокидання портів
            Registry registry = LocateRegistry.createRegistry(8090);
            registry.bind("MatrixEquationSolver", stub);
        } catch (Throwable e){
            System.out.println(e.getMessage());
        }
    }
}




class VectorGen implements Runnable {
    double[][] vc;
    boolean sR;
    Thread t;

    public VectorGen(int size, boolean setRandom) {
        vc = new double[size][1];
        sR = setRandom;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        for(int i = 0; i < vc.length; i++) {
            if(sR) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
                vc[i][0] = (double)randomNum * 10;
                continue;
            }

            if(i+1 % 2 == 0) {
                vc[i][0] = 1 / (Math.pow((double)(i+1), 2) + 2);
            } else {
                vc[i][0] = (double)(1 / (i+1));
            }
        }
    }
    public double[][] getValue() {
        return vc;
    }
}


class Programm {
    public static void main(String args[]) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        int n = 100;
        try {
            System.out.println("Задайте значення n, n>100 = ");
            n = Integer.parseInt(reader.readLine());
            if (n < 100) {
                System.out.println("Дане число менше ніж 100");
                n = 100;
            }
        } catch (IOException e) {
        }

        MatrixGen A = new MatrixGen(n, true);
        MatrixGen A1 = new MatrixGen(n, true);
        MatrixGen A2 = new MatrixGen(n, true);
        MatrixGen B2 = new MatrixGen(n, true);
        MatrixGen C2 = new MatrixGen(n, false);

        VectorGen b1 = new VectorGen(n, true);
        VectorGen c1 = new VectorGen(n, true);
        VectorGen bi = new VectorGen(n, false);

        try {
            A.t.join();
            A1.t.join();
            A2.t.join();
            B2.t.join();
            C2.t.join();

            b1.t.join();
            c1.t.join();
            bi.t.join();

            MatricesActions y1 = new MatricesActions(
                    A.getValue(),
                    bi.getValue()
            );

            MatricesActions y2 = new MatricesActions(
                    A1.getValue(),
                    Lib_Help.sumMatrices(b1.getValue(), c1.getValue())
            );
            MatricesActions y3 = new MatricesActions(
                    A2.getValue(),
                    Lib_Help.substructMatrices(B2.getValue(), C2.getValue())
            );

            y1.t.join();
            y2.t.join();
            y3.t.join();

            MatricesActions y3y1PlusY2 = new MatricesActions(
                    y3.getValue(),
                    Lib_Help.sumMatrices(y1.getValue(), y2.getValue())
            );

            PowerMatrix Y3Pow2 = new PowerMatrix(y3.getValue(), 2);

            Y3Pow2.t.join();

            MatricesActions Y3Pow2Y2 = new MatricesActions(Y3Pow2.getValue(), y2.getValue());

            Y3Pow2Y2.t.join();
            y3y1PlusY2.t.join();

            double[][] x = Lib_Help.sumMatrices(Y3Pow2Y2.getValue(), y3y1PlusY2.getValue());
            Lib_Help.printResult(x);
        } catch (InterruptedException e) {
            System.out.println("Збій");
        }
    }
}


class PowerMatrix implements Runnable {
    double[][] m;
    double[][] result;
    int powTimes;
    Thread t;

    public PowerMatrix(double[][] m, int powTimes) {
        this.m = m;
        this.powTimes = powTimes;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        result = Lib_Help.powMatrix(m, powTimes);
    }

    public double[][] getValue() {
        return result;
    }
}


interface MatrixSolver extends Remote {
    public double[][] solveForN(int n) throws RemoteException;

}
class MatrixSolverImpl implements MatrixSolver {

    @Override
    public double[][] solveForN(int n) throws RemoteException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        long start = System.currentTimeMillis();
// ...


        MatrixGen A = new MatrixGen(n, true);
        MatrixGen A1 = new MatrixGen(n, true);
        MatrixGen A2 = new MatrixGen(n, true);
        MatrixGen B2 = new MatrixGen(n, true);
        MatrixGen C2 = new MatrixGen(n, false);

        VectorGen b1 = new VectorGen(n, true);
        VectorGen c1 = new VectorGen(n, true);
        VectorGen bi = new VectorGen(n, false);

        try {
            A.t.join();
            A1.t.join();
            A2.t.join();
            B2.t.join();
            C2.t.join();

            b1.t.join();
            c1.t.join();
            bi.t.join();

            MatricesActions y1 = new MatricesActions(
                    A.getValue(),
                    bi.getValue()
            );

            MatricesActions y2 = new MatricesActions(
                    A1.getValue(),
                    Lib_Help.sumMatrices(b1.getValue(), c1.getValue())
            );
            MatricesActions y3 = new MatricesActions(
                    A2.getValue(),
                    Lib_Help.substructMatrices(B2.getValue(), C2.getValue())
            );

            y1.t.join();
            y2.t.join();
            y3.t.join();

            MatricesActions y3y1PlusY2 = new MatricesActions(
                    y3.getValue(),
                    Lib_Help.sumMatrices(y1.getValue(), y2.getValue())
            );

            PowerMatrix Y3Pow2 = new PowerMatrix(y3.getValue(), 2);

            Y3Pow2.t.join();

            MatricesActions Y3Pow2Y2 = new MatricesActions(Y3Pow2.getValue(), y2.getValue());

            Y3Pow2Y2.t.join();
            y3y1PlusY2.t.join();

            double[][] x = Lib_Help.sumMatrices(Y3Pow2Y2.getValue(), y3y1PlusY2.getValue());

            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            System.out.println(timeElapsed);
            return x;
        } catch (InterruptedException e) {
            System.out.println("Збій");
        }

        return new double[0][0];
    }

}


class MatrixGen implements Runnable {
    double[][] m;
    boolean sR;
    Thread t;

    public MatrixGen(int size, boolean setRandom) {
        m = new double[size][size];
        t = new Thread(this);
        sR = setRandom;
        t.start();
    }
    @Override
    public void run() {
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                if(sR) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
                    m[i][j] = (double)randomNum * 10;
                    continue;
                }
                m[i][j] = (double)(1 / ((i+1) + 2*(j+1)));
            }
        }
    }
    public double[][] getValue() {
        return m;
    }
}


class MatricesActions implements Runnable {
    double[][] m1;
    double[][] m2;
    double[][] result;
    Thread t;

    public MatricesActions(double[][] m1, double[][] m2) {
        this.m1 = m1;
        this.m2 = m2;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        result = Lib_Help.multiplyMatrices(m1, m2);
    }

    public double[][] getValue() {
        return result;
    }
}



class Lib_Help {
    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }

        return result;
    }

    public static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public static double[][] powMatrix(double[][] m, int powTimes) {
        double[][] result = new double[m.length][m[0].length];
        for(int i = 1; i <= powTimes; i++) {
            result = multiplyMatrices(result, m);
        }
        return result;
    }

    public static double[][] sumMatrices(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    public static double[][] substructMatrices(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }

    public static void printResult(double[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.println(
                        String.format("[%d, %d] = %f", i, j, result[i][j])
                );
            }
        }
    }
}

