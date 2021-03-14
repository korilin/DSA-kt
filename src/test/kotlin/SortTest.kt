import org.junit.jupiter.api.Test
import sort_algorithm.*

class SortTest {

    private val arr0 = intArrayOf()
    private val arr1 = randomGenerateArr(0, 10, 10)
    private val arr2 = randomGenerateArr(-10, 10, 10)
    private val arr3 = randomGenerateArr(-500, 500, 1000)
    private val vast = randomGenerateArr(-100000, 100000, 100000)
    private val allArrMap = mapOf(
        "noValueArray" to arr0,
        "arr1" to arr1,
        "arr2" to arr2,
        "biggerArr" to arr3,
    )

    private fun randomGenerateArr(start: Int, end: Int, number: Int): IntArray {
        val list = mutableListOf<Int>()
        for (i in 1..number) {
            list.add((start..end).random())
        }
        return list.toIntArray()
    }

    private fun showArr(name: String, array: IntArray) {
        print("$name: ")
        for (num in array) print("$num ")
        println("")
    }

    private fun showAllArr() {
        for ((key, arr) in allArrMap) showArr(key, arr)
    }

    private fun sortTestContrast(name: String, runVast: Boolean = false, run: (IntArray) -> Unit) {
        println("————— $name ———")
        showAllArr()
        println("———— to ————")
        var startNanoTime: Long = System.nanoTime()
        for (arr in allArrMap.values) run(arr)
        var endNanoTime: Long = System.nanoTime()
        showAllArr()
        var time = endNanoTime - startNanoTime
        println("Elapsed Time: $time 纳秒，位数: ${time.toString().length}")
        if (runVast) {
            startNanoTime = System.nanoTime()
            run(vast)
            endNanoTime = System.nanoTime()
            time = endNanoTime - startNanoTime
            println("巨大数组排序时间：$time 纳秒，位数: ${time.toString().length}")
        }
    }

    @Test
    fun bubbleSortTest() {
        sortTestContrast("冒泡排序") { array: IntArray -> bubbleSort(array) }
    }

    @Test
    fun selectionSortTest() {
        sortTestContrast("选择排序") { array: IntArray -> selectionSort(array) }
    }

    @Test
    fun directInsertionSortTest() {
        sortTestContrast("直接插入排序") { array: IntArray -> directInsertionSort(array) }
    }

    @Test
    fun quickSortTest() {
        sortTestContrast("快速排序") { array: IntArray -> quickSort(array) }
    }

}