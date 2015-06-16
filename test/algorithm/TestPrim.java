package algorithm;



import io.FileGraphReader;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import algorithms.Prim;
import elements.IGraph;
import elements.IVertex;
import elements.Vertex;

public class TestPrim {
	
	IGraph graph1;
	IGraph graph2;
	IGraph graph3;
	IGraph graph4;
	IGraph graph5;
    FileGraphReader reader;
	
	@Before
	public void init() throws IOException{
        String stringGraph =    "#weighted\n" +
                                "Z,L::31.0\n" +
                                "L,O::242.0\n" +
                                "O,V::167.0\n" +
                                "Z,O::240.0\n" +
                                "Z,V::191.0";
        reader = new FileGraphReader();
        graph1 = reader.loadGraphByString(stringGraph);
	}
	
	@Test
	public void positivTests(){
        
    }
}