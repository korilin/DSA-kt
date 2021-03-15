package sort_algorithm

/**
 * 希尔排序（递减增量排序）
 * 时间复杂度：
 *   最优时间复杂度：Ο(n)
 *   最坏时间复杂度：最好的情况为 Ο(n*log²(n))
 * 空间复杂度：Ο(1)
 *
 * Shell Sort
 * Time Complexity:
 *   Optimal Time Complexity: Ο(n)
 *   Worst Time Complexity: the best case is Ο(n*log²(n))
 * Space Complexity: Ο(1)
 */
fun shellSort(array: IntArray){
    var gap = array.size/2
    var now:Int
    while (gap >= 1){
        for (index in gap until array.size){
            now = index
            while (now >= gap && array[now] < array[now-gap]){
                array[now] = array[now-gap].also { array[now-gap] = array[now] }
                now -= gap
            }
        }
        gap /= 2
    }
}