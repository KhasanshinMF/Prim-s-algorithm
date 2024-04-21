package algorithm;

import java.util.Random;

import static algorithm.PrimsAlgorithm.primMST;

public class GenerateRandomDataSets {

    private static final int NUM_DATA_SETS = 100; // Количество наборов данных
    private static final int MIN_SIZE = 100; // Минималь ный размер набора
    private static final int MAX_SIZE = 10000; // Максимальный размер набора

    public static int[][] generateWeightedGraph(int countVertex) {
        Random random = new Random();
        int[][] graph = new int[countVertex][countVertex];

        // Заполняем матрицу смежности случайными весами
        for (int i = 0; i < countVertex; i++) {
            for (int j = i + 1; j < countVertex; j++) {
                if (random.nextInt(100) < 50) {
                    int weight = random.nextInt(15) + 1;
                    graph[i][j] = weight;
                    graph[j][i] = weight;
                }
            }
        }

        return graph;
    }

    public static void main(String[] args) {
        long[] time = new long[100];
        for (int i = 1; i < NUM_DATA_SETS + 1; i++) {
            long startTime = System.nanoTime();
            int[][] graph = generateWeightedGraph(i * 100);
            primMST(graph);
            long endTime = System.nanoTime();
            time[i - 1] = endTime - startTime;
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(time[i]);
        }
    }
}
