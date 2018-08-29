/**
 *
 *                                              Сортировка слиянием.
 * Сортировка слиянием по скорости превосходит сортировку пузырьком, вставками и еще какую-то простую. Работает за
 * O(N*logN). Недостаток ее в том, что она требует выделения доп памяти для доп массива размером, равным размеру
 * сортируемого массива.
 * Основная идея - слияние двух предварительно отсортированных массивов. Предположим, у нас есть два предвариетльно
 * отсортированных массива, причем они не должны быть одинакового размера. Допустим, массив А содержит 4 элемента, а
 * массив Б - 6. Массивы объединяются посредством слияния в массив С, изначально состоящий из 10(4+6) пустых ячеек.
 * Допустим, массив А состоит из {23,47,87,95}, а массив Б из {7,14,39,55,62,74}. Тогда при их слиянии и заполнении
 * результирующего массива С будут происходить следующие сравнения. 1 - 23 и 7, так как 7 меньше, то в С копируется оно.
 * Далее, 23 и 14 - 14 меньши, копируем в С его. Третье сравнение 23 и 39, на этот раз 23 меньше, в С идет оно. И так
 * далее, пока не скопируются все элементы каждого массива.
 * В методе mergeNotRecursive описан вариант НЕ рекурсивной сортировки слиянием.
 * Ниже рассмотрен рекурсивный способ. Он заключаемся в том, чтобы разбивать массив на половины, пока не дойдем до минимально
 * возможного массива из одного элемента, который считается отсортированным по умолчанию. А далее просто объединяем.
 * В методе mergeRecursive показана рекурсивная сортировка слиянием.
 * Эффективность сортировки слиянием. Как уже упоминалось, сортировка слиянием выполняется на O(N*logN). Количество операций
 * сравнения - минимум N/2 (это при одной итерации), максимум N - 1(опять же, при одной итерации).
 *
 * */

package ch_6;

public class MergeSort {

    public static void main(String[] args) {
        int[] arrayA = {23,47,81,95};
        int[] arrayB = {7,14,39,55,62,74};
        int[] arrayC = new int[10];

        mergeNotRecursive(arrayA, 4, arrayB, 6, arrayC);
        display(arrayC, 10);

        /**
         * Рекурсия
         * */
        int maxSize = 100;
        MergeSort arr = new MergeSort(maxSize);
        arr.insert(64); // Вставка элементов
        arr.insert(21);
        arr.insert(33);
        arr.insert(70);
        arr.insert(12);
        arr.insert(85);
        arr.insert(44);
        arr.insert(3);
        arr.insert(99);
        arr.insert(0);
        arr.insert(108);
        arr.insert(36);

        arr.displayRec();
        arr.mergeSort();
        arr.displayRec();

    }

    private static void mergeNotRecursive(int[] arrayA, int sizeA,
                                         int[] arrayB, int sizeB,
                                         int[] arrayC) {
        int aDex = 0, bDex = 0, cDex = 0;
        // По не дошли до конца какого-либо из массивов
        // перебираем элементы каждого массива, сравниваем
        // их, и копируем наименьший в С
        while(aDex < sizeA && bDex < sizeB) {
            if (arrayA[aDex] < arrayB[bDex]) {
                arrayC[cDex++] = arrayA[aDex++];
            } else {
                arrayC[cDex++] = arrayB[bDex++];
            }
        }
        // Если дошли до конца массива В - то копируем оставшиеся элементы массива А
        while (aDex < sizeA) {
            arrayC[cDex++] = arrayA[aDex++];
        }
        // Если дошли до конца массива А - то копируем оставшиеся элементы массива В
        while (bDex < sizeB) {
            arrayC[cDex++] = arrayB[bDex++];
        }
    }

    private static void display(int[] array, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Здесь пошла рекурсия
     * */

    private long[] theArray;
    private int nElems;

    public MergeSort(int max) {
        theArray = new long[max];
        nElems = 0;
    }

    private void insert(long value) {
        theArray[nElems] = value;
        nElems++;
    }

    private void displayRec() {
        System.out.println("========= Recursion =========");
        for (int i = 0; i < nElems; i++) {
            System.out.print(theArray[i] + " ");
        }
        System.out.println();
    }

    public void mergeSort() {
        long[] workSpace = new long[nElems];
        mergeRecursive(workSpace, 0, nElems-1);
    }

    private void mergeRecursive(long[] workSpace, int lowerBound, int upperBound) {
        /**
         * Если только один элемент - то сортировка не требуется
         * */
        if (lowerBound == upperBound) {
            return;
        } else {
            /**
             * Пройтись по брякам (где помечено (bp)),
             * очень интересное поведение. Запомнить.
             * */
            int mid = (lowerBound + upperBound) / 2;                // (bp) поиск середины
            mergeRecursive(workSpace, lowerBound, mid);             // (bp) сортировка левой половины
            mergeRecursive(workSpace, mid+1, upperBound); // (bp) сортировка правой половины
            merge(workSpace, lowerBound, mid+1, upperBound); // (bp) СЛИЯНИЕ
        }
    }

    /**
     * В метод merge мы передаем начальную точку подмассива левой половины, начальная точка подмассива
     * правой половины, а также верхняя граница подмассива правой же половины.
     * */
    private void merge(long[] workspace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1; // Количество элементов

        while (lowPtr <= mid && highPtr <= upperBound) { // Опять же, пока один из подмассивов не кончится
            if (theArray[lowPtr] < theArray[highPtr]) {  // Заполняем итоговый массив, сдвигая соотвествующий индекс
                workspace[j++] = theArray[lowPtr++];     // левого или правого подмассива
            } else {
                workspace[j++] = theArray[highPtr++];
            }
        }

        /**
         * И точно так же, как и в циклическом примере
         * дозаполняем итоговвый массив остатками элементов.
         * */
        while (lowPtr <= mid) {
            workspace[j++] = theArray[lowPtr++];
        }
        while (highPtr <= upperBound) {
            workspace[j++] = theArray[highPtr++];
        }
        for (j = 0; j < n; j++) {
            theArray[lowerBound + j] = workspace[j];
        }
    }


}
