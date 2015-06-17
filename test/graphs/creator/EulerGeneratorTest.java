package graphs.creator;

import elements.IGraph;
import io.FileGraphReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ole on 17.06.2015.
 */
public class EulerGeneratorTest {

    private FileGraphReader reader;

    private IGraph eulerCircleTrue1;
    private IGraph eulerCircleTrue2;
    private IGraph eulerCircleTrue3;

    private IGraph eulerCircleFalse1;
    private IGraph eulerCircleFalse2;
    private IGraph eulerCircleFalse3;

    private IGraph staticEulerCircleTrue;
    private IGraph staticEulerCircleFalse;


    @Before
    public void setUp() throws Exception {

        reader = new FileGraphReader();

        this.eulerCircleTrue1 = EulerGenerator.generateEulerCircle(PrimGenerator.createRandomInt(10,30));
        this.eulerCircleTrue2 = EulerGenerator.generateEulerCircle(PrimGenerator.createRandomInt(10,30));
        this.eulerCircleTrue3 = EulerGenerator.generateEulerCircle(PrimGenerator.createRandomInt(10,30));

        this.eulerCircleFalse1 = EulerGenerator.generateWrongEulerCircle();
        this.eulerCircleFalse2 = EulerGenerator.generateWrongEulerCircle();
        this.eulerCircleFalse3 = EulerGenerator.generateWrongEulerCircle();

        String stringGraphPositive =    "#weighted\n" +
                                        "A,B::1\n" +
                                        "A,D::2\n" +
                                        "B,C::3\n" +
                                        "D,C::4\n" +
                                        "D,G::5\n" +
                                        "C,G::6\n" +
                                        "D,E::7\n" +
                                        "C,F::8\n" +
                                        "F,E::9";

        String stringGraphNegative =    "#weighted\n" +
                                        "A,B::1\n" +
                                        "A,C::3\n" +
                                        "B,C::2\n" +
                                        "D,E::4\n" +
                                        "D,F::5\n" +
                                        "E,F::6";

        this.staticEulerCircleTrue = reader.loadGraphByString(stringGraphPositive);
        this.staticEulerCircleFalse = reader.loadGraphByString(stringGraphNegative);
    }

    @Test
    public void positiveTest() {
        Assert.assertTrue(EulerGenerator.isEulerCircle(eulerCircleTrue1));
        Assert.assertTrue(EulerGenerator.isEulerCircle(eulerCircleTrue2));
        Assert.assertTrue(EulerGenerator.isEulerCircle(eulerCircleTrue3));
    }

    @Test
    public void negativeTest() {
        Assert.assertFalse(EulerGenerator.isEulerCircle(eulerCircleFalse1));
        Assert.assertFalse(EulerGenerator.isEulerCircle(eulerCircleFalse2));
        Assert.assertFalse(EulerGenerator.isEulerCircle(eulerCircleFalse3));
    }

    @Test
    public void staticPositiveTest() {
        Assert.assertTrue(EulerGenerator.isEulerCircle(staticEulerCircleTrue));
    }

    @Test
    public void staticNegativeTest() {
        Assert.assertFalse(EulerGenerator.isEulerCircle(staticEulerCircleFalse));
    }
}