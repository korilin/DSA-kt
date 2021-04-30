package data_structure

import java.util.*
import kotlin.collections.ArrayList

/**
 * 红黑树
 */
class RBTree {
    sealed class Color {
        object RED : Color()
        object BLACK : Color()
    }

    inner class Node(var `val`: Int, var right: Node?, var left: Node?, var father: Node?, var color: Color)

    inner class NodeWithLevel(val node: Node?, val level: Int)

    private var root: Node? = null

    fun add(e: Int) {
        val newNode = Node(e, null, null, null, Color.RED)
        insertNodeIntoTree(newNode)
        balanceControl(newNode)
    }

    fun get(e: Int) {
        TODO()
    }

    fun remove(e: Int) {
        TODO()
    }

    /**
     * 打印树的结构（包括 NIL 节点）
     */
    fun printTree() {
        val nodeList = LinkedList<NodeWithLevel>()

        // 使用中序遍历
        fun dfs(node: Node?, level: Int) {
            if (node != null) dfs(node.left, level + 1)
            nodeList.add(NodeWithLevel(node, level))
            if (node != null) dfs(node.right, level + 1)
        }

        dfs(root, 0)

        for (nodeWithLevel in nodeList) {
            var nodePrint = ""
            repeat(nodeWithLevel.level) {
                nodePrint += "\t"
            }
            nodePrint += nodeWithLevel.node?.let {
                when (it.color) {
                    Color.BLACK -> "B."
                    Color.RED -> "R."
                } + it.`val`
            } ?: "NIL"
            println(nodePrint)
        }
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
        childChange(node.father, node, right)
        node.father = right
        node.right = right.left
        right.left = node
    }

    /**
     * 以传入的节点为中心进行右旋操作
     */
    private fun rightRotate(node: Node) {
        val left = node.left!!
        childChange(node.father, node, left)
        node.father = left
        node.left = left.right
        left.right = node
    }

    /**
     * 旋转操作时，修改父节点的子节点指向
     */
    private fun childChange(father: Node?, child: Node, newChild: Node) = when {
        father == null -> root = newChild
        father.left == child -> father.left = newChild
        else -> father.right = newChild
    }

}