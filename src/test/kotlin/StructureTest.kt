import data_structure.RBTree
import org.junit.jupiter.api.Test

class StructureTest {

    @Test
    fun rbTreeTest(){
        val rbTree = RBTree<Int>()
        var i = 0
        repeat(10){
            rbTree.add(i, 50)
            i++
        }
        rbTree.printTree()
        println("=====================")
        rbTree.remove(7)
        rbTree.printTree()
    }
}