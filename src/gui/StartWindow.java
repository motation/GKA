package gui;

import graphs.creator.GraphCreator;
import graphs.creator.PrimGenerator;
import io.FileGraphReader;
import io.FileGraphWriter;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgrapht.ext.JGraphModelAdapter;

import algorithms.Dikstra;
import algorithms.Prim;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.graph.JGraphSimpleLayout;

import elements.IGraph;
import elements.IVertex;
import elements.Vertex;

public class StartWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JGraph graphPane = null;
	private IGraph graph = null;
	private IVertex startVertex;
	
	private void initJGraphPane(){
		graphPane.getSelectionModel().addGraphSelectionListener(new GraphSelectionListener() {
			
			@Override
			public void valueChanged(GraphSelectionEvent arg0) {
				String name = arg0.getCell().toString();
				startVertex = new Vertex(name);
			}
		});
			
		
		
		
	}

	public StartWindow() throws IOException {
		super("GKA Graph Simumlation");
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Datei");
		JMenuItem open = new JMenuItem("Öffnen");
		JMenuItem randomGraph = new JMenuItem("Random Graph");
		JMenuItem primRandom = new JMenuItem("PrimGenerator");
		JMenuItem createBIG = new JMenuItem("create BIG");
		final JMenuItem save = new JMenuItem("speichern");
		final JMenu algos = new JMenu("Algorithmen");
		algos.setEnabled(false);
		
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
				fileChooser.setFileFilter(new GkaFileFilter());
				int returnVal = fileChooser.showOpenDialog(StartWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						Container contentPane = StartWindow.this
								.getContentPane();
						graph = new FileGraphReader().loadGraph(file);
						graphPane = new JGraph(new JGraphModelAdapter<>(graph
								.getGraph()));

						final JGraphSimpleLayout graphLayout = new JGraphSimpleLayout(
								JGraphSimpleLayout.TYPE_CIRCLE, 100, 100);
						final JGraphFacade graphFacade = new JGraphFacade(
								graphPane);
						graphLayout.run(graphFacade);
						final Map nestedMap = graphFacade.createNestedMap(true,
								true);
						graphPane.getGraphLayoutCache().edit(nestedMap);

						contentPane.removeAll();
						contentPane.add(graphPane);
						contentPane.revalidate();
						save.setEnabled(true);
						algos.setEnabled(true);
						initJGraphPane();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		save.setEnabled(false);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
				fileChooser.setFileFilter(new GkaFileFilter());
				int returnVal = fileChooser.showSaveDialog(StartWindow.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					String path = f.getPath();
					if (!path.endsWith(".graph")) {
						f = new File(path + ".graph");
					}
					try {
						new FileGraphWriter().saveGraph(graph, f);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		
		randomGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Container contentPane = StartWindow.this
						.getContentPane();
				
				//TODO open UI to enter amount of vertexes
				graph = GraphCreator.createUndirectedAttributedWeightedGraph(10);
				graphPane = new JGraph(new JGraphModelAdapter<>(graph
						.getGraph()));

				final JGraphSimpleLayout graphLayout = new JGraphSimpleLayout(
						JGraphSimpleLayout.TYPE_CIRCLE, 100, 100);
				final JGraphFacade graphFacade = new JGraphFacade(
						graphPane);
				graphLayout.run(graphFacade);
				final Map nestedMap = graphFacade.createNestedMap(true,
						true);
				graphPane.getGraphLayoutCache().edit(nestedMap);

				contentPane.removeAll();
				contentPane.add(graphPane);
				contentPane.revalidate();
				save.setEnabled(true);
				algos.setEnabled(true);
				initJGraphPane();
			}
		});
		
		
		primRandom.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				 String responseVertex = JOptionPane.showInputDialog(null, "Gib die Anzahl der Knoten ein");
				 if(responseVertex != null ){
					 int numVertex = Integer.parseInt(responseVertex);
					 if(numVertex > 0 ){
						 
						 // draw graph and load
						 Container contentPane = StartWindow.this
									.getContentPane();
							
							//TODO open UI to enter amount of vertexes
							graph = PrimGenerator.createPrimGraphWithoutLoop(numVertex);
							graphPane = new JGraph(new JGraphModelAdapter<>(graph
									.getGraph()));

							final JGraphSimpleLayout graphLayout = new JGraphSimpleLayout(
									JGraphSimpleLayout.TYPE_CIRCLE, 100, 100);
							final JGraphFacade graphFacade = new JGraphFacade(
									graphPane);
							graphLayout.run(graphFacade);
							final Map nestedMap = graphFacade.createNestedMap(true,
									true);
							graphPane.getGraphLayoutCache().edit(nestedMap);

							contentPane.removeAll();
							contentPane.add(graphPane);
							contentPane.revalidate();
							save.setEnabled(true);
							algos.setEnabled(true);
							initJGraphPane();
					 }
				 }
				 
			}
		});
		
		
		createBIG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Container contentPane = StartWindow.this
						.getContentPane();
				
				//TODO open UI to enter amount of vertexes --> BIT 100 vertex 6000edges
				graph = GraphCreator.createUndirectedWeightedGraph(100, 6000);
				graphPane = new JGraph(new JGraphModelAdapter<>(graph
						.getGraph()));

				final JGraphSimpleLayout graphLayout = new JGraphSimpleLayout(
						JGraphSimpleLayout.TYPE_CIRCLE, 100, 100);
				final JGraphFacade graphFacade = new JGraphFacade(
						graphPane);
				graphLayout.run(graphFacade);
				final Map nestedMap = graphFacade.createNestedMap(true,
						true);
				graphPane.getGraphLayoutCache().edit(nestedMap);

				contentPane.removeAll();
				contentPane.add(graphPane);
				contentPane.revalidate();
				save.setEnabled(true);
				algos.setEnabled(true);
			}
		});
		
		file.add(open);
		file.add(save);
		file.add(randomGraph);
		file.add(primRandom);
		file.add(createBIG);

		JMenuItem breadthFirst = new JMenuItem("Breitensuche");
		JMenuItem dijkstra = new JMenuItem("Dijkstra");
		JMenuItem aStar = new JMenuItem("A*");
		JMenuItem prim = new JMenuItem("Prim");

		algos.add(breadthFirst);
		algos.add(dijkstra);
		algos.add(aStar);
		algos.add(prim);
		menubar.add(file);
		menubar.add(algos);
		setJMenuBar(menubar);

		dijkstra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new AlgorithmWindow(new Dikstra(graph));
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(768,500);
				frame.setVisible(true);				
			}

		});
		
		prim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Container contentPane = StartWindow.this
						.getContentPane();
				
				//TODO open UI to enter amount of vertexes --> BIT 100 vertex 6000edges
				Prim prim = new Prim(graph);
				prim.init();
				prim.loop();
				System.out.println(prim.getEdgeSum());
				graph = prim.createGraph();
				graphPane = new JGraph(new JGraphModelAdapter<>(graph
						.getGraph()));

				final JGraphSimpleLayout graphLayout = new JGraphSimpleLayout(
						JGraphSimpleLayout.TYPE_CIRCLE, 100, 100);
				final JGraphFacade graphFacade = new JGraphFacade(
						graphPane);
				graphLayout.run(graphFacade);
				final Map nestedMap = graphFacade.createNestedMap(true,
						true);
				graphPane.getGraphLayoutCache().edit(nestedMap);

				contentPane.removeAll();
				contentPane.add(graphPane);
				contentPane.revalidate();
				save.setEnabled(true);
				algos.setEnabled(true);
			}

		});
		
		
	}

	private class GkaFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(".graph")
					|| f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "GKA Graphen (*.graph)";
		}

	}
}
