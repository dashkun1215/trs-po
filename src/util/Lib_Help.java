//package util;
//import util.MatrixSolver.*;
//
//public class Lib_Help {
//    public static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
//        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];
//
//        for (int row = 0; row < result.length; row++) {
//            for (int col = 0; col < result[row].length; col++) {
//                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
//            }
//        }
//
//        return result;
//    }
//
//    public static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
//        double cell = 0;
//        for (int i = 0; i < secondMatrix.length; i++) {
//            cell += firstMatrix[row][i] * secondMatrix[i][col];
//        }
//        return cell;
//    }
//
//    public static double[][] powMatrix(double[][] m, int powTimes) {
//        double[][] result = new double[m.length][m[0].length];
//        for(int i = 1; i <= powTimes; i++) {
//            result = multiplyMatrices(result, m);
//        }
//        return result;
//    }
//
//    public static double[][] sumMatrices(double[][] m1, double[][] m2) {
//        double[][] result = new double[m1.length][m1[0].length];
//        for (int i = 0; i < result.length; i++) {
//            for (int j = 0; j < result[i].length; j++) {
//                result[i][j] = m1[i][j] + m2[i][j];
//            }
//        }
//        return result;
//    }
//
//    public static double[][] substructMatrices(double[][] m1, double[][] m2) {
//        double[][] result = new double[m1.length][m1[0].length];
//        for (int i = 0; i < result.length; i++) {
//            for (int j = 0; j < result[i].length; j++) {
//                result[i][j] = m1[i][j] - m2[i][j];
//            }
//        }
//        return result;
//    }
//
//    public static void printResult(double[][] result) {
//        for (int i = 0; i < result.length; i++) {
//            for (int j = 0; j < result[i].length; j++) {
//                System.out.println(
//                        String.format("[%d, %d] = %f", i, j, result[i][j])
//                );
//            }
//        }
//    }
//}
