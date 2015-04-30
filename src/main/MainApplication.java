package main;

import elements.IGraph;
import elements.IVertex;
import gui.StartWindow;

import java.util.List;
import java.io.IOException;

import javax.swing.JFrame;

import org.jgraph.graph.Edge;
import org.jgrapht.GraphPath;

public class MainApplication {
	public static void main(String[] args) throws IOException {
		JFrame frame = new StartWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(768,500);
		frame.setVisible(true);
		
		
//		
//		GraphPath<IVertex, Edge> path=null;
//		
//		List<Edge> edges = path.getEdgeList();
//		
//		IGraph graph = null;
//		
//		
//		for(Edge edge : edges){
//			
//			IVertex source = (IVertex)edge.getSource();
//			IVertex target = (IVertex)edge.getTarget();
//			
//			graph.addVertex(source);
//			graph.addVertex(target);
//			graph.addEdge(source, target, edge);
//		}
	}
}
