//package util;
//import util.MatrixSolver.*;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//class MatrixGen implements Runnable {
//    double[][] m;
//    boolean sR;
//    Thread t;
//
//    public MatrixGen(int size, boolean setRandom) {
//        m = new double[size][size];
//        t = new Thread(this);
//        sR = setRandom;
//        t.start();
//    }
//    @Override
//    public void run() {
//        for(int i = 0; i < m.length; i++) {
//            for(int j = 0; j < m[0].length; j++) {
//                if(sR) {
//                    int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
//                    m[i][j] = (double)randomNum * 10;
//                    continue;
//                }
//                m[i][j] = (double)(1 / ((i+1) + 2*(j+1)));
//            }
//        }
//    }
//    public double[][] getValue() {
//        return m;
//    }
//}
