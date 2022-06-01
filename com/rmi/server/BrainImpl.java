package com.rmi.server;

import java.io.Serializable;

public class BrainImpl implements Brain, Serializable {
    public static int n = 0;

    private static double[][] A = new double[n][n];
    private static double[][] b = new double[n][1];
    public static double[][] y1 = new double[n][1];

    private static double[][] A1 = new double[n][n];
    private static double[][] b1 = new double[n][1];
    private static double[][] c1 = new double[n][1];
    public static double[][] y2 = new double[n][1];

    private static double[][] A2 = new double[n][n];
    private static double[][] B2 = new double[n][n];
    private static double[][] C2 = new double[n][n];
    public static double[][] Y3 = new double[n][n];


    public BrainImpl(int n) {
        BrainImpl.n = n;
        RandomChoice();
    }

    public BrainImpl() {
        BrainImpl.n = 600;
        RandomChoice();
    }

    /**
     * y1 = A * b, де bi=3/(i2+3) --  для парних i
     * bi=3/i      -- для непарних і
     */
    public Matrix calculate_y1() {
        int m = A.length;
        int u = b[0].length;
        int o = b.length;
        y1 = new double[n][1];
        //множення матриць
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < u; j++) {
                for (int k = 0; k < o; k++) {
                    y1[i][j] += A[i][k] * b[k][j];
                }
            }
        }
        //System.out.println("Матриця y1: ");
        for (int i = 0; i < n; i++) {
            //System.out.println(y1[i][0]);
        }
        return new Matrix(y1);
    }

    /**
     * y2 = A1(3b1+c1)
     */
    public Matrix calculate_y2() {
        double[][] multThree_b1 = b1; //3b1
        double[][] multThreeb1_Plus_c1 = c1; //3b1+c1
        //3*b1
        for (int i = 0; i < n; i++) {
            multThree_b1[i][0] = 3 * b1[i][0];
        }
        //3*b1 + c1
        for (int i = 0; i < n; i++) {
            multThreeb1_Plus_c1[i][0] = multThree_b1[i][0] + c1[i][0];
        }
        //A1(3b1+c1)
        int m = A1.length;
        int u = multThreeb1_Plus_c1[0].length;
        int o = multThreeb1_Plus_c1.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < u; j++) {
                for (int k = 0; k < o; k++) {
                    y2[i][j] += A1[i][k] * multThreeb1_Plus_c1[k][j];
                }
            }
        }
        //System.out.println("y2:");
        for (int i = 0; i < n; i++) {
            //System.out.println(y2[i][0]);
        }
        return new Matrix(y2);
    }

    /**
     * A2(C2-B2), Cij=1/(i+j)
     */
    public Matrix calculate_Y3() {
        double[][] C2MinusB2 = C2;
        //C2-B2
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C2MinusB2[i][j] = C2[i][j] - B2[i][j];
            }
        }
        //A2(C2-B2)
        int m = A2.length;
        int u = C2MinusB2[0].length;
        int o = C2MinusB2.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < u; j++) {
                for (int k = 0; k < o; k++) {
                    Y3[i][j] = A2[i][k] * C2MinusB2[k][j];
                }
            }
        }
        //System.out.println("Matrix Y");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.print(Y3[i][j] + " ");
            }
            //System.out.println();
        }
        return new Matrix(Y3);
    }

    public Matrix findX(Matrix y1, Matrix y2, Matrix Y3) {
        double[][] transposed_y1;
        double[][] transposed_y2;
        transposed_y1 = transpose(y1.getMatrix());
        transposed_y2 = transpose(y2.getMatrix());

        System.out.println("X=");
        double[][] Y3_mult_y2 = multiply(Y3.getMatrix(), y2.getMatrix());
        double[][] part1 = multiplyV(Y3_mult_y2, transposed_y2); //(Y3y2y2')

        double[][] part2 = pow(Y3.getMatrix(), 3); //Y3^3

        double[][] part3 = Y3.getMatrix(); //Y3

        double[][] part4 = multiplyV(y2.getMatrix(), transposed_y1); //y2*y1'

        double[][] part5 = multiply(pow(Y3.getMatrix(), 2), multiplyV(y1.getMatrix(), transposed_y1)); //Y3^2*y1*y1'

        double[][] res1 = sum(part2, part1);

        double[][] res2 = sub(res1, part3);

        double[][] res3 = sum(res2, part4);

        double[][] X = sum(res3, part5);

        printVector(X);
        return new Matrix(X);
    }

    static void RandomChoice() {
        //for y1
        BrainImpl.A = new double[BrainImpl.n][BrainImpl.n];
        BrainImpl.b = new double[BrainImpl.n][1];
        BrainImpl.y1 = new double[BrainImpl.n][1];
        //for y2
        BrainImpl.A1 = new double[BrainImpl.n][BrainImpl.n];
        BrainImpl.b1 = new double[BrainImpl.n][1];
        BrainImpl.c1 = new double[BrainImpl.n][1];
        BrainImpl.y2 = new double[BrainImpl.n][1];
        //for Y3
        BrainImpl.A2 = new double[BrainImpl.n][BrainImpl.n];
        BrainImpl.B2 = new double[BrainImpl.n][BrainImpl.n];
        BrainImpl.C2 = new double[BrainImpl.n][BrainImpl.n];
        BrainImpl.Y3 = new double[BrainImpl.n][BrainImpl.n];
        int min = 0;
        int max = BrainImpl.n;
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                BrainImpl.A[i][j] = min + (int) (Math.random() * max);
                BrainImpl.A1[i][j] = min + (int) (Math.random() * max);
                BrainImpl.A2[i][j] = min + (int) (Math.random() * max);
                BrainImpl.B2[i][j] = min + (int) (Math.random() * max);
            }
        }
        for (int i = 0; i < BrainImpl.n; i++) {
            BrainImpl.b1[i][0] = min + (int) (Math.random() * max);
            BrainImpl.c1[i][0] = min + (int) (Math.random() * max);
        }
        for (int i = 0; i < BrainImpl.n; i++) {
            if (i % 2 == 0) {
                BrainImpl.b[i][0] = (int) (3 / (Math.pow(i, 2) + 3));
            } else {
                BrainImpl.b[i][0] = 1 / i;
            }
        }
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                if (i == 0 && j == 0) {
                    BrainImpl.C2[i][j] = 0;
                } else {
                    BrainImpl.C2[i][j] = (1 / (i + Math.pow(j, 2)));
                }
            }
        }
        ////System.out.println("Матриця A: ");
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                ////System.out.print(A[i][j] + " ");
            }
            ////System.out.println();
        }
        ////System.out.println("Matrix b");
        for (int i = 0; i < BrainImpl.n; i++) {
            ////System.out.println(b[i][0]);
        }
        ////System.out.println("Матриця A1: ");
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                ////System.out.print(A1[i][j] + " ");
            }
            ////System.out.println();
        }
        ////System.out.println("Matrix b1");
        for (int i = 0; i < BrainImpl.n; i++) {
            ////System.out.println(b1[i][0]);
        }
        ////System.out.println("Matrix c1");
        for (int i = 0; i < BrainImpl.n; i++) {
            ////System.out.println(c1[i][0]);
        }
        ////System.out.println("Матриця A2: ");
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                ////System.out.print(A2[i][j] + " ");
            }
            ////System.out.println();
        }
        // //System.out.println("Матриця B2: ");
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                //System.out.print(B2[i][j] + " ");
            }
            //System.out.println();
        }
        //System.out.println("Матриця C2: ");
        for (int i = 0; i < BrainImpl.n; i++) {
            for (int j = 0; j < BrainImpl.n; j++) {
                //System.out.print(C2[i][j] + " ");
            }
            //System.out.println();
        }

    }

    static double[][] sum(double[][] A, double[][] B) {
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

    static double[][] sub(double[][] A, double[][] B) {
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

    static double[][] multiply(double[][] A, double[][] B) {
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

    static double[][] multiplyV(double[][] A, double[][] B) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                A[i][j] = A[i][j] * B[i][j];
            }
        }
        return A;
    }

    static double[][] pow(double[][] matrix, int power) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.pow(matrix[i][j], power);
            }
        }
        return matrix;
    }

    static double[][] transpose(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                // Здесь начальное значение j определено как i,
                // если оно определено как 1, будет двойная транспонирование,
                // что эквивалентно исходному начальному массиву;
                if (i != j) {
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        return matrix;
    }

    static void printVector(double[][] v) {
        for (int i = 0; i < v[0].length; i++) {
            System.out.printf("%.2f\n", v[i][0]);
        }
    }
}
