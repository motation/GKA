package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

import elements.IVertex;
import elements.IGraph;

public class DijkstraShortestPath_  {

	private IVertex startVertx;
	private IVertex endVertex;
	
	private IGraph searchGraph;
	
	
	
	private ArrayList<IVertex> arrList = new ArrayList<IVertex>();
	
	private ArrayList<IVertex> vorgeanger = new ArrayList<IVertex>();
	private ArrayList<Double> entf = new ArrayList<Double>();
	private ArrayList<Boolean> ok = new ArrayList<Boolean>();
	
	public DijkstraShortestPath_(IGraph searchGraph){
		
		
		this.searchGraph = searchGraph;
		if(!(searchGraph.isWeighted())){
			throw new IllegalArgumentException("Graph must be Weighted for Algoyrthm to Run");
		}
	
	}

	
	// Getter & Setter
	public IVertex getStartVertx() {
		return startVertx;
	}
	public void setStartVertx(IVertex startVertx) {
		//if(!(this.searchGraph.getGraph().containsVertex(startVertx))){
			//throw new IllegalArgumentException("Graph must contain start vertex");
		//}
		this.startVertx = startVertx;
	}
	public IVertex getEndVertex() {
		return endVertex;
	}
	public void setEndVertex(IVertex endVertex) {
		//if(!(this.searchGraph.getGraph().containsVertex(endVertex))){
		//	throw new IllegalArgumentException("Graph must contain target Vertex");
		//}
		this.endVertex = endVertex;
	}
	
	
	// 
	public void CalculateDijkstra(){
		arrList.addAll(searchGraph.getGraph().vertexSet());
		Graph<IVertex, Edge> currentGraph = searchGraph.getGraph();
		
		for(int i = 0;i < arrList.size();i++){
			entf.add(Double.POSITIVE_INFINITY);
			ok.add(false);
		}
		
		IVertex tempVertex = startVertx;					// Initialisierung
		entf.add(arrList.lastIndexOf(startVertx), 0.0);		// Initialisieirung
		
		
			while(ok.contains(false)){
				int currentIndex = arrList.indexOf(tempVertex);
				ArrayList<Edge> tempEdgeArr = new ArrayList<Edge>();
				tempEdgeArr.addAll(currentGraph.edgesOf(tempVertex));
				ok.remove(currentIndex);
				ok.add(currentIndex, true);
				
				for(int i = 0 ;  i < tempEdgeArr.size(); i++){
					IVertex currentTarget =  currentGraph.getEdgeTarget(tempEdgeArr.get(i));
					double currentEdgeWeight =  currentGraph.getEdgeWeight(tempEdgeArr.get(i));
					int targetIndex = arrList.indexOf(currentTarget);
					
					if(entf.get(currentIndex) + currentEdgeWeight < entf.get(targetIndex)){
						entf.remove(targetIndex);
						entf.add(entf.get(currentIndex) + currentEdgeWeight);
						vorgeanger.remove(currentTarget);
						vorgeanger.add(tempVertex);
					}
					

			 }
				 int tempindex = 0;
				 for(int i = 1; i < arrList.size(); i++){
					 if(entf.get(tempindex) > entf.get(i))
					 { tempindex = i;
					 } 
				 }
				 tempVertex = arrList.get(tempindex);
		}
	}
	
	public GraphPath getShortestPath(){
		
		ArrayList<Edge> EdgePath = new ArrayList<Edge>();
		IVertex tempVertex	= endVertex;
		IVertex nextVertex = null;
		double weight = 0.0;
		
		while((tempVertex != startVertx)){
			
			nextVertex = vorgeanger.get(arrList.indexOf(tempVertex));
			//searchGraph.getGraph().getEdge(tempVertex, nextVertex)
			
			EdgePath.add(searchGraph.getGraph().getEdge(tempVertex, nextVertex));
			weight = weight + searchGraph.getGraph().getEdgeWeight(searchGraph.getGraph().getEdge(tempVertex, nextVertex));
			tempVertex = nextVertex;
			}
		
					
					
		GraphPath retunrGraphPath = new GraphPathImpl<IVertex,Edge>(searchGraph.getGraph(),endVertex,startVertx,EdgePath,weight);
		
		return retunrGraphPath;
		
		
		
	}
	
	
}
