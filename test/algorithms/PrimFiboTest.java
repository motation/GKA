package algorithms;

import elements.IGraph;
import io.FileGraphReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class PrimFiboTest {

    private IGraph graph;
    private FileGraphReader reader;

    @Before
    public void init() throws IOException {
        String stringGraph =    "#weighted\n" +
                "Z,L::31.0\n" +
                "L,O::242.0\n" +
                "O,V::167.0\n" +
                "Z,O::240.0\n" +
                "Z,V::191.0\n" +
                "L,Z::31.0\n" +
                "O,L::242.0\n" +
                "V,O::167.0\n" +
                "O,Z::240.0\n" +
                "V,Z::191.0";

        reader = new FileGraphReader();
        graph = reader.loadGraphByString(stringGraph);
    }

    @Test
    public void positivTests(){
        PrimFibo fibo = new PrimFibo(graph);
        fibo.minimumSpanningTree();
        Assert.assertEquals(389.0,fibo.getSum(),0.1);

    }

}