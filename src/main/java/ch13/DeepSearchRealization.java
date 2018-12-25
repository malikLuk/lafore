/**
 *
 *                                                      Реализация на Java.
 * Центральное место при поиске в глубину занимает поиск вершин, смежных по отношению к заданной. Решить эту задачу можно
 * при помощи матрицы смежности. Программа просматривает строку заданной вершины (например, gr_9.png) и отбирает столбцы,
 * содержащие 1; номер столбца определяет номер смежной вершины. Далее, программа проверяет, посещалась ли вершина ранее.
 * Если вершина не посещалась, значит, мы нашли искомое - следующую вершину для посещения. Если в строке не осталось ни
 * одной вершины с 1, для которой не был установлен флаг посещения, значит, непосещенных вершин больше не осталось. Это
 * реализовано в методе getAdjUnvisitedVertex();
 *
 * */

package ch13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DeepSearchRealization {

    public static void main(String[] args) {
        GraphExample theGraph = new GraphExample();
        /**
         * Получится граф gr_10.png
         */
        theGraph.addVertex('A'); // 0 (исходная вершина)
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4
        theGraph.addEdge(0, 1); // AB
        theGraph.addEdge(1, 2); // BC
        theGraph.addEdge(0, 3); // AD
        theGraph.addEdge(3, 4); // DE
        System.out.println("Visits: ");
        theGraph.dfs();
        System.out.println();
        theGraph.bfs();
        System.out.println();
    }

}

/**
 * Класс вершины
 * */
class Vertex {

    public char label;
    public boolean wasVisited;

    public Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }

}

/**
 * Граф
 * */
class GraphExample {

    private final int MAX_VERTS = 20; // максимальное количество возможных вершин
    private Vertex[] vertexList; // массив вершин
    private int[][] adjMat; // матрица смежности
    private int nVerts; // текущее количество вершин в стеке
    private Stack<Integer> theStack; // наш стек
    private Queue<Integer> theQueue; // наша очередь в виде связного списка

    public GraphExample() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        theStack = new Stack<Integer>();
        theQueue = new LinkedList<Integer>();
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    public void addVertex(char label) {
        vertexList[nVerts++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.println(vertexList[vertex].label);
    }

    /**
     * Возвращает непосещенную вершину, смежную с v
     * то есть, получается, что мы смотрим v-ую строку
     * */
    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < nVerts; i++) {
            if (adjMat[v][i] == 1 && vertexList[i].wasVisited == false) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Обход в глубину
     * Алгоритм начинается с вершины 0
     * */
    public void dfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theStack.push(0);

        while (!theStack.isEmpty()) {
            // Получаем непосещенную вершину, смежную с текущей
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -1) {
                theStack.pop();
            } else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                theStack.push(v);
            }
        }
        // Сброс флагов посещения
        for (int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    /**
     * Обход в ширину
     * Алгоритм начинается с вершины 0
     * */
    public void bfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        theQueue.add(0); // Вставка в конец очереди
        int v2;

        while (!theQueue.isEmpty()) { // Пока очередь не опустее
            int v1 = theQueue.remove(); // извлекаем вершину из головы очереди

            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) { // Пока остаются непосещенные соседи
                vertexList[v2].wasVisited = true; // получаем и помечаем вершину
                displayVertex(v2);
                theQueue.add(v2); // и добавляем в конец очерди
            }
        }

        // Сброс флагов посещения
        for (int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }

}
