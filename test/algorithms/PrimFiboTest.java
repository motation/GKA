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
    private IGraph graph2;
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

        String stringGraph2 = "#weighted\n" +
                "F,W::283.0\n" +
                "W,B::2.0\n" +
                "B,E::228.0\n" +
                "F,B::3.0\n" +
                "F,E::138.0\n" +
                "W,E::70.0\n" +
                "W,F::283.0\n" +
                "B,W::2.0\n" +
                "E,B::228.0\n" +
                "B,F::3.0\n" +
                "E,F::138.0\n" +
                "E,W::70.0\n";

        graph2 = reader.loadGraphByString(stringGraph2);
    }

    @Test
    public void positivTests(){
        PrimFibo fibo = new PrimFibo(graph);
        fibo.minimumSpanningTree();
        Assert.assertEquals(389.0,fibo.getSum(),0.1);

    }

}