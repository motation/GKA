package algorthmTest;

import static org.junit.Assert.*;
import io.FileGraphReader;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.WeightedGraphGenerator;
import org.junit.Test;

import algorithms.Dikstra;



public class TestDijkstra {
		
	
	IGraph testGraph1 = UndirectedWeightedGraph.createNewGraph();
	IVertex v1 = new Vertex("V1");
	IVertex v2 = new Vertex("V2");
	IVertex v3 = new Vertex("V3");
	IVertex v4 = new Vertex("V4");
	IVertex v5 = new Vertex("V5");
	IVertex v6 = new Vertex("V6");
	IVertex v7 = new Vertex("V7");
	
	Edge e1 = new WeightedEdge(v1,v2,10.0);
	Edge e2 = new WeightedEdge(v2,v3,7.0);
	Edge e3 = new WeightedEdge(v2,v4,1.0);
	Edge e4 = new WeightedEdge(v4,v1,3.0);
	Edge e5 = new WeightedEdge(v5,v6,4.0);
	Edge e6 = new WeightedEdge(v6,v7,7.0);
	Edge e7 = new WeightedEdge(v4,v5,7.0);
	
	
	
	public TestDijkstra(){
		testGraph1.addVertex(v1);
		testGraph1.addVertex(v2);
		testGraph1.addVertex(v3);
		testGraph1.addVertex(v4);
		testGraph1.addVertex(v5);
		testGraph1.addVertex(v6);
		testGraph1.addVertex(v7);
		testGraph1.addEdge(v1, v2, e1);
		testGraph1.addEdge(v2, v3, e2);
		testGraph1.addEdge(v2, v4, e3);
		testGraph1.addEdge(v4, v1, e4);
		testGraph1.addEdge(v5, v6, e5);
		testGraph1.addEdge(v6, v7, e6);
		testGraph1.addEdge(v4, v5, e7);

		//Dikstra dijk = new Dikstra(testGraph1);
		
		
	//	dijk.setStartVertx(v1);
		//dijk.setEndVertex(v3);
		//dijk.calculateDijkstra();
		
		 
		
		
	}
	@Test
	public void test() {
		
		
		Dikstra dijk = new Dikstra(testGraph1,v1,v2);
		

		dijk.calculateDijkstra();
		DijkstraShortestPath dijk2 = new DijkstraShortestPath((Graph) testGraph1,v1,v3);	
		assertEquals(dijk.getShortestPath(), dijk2.getPath());
	}

}
