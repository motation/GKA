package algorithm;

import static org.junit.Assert.*;
import io.FileGraphReader;

import java.io.File;
import java.io.IOException;

import org.jgraph.graph.Edge;
import org.junit.Test;

import algorithms.Fleury;
import algorithms.Hierholzer;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;
import graphs.creator.EulerGenerator;

public class TestEulerCircle {
	IGraph graph1_static = UndirectedWeightedGraph.createNewGraph();
	IGraph graph2_static = UndirectedWeightedGraph.createNewGraph();
	IGraph graph1;
	IGraph graph2;
	IGraph graph3;
	IGraph graph4;
	IGraph graph5;
	
	
	Hierholzer hierh = new Hierholzer();
	Fleury flury = new Fleury();
	//IGraph
	
	public void init() throws IOException{
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
		graph1_static.addEdge(v1, v2, e1);
		graph1_static.addEdge(v2, v3, e2);
		graph1_static.addEdge(v3, v4, e3);
		graph1_static.addEdge(v4, v5, e4);
		graph1_static.addEdge(v5, v1, e5);
		graph1_static.addEdge(v1, v3, e6);
		graph1_static.addEdge(v1, v4, e7);
		graph1_static.addEdge(v2, v4, e8);
		graph1_static.addEdge(v2, v5, e9);
		graph1_static.addEdge(v3, v1, e10);
		graph1_static.addEdge(v3, v5, e11);
		graph1_static.addEdge(v4, v1, e12);
		graph1_static.addEdge(v4, v2, e13);
		graph1_static.addEdge(v5, v3, e14);
		graph1_static.addEdge(v5, v2, e15);
		
		
		IVertex v11 = new Vertex("V1");
		IVertex v21 = new Vertex("V2");
		IVertex v31 = new Vertex("V3");
		IVertex v41 = new Vertex("V4");
		graph2_static.addVertex(v11);
		graph2_static.addVertex(v21);
		graph2_static.addVertex(v31);
		graph2_static.addVertex(v41);
		Edge e111 = new WeightedEdge(v11,v21,0.0);
		Edge e21 = new WeightedEdge(v11,v31,0.0);
		Edge e31 = new WeightedEdge(v11,v41,0.0);
		graph2_static.addEdge(v11, v21, e111);
		graph2_static.addEdge(v11, v31, e111);
		graph2_static.addEdge(v11, v41, e111);
		
		
//		File file1=new File("Z:\\win7\\GKA\\weighted1.graph");
//		File file2=new File("Z:\\win7\\GKA\\weighted2.graph");
//		File file3=new File("Z:\\win7\\GKA\\weighted3.graph");
//		File file4=new File("Z:\\win7\\GKA\\weighted4.graph");
//		File file5=new File("Z:\\win7\\GKA\\weighted5.graph");
//		
//		FileGraphReader reader = new FileGraphReader();
//		graph1 = reader.loadGraph(file1);
//		graph2 = reader.loadGraph(file2);
//		graph3 = reader.loadGraph(file3);
//		graph4 = reader.loadGraph(file4);
//		graph5 = reader.loadGraph(file5);
		
	}
	
	
	@Test
	public void testEulerCircle(){
		IGraph Tempgraph = UndirectedWeightedGraph.createNewGraph();
		IVertex v1 = new Vertex("V1");
		IVertex v2 = new Vertex("V2");
		IVertex v3 = new Vertex("V3");
		IVertex v4 = new Vertex("V4");
		Tempgraph.addVertex(v1);
		Tempgraph.addVertex(v2);
		Tempgraph.addVertex(v3);
		Tempgraph.addVertex(v4);
		Edge e1 = new WeightedEdge(v1,v2,0.0);
		Edge e2 = new WeightedEdge(v2,v3,0.0);
		Edge e3 = new WeightedEdge(v3,v4,0.0);
		Edge e4 = new WeightedEdge(v4,v1,0.0);
		Tempgraph.addEdge(v1, v2, e1);
		Tempgraph.addEdge(v2, v3, e2);
		Tempgraph.addEdge(v3, v4, e3);
		Tempgraph.addEdge(v4, v1, e4);
		
		Hierholzer hierh = new Hierholzer();
		hierh.getEulertour(v1, Tempgraph);
		
		assertEquals(hierh.getEulertour(v1, Tempgraph), true);
		
	}
	
	@Test
	public void TestlegitGraph() throws IOException {
		init();
		
		 for(int i=3;i<50;i++){
			 
			 
			 IGraph tempGraph = EulerGenerator.generateEulerCircle(i);
			 assertEquals(hierh.legitGraph(tempGraph), true);
		
		
		 }
	}
	
	@Test
	public void testPositiv() throws IOException {
		init();
		//#Hail_satan;#down_there:#hot_in_here
		
		
		hierh.compute(graph1_static);
		flury.compute(graph1_static);

		
		assertEquals(hierh.getResultPath(), flury.getResultPath());
	}
	
	@Test
	public void testNegativ() throws IOException{


		init();
		assertFalse(hierh.compute(graph1_static));
		assertFalse(flury.compute(graph1_static));
	}
	@Test
	
	public void TestFiles(){
		hierh.compute(graph1);
		flury.compute(graph1);
		assertEquals(hierh.getResultPath(), flury.getResultPath());
		
		
		hierh.compute(graph2);
		flury.compute(graph2);
		assertEquals(hierh.getResultPath(), flury.getResultPath());
		
		hierh.compute(graph3);
		flury.compute(graph3);
		assertEquals(hierh.getResultPath(), flury.getResultPath());
		
		hierh.compute(graph4);
		flury.compute(graph4);
		assertEquals(hierh.getResultPath(), flury.getResultPath());
		
		hierh.compute(graph5);
		flury.compute(graph5);
		assertEquals(hierh.getResultPath(), flury.getResultPath());
		
	}

}
