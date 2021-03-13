package sort_algorithm

/**
 * 选择排序
 * 时间复杂度：
 *   平均时间复杂度：O(n²)
 *   最坏时间复杂度：O(n²)
 * 空间复杂度：O(1)
 *
 * Selection Sort
 * Time Complexity:
 *   Average Time Complexity: O(n²)
 *   Worst Time Complexity: O(n²)
 * Space Complexity: O(1)
 */
fun selectionSort(array: IntArray) {
    var minimumValue: Int
    var minimumIndex: Int
    for (index in array.indices) {
        minimumValue = array[index]
        minimumIndex = index
        for (scanIndex in index + 1 until array.size) {
            if (array[scanIndex] < minimumValue) {
                minimumValue = array[scanIndex]
                minimumIndex = scanIndex
            }
        }
        if (index != minimumIndex) {
            array[minimumIndex] = array[index]
            array[index] = minimumValue
        }
    }
}