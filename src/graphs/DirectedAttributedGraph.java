package graphs;

import java.io.File;
import java.util.Set;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;
import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.ListenableDirectedGraph;

import elements.IGraph;
import elements.IVertex;

public class DirectedAttributedGraph implements IGraph {
	
	DirectedGraph<IVertex, Edge> graph;
	
	private DirectedAttributedGraph(DirectedGraph<IVertex, Edge> graph) {
		// TODO Auto-generated constructor stub
		this.graph = graph;
	}
	
	public static DirectedAttributedGraph createNewGraph(DirectedGraph<IVertex, Edge> graph){
		return new DirectedAttributedGraph(graph);
	}
	
	public static DirectedAttributedGraph createNewGraph(){
		return new DirectedAttributedGraph(new ListenableDirectedGraph<IVertex, Edge>(DefaultEdge.class));
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
		return false;
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
		graph.addEdge(vertexSource, vertexTarget, edge);
	}

	@Override
	public void save(File file) {
		// TODO Auto-generated method stub
		
	}
}
