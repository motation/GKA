package algorithm;

import static org.junit.Assert.*;

import org.jgraph.graph.Edge;
import org.junit.Test;

import algorithms.Fleury;
import algorithms.Hierholzer;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;

public class TestEulerCircle {
	IGraph graph1 = UndirectedWeightedGraph.createNewGraph();
	IGraph graph2 = UndirectedWeightedGraph.createNewGraph();
	Hierholzer hierh = new Hierholzer();
	Fleury flury = new Fleury();
	//IGraph
	public void init(){
		IVertex v1 = new Vertex("V1");
		IVertex v2 = new Vertex("V2");
		IVertex v3 = new Vertex("V3");
		IVertex v4 = new Vertex("V4");
		IVertex v5 = new Vertex("V5");
		graph1.addVertex(v1);
		graph1.addVertex(v2);
		graph1.addVertex(v3);
		graph1.addVertex(v4);
		graph1.addVertex(v5);
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
		
		
		IVertex v11 = new Vertex("V1");
		IVertex v21 = new Vertex("V2");
		IVertex v31 = new Vertex("V3");
		IVertex v41 = new Vertex("V4");
		graph2.addVertex(v11);
		graph2.addVertex(v21);
		graph2.addVertex(v31);
		graph2.addVertex(v41);
		Edge e111 = new WeightedEdge(v11,v21,0.0);
		Edge e21 = new WeightedEdge(v11,v31,0.0);
		Edge e31 = new WeightedEdge(v11,v41,0.0);
		graph2.addEdge(v11, v21, e111);
		graph2.addEdge(v11, v31, e111);
		graph2.addEdge(v11, v41, e111);
	}
	
	@Test
	public void testPositiv() {
		init();
		//#Hail_satan;#down_there:#hot_in_here
		hierh.compute(graph1);
		flury.compute(graph1);

		assertEquals(hierh.getResultPath(), flury.getResultPath());
	}
	@Test
	public void testNegativ(){


		init();
		assertFalse(hierh.compute(graph1));
		assertFalse(flury.compute(graph1));

		

	}

}
