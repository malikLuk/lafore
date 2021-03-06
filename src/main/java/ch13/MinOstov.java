/**
 *
 *                                              Минимальные остовные деревья.
 * Допустим, мы проектируем печатную плату (вроде той, что на рисунке gr_7.png) и хотим свести количество дорожек к
 * минимуму. Иначе говоря, между контактами не должно быть лишних соединений, которые только занимают место и усложняют
 * проводку других цепей. Для решения этой задачи понадобится алгоритм, который бы для любого связного набора вершин и
 * ребер (в нашем случае, контактов и дорожек) удалял все лишние ребра. Результатом его работы является граф с минимальным
 * количеством ребер, необходимых для соединения вершин. Например, граф на gr_12.png (a) состоит из пяти вершин с избыточными
 * ребрами, а под (б) изображены те же пять вершин с минимальным количеством ребер, необходимым для их соединения. Эти
 * ребра образуют Минимальное Остовное Дерево (Minimum Spanning Tree).
 * Для заданного набора вершин существует много возможных минимальных остовных деревьев. На gr_12 (б) обозначены ребра
 * AB, DC, CD и DE, но с таким же успехом можно было составить минимальное остовное дерево из ребер AC, CE, ED и DB. Можно
 * заметить, что количество ребер Е в минимальном остовном дереве всегда на единицу меньше количества врешин V, то есть
 * E = V -1. Так как речь идет о невзвешенных графах, то нас не инетерсует длина ребер, мы хотим определить минимальное
 * их количество. Алгоритм построения минимального остовного дерева почти идентичен обходу. Он может базироваться как
 * на обходе в глубину, так и на обходе в ширину. В нашем примере будет использовтаься обход в глубину. Может показаться
 * неожиданным, но обход в глубину с сохранением ребер, посещенных при поиске, приводит к автоматическому построению
 * минимального остовного дерева. Единственное отличие метода mst() от метода dfs() состоит в том, что метод mst() должен
 * каким то образом сохранять посещенные ребра. Метод mst() в классе DeepSearchRealization.
 *
 * */

package ch13;

public class MinOstov {
}
