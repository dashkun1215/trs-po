//package util;
//import util.MatrixSolver.*;
//
//public class PowerMatrix implements Runnable {
//    double[][] m;
//    double[][] result;
//    int powTimes;
//    Thread t;
//
//    public PowerMatrix(double[][] m, int powTimes) {
//        this.m = m;
//        this.powTimes = powTimes;
//        t = new Thread(this);
//        t.start();
//    }
//    @Override
//    public void run() {
//        result = Lib_Help.powMatrix(m, powTimes);
//    }
//
//    public double[][] getValue() {
//        return result;
//    }
//}
