import org.junit.jupiter.api.Test
import sort_algorithm.bubbleSort
import sort_algorithm.directInsertionSort
import sort_algorithm.selectionSort

class SortTest {

    private val arr0 = intArrayOf()
    private val arr1 = randomArr(0, 100, 20)
    private val arr2 = randomArr(-10, 10, 10)

    private fun randomArr(start: Int, end: Int, number: Int): IntArray {
        val list = mutableListOf<Int>()
        for (i in 1..number) {
            list.add((start..end).random())
        }
        return list.toIntArray()
    }

    private fun showArr(name:String, array: IntArray) {
        print("$name: ")
        for (num in array) print("$num ")
        println("")
    }

    private fun showAllArr() {
        showArr("arr0", arr0)
        showArr("arr1", arr1)
        showArr("arr2", arr2)
    }

    private fun sortTestContrast(name:String, run:(IntArray)->Unit){
        println("————— $name ———")
        showAllArr()
        println("———— to ————")
        run(arr0)
        run(arr1)
        run(arr2)
        showAllArr()
    }

    @Test
    fun bubbleSortTest() {
        sortTestContrast("冒泡排序"){ array:IntArray -> bubbleSort(array) }
    }

    @Test
    fun selectionSortTest() {
        sortTestContrast("选择排序"){ array:IntArray -> selectionSort(array) }
    }

    @Test
    fun directInsertionSortTest(){
        sortTestContrast("直接插入排序"){ array:IntArray -> directInsertionSort(array) }
    }

}