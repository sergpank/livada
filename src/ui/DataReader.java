package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import logic.Data;

public class DataReader {
    public String[][] readTrees(String fileName) {
        String[][] data;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String header = br.readLine();
            String[] size = header.split(" ");
            int n = Integer.parseInt(size[0]);
            int m = Integer.parseInt(size[1]);

            System.out.printf("Array size is %d x %d", n, m);

            data = new String[n][m];

            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] trees = line.split(" ");
                data[row++] = trees;
            }
            Arrays.toString(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public double[][] readHarvest(String fileName, int rows) {
        double[][] data = new double[rows][];
        int row = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(" ");
                double[] centners = new double[s.length];
                for (int i = 0; i < s.length; i++) {
                    centners[i] = Double.parseDouble(s[i]);
                }
                data[row++] = centners;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    public Data[][] mergeData(String[][] trees, double[][] harvest) {
        Data[][] result = new Data[trees.length][trees[0].length];

        for (int row = 0; row < trees.length; row++) {
            for (int col = 0; col < trees[0].length; col++) {
                result[row][col] = new Data(trees[row][col], harvest[row][col]);
            }
        }

        return result;
    }
}
