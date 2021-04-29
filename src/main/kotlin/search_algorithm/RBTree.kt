package search_algorithm

/**
 * 红黑树
 */
class RBTree {
    sealed class Color {
        object RED : Color()
        object BLACK : Color()
    }

    inner class Node(var `val`: Int, var right: Node?, var left: Node?, var father: Node?, var color: Color)

    private var root: Node? = null

    fun add(e: Int) {
        val newNode = Node(e, null, null, null, Color.RED)
        insertNodeIntoTree(newNode)
        balanceControl(newNode)
    }

    fun get(e: Int) {
        TODO()
    }

    fun remove(e: Int){
        TODO()
    }

    /**
     * 将节点插入的红黑树中
     */
    private fun insertNodeIntoTree(node: Node) {
        var compareNode = root
        var father: Node? = null

        while (compareNode != null) {
            father = compareNode
            compareNode = if (node.`val` >= compareNode.`val`) compareNode.right else compareNode.left
        }

        when {
            father == null -> {
                node.color = Color.BLACK
                root = node
            }
            node.`val` >= father.`val` -> father.right = node
            else -> father.left = node
        }

        node.father = father
    }


    /**
     * 对当前操作的红色节点进行平衡处理
     */
    private fun balanceControl(node: Node) {
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
    private fun rotate(node: Node, father: Node, grandfather: Node) {
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
    private fun leftRotate(node: Node) {
        val right = node.right!!
        childChange(node.father, right)
        node.father = right
        node.right = right.right
        right.left = node
    }

    /**
     * 以传入的节点为中心进行右旋操作
     */
    private fun rightRotate(node: Node) {
        val left = node.left!!
        childChange(node.father, left)
        node.father = left
        node.left = left.left
        left.right = node
    }

    /**
     * 旋转操作时，修改父节点的子节点指向
     */
    private fun childChange(father: Node?, child: Node) = when {
        father == null -> root = child
        father.left == child -> father.left = child
        else -> father.right = child
    }

}