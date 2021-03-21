package sort_algorithm

/**
 * 归并排序
 * 时间复杂度：
 *   最优时间复杂度：
 *   平均时间复杂度：
 *   最坏时间复杂度：
 * 空间复杂度：
 *
 * Merge Sort
 * Time Complexity:
 *   Optimal Time Complexity:
 *   Average Time Complexity:
 *   Worst Time Complexity:
 * Space Complexity:
 */
fun mergeSort(array: IntArray) {

    fun merge(left: Int, right: Int) {
        if (right - left + 1 < 2) {
            return
        }
        val middle = (left + right) / 2
        merge(left, middle)
        merge(middle + 1, right)
        var leftIndex = left
        var rightIndex = middle + 1
        var mergeIndex = 0
        val mergeArray = arrayOfNulls<Int>(right - left + 1)
        while (leftIndex <= middle || rightIndex <= right) {
            if (leftIndex <= middle && (rightIndex > right || array[leftIndex] <= array[rightIndex])) {
                mergeArray[mergeIndex] = array[leftIndex]
                leftIndex += 1
            } else {
                mergeArray[mergeIndex] = array[rightIndex]
                rightIndex += 1
            }
            mergeIndex += 1
        }
        for ((index, value) in mergeArray.withIndex()) {
            array[left + index] = value!!
        }
    }
    merge(0, array.size - 1)
}