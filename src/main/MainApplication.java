package main;

import gui.StartWindow;

import java.io.IOException;

import javax.swing.JFrame;

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
