# Kotlin 实现常见排序算法

所有算法的代码例子均为ASC（降序排序）

## 冒泡排序

时间复杂度：
- 最坏时间复杂度：O(n²)
- 平均时间复杂度：O(n²)
- 最优时间复杂度：O(n²)

空间复杂度：O(1)

```Kotlin
fun bubbleSort(arr: IntArray) {
    var temp: Int
    for (c in 1 until arr.size) {
        for (i in 0 until arr.size - c) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i]
                arr[i] = arr[i + 1]
                arr[i + 1] = temp
            }
        }
    }
}
```

## 选择排序

时间复杂度：
- 最坏时间复杂度：O(n²)
- 平均时间复杂度：O(n²)
- 最优时间复杂度：O(n²)
- 
空间复杂度：O(1)

```
fun selectionSort(arr: IntArray) {
    var minimumValue: Int
    var minimumIndex: Int
    for (index in arr.indices) {
        minimumValue = arr[index]
        minimumIndex = index
        for (scanIndex in index + 1 until arr.size) {
            if (arr[scanIndex] < minimumValue) {
                minimumValue = arr[scanIndex]
                minimumIndex = scanIndex
            }
        }
        if (index != minimumIndex) {
            arr[minimumIndex] = arr[index]
            arr[index] = minimumValue
        }
    }
}
```