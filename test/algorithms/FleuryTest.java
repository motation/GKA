package algorithms;

import elements.IGraph;
import graphs.creator.EulerGenerator;
import graphs.creator.PrimGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ole on 17.06.2015.
 */
public class FleuryTest {

    private IGraph testGraph1;
    private IGraph testGraph2;
    private IGraph testGraph3;
    private Fleury fleury;

    @Before
    public void setUp() throws Exception {
        this.testGraph1 = EulerGenerator.generateEulerCircle(10);
        this.testGraph2 = EulerGenerator.generateEulerCircle(15);
        this.testGraph3 = EulerGenerator.generateEulerCircle(22);
        fleury = new Fleury();
    }

    @Test
    public void testCompute() throws Exception {
        Assert.assertTrue(fleury.compute(testGraph1));
        Assert.assertTrue(fleury.compute(testGraph2));
        Assert.assertTrue(fleury.compute(testGraph3));

        for (int i = 0; i < 100; i++) {
            this.testGraph1 = EulerGenerator.generateEulerCircle(PrimGenerator.createRandomInt(50, 100));
            Assert.assertTrue(fleury.compute(testGraph1));
        }
    }
}