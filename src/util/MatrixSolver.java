//package util;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.rmi.Remote;
//import java.rmi.RemoteException;
//import util.VectorGen;
//import util.MatrixSolverImpl;
//
//public interface MatrixSolver extends Remote {
//    public double[][] solveForN(int n) throws RemoteException;
//
//}
//class MatrixSolverImpl implements MatrixSolver {
//
//    @Override
//    public double[][] solveForN(int n) throws RemoteException {
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(System.in));
//
//        long start = System.currentTimeMillis();
//// ...
//
//
//        MatrixGen A = new MatrixGen(n, true);
//        MatrixGen A1 = new MatrixGen(n, true);
//        MatrixGen A2 = new MatrixGen(n, true);
//        MatrixGen B2 = new MatrixGen(n, true);
//        MatrixGen C2 = new MatrixGen(n, false);
//
//        VectorGen b1 = new VectorGen(n, true);
//        VectorGen c1 = new VectorGen(n, true);
//        VectorGen bi = new VectorGen(n, false);
//
//        try {
//            A.t.join();
//            A1.t.join();
//            A2.t.join();
//            B2.t.join();
//            C2.t.join();
//
//            b1.t.join();
//            c1.t.join();
//            bi.t.join();
//
//            MatricesActions y1 = new MatricesActions(
//                    A.getValue(),
//                    bi.getValue()
//            );
//
//            MatricesActions y2 = new MatricesActions(
//                    A1.getValue(),
//                    Lib_Help.sumMatrices(b1.getValue(), c1.getValue())
//            );
//            MatricesActions y3 = new MatricesActions(
//                    A2.getValue(),
//                    Lib_Help.substructMatrices(B2.getValue(), C2.getValue())
//            );
//
//            y1.t.join();
//            y2.t.join();
//            y3.t.join();
//
//            MatricesActions y3y1PlusY2 = new MatricesActions(
//                    y3.getValue(),
//                    Lib_Help.sumMatrices(y1.getValue(), y2.getValue())
//            );
//
//            PowerMatrix Y3Pow2 = new PowerMatrix(y3.getValue(), 2);
//
//            Y3Pow2.t.join();
//
//            MatricesActions Y3Pow2Y2 = new MatricesActions(Y3Pow2.getValue(), y2.getValue());
//
//            Y3Pow2Y2.t.join();
//            y3y1PlusY2.t.join();
//
//            double[][] x = Lib_Help.sumMatrices(Y3Pow2Y2.getValue(), y3y1PlusY2.getValue());
//
//            long finish = System.currentTimeMillis();
//            long timeElapsed = finish - start;
//            System.out.println(timeElapsed);
//            return x;
//        } catch (InterruptedException e) {
//            System.out.println("Збій");
//        }
//
//        return new double[0][0];
//        }
//
//    }
