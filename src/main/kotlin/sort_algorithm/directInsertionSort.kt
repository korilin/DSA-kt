package sort_algorithm

/**
 * 直接插入排序
 * 时间复杂度：
 *   最优时间复杂度：O(n)
 *   平均时间复杂度：O(n²)
 *   最坏时间复杂度：O(n²)
 * 空间复杂度：O(1)
 *
 * Direct Insertion Sort
 * Time Complexity:
 *   Optimal Time Complexity: O(n)
 *   Average Time Complexity: O(n²)
 *   Worst Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
fun directInsertionSort(array: IntArray) {
    var temp: Int
    var now: Int
    for (index in 1 until array.size) {
        now = index
        while (now != 0 && array[now] < array[now - 1]) {
            array[now] = array[now - 1].also { array[now - 1] = array[now] }
            now--
        }
    }
}
