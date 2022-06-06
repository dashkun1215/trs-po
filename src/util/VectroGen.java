//package util;
//import util.MatrixSolver.*;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//class VectorGen implements Runnable {
//    double[][] vc;
//    boolean sR;
//    Thread t;
//
//    public VectorGen(int size, boolean setRandom) {
//        vc = new double[size][1];
//        sR = setRandom;
//        t = new Thread(this);
//        t.start();
//    }
//    @Override
//    public void run() {
//        for(int i = 0; i < vc.length; i++) {
//            if(sR) {
//                int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
//                vc[i][0] = (double)randomNum * 10;
//                continue;
//            }
//
//            if(i+1 % 2 == 0) {
//                vc[i][0] = 1 / (Math.pow((double)(i+1), 2) + 2);
//            } else {
//                vc[i][0] = (double)(1 / (i+1));
//            }
//        }
//    }
//    public double[][] getValue() {
//        return vc;
//    }
//}
