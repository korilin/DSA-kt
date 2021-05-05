package data_structure

/**
 * 红黑树
 */
class RBTree<E> {
    sealed class Color {
        object RED : Color()
        object BLACK : Color()
    }

    inner class Node<E>(
        var key: Int,
        var value: E,
        var right: Node<E>?,
        var left: Node<E>?,
        var father: Node<E>?,
        var color: Color
    )

    private var root: Node<E>? = null

    /**
     * 打印树的结构（包括 NIL 节点）
     */
    fun printTree() {
        fun printNode(node: Node<E>?, level: Int) {
            var nodePrint = ""
            repeat(level) {
                nodePrint += "\t"
            }
            nodePrint += node?.let {
                when (it.color) {
                    Color.BLACK -> "B|"
                    Color.RED -> "R|"
                } + it.key + "|" + it.value
            } ?: "NIL"
            println(nodePrint)
        }

        // 使用中序遍历
        fun dfs(node: Node<E>?, level: Int) {
            if (node != null) dfs(node.left, level + 1)
            printNode(node, level)
            if (node != null) dfs(node.right, level + 1)
        }

        dfs(root, 0)
    }

    /**
     * 添加元素，当对应 key 的节点存在时，将覆盖原有节点的 value
     */
    fun add(key: Int, value: E) {
        val newNode = Node(key, value, null, null, null, Color.RED)
        if (insertNodeIntoTree(newNode)) balanceControl(newNode)
    }

    /**
     * 将节点插入的红黑树中，当对应 key 的节点存在时，使用覆盖原本节点的 value，而不是进行插入操作
     *
     * @return 是否进行了插入操作
     */
    private fun insertNodeIntoTree(node: Node<E>): Boolean {
        var compareNode = root
        var father: Node<E>? = null

        while (compareNode != null) {
            if (compareNode.key == node.key) {
                compareNode.value = node.value
                return false
            }
            father = compareNode
            compareNode = if (node.key > compareNode.key) compareNode.right else compareNode.left
        }

        when {
            father == null -> {
                node.color = Color.BLACK
                root = node
            }
            node.key >= father.key -> father.right = node
            else -> father.left = node
        }

        node.father = father
        return true
    }


    /**
     * 对当前操作的红色节点进行平衡处理
     * 自底向上处理方案
     */
    private fun balanceControl(node: Node<E>) {
        val father = node.father
        if (father == null) {
            node.color = Color.BLACK
            return
        }
        if (father.color == Color.BLACK) return

        val grandfather = father.father!!
        val uncle = if (grandfather.left == father) grandfather.right else grandfather.left

        if (uncle?.color == Color.RED) {
            father.color = Color.BLACK
            uncle.color = Color.BLACK
            grandfather.color = Color.RED
            balanceControl(grandfather)
        } else {
            rotate(node, father, grandfather)
        }
    }

    /**
     * 进行旋转操作的方向选择
     */
    private fun rotate(node: Node<E>, father: Node<E>, grandfather: Node<E>) {
        if (grandfather.left == father) {
            if (father.left == node) {
                father.color = Color.BLACK
                grandfather.color = Color.RED
                rightRotate(grandfather)
            } else {
                leftRotate(father)
            }
        } else {
            if (father.left == node) {
                rightRotate(father)
            } else {
                father.color = Color.BLACK
                grandfather.color = Color.RED
                leftRotate(grandfather)
            }
        }
    }

    /**
     * 获取对应 key 的元素
     */
    fun get(key: Int): E? {
        var compareNode = root
        while (compareNode != null) {
            if (compareNode.key == key) return compareNode.value
            compareNode = if (key < compareNode.key) compareNode.left else compareNode.right
        }
        return null
    }

    /**
     * 移除元素
     */
    fun remove(key: Int): E? {

        // 处理 root 为 2-node 的情况
        root?.let {
            if (is2NodeHelper(it)) {
                val left = it.left
                val right = it.right
                if (left != null && is2NodeHelper(left) && is2NodeHelper(right!!)) {
                    left.color = Color.RED
                    right.color = Color.RED
                }
            }
        } ?: return null

        var current = root

        // 找到要删除的节点
        while (current != null) {
            if (current.key == key) break
            current = if (key < current.key) combine2NodeHelper(current, current.left, current.right)
            else combine2NodeHelper(current, current.right, current.left)
        }

        val node = current ?: return null
        val value = node.value

        // 寻找替换节点
        current = searchReplaceNode(current)

        node.key = current.key
        node.value = current.value
        // 删除替换节点
        deleteNode(current)

        return value
    }

