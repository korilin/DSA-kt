package algorithm

/**
 * 选择排序
 * 时间复杂度：
 *   最坏时间复杂度：O(n²)
 *   平均时间复杂度：O(n²)
 *   最优时间复杂度：O(n²)
 * 空间复杂度：O(1)
 *
 * Selection Sort
 * Time Complexity:
 *   Optimal Time Complexity: O(n²)
 *   Average Time Complexity: O(n²)
 *   Worst Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
fun selectionSort(arr: IntArray) {
    var minimumValue: Int
    var minimumIndex: Int
    for (index in arr.indices) {
        minimumValue = arr[index]
        minimumIndex = index
        for (scanIndex in index + 1 until arr.size) {
            if (arr[scanIndex] < minimumValue) {
                minimumValue = arr[scanIndex]
                minimumIndex = scanIndex
            }
        }
        if (index != minimumIndex) {
            arr[minimumIndex] = arr[index]
            arr[index] = minimumValue
        }
    }
}