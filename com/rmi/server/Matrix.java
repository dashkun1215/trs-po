package com.rmi.server;

import java.io.Serializable;
import java.rmi.Remote;

public class Matrix implements Remote, Serializable {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }
}