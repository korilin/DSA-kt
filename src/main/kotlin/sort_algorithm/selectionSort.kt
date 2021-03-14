package sort_algorithm

/**
 * 选择排序
 * 时间复杂度：
 *   最优时间复杂度：Ο(n²)
 *   平均时间复杂度：Ο(n²)
 *   最坏时间复杂度：Ο(n²)
 * 空间复杂度：Ο(1)
 *
 * Selection Sort
 * Time Complexity:
 *   Optimal Time Complexity: Ο(n²)
 *   Average Time Complexity: Ο(n²)
 *   Worst Time Complexity: Ο(n²)
 * Space Complexity: Ο(1)
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