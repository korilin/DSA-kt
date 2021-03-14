package sort_algorithm

/**
 * 冒泡排序
 * 时间复杂度分析：
 *   平均时间复杂度：Ο(n²)
 *   最坏时间复杂度：Ο(n²)
 * 空间复杂度：Ο(1)
 *
 * Bubble Sort
 * Time Complexity:
 *   Average Time Complexity: Ο(n²)
 *   Worst Time Complexity: Ο(n²)
 * Space Complexity: Ο(1)
 */
fun bubbleSort(array: IntArray) {
    var temp: Int
    for (c in 1 until array.size) {
        for (i in 0 until array.size - c) {
            if (array[i] > array[i + 1]) {
                array[i] = array[i + 1].also { array[i + 1] = array[i] }
            }
        }
    }
}
