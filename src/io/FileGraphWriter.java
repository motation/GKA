package io;

import java.io.File;
import java.io.IOException;

import elements.IGraph;

public class FileGraphWriter {
	
	public void saveGraph(IGraph graph, File file) throws IOException{
		graph.save(file);
	}
}
