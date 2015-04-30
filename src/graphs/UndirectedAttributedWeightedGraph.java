package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import elements.IAttributedVertex;
import elements.IGraph;
import elements.IVertex;
import elements.WeightedEdge;

public class UndirectedAttributedWeightedGraph implements IGraph {
	WeightedPseudograph<IVertex, Edge> graph;
	
	
	private UndirectedAttributedWeightedGraph(WeightedPseudograph<IVertex, Edge> graph){
		this.graph = graph;
	}
	
	public static UndirectedAttributedWeightedGraph createNewGraph(WeightedPseudograph<IVertex, Edge> graph){
		return new UndirectedAttributedWeightedGraph(graph);
	}
	
	public static UndirectedAttributedWeightedGraph createNewGraph(){
		return new UndirectedAttributedWeightedGraph(new WeightedPseudograph<IVertex, Edge>(WeightedEdge.class));
	}
	
	@Override
	public Graph<IVertex, Edge> getGraph() {
		// TODO Auto-generated method stub
		return this.graph;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
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
		String firstLine = "#weighted #attributed"+System.lineSeparator();
//		byte[] buffer = new byte[1024];
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(firstLine);
			writer.flush();
			
			
			for(Edge edge : getGraph().edgeSet()){
				WeightedEdge wEdge = (WeightedEdge) edge;
				IAttributedVertex source = (IAttributedVertex) wEdge.getSource();
				IAttributedVertex target = (IAttributedVertex) wEdge.getTarget();
				String line = source.getName()+":"+source.getAttribute()+",";
				line += target.getName()+":"+target.getAttribute()+"::"+wEdge.getWeight()+System.lineSeparator();
				writer.write(line);
				writer.flush();
			}
			writer.close();
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
