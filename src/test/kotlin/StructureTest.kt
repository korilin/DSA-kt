import data_structure.RBTree
import org.junit.jupiter.api.Test

class StructureTest {

    @Test
    fun rbTreeTest(){
        val rbTree = RBTree<Int>()
        rbTree.add(1, 50)
        rbTree.add(2, 50)
        rbTree.add(3, 50)
        rbTree.add(4, 50)
        rbTree.add(5, 50)
        rbTree.add(6, 50)
        rbTree.add(7, 50)
        rbTree.add(8, 50)
        rbTree.add(9, 50)
        rbTree.add(10, 50)
        rbTree.add(11, 50)
//        rbTree.add(12, 50)
//        rbTree.add(13, 50)
//        rbTree.add(14, 50)
//        rbTree.add(15, 50)
//        rbTree.add(16, 50)
        rbTree.printTree()
    }
}