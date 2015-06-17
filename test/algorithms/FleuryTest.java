package algorithms;

import elements.IGraph;
import graphs.creator.EulerGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Ole on 17.06.2015.
 */
public class FleuryTest {

    private IGraph testGraph1;
    private IGraph testGraph2;
    private IGraph testGraph3;
    private Fleury fleury;
    private Hierholzer hierholzer;

    @Before
    public void setUp() throws Exception {
        this.testGraph1 = EulerGenerator.generateEulerCircle(10);
        this.testGraph2 = EulerGenerator.generateEulerCircle(15);
        this.testGraph3 = EulerGenerator.generateEulerCircle(22);
        fleury = new Fleury();
        hierholzer = new Hierholzer();
    }

    @Test
    public void testCompute() throws Exception {
        Assert.assertTrue(fleury.compute(testGraph1));
        Assert.assertTrue(fleury.compute(testGraph2));
        Assert.assertTrue(fleury.compute(testGraph3));
    }

    @Ignore
    @Test
    public void testGetResultPath() throws Exception {
        Assert.assertEquals(hierholzer.getResultPath(),fleury.getResultPath());
    }
}