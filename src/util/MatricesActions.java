//package util;
//import util.MatrixSolver.*;
//
//public class MatricesActions implements Runnable {
//    double[][] m1;
//    double[][] m2;
//    double[][] result;
//    Thread t;
//
//    public MatricesActions(double[][] m1, double[][] m2) {
//        this.m1 = m1;
//        this.m2 = m2;
//        t = new Thread(this);
//        t.start();
//    }
//    @Override
//    public void run() {
//        result = Lib_Help.multiplyMatrices(m1, m2);
//    }
//
//    public double[][] getValue() {
//        return result;
//    }
//}
