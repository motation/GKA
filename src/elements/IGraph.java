package elements;

import java.io.File;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;

public interface IGraph {
	Graph<IVertex, Edge> getGraph();
	boolean isDirected();
	boolean isAttributed();
	boolean isWeighted();
	Set<Edge> getOutgoingEdges(IVertex v);
	Set<Edge> getIncomingEdges(IVertex v);
	Set<Edge> getAllEdges();
	void addVertex(IVertex vertex);
	void addEdge(IVertex vertexSource, IVertex vertexTarget, Edge edge);
	void save(File file);
	Set<Edge> edgesFrom(IVertex vertex);
}
