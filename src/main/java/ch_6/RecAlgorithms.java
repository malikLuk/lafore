/**
 *
 *                                                  Рекурсия и стеки.
 * Одни алгоритмы хорошо адаптируются к рекурсии, другие - нет. Как мы уже видели, вычисление факториала все таки более
 * эффективно с применением цикла. С другой стороны, алгоритмы последовательного разделения (например, сортировка слиянием)
 * отлично работают в рекурсивной форме. Заменой рекурсии может быть стек. Между рекурсией и стеками существует очень тесная
 * связь. Компиляторы чаще всего применяют стеки, для реализации рекурсии. Как уже говорилось ранее, при вызове метода,
 * компилятор заносит аргументы метода и адрес возврата (по которому должно быть передано управление при выходе из метода)
 * в стек, после чего передает управление вызванному методу. При выходе из метода значения извлекаются из стека. Аргументы
 * игнорируются, а управление передается по адресу возврата. Таким образом, любое рекурсивное решение преобразуется в
 * решение на базе стека. Рассмотрим рекурсивный метод triangle(int n), который считает треугольные числа. Мы разобьем
 * этот метода на отдельные операции, каждая из которых станет отдельной секцией case в конструкции switch. В C++ в этом
 * не было бы необходимости, так как там есть goto. В текущем методе triangle, помимо очевидных операций сложения, сравнения
 * с 1 и рекурсивного вызова, происходит еще кое-что. Метод triangle также выполняет операции, необходимые для управления
 * самим методом, включая передачу управления, обращение к аргументом и адресу возврата. Эти операции не видны в программном
 * коде, но они встраиваются во все методы. В общих чертах, при вызове метода происходит следующее:
 *  - Аргументы и адрес возврата заносятся в стек.
 *  - Метод обращается к своим аргументам, читая значения с вершины стека.
 *  - Непосредственно перед возвратом управления метод читает из стека адрес возврата, после чего извлекает этот адрес и
 *      аргументы из стека и уничтожает их.
 * Ниже представлена программа, имитирующая рекурсию через стек. Я все рассмотрел и понял, но не факт, что сходу врублюсь
 * через месяц, поэтому не лениться и скомпилировтаь в голове весь код и пройтись по шагам для цифры 4. Если что, страницы,
 * начиная с 285. Также, на 287 странице описано, как в нашем случае избавиться от switch-case. Там по сути стек не нужен,
 * но в более сложных алгоритмах без него не обойтись.
 *
 * */

package ch_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RecAlgorithms {

    int triangle(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n + triangle(n-1);
        }
    }

    static int theNumber;
    static int theAnswer;
    static StackX theStack;
    static int codePart;
    static Params theseParams;

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the number: ");
        theNumber = getInt();
        rectriangle();
        System.out.println("Triangle = " + theAnswer);
    }

    public static void rectriangle() {
        theStack = new StackX(10000);
        codePart = 1;
        while( step() == false) { // Вызывать, пока step() не вернет true
            ; // Пустое тело цикла
        }
    }

    public static boolean step()
    {
        switch(codePart)
        {
            case 1: // Исходный вызов
                theseParams = new Params(theNumber, 6);
                theStack.push(theseParams);
                codePart = 2;
                break;
            case 2: // Вход в метод
                theseParams = theStack.peek();
                if(theseParams.n == 1) // Проверка
                {
                    theAnswer = 1;
                    codePart = 5; // Выход
                }
                else
                    codePart = 3; // Рекурсивный вызов
                break;
            case 3: // Вызов метода
                Params newParams = new Params(theseParams.n - 1, 4);
                theStack.push(newParams);
                codePart = 2; // Вход в метод
                break;
            case 4: // Вычисление
                theseParams = theStack.peek();
                theAnswer = theAnswer + theseParams.n;
                codePart = 5;
                break;
            case 5: // Выход из метода
                theseParams = theStack.peek();
                codePart = theseParams.returnAddress; // (4 или 6)
                theStack.pop();
                break;
            case 6: // Точка возврата
                return true;
        }
        return false;
    }

    static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

}

class Params { // Параметры, сохраняемые в стеке
    public int n;
    public int returnAddress;

    public Params(int n, int returnAddress) {
        this.n = n;
        this.returnAddress = returnAddress;
    }
}

class StackX {

    private int maxSize;
    private Params[] stackArray;
    private int top;    // вершина стека

    public StackX(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = new Params[maxSize];
        this.top = -1;
    }

    public void push(Params p) {
        stackArray[++top] = p;
    }

    public Params pop() {
        return stackArray[top--];
    }

    public Params peek() {
        return stackArray[top];
    }

}