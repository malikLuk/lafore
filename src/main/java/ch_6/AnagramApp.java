/**
 *
 *                                                   Использование рекурсии для
 *                                                      составления анаграмм.
 * Рекурсия предоставляет элегантное решение этой задачи. Анаграмма слова - это все возможные в слове перестановки букв.
 * Например, для слова cat список анаграмм будет таким: cat, cta, atc, act, tca, tac. Из примера ясно, что количество
 * вариантов равно факториалу количества букв (т.е для 3-х буквенного слова - 6, для 5-буквенного - 120) предполагая, что
 * буквы в слове не повторяются. При повторяющихся буквах количесвто возможных слов будет меньше. В методе doAnagram
 * при каждом вызове происходит уменьшение слова на одну букву, а начальная позиция сдвигается на одну ячейку вправо.
 * Циклический сдвуиг - это когда первая буква становится последней а остальне пермещаются на одну позицию влево.
 * У слова cat будут такие стадии циклического сдвига: cat --> atc --> tca.
 *
 * */


package ch_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnagramApp {

    static int size;
    static int count;
    static char[] arrChar = new char[100];

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Enter the word: ");         // Получение слова
            String input = getString();
            size = input.length();                          // Определение размера
            count = 0;
            for (int i = 0; i < size; i++) {
                arrChar[i] = input.charAt(i);               // Сохранение в массиве
            }
            doAnagram(size);                                // И строим анаграммы
        }
    }

    public static void doAnagram(int newSize) {
        if (newSize == 1) {                     // Если слово слишком маленькое
            return;                             // не продолжать
        }
        for (int i = 0; i < newSize; i++) {     // Для каждой позиции
            doAnagram(newSize - 1);      // построить анаграммы остальных букв
            if (newSize == 2) {                 // Если внутреннее состояние
                displayWord();                  // вывести слово
            }
            rotate(newSize);                    // Циклический сдвиг всего слова
        }
    }

    public static void rotate(int newSize) {
        int j;
        int position = size - newSize;
        char temp = arrChar[position];           // Сохранение первой буквы
        for (j = position + 1; j < size; j++) {  // Сдвиг остальных букв влево
            arrChar[j-1] = arrChar[j];
        }
        arrChar[j-1] = temp;                     // Перемещение первой буквы на правый край
    }


    static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    static void displayWord() {
        if (count < 99) {
            System.out.print(" ");
        }
        if (count < 9) {
            System.out.print(" ");
        }
        System.out.print(++count + " ");
        displayArr("");
        System.out.print("     ");
        System.out.flush();
        if (count % 6 == 0) {
            System.out.println();
        }
    }

    static void displayArr(String s) {
        for (int i = 0; i < size; i++) {
            System.out.print(arrChar[i]);
        }
        System.out.print(s);
    }

}
