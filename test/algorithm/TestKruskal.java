package algorithm;

import static org.junit.Assert.*;

import org.jgraph.graph.Edge;
import org.junit.Assert;
import org.junit.Test;

import algorithms.Kruskal;
import algorithms.Prim;
import algorithms.PrimFibo;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
import graphs.creator.PrimGenerator;



public class TestKruskal {
	
    private IGraph graph ;
    private Prim prim;
    private PrimFibo primFibo;


	@Test
	public void test() {
		
		IGraph graph1_static = UndirectedWeightedGraph.createNewGraph();
		IVertex v1 = new Vertex("V1");
		IVertex v2 = new Vertex("V2");
		IVertex v3 = new Vertex("V3");
		IVertex v4 = new Vertex("V4");
		IVertex v5 = new Vertex("V5");
		graph1_static.addVertex(v1);
		graph1_static.addVertex(v2);
		graph1_static.addVertex(v3);
		graph1_static.addVertex(v4);
		graph1_static.addVertex(v5);
		
		Edge e1 = new WeightedEdge(v1,v2,1.0);
		Edge e2 = new WeightedEdge(v2,v4,4.0);
		Edge e3 = new WeightedEdge(v2,v3,3.0);
		Edge e4 = new WeightedEdge(v1,v3,2.0);
		Edge e5 = new WeightedEdge(v3,v4,5.0);
		Edge e6 = new WeightedEdge(v4,v5,7.0);
		Edge e7 = new WeightedEdge(v3,v5,6.0);
		
		graph1_static.addEdge(v1, v2, e1);
		graph1_static.addEdge(v2, v4, e2);
		graph1_static.addEdge(v2, v3, e3);
		graph1_static.addEdge(v1, v3, e4);
		graph1_static.addEdge(v3, v4, e5);
		graph1_static.addEdge(v4, v5, e6);
		graph1_static.addEdge(v3, v5, e7);
		
		Kruskal krus = new Kruskal(graph1_static);
		
		
		assertEquals(krus.getMinimumSpanningTreeTotalWeight(),13.0,0.001);
	}
	
	
	 @Test
	    public void primVsFiboTests(){
		    

		    
	        for(int i=3;i<50;i++){
	            graph = PrimGenerator.createPrimGraphWithoutLoop(i);
	            prim = new Prim(graph);
	            primFibo = new PrimFibo(graph);
	            Kruskal krus1 = new Kruskal(graph);
	            prim.init();
	            prim.loop();
	            primFibo.minimumSpanningTree();

	            Assert.assertEquals(krus1.getMinimumSpanningTreeTotalWeight(),primFibo.getSum(),0.1);
	        }
	    }

}
