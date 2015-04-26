package gui;

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
import javax.swing.filechooser.FileFilter;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.graph.JGraphSimpleLayout;

import elements.IGraph;

public class StartWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JGraph graphPane = null;
	private IGraph graph = null;

	public StartWindow() throws IOException {
		super("GKA Graph Simumlation");
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Datei");
		JMenuItem open = new JMenuItem("Öffnen");
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
						graphPane = new JGraph(new JGraphModelAdapter<>(graph.getGraph()));

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
		file.add(open);
		file.add(save);

		JMenuItem breadthFirst = new JMenuItem("Breitensuche");
		// breadthFirst.addActionListener(new SearchFrameAction(new
		// BreadthFirst()));
		// algos.add(breadthFirst);
		menubar.add(file);
		menubar.add(algos);
		setJMenuBar(menubar);
	}

	private class GkaFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(".graph")
					|| f.isDirectory();
		}

		@Override
		public String getDescription() {
			return "GKA Graphen (*.gka)";
		}

	}
}
