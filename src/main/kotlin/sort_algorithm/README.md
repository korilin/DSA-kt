# Kotlin 实现常见排序算法

所有算法的代码例子均为ASC（降序排序），代码例子的实现使用较为简单的版本

## 冒泡排序

时间复杂度：
- 平均时间复杂度：Ο(n²)
- 最坏时间复杂度：Ο(n²)

空间复杂度：O(1)

Wiki: https://zh.wikipedia.org/wiki/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F

```Kotlin
fun bubbleSort(array: IntArray) {
    var temp: Int
    for (c in 1 until array.size) {
        for (i in 0 until array.size - c) {
            if (array[i] > array[i + 1]) {
                 array[i] = array[i + 1].also { array[i + 1] = array[i] }
            }
        }
    }
}
```

## 选择排序

时间复杂度：
- 最优时间复杂度：Ο(n²)
- 平均时间复杂度：Ο(n²)
- 最坏时间复杂度：Ο(n²)

空间复杂度：O(1)

Wiki: https://zh.wikipedia.org/wiki/%E9%80%89%E6%8B%A9%E6%8E%92%E5%BA%8F

```Kotlin
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
```

## 直接插入排序

时间复杂度：
- 最优时间复杂度：Ο(n)
- 平均时间复杂度：Ο(n²)
- 最坏时间复杂度：Ο(n²)

空间复杂度：Ο(1)

Wiki: https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F

```Kotlin
fun directInsertionSort(array: IntArray) {
    var now: Int
    for (index in 1 until array.size) {
        now = index
        while (now != 0 && array[now] < array[now - 1]) {
            array[now] = array[now - 1].also { array[now - 1] = array[now] }
            now--
        }
    }
}
```

## 快速排序

时间复杂度：
- 最优时间复杂度：Ο(n*log(n))
- 平均时间复杂度：Ο(n*log(n))
- 最坏时间复杂度：Ο(n²)

空间复杂度：Ο(1)

Wiki: https://zh.wikipedia.org/zh-cn/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F

```Kotlin
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
```

## 希尔排序

时间复杂度：
- 最优时间复杂度：
- 平均时间复杂度：
- 最坏时间复杂度：

空间复杂度：Ο(1)

Wiki: https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F

```Kotlin
fun shellSort(array: IntArray){
    var gap = array.size/2
    var now:Int
    while (gap >= 1){
        for (index in gap until array.size){
            now = index
            while (now >= gap && array[now] < array[now-gap]){
                array[now] = array[now-gap].also { array[now-gap] = array[now] }
                now -= gap
            }
        }
        gap /= 2
    }
}
```
