package algorithm;

import static org.junit.Assert.*;

import org.jgraph.graph.Edge;
import org.junit.Test;

import algorithms.Kruskal;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;

public class TestKruskal {

	@Test
	public void test() {
		IGraph graph1_static = UndirectedWeightedGraph.createNewGraph();;
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
		krus.compute();
		
		assertEquals(krus.weightSum(),13.0,0.001);
	}

}
