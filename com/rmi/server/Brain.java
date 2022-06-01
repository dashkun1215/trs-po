package com.rmi.server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Brain extends Remote, Serializable {
    static double[][] sum(double[][] A, double[][] B) throws RemoteException {
        int n = A.length;
        int n1 = B[0].length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n1; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    static double[][] sub(double[][] A, double[][] B) throws RemoteException{
        int n = A.length;
        int n1 = B[0].length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n1; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    static double[][] multiply(double[][] A, double[][] B) throws RemoteException{
        int m = A.length;
        int u = B[0].length;
        int o = B.length;
        double[][] res = new double[m][u];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < u; j++) {
                for (int k = 0; k < o; k++) {
                    res[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return res;
    }

    static double[][] multiplyV(double[][] A, double[][] B) throws RemoteException{
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                A[i][j] = A[i][j] * B[i][j];
            }
        }
        return A;
    }

    static double[][] pow(double[][] matrix, int power) throws RemoteException{
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.pow(matrix[i][j], power);
            }
        }
        return matrix;
    }

    static double[][] transpose(double[][] matrix) throws RemoteException{
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                if (i != j) {
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        return matrix;
    }

    static void printVector(double[][] v) throws RemoteException{
        for (int i = 0; i < v[0].length; i++) {
            System.out.printf("%.2f\n", v[i][0]);
        }
    }

    Matrix calculate_y1() throws RemoteException;

    Matrix calculate_y2() throws RemoteException;

    Matrix calculate_Y3() throws RemoteException;

    Matrix findX(Matrix y1, Matrix y2, Matrix Y3) throws RemoteException;
}
