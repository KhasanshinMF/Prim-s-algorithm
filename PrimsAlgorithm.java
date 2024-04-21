package algorithm;

import java.util.Arrays;

public class PrimsAlgorithm {
    // Метод для построения минимального остовного дерева по матрице смежности графа
    public static int[][] primMST(int[][] graph) {

        int countVertex = graph.length;
        int[] parents = new int[countVertex];
        int[] weights = new int[countVertex];
        boolean[] vertices = new boolean[countVertex];

        Arrays.fill(weights, Integer.MAX_VALUE);
        weights[0] = 0;
        parents[0] = -1;

        // Построение остовного дерева
        for (int i = 0; i < countVertex - 1; i++) {
            int u = minWeights(weights, vertices);
            vertices[u] = true;

            // Обновляем ключи и родительские вершины смежных вершин
            for (int v = 0; v < countVertex; v++) {
                if (graph[u][v] != 0 && !vertices[v] && graph[u][v] < weights[v]) {
                    parents[v] = u;
                    weights[v] = graph[u][v];
                }
            }
        }

        // Построение матрицы минимального остовного дерева
        int[][] mst = new int[countVertex][countVertex];
        for (int i = 1; i < countVertex; i++) {
            mst[parents[i]][i] = graph[i][parents[i]];
            mst[i][parents[i]] = graph[i][parents[i]];
        }

        return mst;
    }

    // Метод для поиска вершины с минимальным ключом
    private static int minWeights(int[] weights, boolean[] vertices) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < weights.length; v++) {
            if (!vertices[v] && weights[v] < min) {
                min = weights[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Вывод матрицы минимального остовного дерева
    public static void printMst(int[][] mst) {
        for (int i = 0; i < mst.length; i++) {
            for (int j = 0; j < mst[i].length; j++) {
                System.out.print(mst[i][j] + " ");
            }
            System.out.println();
        }
    }
}
