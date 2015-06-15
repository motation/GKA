package graphs;

import elements.IGraph;
import elements.IVertex;
import elements.WeightedEdge;

import java.io.File;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;

public class DirectedAttributedWeightedGraph implements IGraph {

	ListenableDirectedWeightedGraph<IVertex, Edge> graph;
	
	private DirectedAttributedWeightedGraph(ListenableDirectedWeightedGraph<IVertex, Edge> graph) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
	}
	
	public static DirectedAttributedWeightedGraph createNewGraph(ListenableDirectedWeightedGraph<IVertex, Edge> graph){
		return new DirectedAttributedWeightedGraph(graph);
	}
	
	public static DirectedAttributedWeightedGraph createNewGraph(){
		return new DirectedAttributedWeightedGraph(new ListenableDirectedWeightedGraph<IVertex, Edge>(WeightedEdge.class));
	}
	
	@Override
	public Graph<IVertex, Edge> getGraph() {
		// TODO Auto-generated method stub
		return this.graph;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAttributed() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isWeighted() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Set<Edge> getOutgoingEdges(IVertex v) {
		// TODO Auto-generated method stub
		return graph.outgoingEdgesOf(v);
	}

	@Override
	public Set<Edge> getIncomingEdges(IVertex v) {
		// TODO Auto-generated method stub
		return graph.incomingEdgesOf(v);
	}

	@Override
	public Set<Edge> getAllEdges() {
		// TODO Auto-generated method stub
		return graph.edgeSet();
	}
	
	@Override
	public String toString() {
		return graph.toString();
	}
	
	@Override
	public void addVertex(IVertex vertex) {
		// TODO Auto-generated method stub
		graph.addVertex(vertex);
	}

	@Override
	public void addEdge(IVertex vertexSource, IVertex vertexTarget, Edge edge) {
		// TODO Auto-generated method stub
		graph.setEdgeWeight(edge, ((WeightedEdge) edge).getWeight());
		graph.addEdge(vertexSource, vertexTarget, edge);
	}
	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Edge> edgesFrom(IVertex vertex) {
		return null;
	}
}
