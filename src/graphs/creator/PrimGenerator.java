package graphs.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedWeightedGraph;

public class PrimGenerator {
	public static IGraph createRandomPrimGraph(int numVertex,int numEdges){
		IGraph graph = UndirectedWeightedGraph.createNewGraph();
		
		// add vertexes
		for(int i=0;i<numVertex;i++){
			IVertex source = new Vertex(String.valueOf(createLetter()));
			graph.addVertex(source);
		}
		
		//add Edges
		List<IVertex> vertexList = new ArrayList<IVertex>(graph.getGraph().vertexSet());
		
		for(int i=0;i < numEdges; i++){
			double weight = createRandomInt(1, 100);
			IVertex source = vertexList.get((createRandomInt(0, vertexList.size()-1)));
			IVertex target = vertexList.get((createRandomInt(0, vertexList.size()-1)));
			if(source.equals(target)){
				while(!source.equals(target)){
					source = vertexList.get((createRandomInt(0, vertexList.size()-1)));
					target = vertexList.get((createRandomInt(0, vertexList.size()-1)));
				}
			}
			
			
			WeightedEdge edge = new WeightedEdge(source, target, weight);
			graph.addEdge(source, target, edge);
			WeightedEdge edge2 = new WeightedEdge(target, source, weight);
			graph.addEdge(target, source, edge2);
		}
		
		
		return graph;
	}
	
	private static char createLetter(){
		return (char)createRandomInt(65,90);
	}
	
	private static int createRandomInt(int low, int high){
		Random r = new Random();
		int result = r.nextInt(high - low + 1) + low;
		return result;
	}
}
