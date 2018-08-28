/**
 *
 *                                              Рекурсивный двоичный поиск.
 * Рекурсивный поиск является примером алгоритма последовательного разделения, где бОльшая задача делится на две меньшие
 * задачи, решаемые по отдельности. Меньшие задачи, в свою очередь, тоже делятся на две подзадачи и так до тех пор, пока
 * процесс не доберется до базового ограничения, которое решается легко и без разделения. Метод последовательно разделения
 * часто применяется в сочетании с рекурсией, хотя, это необязательно. Например, двоичный поиск возможен и с циклом. Как
 * правило, в задачах, решаемых с помощью метода последовательного разделения обычно имеется два рекурсивных вызова, но
 * фактически вызывается лишь один из них, в зависимости от условий(в двоичном поиске - от значения ключа). Но бывает и
 * так, что используются оба вызова. Алгоритм сортировки Слиянием, рассмотренный далее, именно это и делает для каждой из
 * половин разделенного массива.
 *
 * */


package ch_6;

public class RecursiveBinary {

    public static void main(String[] args) {
        recFind(27,0,arr.length-1);
    }

    private static int[] arr = new int[]{1,5,6,8,9,11,15,27,34,48,60,72,77,79,85,93,95,97,100};

    private static int recFind(long searchKey, int lowerBound, int upperBound) {
        int curIn = (upperBound + lowerBound)/2;
        if (arr[curIn] == searchKey) {
            System.out.println("=====================================");
            System.out.println(curIn);
            System.out.println(arr[curIn]);
            return curIn;
        } else if (lowerBound > upperBound) {
            return 0;
        } else {
            if (arr[curIn] > searchKey) {
                System.out.println(lowerBound + ":::" + (curIn-1));
                return recFind(searchKey, lowerBound, curIn - 1);
            } else {
                System.out.println((curIn + 1) + ":::" + upperBound);
                return recFind(searchKey, curIn + 1, upperBound);
            }
        }
    }

}
