package algorithm;

import static org.junit.Assert.*;

import org.jgraph.graph.Edge;
import org.junit.Test;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;

public class TestEulerCircle {
	IGraph graph1 = null;
	//IGraph
	
	@Test
	public void test() {
		IVertex v1 = new Vertex("V1");
		IVertex v2 = new Vertex("V2");
		IVertex v3 = new Vertex("V3");
		IVertex v4 = new Vertex("V4");
		IVertex v5 = new Vertex("V5");
		
		Edge e1 = new WeightedEdge(v1,v2,0.0);
		Edge e2 = new WeightedEdge(v2,v3,0.0);
		Edge e3 = new WeightedEdge(v3,v4,0.0);
		Edge e4 = new WeightedEdge(v4,v5,0.0);
		Edge e5 = new WeightedEdge(v5,v1,0.0);
		
		Edge e6 = new WeightedEdge(v1,v3,0.0);
		Edge e7 = new WeightedEdge(v1,v4,0.0);
		
		Edge e8 = new WeightedEdge(v2,v4,0.0);
		Edge e9 = new WeightedEdge(v2,v5,0.0);
		
		Edge e10 = new WeightedEdge(v3,v1,0.0);
		Edge e11 = new WeightedEdge(v3,v5,0.0);
		
		Edge e12 = new WeightedEdge(v4,v1,0.0);
		Edge e13 = new WeightedEdge(v4,v2,0.0);
		
		Edge e14 = new WeightedEdge(v5,v3,0.0);
		Edge e15 = new WeightedEdge(v5,v2,0.0);
		
		graph1.addEdge(v1, v2, e1);
		graph1.addEdge(v2, v3, e2);
		graph1.addEdge(v3, v4, e3);
		graph1.addEdge(v4, v5, e4);
		graph1.addEdge(v5, v1, e5);
		
		graph1.addEdge(v1, v3, e6);
		graph1.addEdge(v1, v4, e7);
		
		graph1.addEdge(v2, v4, e8);
		graph1.addEdge(v2, v5, e9);
		
		graph1.addEdge(v3, v1, e10);
		graph1.addEdge(v3, v5, e11);
		
		graph1.addEdge(v4, v1, e12);
		graph1.addEdge(v4, v2, e13);
		
		graph1.addEdge(v5, v3, e14);
		graph1.addEdge(v5, v2, e15);
		//#Hail_satan;#down_there:#hot_in_here
		
		
		
		
	}

}
