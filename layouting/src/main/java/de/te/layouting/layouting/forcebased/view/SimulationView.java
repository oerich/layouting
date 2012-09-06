package de.te.layouting.layouting.forcebased.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.layouting.forcebased.ForceBasedLayouter;
import de.te.layouting.layouting.forcebased.forces.BorderRepulsionForce;
import de.te.layouting.layouting.forcebased.forces.CoulumbNodeRepulsionForce;
import de.te.layouting.layouting.forcebased.forces.HookeConnectionForce;
import de.te.layouting.util.DefaultGraphProvider;
import de.te.layouting.util.IConnection;
import de.te.layouting.util.INode;
import de.te.layouting.util.RandomGraph;

public class SimulationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private ForceBasedLayoutVisualizer canvas;
	private ForceBasedLayouter<RandomGraph, IConnection<String>, INode> layout;
	private DefaultGraphProvider graphProvider;

	private TimerTask timer = new TimerTask() {

		@Override
		public void run() {
			if (play) {
				layout.computeForces();
				layout.setPositions();
				updateView();
			}
		}

	};

	private boolean play = false;

	private JButton playButton;
	private JProgressBar energyBar;
	private JLabel energyLabel;
	private JSlider nodesSlider;
	private JSlider edgesSlider;
	private JSlider dampingSlider;
	private JSlider distanceSlider;
	private RandomGraph graph;
	private Bounds bounds;

	public SimulationView() {
		setLayout(new BorderLayout());

		Dimension d = new Dimension(1000, 500);
		this.bounds = new Bounds(0, 0, d.width, d.height);

		this.layout = new ForceBasedLayouter<RandomGraph, IConnection<String>, INode>();
		this.layout.addForce(new HookeConnectionForce());
		this.layout.addForce(new CoulumbNodeRepulsionForce());
		this.layout.addForce(new BorderRepulsionForce(d, 50));

		this.canvas = new ForceBasedLayoutVisualizer(this.layout);
		this.canvas.addMouseListener(new MouseTool());
		add(this.canvas, BorderLayout.CENTER);

		this.graphProvider = new DefaultGraphProvider();
		this.graph = new RandomGraph();

		this.canvas.setPreferredSize(d);

		JPanel buttonPane = new JPanel();
		add(buttonPane, BorderLayout.NORTH);
		JButton computeButton = new JButton("Compute Step");
		computeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.computeForces();
				updateView();
			}
		});

		JButton setButton = new JButton("Set");
		setButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.setPositions();
				updateView();
			}
		});

		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				play = !play;

				if (play)
					playButton.setText("Stop");
				else
					playButton.setText("Play");

			}
		});

		JButton layoutButton = new JButton("Layout");
		layoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layout.layout(Integer.MAX_VALUE);
				updateView();
			}
		});

		Timer t = new Timer();
		t.schedule(this.timer, 50, 100);

		buttonPane.add(computeButton);
		buttonPane.add(setButton);
		buttonPane.add(playButton);
		buttonPane.add(layoutButton);
		energyLabel = new JLabel();
		add(energyLabel, BorderLayout.SOUTH);

		energyBar = new JProgressBar(JProgressBar.VERTICAL);
		energyBar.setForeground(Color.RED);
		add(energyBar, BorderLayout.EAST);
		energyBar.addChangeListener(new ChangeListener() {

			int lastVal = 0;

			@Override
			public void stateChanged(ChangeEvent e) {
				JProgressBar b = (JProgressBar) e.getSource();
				int value = b.getValue();
				if (value > lastVal)
					b.setBackground(Color.DARK_GRAY);
				else
					b.setBackground(Color.LIGHT_GRAY);
				lastVal = value;
				if (value > 50)
					b.setForeground(Color.RED);
				else if (value > 10)
					b.setForeground(Color.YELLOW);
				else
					b.setForeground(Color.GREEN);
			}
		});

		JPanel settingsPanel = new JPanel(new BorderLayout());
		add(settingsPanel, BorderLayout.WEST);
		JPanel sliderPanel = new JPanel();
		settingsPanel.add(sliderPanel, BorderLayout.CENTER);
		distanceSlider = new JSlider(JSlider.VERTICAL, 20, 250, 200);
		sliderPanel.add(distanceSlider);
		distanceSlider.setToolTipText("Preferred Distance: 200");
		dampingSlider = new JSlider(JSlider.VERTICAL, 1, 100, 40);
		sliderPanel.add(dampingSlider);
		dampingSlider.setToolTipText("Damping: 0.4");

		edgesSlider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
		sliderPanel.add(edgesSlider);
		edgesSlider.setToolTipText("Edges: 0.5");
		nodesSlider = new JSlider(JSlider.VERTICAL, 1, 30, 10);
		sliderPanel.add(nodesSlider);
		nodesSlider.setToolTipText("Nodes: 40");

		ChangeListener sliderListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider s = (JSlider) e.getSource();
				String text = s.getToolTipText();
				text = text.substring(0, text.indexOf(':') + 2) + s.getValue();
				s.setToolTipText(text);
			}
		};

		dampingSlider.addChangeListener(sliderListener);
		distanceSlider.addChangeListener(sliderListener);
		edgesSlider.addChangeListener(sliderListener);
		nodesSlider.addChangeListener(sliderListener);

		JButton createButton = new JButton("Create");
		settingsPanel.add(createButton, BorderLayout.SOUTH);
		createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createRandomScene();
				updateView();
			}
		});

		createRandomScene();

	}

	private void createRandomScene() {
		this.layout.setDamping(this.dampingSlider.getValue() / 100.0);

		this.graph
				.setConnectionProbability(this.edgesSlider.getValue() / 100.0);
		this.graph.setSize(bounds);
		this.graph.setNodeCount(this.nodesSlider.getValue());

		this.layout.initialize(this.graph, this.graphProvider, this.bounds,
				this.distanceSlider.getValue());
	}

	private void updateView() {
		canvas.repaint();
		energyLabel
				.setText(Double.toString(this.layout.getTotalCineticEnergy()));
		energyBar
				.setValue((int) (Math.log(this.layout.getTotalCineticEnergy()) * 10));
	}

	public static void main(String[] args) {
		SimulationView v = new SimulationView();
		v.setVisible(true);
		v.pack();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
