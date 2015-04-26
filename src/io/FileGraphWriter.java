package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import elements.IGraph;

public class FileGraphWriter {
	
	public void saveGraph(IGraph graph, File file) throws IOException{
		graph.save(file);
	}
}
