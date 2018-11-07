/**
 *
 *                                                  Удаление узла.
 * Удаление узлов является самой сложной из стандартных операций с деревьями двоичного поиска. Удаление начинается с поиска
 * удаляемого узла - это уже было продемонстрировано в методах find() и insert(). Когда узел найден - необходимо рассмотреть
 * три возможных случая:
 *   1. Удаляемый узел является листом(не имеет потомков)
 *   2. Удаляемый узел имеет одного потомка.
 *   3. Удаляемый узел имеет двух потомков.
 * Первый случай совсем просто, второй - чуть сложнее, третий - сложный.
 * Чтобы удалить листовой узел, достаточно изменить его поле в родительском узле, записав туда null вместо ссылки на узел.
 * Рисунок tree_12. Благодаря сборщику мусора - нам не надо беспокоиться об уничтожении самого узла. Метод delete()
 * в классе Tree реализовывает удаление во всех трех случаях.
 * Второй случай тоже не имеет особых сложностей. Узел имеет только две связи: с родитем и со своим единственным потомком.
 * Требуеся вырезать сам узел из этой цепочки и соединить родителя узла с потомком этого узла напрямую. То есть изменить
 * соответсвующую ссылку (leftChild или rightChild) родителя так, чтобы она указывала потомка удаляемого узла. Смотреть
 * рисунок tree_13. Стоит обратить внимание на то, как ссылки упрощают удаление целых поддеревьев. Для этого достаточно
 * отсоединить старую ссылку от поддерева и создать новую ссылку на него в другом месте. Нет необходимости удалять узлы
 * по отдельности.
 * Третий случай - узел имеет двух потомков. В этом случае, нельзя просто заменить его одним из этих потомков. Смотреть
 * рисунок tree_14. Существует полезный прием, помогающий разобраться с этим бардаком, но даже при его использовании
 * нужно многое учитывать, так как мы работаем с деревом двоичного поиска, в котором узлы располагаются в порядке
 * возрастания ключей. Для каждого узла, узел со следующим по величине ключом называется его преемником. Так на рисунке
 * tree_15 узел 30 является преемником узла 25. Прием состоит в том, чтобы заменить удаляемый узел преемником. Можно
 * обратить внимание, что порядок узлов при замене не нарушился. Таким образом, нам сначала надо найти преемника. Алгоритм
 * такой: сначала программа  переходит к правому потомку исходного узла, ключ которого должен быть больше клуча узла.
 * Затем она переходит к левому потомку правого потомка(если он существует), далее к левому потомку левого потомка и т д.
 * следуя вниз по цепочке левых потомков. Последний левый потомок на этом пути и будет преемником (рисунок tree_16).
 * Другими словами - мы ищем наименьший узлез из набора узлов, больших, чем удаляемый. В поддереве правого потомка все
 * узлы больше исходного, так как это дерево двоичного поиска. И в этом поддереве ищется наименьшее значение, то есть,
 * самый левый узел. Если у правого потомка удаляемого узла нет левых потомков - то сам правый потомок и является
 * преемником (рисунок tree_17)/
 *
 *
 * */

package ch_8;

public class DeleteNode {
}
