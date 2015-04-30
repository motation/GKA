package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.Edge;

import elements.AttributedVertex;
import elements.IAttributedVertex;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;
import elements.WeightedEdge;
import graphs.DirectedAttributedGraph;
import graphs.DirectedAttributedWeightedGraph;
import graphs.DirectedGraph;
import graphs.DirectedWeightedGraph;
import graphs.UndirectedAttributedGraph;
import graphs.UndirectedAttributedWeightedGraph;
import graphs.UndirectedGraph;
import graphs.UndirectedWeightedGraph;

public class FileGraphReader {

	// CONFIG
	public static final String CONFIG_SIGN = "#";
	public static final String DIRECTED = "#directed";
	public static final String ATTRIBUTED = "#attributed";
	public static final String WEIGHTED = "#weighted";

	// PATTERN
	public static final String PATTERN_ATTRIBUTED = "^[a-zA-Z0-9]+:([a-zA-Z0-9]+),[a-zA-Z0-9]+:([a-zA-Z0-9]+).*?$|^[a-zA-Z0-9]+:([a-zA-Z0-9]+).*?$";

	public static final String PATTERN_WEIGHTED = "^.*?::([a-zA-Z0-9]+\\.?[a-zA-Z0-9]*)$";

	public static final String PATTERN_GRAPH = "^([a-zA-Z0-9]+).*?,([a-zA-Z0-9]+)[:]{0,2}.*?$|^([a-zA-Z0-9]+)[:]{0,2}.*?$";

	public IGraph loadGraph(String path) throws IOException {
		return loadGraph(new File(path));
	}

	public IGraph loadGraph(File file) throws IOException {
		IGraph graph = buildGraph(file);
		return graph;
	}

	private IGraph buildGraph(File file) throws IOException {
		IGraph graph = null;

		BufferedReader reader = new BufferedReader(new FileReader(file));

		boolean isDirected = false;
		boolean isAttributed = false;
		boolean isWeighted = false;

		// figure out which config to use

		String line = reader.readLine();
		if (line.contains(CONFIG_SIGN)) {
			if (line.contains(DIRECTED)) {
				isDirected = true;
			}
			if (line.contains(ATTRIBUTED)) {
				isAttributed = true;
			}
			if (line.contains(WEIGHTED)) {
				isWeighted = true;
			}
		} else {
			reader.close();
			reader = new BufferedReader(new FileReader(file));
		}

		if (isDirected) {
			if (isAttributed && isWeighted) {
				graph = buildDirectedAttributedWeightedGraph(reader);
			} else if (isAttributed && !isWeighted) {
				graph = buildDirectedAttributedGraph(reader);
			} else if (!isAttributed && isWeighted) {
				graph = buildDirectedWeightedGraph(reader);
			} else {
				graph = buildDirectedGraph(reader);
			}
		} else {
			if (isAttributed && isWeighted) {
				graph = buildUndirectedAttributedWeightedGraph(reader);
			} else if (isAttributed && !isWeighted) {
				graph = buildUndirectedAttributedGraph(reader);
			} else if (!isAttributed && isWeighted) {
				graph = buildUndirectedWeightedGraph(reader);
			} else {
				graph = buildUndirectedGraph(reader);
			}
		}
		reader.close();
		return graph;
	}

