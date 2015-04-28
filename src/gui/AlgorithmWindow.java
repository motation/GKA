package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algorithms.IAlgorithm;
import elements.IGraph;
import elements.IVertex;

public class AlgorithmWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private IGraph graph = null;
	private JComboBox<IVertex> startVertex;
	private JComboBox<IVertex> endVertex;
	//TODO maybe refactor
	private IAlgorithm algorithm;
	private JLabel shortestPathText;
	private JLabel shortestPathResult;

	public AlgorithmWindow(IGraph graph, IAlgorithm algorithm) {
		super();
		this.graph = graph;
		
		this.algorithm = algorithm;
		
		this.initLayout();
		this.initStartAndEndVertex();
		this.initButton();
		this.initLabel();

		this.pack();
	}

	private void initLabel() {
		JPanel panel = new JPanel();
		shortestPathText = new JLabel();
		shortestPathText.setText("Kürzeste Weg");
		shortestPathResult = new JLabel();
		shortestPathResult.setText("");
		panel.add(shortestPathText);
		panel.add(shortestPathResult);
		this.add(panel);
	}

	private void initButton() {
		JButton calculateShortestPath = new JButton();
		calculateShortestPath.setText("Berechne kürzesten Pfad");
		calculateShortestPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IVertex selectedStartVertex = startVertex.getItemAt(startVertex.getSelectedIndex());
				IVertex selectedEndVertex = endVertex.getItemAt(endVertex.getSelectedIndex());
				//TODO algo set algorithm
				//TODO start calculation
			}
		});
		this.add(calculateShortestPath);
	}

	private void initLayout() {
		GridLayout experimentLayout = new GridLayout(3, 2);
		this.setLayout(experimentLayout);
	}

	private void initStartAndEndVertex() {
		startVertex = new JComboBox<IVertex>();

		for (IVertex src : graph.getGraph().vertexSet()) {
			startVertex.addItem(src);
		}

		this.startVertex.setSize(50, 35);
		this.add(startVertex);

		endVertex = new JComboBox<IVertex>();

		for (IVertex dst : graph.getGraph().vertexSet()) {
			endVertex.addItem(dst);
		}
		this.add(endVertex);
	}

}