    private fun searchReplaceNode(node: Node<E>): Node<E> {
        var current = node
        if (current.left != null) {
            current = combine2NodeHelper(current, current.left, current.right)!!
            while (current.right != null)
                current = combine2NodeHelper(current, current.right, current.left)!!
        } else {
            current = combine2NodeHelper(current, current.right, current.left)!!
            while (current.left != null)
                current = combine2NodeHelper(current, current.left, current.right)!!
        }
        return current
    }

    private fun deleteNode(node: Node<E>) {
        val father = node.father
        when {
            father == null -> root = null
            father.left == node -> father.left = null
            father.right == node -> father.right = null
        }
        node.father = null
    }

    private fun is2NodeHelper(node: Node<E>): Boolean {
        return node.left?.color != Color.RED && node.right?.color != Color.RED
    }

    /**
     * 自顶向下合并 2-node 的节点删除平衡保持解决方案
     *
     * @param current 当前遍历的节点
     * @param next 下一个查找节点
     * @param border next的兄弟节点
     * @return next
     */
    private fun combine2NodeHelper(current: Node<E>, next: Node<E>?, border: Node<E>?): Node<E>? {
        if (next != null && is2NodeHelper(next)) {
            border!!
            if (is2NodeHelper(border)) {
                // combine siblings
                current.color = Color.BLACK
                border.color = Color.RED
                next.color = Color.RED
            } else {
                // borrow from sibling
                borrowFromSiblingBefore(current, next, border)
            }
        }
        return next
    }

    /**
     * borrow from sibling 预处理
     */
    private fun borrowFromSiblingBefore(current: Node<E>, next: Node<E>, border: Node<E>) {

        fun samePathVerity(it: Node<E>, rotate: (node: Node<E>) -> Unit) {
            if (it.color == Color.RED) it.color =
                Color.BLACK.also { borrowFromSiblingImprove(current, next, border, rotate) }
        }

        fun differentPathInvoke(borderRotate: (node: Node<E>) -> Unit, currentRotate: (node: Node<E>) -> Unit) {
            borderRotate(border).also { borrowFromSiblingImprove(current, next, border, currentRotate) }
        }

        when (border) {
            current.left -> if (border.right?.color == Color.RED)
                differentPathInvoke(::leftRotate, ::rightRotate)
            else border.left?.let {
                samePathVerity(it, ::rightRotate)
            }

            current.right -> if (border.left?.color == Color.RED)
                differentPathInvoke(::rightRotate, ::leftRotate)
            else border.right?.let {
                samePathVerity(it, ::leftRotate)
            }
        }
    }

    /**
     * borrow from sibling improve
     */
    private fun borrowFromSiblingImprove(
        current: Node<E>,
        next: Node<E>,
        border: Node<E>,
        rotate: (node: Node<E>) -> Unit
    ) {
        border.color = current.color
        current.color = Color.BLACK
        next.color = Color.RED
        rotate(current)
    }

    /**
     * 以传入的节点为中心进行左旋操作
     */
    private fun leftRotate(node: Node<E>) {
        val right = node.right!!
        relationChange(node.father, node, right)
        node.right = right.left
        right.left = node
    }

    /**
     * 以传入的节点为中心进行右旋操作
     */
    private fun rightRotate(node: Node<E>) {
        val left = node.left!!
        relationChange(node.father, node, left)
        node.left = left.right
        left.right = node
    }

    /**
     * 旋转操作时，修改父节点的子节点指向
     */
    private fun relationChange(father: Node<E>?, child: Node<E>, newChild: Node<E>) = when {
        father == null -> root = newChild
        father.left == child -> father.left = newChild
        else -> father.right = newChild
    }.also {
        newChild.father = father
        child.father = newChild
    }

}