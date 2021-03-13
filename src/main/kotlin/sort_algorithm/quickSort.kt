package sort_algorithm

fun quickSort(array: IntArray) {

    fun inner(left: Int, right: Int) {

        fun partition(left: Int, right: Int): Int {
            // 以最左边的值作为基准值
            var pivotPreIndex = left
            for (index in left..right) {
                if (array[index] <= array[right]) {
                    array[pivotPreIndex] = array[index].also { array[index] = array[pivotPreIndex] }
                    pivotPreIndex += 1
                }
            }
            return pivotPreIndex - 1
        }

        if (left < right) {
            val p = partition(left, right)
            inner(left, p - 1)
            inner(p + 1, right)
        }
    }

    inner(0, array.size - 1)
}