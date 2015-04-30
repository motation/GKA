package graphs.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.WeightedGraphGenerator;

import elements.AttributedVertex;
import elements.IAttributedVertex;
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
		
		VertexFactory<IVertex> vertexFactory = new VertexFactory<IVertex>() {

			private int value=0;
			
			@Override
			public IVertex createVertex() {
				value++;
				return new Vertex(Integer.toString(value)); 
			}
			
		};
		
		randomGraph.generateGraph(undirectedGraph.getGraph(), vertexFactory, null);
		return undirectedGraph;
	}
	
	public static IGraph createUndirectedAttributedWeightedGraph(int numVertexes){
		IGraph graph = UndirectedAttributedWeightedGraph.createNewGraph();
		//TODO implement undirectedAttributedWeightedGraph
		
		WeightedGraphGenerator<IVertex, Edge> generator = new WeightedGraphGenerator<IVertex, Edge>() {
			
			@Override
			public void generateGraph(Graph<IVertex, Edge> arg0, VertexFactory<IVertex> arg1, Map<String, IVertex> arg2) {
				if( numVertexes==0 ) return;
				// create Vertexes and add to graph
				for(int i=1;i<=numVertexes;i++){
					IVertex vertex = arg1.createVertex();
					arg0.addVertex(vertex);
				}
				
				List<IVertex> vertexesAsList = new ArrayList<IVertex>(arg0.vertexSet());
				
				int numEdges = calcNumEdges(numVertexes);
				
				while(arg0.edgeSet().size() <= numEdges ){
					
					IVertex source = vertexesAsList.get(randInt(0, vertexesAsList.size()-1));
					IVertex target = vertexesAsList.get(randInt(0, vertexesAsList.size()-1));
					
					if(!(source.getName().equals(target.getName()))){
						int start = Integer.parseInt(((IAttributedVertex) source).getAttribute());
						int end = Integer.parseInt(((IAttributedVertex) target).getAttribute());
						Edge edge = new WeightedEdge(source, target, calcWeight(start,end));
						if(arg0.getEdge(source, target)==null && arg0.getEdge(target, source) == null){
							arg0.addEdge(source, target, edge);
						} 
						
					}
				}
				
			}
			
			private int randInt(int min, int max) {
			    Random rand = new Random();
			    int randomNum = rand.nextInt((max - min) + 1) + min;
			    return randomNum;
			}
			
			private double calcWeight(int start, int end){
				double value = 0;
				
				value = randInt(start+end+10, start+end+25);
				
				return value;
			}
			
			private int calcNumEdges(int numVertexes){
				if(numVertexes==1) return 0;
				if(numVertexes == 2) return 1;
				if(numVertexes == 3) return 3;
				if(numVertexes == 4) return 4;
				return numVertexes + 2;
			}
		};
		
		VertexFactory<IVertex> vertexFactory = new VertexFactory<IVertex>() {

			private int value=0;
			private int attribute=0;
			
			@Override
			public IVertex createVertex() {
				value++;
				IVertex vertex = new AttributedVertex(Integer.toString(value), Integer.toString(attribute));
				attribute = randInt(attribute+50,(attribute+100));
				return vertex;
			}
			
			private int randInt(int min, int max) {
			    Random rand = new Random();
			    int randomNum = rand.nextInt((max - min) + 1) + min;
			    return randomNum;
			}
			
		};
		generator.generateGraph(graph.getGraph(), vertexFactory, null);
		return graph;
	}
}
