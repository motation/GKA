package algorithms;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;



import elements.IGraph;
import elements.IVertex;

public class Kruskal {


	private IGraph CompleteGraph;
	private IGraph SCC;
	private Set<Edge> EdgeSet = new HashSet<Edge>();
	
	
	

	
	public Kruskal(IGraph completeGraph, Set<Edge> vertexSet) {
		super();
		CompleteGraph = completeGraph;
		this.EdgeSet = completeGraph.getAllEdges();
	}

	public IGraph compute(){
		
		
		return null;
		
	}
	
	public boolean reachable(IVertex start, IVertex end,IGraph graph){
		Set<Edge> tempEdgeSet = graph.getOutgoingEdges(start);
		for(Edge edge: tempEdgeSet){
			if( edge.getTarget() == end){
				return true;
			}
			reachable((IVertex) edge.getTarget(),end,graph);
		}
		return false;
	}
	
	public boolean hasCircle(IGraph graph){
		
		Set<IVertex> tempVertexSet = graph.getGraph().vertexSet();
		//ein Graph enthält einen Kreis falls es ein Knotentupel (IVertex1, IVertex2) gibt für das gilt:
		// IVertex 2 ist von IVertex 1 aus erreichbahr und IVertex 1 ist von IVertex 2 aus erreichbahr
		for(IVertex vertex: tempVertexSet){	
			Set<IVertex> tempVertexSet2 = tempVertexSet;
			tempVertexSet2.remove(vertex);
			for(IVertex vertex2: tempVertexSet2){
				if((reachable(vertex,vertex2,graph) && reachable(vertex2,vertex,graph))){
					return true;
				}
			}
		}
		return false;
	}
	
}
