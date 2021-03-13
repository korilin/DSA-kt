package algorithm

import org.junit.jupiter.api.Test

class Test {

    private val arr = intArrayOf()
    private val arr1 = randomArr(0, 100, 20)
    private val arr2 = randomArr(-10, 10, 10)

    private fun randomArr(start: Int, end: Int, number: Int): IntArray {
        val list = mutableListOf<Int>()
        for (i in 1..number) {
            list.add((start..end).random())
        }
        return list.toIntArray()
    }

    private fun showArr(array: IntArray) {
        for (num in array) print("$num ")
        println("")
    }

    private fun showAllArr() {
        showArr(arr)
        showArr(arr1)
        showArr(arr2)
    }

    @Test
    fun bubbleSortTest() {
        bubbleSort(arr)
        bubbleSort(arr1)
        bubbleSort(arr2)
        showAllArr()
    }

    @Test
    fun selectionSortTest() {
        selectionSort(arr)
        selectionSort(arr1)
        selectionSort(arr2)
        showAllArr()
    }
}