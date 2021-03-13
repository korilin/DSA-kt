# DSA-kt

## Kotlin 实现常见排序算法

所有算法的代码例子均为ASC（降序排序），且均不讨论最优情况的时间复杂度，需要看详情可参考对应 Wiki

### 冒泡排序

时间复杂度：
- 平均时间复杂度：O(n²)
- 最坏时间复杂度：O(n²)

空间复杂度：O(1)

Wiki: https://zh.wikipedia.org/wiki/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F

```Kotlin
fun bubbleSort(array: IntArray) {
    var temp: Int
    for (c in 1 until array.size) {
        for (i in 0 until array.size - c) {
            if (array[i] > array[i + 1]) {
                temp = array[i]
                array[i] = array[i + 1]
                array[i + 1] = temp
            }
        }
    }
}
```

### 选择排序

时间复杂度：
- 平均时间复杂度：O(n²)
- 最坏时间复杂度：O(n²)

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

### 直接插入排序

- 平均时间复杂度：O(n²)
- 最坏时间复杂度：O(n²)

空间复杂度：O(1)

Wiki: https://zh.wikipedia.org/wiki/%E6%8F%92%E5%85%A5%E6%8E%92%E5%BA%8F

```Kotlin
fun directInsertionSort(array: IntArray) {
    var temp: Int
    for (index in 1 until array.size) {
        var now = index
        while (now != 0 && array[now] < array[now - 1]) {
            temp = array[now]
            array[now] = array[now - 1]
            array[now - 1] = temp
            now--
        }
    }
}
```
