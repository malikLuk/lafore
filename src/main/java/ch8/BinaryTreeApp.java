/**
 *
 *                                                  Двоичные деревья.
 * Двоичное дерево сочетает в себе преимущества двух других структур данных: упорядоченного массива и связного списка.
 * Поиск выполняется также быстро как в упорядоченном массиве, а операции вставки и удаления - как в связном списке.
 * Дерево состоит из узлов, соединенных ребрами (рис tree_1). На самом деле, дерево является частным случаем более общей
 * структуры, называемой графом. В программах узлы часто представляют сущности: людей, детали машин, билеты и т д. Ребра,
 * или соединительные линии между узлами, представляют отношения между этими самыми узлами. Упрощенно говоря, программа
 * может легко и быстро перейти от узла к узлу, если между ними имеется соединительная линия. В общем случае - перемещение
 * происходит только в одном направлении: от корневого узла вниз. В Java-программах ребра - это ссылки. В C или C++ - это
 * указатели.
 * В Двоичном Дереве каждый узел имеет не более двух потомков. Формально, они называются Деревьями Двоичными Поиска(рис tree_4).
 * Ниже представлена терминология (рис tree_2).
 * Путь - это последовательность узлов, по которым надо пройти, чтобы соединить два нужных узла.
 * Корень - узел на самом верху дерева, он же - корневой узел. Дерево имеет только один корень. Чтобы совокупность узлов
 *      и ребер могла называться деревом, от корня к любому другому узлу должен вести один (и тольк один!) путь. Структура
 *      нарисунке tree_3 не является деревом, поскольку нарушает это правило.
 * Родитель - любой узел, кроме корневого имеет ровно одно ребро, уходящее вверх к другому узлу. Узел, расположенный выше
 *      него называется родительским узлом(родителем) по отношению к данному узлу.
 * Потомок - любой узел может иметь одно или несколько ребер, соединяющих его с узлами более низкого уровня. Узлы,
 *      находящиеся ниже заданного называются его потомками.
 * Лист - узел, не имеющий потомков.
 * Поддерево - любой узел может рассматриваться как корень поддерева, состоящего из его потомком, потомком его потомков
 *      и т д.
 * Посещение - переход программы к узлу (обычно, с целью выполнения некоторой операции). Простое прохождение мимо посещением
 *      не считается.
 * Обход дерева - посещение всех его узлов в некотором заданном порядке.
 * Уровень узла - количество поколений, отделяющих его от корня, если считать, что корень находится на нулевом уровне.
 * Ключи - одно из полей объекта называется ключевым. Ключ используется при поиске элемента или выполнении с ним других
 *      операций. В данном случае - это нужное нам значение узла-объекта.
 * Грубый пример дерева (правда, не двочиного) - иерархическая файловая структура.
 * Несбалансированные деревья - это дерево, в котором большинство узлов состредоточено с одной стороны корня (tree_5).
 * Несбалансированными могут быть также и отдельные поддеревья. Деревья, в самом распространенном случае, хранятся в
 * несмежных блоках памяти компьютера, а для их связывания в каждый узел включаются ссылки на его потомков. Дерево в
 * памяти также может быть представлено в виде массива - узлы, находящиеся в конкретных позициях, хранятся в соответствующих
 * позициях массива. В памяти компьютера деревья могут быть представлены различными структурами данных. В самом частом
 * случае, узлы хранятся в несмежных блоках памяти, а для их связывания в каждый узел включаются ссылки на его потомков.
 * Дерево в памяти также может быть предствлено в виде массива; узлы, находящиеся в конкретных позициях, хранятся в
 * соотвествующих позициях массива. В следующих примерах кода Java используется модель с соединением узлов посредством
 * ссылок.
 *
 * */

package ch8;

/**
 * Для начала нам понадобится класс для представления объектов узлов.
 * Класс содержит данные, представляющие хранимые объекты (например,
 * описания работников для БД отдела кадров), а также ссылки на каждого
 * из двух потомков текущего узла. Некоторые программисты также включают
 * ссылку на родительский узел. Наличие таких ссылок упрощает одни операции,
 * но усложняет другие, поэтому мы их не используем. Сущетсвуют и другие
 * варианты проектирования класса Node. Вместо размещения данных непосредственно
 * в узле, можно воспользоваться ссылкой на объект, представляющий набор данных
 *
 * class Node {
 *     Person p1;
 *     Node leftChild;
 *     Node rightChild;
 * }
 * class Person {
 *     int iData;
 *     double fData;
 * }
 *
 * */

class Node {

    int iData;                  // Данные, используемые в качестве ключа
    double dData;               // Другие данные
    Node leftChild;             // Левый потомок
    Node rightChild;            // Правый потомоу

    public void displayNode() {

    }

}

/**
 * Также нам понадобится класс для представления всего дерева, Tree. Он содержит только одно поле, переменную Node,
 * в которой хранится корень дерева. Поля для других узлов не нужны, поскольку доступ к ним осуществляется через корневой
 * узел. Класс Tree содержит ряд методов для поиска, вставки и удаления узлов, различных видов обхода и вывода
 * содержимого дерева.
 * */
class Tree {

    private Node root;

    /**
     * Поиск всегда начинается с первого узла root, так как в дереве изначально напрямую доступен только он.
     * Поиск работает за логорифмическое время
     * */
    public Node find(int key) {
        Node current = root;
        while (current.iData != key) {
            if (key < current.iData) {          // если ключ меньше значения в текущем узле
                current = current.leftChild;    // идем налево
            } else {
                current = current.rightChild;   // иначе направо
            }
            if (current == null) {              // если ничего не нашли
                return null;                    // :'(
            }
        }
        return current;
    }

    /**
     * Здесь, в новой переменной parent(родитель current) хранится последний отличный от null узел дерева, посещенный при
     * переборе. Хранение его необходимо, так как для проверки того, что предыдущее значение current не имело подходящего
     * потомка, current присваивается null и если не сохранить узел в parent, то позиция в дереве потеряется.
     * */
    public void insert(int id, double dd) {
        Node newNode = new Node();          // Создание новго узла
        newNode.iData = id;
        newNode.dData = dd;

        if (root == null) {                 // Если нет корня - то вставляем в корень
            root = newNode;
        } else {
            Node current = root;            // Начать с корневого узла
            Node parent;
            while(true) {
                parent = current;
                if (id < current.iData) {
                    current = current.leftChild;
                    if (current == null) {              // Если дерево кончилось - присваиваем паренту новую ноду
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {              // Если дерево кончилось - присваиваем паренту новую ноду
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void delete(int id) {}

}

/**
 * наконец, с созданным дерево нужно выполнять операции. Следующий класс создает дерево и вставляет в него три узла, а
 * затем выполняет поск одного из них.
 * */
public class BinaryTreeApp {

    public static void main(String[] args) {
        Tree theTree = new Tree();
        theTree.insert(50, 1.5);
        theTree.insert(25, 1.7);
        theTree.insert(75, 1.9);

        Node found = theTree.find(25);

        if (found != null) {
            System.out.println("Found the node with key 25");
        } else {
            System.out.println("Not found");
        }
    }

}
