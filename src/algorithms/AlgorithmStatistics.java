package algorithms;

import elements.IGraph;
import graphs.creator.PrimGenerator;

import java.awt.datatransfer.SystemFlavorMap;

/**
 * Created by Ole on 17.06.2015.
 */
public class AlgorithmStatistics {
    private Prim prim;
    private PrimFibo primFibo;

    public void runStatisticFiboAndPrim() {
        long start = System.currentTimeMillis();
        IGraph graph = PrimGenerator.createPrimGraphWithoutLoop(100);
        long end = System.currentTimeMillis();
        System.out.println("Graph generation took " + (end - start) + "ms" + " " +
                "and has " + graph.getGraph().vertexSet().size() + " vertexes and has " + graph.getGraph().edgeSet().size() + " " +
                "edges");
        start = System.currentTimeMillis();
        prim = new Prim(graph);
        prim.init();
        prim.loop();
        end = System.currentTimeMillis();
        System.out.println("Prim:: Calculation took " + (end-start) +" ms");

        start = System.currentTimeMillis();
        primFibo = new PrimFibo(graph);
        primFibo.minimumSpanningTree();
        end = System.currentTimeMillis();
        System.out.println("Fibo:: Calculation took " + (end-start) +" ms");

    }

    public static void main(String[] args) {
        AlgorithmStatistics algorithmStatistics = new AlgorithmStatistics();
        algorithmStatistics.runStatisticFiboAndPrim();
    }
}
