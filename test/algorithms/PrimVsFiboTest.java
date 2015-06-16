package algorithms;

import elements.IGraph;
import graphs.creator.PrimGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ole on 17.06.2015.
 */
public class PrimVsFiboTest {
    private IGraph graph;
    private Prim prim;
    private PrimFibo primFibo;


    @Test
    public void primVsFiboTests(){

        for(int i=3;i<50;i++){
            graph = PrimGenerator.createPrimGraphWithoutLoop(i);
            prim = new Prim(graph);
            primFibo = new PrimFibo(graph);
            prim.init();
            prim.loop();
            primFibo.minimumSpanningTree();

            Assert.assertEquals(prim.getEdgeSum(),primFibo.getSum(),0.1);
        }
    }
}
