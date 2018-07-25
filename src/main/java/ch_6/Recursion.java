/**
 *
 *                                                      Рекурсия.
 *                                                  Треугольные числа.
 * Это числа последовательности 1, 3(+2), 6(+3), 10(+4), 15(+5), 21(+6), 28(+7), 36(+8), 45(+9), 55(+10) и т д. Такие
 * числа называются треугольными, потому что их можно наглядно представить как число объектов, которые могут быть
 * расставлены в форме треугольника:
 *                                                                  *
 *                                                   *              * *
 *                                      *            * *            * * *
 *                           *          * *          * * *          * * * *
 *                  *        * *        * * *        * * * *        * * * * *
 *           *      * *      * * *      * * * *      * * * * *      * * * * * *
 *      *    * *    * * *    * * * *    * * * * *    * * * * * *    * * * * * * *
 *      1    2        3         4           5             6               7             и так далее.
 * Вычисление n-го треугольного числа в цикле:
 *      Решение представленно в методе triangle(int n)
 *      Решение рекурсией представлено в методе triangleRec(int n) - мое решение
 *      Решение рекурсией представлено в методе triangleRecLafore(int n) - Роберт Лафоре
 *
 * Посмотреть, что реально происходит во время вызова рекурсивного метода на примере triangleRecLafore.
 * Рекурсия, как правило, применяется для концептуального упрощения задачи, а не для повышения производительности (этого
 * не будет, цикл выигрывает).
 *
 * Математическая индукция.
 * Рекурсия является программным аналогом математической индукции - способа определения чего-либо в контексте определяемой
 * сущности.
 *
 * */


package ch_6;

public class Recursion {

    public static void main(String[] args) {

        int tri = triangleRecLafore(5);
        System.out.println(tri);
        /*long currentTimeRec = System.currentTimeMillis();
        System.out.println(triangleRec(7000));
        System.out.println(System.currentTimeMillis() - currentTimeRec);

        System.out.println("======================");

        long currentTimeWhile = System.currentTimeMillis();
        System.out.println(triangle(5000));
        System.out.println(System.currentTimeMillis() - currentTimeWhile);*/

    }

    static int triangle(int n) {
        int total = 0;
        while (n > 0) {
            total += n;
            --n;
        }
        return total;
    }

    static int triangleRec(int n) {
        if (n == 1) {
            return 1;
        }
        return n + triangleRec(n-1);
    }

    static int triangleRecLafore(int n) {
        System.out.println("Entering: n = " + n);
        if (n == 1) {
            System.out.println("Returning 1");
            return 1;
        }
        int temp = n + triangleRecLafore(n - 1);
        System.out.println("Returning " + temp);
        return temp;
    }

}
