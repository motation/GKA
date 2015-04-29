package graphs.creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.WeightedGraphGenerator;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.UndirectedAttributedWeightedGraph;
import graphs.UndirectedWeightedGraph;

public class GraphCreator {
	
	private GraphCreator(){
		
	}
	
	public static IGraph createUndirectedWeightedGraph(int numVertexes, int numEdges){
		if(numVertexes <= -1 && numEdges <= -1){
			return null;
		}
		IGraph undirectedGraph = UndirectedWeightedGraph.createNewGraph();
		
		WeightedGraphGenerator<IVertex, Edge> randomGraph = new WeightedGraphGenerator<IVertex, Edge>() {
			
			@Override
			public void generateGraph(Graph<IVertex, Edge> arg0, VertexFactory<IVertex> arg1, Map<String, IVertex> arg2) {
				if(numVertexes==0 || numVertexes == 0) return;
				// create Vertexes and add to graph
				for(int i=1;i<=numVertexes;i++){
					IVertex vertex = arg1.createVertex();
					arg0.addVertex(vertex);
				}
				
				// add randomly edges between two vertexes or loop an edge on a single vertex
				List<IVertex> vertexesAsList = new ArrayList<>();
				vertexesAsList.addAll(arg0.vertexSet());
				for(int i=1;i<=numEdges;i++){
					IVertex source = vertexesAsList.get(randInt(0, vertexesAsList.size()-1));
					IVertex target = vertexesAsList.get(randInt(0, vertexesAsList.size()-1));
					
					if(source.getName().equals(target.getName())){
						Edge edge = new WeightedEdge(source, source, getRandomDouble());
						arg0.addEdge(source, source, edge);
					} else {
						Edge edge = new WeightedEdge(source, target, getRandomDouble());
						arg0.addEdge(source, target, edge);
					}

				}
				
			}
			
			private int randInt(int min, int max) {
			    Random rand = new Random();
			    int randomNum = rand.nextInt((max - min) + 1) + min;
			    return randomNum;
			}
			
			private Double getRandomDouble(){
				Random r = new Random();
				double min = 0;
				double max = Double.MAX_VALUE;
				double randomValue = min + (max - min) * r.nextDouble();
				return randomValue;
			}
			
		};
		
		Map<String,IVertex> map=new HashMap<String, IVertex>();
		map.put("hallo",new Vertex("Peter"));
		VertexFactory<IVertex> vertexFactory = new VertexFactory<IVertex>() {

			private Integer value=0;
			
			@Override
			public IVertex createVertex() {
				value++;
				return new Vertex(value.toString()); 
			}
			
		};
		
		randomGraph.generateGraph(undirectedGraph.getGraph(), vertexFactory, null);
		return undirectedGraph;
	}
	
	public static IGraph createUndirectedAttributedWeightedGraph(){
		IGraph graph = UndirectedAttributedWeightedGraph.createNewGraph();
		//TODO implement undirectedAttributedWeightedGraph
		return graph;
	}
}
