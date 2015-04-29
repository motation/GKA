package graphs.creator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import elements.IGraph;

public class TestGraphCreator {
	
	List<IGraph> undirectedWeightedGraphs;
	
	@Before
	public void init(){
		undirectedWeightedGraphs = new ArrayList<IGraph>();
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(10, 10));	//0
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(20, 1));	//1
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(50, 1));	//2
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(30, 0));	//3
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(11, 6));	//4
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(12, 5));	//5
		undirectedWeightedGraphs.add(GraphCreator.createUndirectedWeightedGraph(0, 1));		//6
	}
	
	@Test
	public void createUndirectedWeightedGraphSizeTrueTest(){
		//VERTEXES
		Assert.assertTrue(undirectedWeightedGraphs.get(0).getGraph().vertexSet().size()==10);
		Assert.assertTrue(undirectedWeightedGraphs.get(6).getGraph().vertexSet().size()==0);
		
		
		//EDGES
		Assert.assertTrue(undirectedWeightedGraphs.get(6).getGraph().edgeSet().size()==0);
		Assert.assertTrue(undirectedWeightedGraphs.get(4).getGraph().edgeSet().size()==6);
	}
	
	@Test
	public void createUndirectedWeightedGraphSizeFalseTest(){
		//VERTEXES
		Assert.assertFalse(undirectedWeightedGraphs.get(0).getGraph().vertexSet().size()==11);
		Assert.assertFalse(undirectedWeightedGraphs.get(5).getGraph().vertexSet().size()==111);
		
		
		//EDGES
		Assert.assertFalse(undirectedWeightedGraphs.get(6).getGraph().edgeSet().size()==1);
		Assert.assertFalse(undirectedWeightedGraphs.get(6).getGraph().edgeSet().size()==1);
	}
}
