package algorithms;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.GraphPathImpl;







import org.jgrapht.traverse.BreadthFirstIterator;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;

public class Kruskal {


	private IGraph CompleteGraph;
	private IGraph SCC;
	private Set<Edge> EdgeSet = new HashSet<Edge>();
	
	
	

	
	public Kruskal(IGraph completeGraph) {
		CompleteGraph = completeGraph;
		SCC = UndirectedWeightedGraph.createNewGraph();
		this.EdgeSet = completeGraph.getAllEdges();
	}

	public void compute(){
		
		IGraph tempGraph = CompleteGraph;
		while(!(tempGraph.getAllEdges().isEmpty())){
			Edge tempLowestEdge = getLowestEdge(tempGraph);
			IVertex start = (IVertex) tempLowestEdge.getSource();
			IVertex end = (IVertex) tempLowestEdge.getTarget();
			//SCC.addEdge(start, end, tempLowestEdge);
			Set<IVertex> vertexSet = SCC.getGraph().vertexSet(); 
			if(!(hasCircle(SCC,start,end))){
					if(!(vertexSet.contains(end))){
						SCC.addVertex(end);
					}
					if(!(vertexSet.contains(start))){
						SCC.addVertex(start);
					}
					SCC.addEdge(end, start, tempLowestEdge);
			}
			tempGraph.getGraph().removeEdge(tempLowestEdge);
		}
	}
	
	public double weightSum(){
		double WeightSum = 0;
		for(Edge edge : SCC.getGraph().edgeSet()){
		
			WeightedEdge weightedEdge = (WeightedEdge) edge;
			WeightSum = WeightSum + weightedEdge.getWeight();
		}
		return WeightSum;
	}
	
	public IGraph returnGraph(){
		return this.SCC;
	}
	
	public Edge getLowestEdge(IGraph Graph){
		double smalestWeight = Double.POSITIVE_INFINITY;
		Edge smalestEdge = null;
		
		for(Edge edge : Graph.getGraph().edgeSet()){
			WeightedEdge weightedEdge = (WeightedEdge) edge;
			if(weightedEdge.getWeight() < smalestWeight){
				smalestWeight = weightedEdge.getWeight();
				smalestEdge = weightedEdge;
			}
		}
		
		
		return smalestEdge;
	}
	
	public boolean reachable(IVertex start, IVertex end,IGraph graph){
			// Set<Edge> edgeSet = graph.edgesFrom(start);
			Set<IVertex> vertSet = new HashSet<>();
			Queue<IVertex> vertQueue = new PriorityQueue<>();
			while(!vertQueue.isEmpty()){
				IVertex currVert = vertQueue.poll();
				
				Set<Edge> edgeSet = graph.edgesFrom(currVert);
				for(Edge currEdge : edgeSet){
					IVertex v1 = graph.getGraph().getEdgeTarget(currEdge);
					IVertex v2 = graph.getGraph().getEdgeSource(currEdge);
					if (v1.equals(end) || v2.equals(end)) return true;
					
					if (vertSet.add(v1)){
						vertQueue.add(v1);
					}
					
					if (vertSet.add(v2)){
						vertQueue.add(v2);
					}
				}
			}
		return false;
	}
	
	public boolean hasCircle(IGraph graph, IVertex start, IVertex end){
		Set<IVertex> vertexSet = graph.getGraph().vertexSet();
		if(vertexSet.contains(start) && vertexSet.contains(end)){
			if(reachable(start,end,graph))
				return true;
		}
		
		return false;
	}
	
}
