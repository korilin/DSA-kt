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

    fun add(key: Int, value: E) {
        val newNode = Node(key, value, null, null, null, Color.RED)
        if (insertNodeIntoTree(newNode))  balanceControl(newNode)
    }

    fun get(key: Int): E? {
        var compareNode = root
        while (compareNode != null) {
            if (compareNode.key == key) return compareNode.value
            compareNode = if (key < compareNode.key) compareNode.left else compareNode.right
        }
        return null
    }

    fun remove(e: Int) {
        TODO()
    }

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
     * 进行旋转操作的选择
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
     * 以传入的节点为中心进行左旋操作
     */
    private fun leftRotate(node: Node<E>) {
        val right = node.right!!
        childChange(node.father, node, right)
        node.right = right.left
        right.left = node
    }

    /**
     * 以传入的节点为中心进行右旋操作
     */
    private fun rightRotate(node: Node<E>) {
        val left = node.left!!
        childChange(node.father, node, left)
        node.left = left.right
        left.right = node
    }

    /**
     * 旋转操作时，修改父节点的子节点指向
     */
    private fun childChange(father: Node<E>?, child: Node<E>, newChild: Node<E>) = when {
        father == null -> root = newChild
        father.left == child -> father.left = newChild
        else -> father.right = newChild
    }.also {
        newChild.father = father
        child.father = newChild
    }

}