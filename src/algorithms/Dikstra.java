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

public class Dikstra implements IAlgorithm {
	
	
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
	private Set<IVertex> vertexSet = new HashSet<IVertex>();
	
	class Values{
		public double entfernung;
		public IVertex vorgeanger;
		public boolean ok ;
		
		public Values(double entfernung,IVertex vorgeanger,boolean ok){
			this.entfernung = entfernung;
			this.vorgeanger = vorgeanger;
			this.ok = ok;
		}
		
		}

	
	public Dikstra(IGraph searchGraph){
		this.searchGraph = searchGraph;
		}
	
	public boolean containsFalse(){
		 Iterator<IVertex> iterator = vertexSet.iterator();
		 iterator.next();
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
		
		Iterator<IVertex> iterator = vertexSet.iterator();
		IVertex tempVertex = null;
		double smalest = Double.POSITIVE_INFINITY;
		
		while(iterator.hasNext()){
			IVertex currentVertex = (IVertex) iterator.next();
			
			if(Table.get(currentVertex).ok != true){

				
				if(Table.get(currentVertex).entfernung < smalest){
					tempVertex = currentVertex;
					smalest = Table.get(currentVertex).entfernung;
				}
			}
		}
		return tempVertex;
	}
	
	public Set<IVertex> returnConnected(IVertex Center){
		Set<IVertex> returnSet = new HashSet<IVertex>();
		Iterator<IVertex> iterator = vertexSet.iterator();
		
		while(iterator.hasNext()){
			IVertex currentVertex = (IVertex) iterator.next();
			if(searchGraph.getGraph().containsEdge(Center, currentVertex)){
				returnSet.add(currentVertex);
			}
		}
		return returnSet;	
	}
	

	
	public void calculateDijkstra(){

			while(containsFalse()){
				

				IVertex currentVertex = getSmalestNotVisited();
				Table.get(currentVertex).ok = true;
					Set<?> connectedSet = returnConnected(currentVertex);
					Iterator<?> iterator = connectedSet.iterator();
					
					while(iterator.hasNext()){
						IVertex currentVertex2 = (IVertex) iterator.next();
						if(Table.get(currentVertex2).entfernung > Table.get(currentVertex).entfernung + searchGraph.getGraph().getEdgeWeight(searchGraph.getGraph().getEdge(currentVertex, currentVertex2))){
							Table.get(currentVertex2).entfernung = Table.get(currentVertex).entfernung + searchGraph.getGraph().getEdgeWeight(searchGraph.getGraph().getEdge(currentVertex, currentVertex2));
							Table.get(currentVertex2).vorgeanger = currentVertex;
						}
					}
			}
	}
	
	public GraphPath getShortestPath(){
		ArrayList<Edge> EdgePath = new ArrayList<Edge>();
		
		IVertex vertex1 = endVertex;
		IVertex vertex2 = Table.get(endVertex).vorgeanger;
		
		
		while (vertex1 != startVertx)
		{
			EdgePath.add(searchGraph.getGraph().getEdge(vertex1, vertex2));
			vertex1 = vertex2;
			vertex2 = Table.get(vertex2).vorgeanger;

		}
		
		
//		System.out.println("-------------Debug-------------------");
//		Iterator testIT = vertexSet.iterator();
//			while(testIT.hasNext()){
//				IVertex currentVertex = (IVertex) testIT.next();
//				System.out.println("Knoten : " + currentVertex);
//				System.out.println("Hat entfernung : " + Table.get(currentVertex).entfernung);
//				System.out.println("wurde bearbeitet : " + Table.get(currentVertex).ok);
//				System.out.println("hatt den vorgeanger : " + Table.get(currentVertex).vorgeanger.toString());
//				System.out.println("_________________");
//				System.out.println("_________________");
//			}
			
	Collections.reverse(EdgePath);
	GraphPath retunrGraphPath = new GraphPathImpl<IVertex,Edge>(searchGraph.getGraph(),endVertex,startVertx,EdgePath,Table.get(endVertex).entfernung);
	return retunrGraphPath;
	}
	@Override
	public void calculate() {
		if(startVertx == null || endVertex == null) return;
		init();
		this.calculateDijkstra();
		
	}
	private void init() {
		vertexSet.addAll((searchGraph.getGraph().vertexSet()));
		 Iterator<IVertex> iterator = vertexSet.iterator();

		 			while (iterator.hasNext()) {
		 	        	IVertex currentVertex = (IVertex) iterator.next();
		 	
		 	        	if(currentVertex == this.startVertx){
		 	        		Table.put((IVertex) currentVertex, new Values(0.0,startVertx,false));
		 	        	}
		 	        	else{
		 	        		Table.put((IVertex) currentVertex, new Values(Double.POSITIVE_INFINITY,null,false));
		 	        	
		 	        	}
		 	        }
		
	}
	@Override
	public IGraph getGraph() {
		return this.searchGraph;
	}
}
