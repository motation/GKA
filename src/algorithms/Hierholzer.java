package algorithms;

import org.jgrapht.GraphPath;

import elements.IGraph;
import elements.IVertex;

public class Hierholzer {

	
	
	public boolean compute(IGraph graph1) {
		
		for(IVertex vertex : graph1.getGraph().vertexSet()){
			if(knotengrad(vertex,graph1) % 2 == 1) return false;
		}
		return false;
		// TODO Auto-generated method stub
		
	}

	public GraphPath getResultPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int knotengrad(IVertex vertex, IGraph graph1){
		return graph1.getGraph().edgesOf(vertex).size();
	}
}