	private IGraph buildDirectedGraph(BufferedReader reader) throws IOException {
		IGraph graph = null;
		graph = DirectedGraph.createNewGraph();
		String line = reader.readLine();
		while (line != null) {
			if (!line.isEmpty()) {
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH)){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IVertex source = new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"));
						IVertex target = new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						Edge edge = new DefaultEdge();
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new Vertex(singleVertex));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private IGraph buildDirectedAttributedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = DirectedAttributedGraph.createNewGraph();

		String line = reader.readLine(); 
		
		while (line != null) {
			if(!line.isEmpty()){
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH) && lineWithoutWhitespaces.matches(PATTERN_ATTRIBUTED)){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IAttributedVertex source = new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$1"));
						IAttributedVertex target =  new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						Edge edge = new DefaultEdge();
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new AttributedVertex(singleVertex,lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$3")));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private IGraph buildDirectedAttributedWeightedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = null;
		graph = DirectedAttributedWeightedGraph.createNewGraph();

		String line = reader.readLine();

		while (line != null) {
			if(!line.isEmpty()){
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH) && lineWithoutWhitespaces.matches(PATTERN_ATTRIBUTED)
						&& lineWithoutWhitespaces.matches(PATTERN_WEIGHTED)
						){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IAttributedVertex source = new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$1"));
						IAttributedVertex target =  new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						double weight = Double.parseDouble(lineWithoutWhitespaces.replaceAll(PATTERN_WEIGHTED, "$1"));
						Edge edge = new WeightedEdge(source, target, weight );
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new AttributedVertex(singleVertex,lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$3")));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private IGraph buildDirectedWeightedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = DirectedWeightedGraph.createNewGraph();

		String line = reader.readLine();
		
		
		while (line != null) {
			if(!line.isEmpty()){
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH) && lineWithoutWhitespaces.matches(PATTERN_WEIGHTED)
						){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IVertex source = new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"));
						IVertex target =  new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						double weight = Double.parseDouble(lineWithoutWhitespaces.replaceAll(PATTERN_WEIGHTED, "$1"));
						Edge edge = new WeightedEdge(source, target, weight );
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new Vertex(singleVertex));
					}
				}
			}
			line = reader.readLine();
		}
		
		
		getMyClass(graph);
		return graph;
	}

	private IGraph buildUndirectedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = UndirectedGraph.createNewGraph();
		String line = reader.readLine();

		while (line != null) {
			if (!line.isEmpty()) {
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH)){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IVertex source = new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"));
						IVertex target = new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						Edge edge = new DefaultEdge();
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new Vertex(singleVertex));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private IGraph buildUndirectedAttributedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = UndirectedAttributedGraph.createNewGraph();
		String line = reader.readLine();
		while (line != null) {
			if(!line.isEmpty()){
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH) && lineWithoutWhitespaces.matches(PATTERN_ATTRIBUTED)){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IAttributedVertex source = new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$1"));
						IAttributedVertex target =  new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						Edge edge = new DefaultEdge();
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new AttributedVertex(singleVertex,lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$3")));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private IGraph buildUndirectedAttributedWeightedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = UndirectedAttributedWeightedGraph.createNewGraph();

		String line = reader.readLine();

		while (line != null) {
			if(!line.isEmpty()){
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH) && lineWithoutWhitespaces.matches(PATTERN_ATTRIBUTED)
						&& lineWithoutWhitespaces.matches(PATTERN_WEIGHTED)
						){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IAttributedVertex source = new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$1"));
						IAttributedVertex target =  new AttributedVertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"), lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						double weight = Double.parseDouble(lineWithoutWhitespaces.replaceAll(PATTERN_WEIGHTED, "$1"));
						Edge edge = new WeightedEdge(source, target, weight );
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new AttributedVertex(singleVertex,lineWithoutWhitespaces.replaceAll(PATTERN_ATTRIBUTED, "$3")));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private IGraph buildUndirectedWeightedGraph(BufferedReader reader)
			throws IOException {
		IGraph graph = UndirectedWeightedGraph.createNewGraph();		
		String line = reader.readLine();
		
		while (line != null) {
			if(!line.isEmpty()){
				String lineWithoutWhitespaces = line.replace(" ", "");
				if(lineWithoutWhitespaces.matches(PATTERN_GRAPH) && lineWithoutWhitespaces.matches(PATTERN_WEIGHTED)
						){
					String singleVertex = lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$3");
					if(singleVertex.isEmpty()){
						IVertex source = new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$1"));
						IVertex target =  new Vertex(lineWithoutWhitespaces.replaceAll(PATTERN_GRAPH, "$2"));
						graph.addVertex(source);
						graph.addVertex(target);
						double weight = Double.parseDouble(lineWithoutWhitespaces.replaceAll(PATTERN_WEIGHTED, "$1"));
						Edge edge = new WeightedEdge(source, target, weight );
						edge.setSource(source);
						edge.setTarget(target);
						graph.addEdge(source, target, edge);
					} else {
						graph.addVertex(new Vertex(singleVertex));
					}
				}
			}
			line = reader.readLine();
		}
		getMyClass(graph);
		return graph;
	}

	private void getMyClass(IGraph graph) {
		Class<?> enclosingClass = graph.getClass().getEnclosingClass();
		if (enclosingClass != null) {
			System.out.println(enclosingClass.getName());
		} else {
			System.out.println(graph.getClass().getName());
		}
	}

}
