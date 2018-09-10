/**
 *
 *                                          Комбинации и выбор команды.
 * В математике комбинацией называется совокупность элементов, которые могут следовать в произвольном порядке. Допустим,
 * имеется группа из пяти альпинистов A, B, C, D, E. Из этой группы надо набрать команду из трех человек. Нужно сгенерировтаь
 * все возможные комбинации из трех альпинистов. В нашем случае возможны десять комбинаций: ABC, ABD, ABE, ACD, ACE, ADE,
 * BCD, BCE, BDE, CDE.
 * У этой задачи имеется элегантное рекурсивное решение. Все комбинации делятся на две группы: начинающиеся с А и начинающиеся
 * с других букв. Для удобства - концепция выделения 3 участников в группу из 5 возможных (5,3) записывается таким образом:
 * (n,k) = (n-1, k-1)+(n-1, k) - это теорема из комбинаторики. Получим (5,3) = (4,2) + (4,3). Таким образом, мы разбили
 * одну большую задачу на дву меньших. Аналогичный процесс декомпозиции может быть применен для каждого из получившихся
 * слагаемых. Полное разбиение смотреть combination.png. Базовым ограничением здесь будут команды, не имеющие смысла, то
 * есть те, у которых один из компонентов 0 или те, у которых размер команды(второе число) больше размера группы(первое число).
 * На рисунке базоывае ограничение отмечены пунктиром. Комбинация (1,1) допустима, но дальнейшее деление не имеет смысла.
 * Смотреть метод CombinationUtil
 *
 * Разобрать https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 *
 * */

package ch_6;

public class Combinations {

    public static void main(String[] args) {
        String input = "ABCDE";
        int r = 3;
        int n = input.length();
        printCombination(input, n, r);
    }

    static void combinationUtil(String input, String[] data, int start,
                                int end, int index, int r)
    {
        if (index == r)
        {
            for (int j=0; j<r; j++)
                System.out.print(data[j]+" ");
            System.out.println("");
            return;
        }

        for (int i=start; i<=end && end-i+1 >= r-index; i++)
        {
            data[index] = String.valueOf(input.charAt(i));
            combinationUtil(input, data, i+1, end, index+1, r);
        }
    }

    static void printCombination(String input, int n, int r)
    {
        String data[]=new String[r];
        combinationUtil(input, data, 0, n-1, 0, r);
    }

}
