import data_structure.RBTree
import org.junit.jupiter.api.Test

class StructureTest {

    @Test
    fun rbTreeTest(){
        val rbTree = RBTree()
        rbTree.add(5)
        rbTree.add(6)
        rbTree.add(4)
        rbTree.add(3)
        rbTree.add(2)
        rbTree.add(1)
        rbTree.printTree()
    }
}