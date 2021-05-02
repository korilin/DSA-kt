import data_structure.RBTree
import org.junit.jupiter.api.Test

class StructureTest {

    @Test
    fun rbTreeTest(){
        val rbTree = RBTree<Int>()
        repeat(10){
            rbTree.add(1, 50)
        }
        rbTree.printTree()
    }
}