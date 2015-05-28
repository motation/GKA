package algorithm;



import io.FileGraphReader;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algorithms.Prim;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;

public class TestPrim {
	
	IGraph graph1;
	IGraph graph2;
	IGraph graph3;
	IGraph graph4;
	IGraph graph5;
	
	@Before
	public void init() throws IOException{
		
		File file1=new File("Z:\\win7\\GKA\\weighted1.graph");
		File file2=new File("Z:\\win7\\GKA\\weighted2.graph");
		File file3=new File("Z:\\win7\\GKA\\weighted3.graph");
		File file4=new File("Z:\\win7\\GKA\\weighted4.graph");
		File file5=new File("Z:\\win7\\GKA\\weighted5.graph");
		
		FileGraphReader reader = new FileGraphReader();
		graph1 = reader.loadGraph(file1);
		graph2 = reader.loadGraph(file2);
		graph3 = reader.loadGraph(file3);
		graph4 = reader.loadGraph(file4);
		graph5 = reader.loadGraph(file5);
	}
	
	@Test
	public void positivTests(){
		IVertex vertex1 = new Vertex("a");
		Prim prim1 = new Prim(graph1, vertex1);
		
		IVertex vertex2 = new Vertex("a");
		Prim prim2 = new Prim(graph2, vertex2);
		
		IVertex vertex3 = new Vertex("b");
		Prim prim3 = new Prim(graph3,vertex3);
		
		IVertex vertex4 = new Vertex("b");
		Prim prim4 = new Prim(graph3,vertex3);
		
		Assert.assertEquals(prim1.getEdgeSum(), prim2.getEdgeSum(),0.0001);
		Assert.assertEquals(prim4.getEdgeSum(), prim3.getEdgeSum(),0.0001);
	}
}

