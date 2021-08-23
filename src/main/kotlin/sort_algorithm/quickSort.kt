package sort_algorithm

/**
 * 快速排序
 * 时间复杂度：
 *   最优时间复杂度：Ο(n*log(n))
 *   平均时间复杂度：Ο(n*log(n))
 *   最坏时间复杂度：Ο(n²)
 * 空间复杂度：Ο(1)
 *
 * Quick Sort
 * Time Complexity:
 *   Optimal Time Complexity: Ο(n*log(n))
 *   Average Time Complexity: Ο(n*log(n))
 *   Worst Time Complexity: Ο(n²)
 * Space Complexity: Ο(1)
 */
fun quickSort(array: IntArray) {

    fun partition(left: Int, right: Int): Int {
        // 以最左边的值作为交换值
        var pivotPreIndex = left
        for (index in left..right) {
            if (array[index] <= array[right]) {
                array[pivotPreIndex] = array[index].also { array[index] = array[pivotPreIndex] }
                pivotPreIndex += 1
            }
        }
        return pivotPreIndex - 1
    }

    fun inner(left: Int, right: Int) {
        if (left < right) {
            val p = partition(left, right)
            inner(left, p - 1)
            inner(p + 1, right)
        }
    }

    inner(0, array.size - 1)
}