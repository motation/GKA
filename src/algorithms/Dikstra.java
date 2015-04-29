package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;


import elements.IAttributedVertex;
import elements.IGraph;
import elements.IVertex;

public class Dikstra {
	
	
	public IVertex getStartVertx() {
		return startVertx;
	}
	public void setStartVertx(IVertex startVertx) {
		this.startVertx = startVertx;
	}
	public IVertex getEndVertex() {
		return endVertex;
	}
	public void setEndVertex(IVertex endVertex) {
		this.endVertex = endVertex;
	}
	public IGraph getSearchGraph() {
		return searchGraph;
	}
	public void setSearchGraph(IGraph searchGraph) {
		this.searchGraph = searchGraph;
	}

	private IVertex startVertx;
	private IVertex endVertex;
	private HashMap<IVertex, Values> Table = new HashMap<IVertex, Values>();
	
	private IGraph searchGraph;
	private Set vertexSet = new HashSet();
	
	class Values{
		private double entfernung;
		private IVertex vorgeanger;
		private boolean ok ;
		public Values(double entfernung,IVertex vorgeanger,boolean ok){
			this.entfernung = entfernung;
			this.vorgeanger = vorgeanger;
			this.ok = ok;
		}
		
		}

	
	public Dikstra(IGraph searchGraph){
		this.searchGraph = searchGraph;
		
		vertexSet.add(searchGraph.getGraph().vertexSet());
		
		 Iterator iterator = vertexSet.iterator();
		 	        while (iterator.hasNext()) {
		 	        	if(iterator.next() == startVertx){
		 	        		Table.put((IVertex) iterator.next(), new Values(0.0,startVertx,true));
		 	        	}
		 	        	else{
		 	        		Table.put((IVertex) iterator.next(), new Values(Double.POSITIVE_INFINITY,null,false));
		 	        	}
		 	        }
		}
	
	public boolean containsFalse(){
		 Iterator iterator = vertexSet.iterator();
	        while (iterator.hasNext()) {
	        	if(Table.get(iterator.next()).ok == false){
	        		return true;
	        	}
	        	else{
	        		
	        	}
	        }
			return false;
	}
	
	public IVertex getSmalestNotVisited(){
		Iterator iterator = vertexSet.iterator();
		IVertex tempVertex = null;
		double smalest = Double.POSITIVE_INFINITY;
		while(iterator.hasNext()){
			if(Table.get(iterator.next()).ok == false){
				if(Table.get(iterator.next()).entfernung < smalest){
					tempVertex = (IVertex) iterator.next();
					smalest = Table.get(iterator.next()).entfernung;
				}
			}
		}
		return tempVertex;
	}
	
	public Set<IVertex> returnConnected(IVertex Center){
		Set returnSet = new HashSet();
		Iterator iterator =  searchGraph.getOutgoingEdges(Center).iterator();
		while(iterator.hasNext()){
			if(searchGraph.getGraph().containsEdge(Center, (IVertex) iterator.next())){
				returnSet.add(iterator.next());
			}
		}
		return returnSet;
		
	}
	

	
	public void calculateDijkstra(){
		int i = 0;
			while(containsFalse()){
				i++;
				if (i > 100){
					break;
				}
				IVertex currentVertex = getSmalestNotVisited();
				Table.get(currentVertex).ok = true;
					Set connectedSet = returnConnected(currentVertex);
					Iterator iterator = connectedSet.iterator();
					
					while(iterator.hasNext()){
						if(Table.get(iterator.next()).entfernung > Table.get(currentVertex).entfernung + searchGraph.getGraph().getEdgeWeight(searchGraph.getGraph().getEdge(currentVertex, (IVertex) iterator.next()))){
							Table.get(iterator.next()).entfernung = Table.get(currentVertex).entfernung + searchGraph.getGraph().getEdgeWeight(searchGraph.getGraph().getEdge(currentVertex, (IVertex) iterator.next()));
							Table.get(iterator.next()).vorgeanger = currentVertex;
						}
					}
			}
	}
	
	public GraphPath getShortestPath(){
		ArrayList<Edge> EdgePath = new ArrayList<Edge>();
		double weight = Table.get(endVertex).entfernung;
		IVertex vertex1 = endVertex;
		IVertex vertex2 = Table.get(endVertex).vorgeanger;
	int i = 0;
		while (vertex2 != startVertx){
			i++;
			EdgePath.add(searchGraph.getGraph().getEdge(vertex1, vertex2));
			if(i > 100){
				break;
			}
		}
		
	GraphPath retunrGraphPath = new GraphPathImpl<IVertex,Edge>(searchGraph.getGraph(),endVertex,startVertx,EdgePath,weight);
	
	return retunrGraphPath;
	}
}
