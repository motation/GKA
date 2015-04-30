package algorithms;

import org.jgrapht.GraphPath;

import elements.IGraph;
import elements.IVertex;


public interface IAlgorithm {
	public void setStartVertx(IVertex startVertx);
	public void setEndVertex(IVertex endVertex);
	public void calculate();
	public GraphPath getShortestPath();
	public IGraph getGraph();
}
