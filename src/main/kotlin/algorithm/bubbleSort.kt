package algorithm

/**
 * 冒泡排序
 * 时间复杂度分析：
 *   外部循环次数永远需要 n-1，内部循环次数从 n-1 到 1，平均为 n/2
 *   最坏时间复杂度：O(n²)
 *   平均时间复杂度：O(n²)
 *   最优时间复杂度：O(n²)
 * 空间复杂度：O(1)
 *
 * Bubble Sort
 * Time Complexity:
 *   Optimal Time Complexity: O(n²)
 *   Average Time Complexity: O(n²)
 *   Worst Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
fun bubbleSort(arr: IntArray) {
    var temp: Int
    for (c in 1 until arr.size) {
        for (i in 0 until arr.size - c) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i]
                arr[i] = arr[i + 1]
                arr[i + 1] = temp
            }
        }
    }
}
