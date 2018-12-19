package AVLTree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.geom.*;
import java.util.Iterator;
import java.util.Random;

/**
 * Visualize your tree!
 * 
 * @author Zhihao Ni. Created Dec 30, 2011.
 */
@SuppressWarnings("javadoc")
public class VisualTreeFrame extends JFrame {
	static AVLTree<Integer> b = null;
	private Panel sp = new Panel();
	int TX = 60;
	int TY = 30;
	private Console cs = new Console();

	public VisualTreeFrame() {
		super("MasteringTree!");
		this.setSize(800, 500);// this joke never exists because of the size change
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(new ControlPanel(), BorderLayout.NORTH);
		TitledBorder border = BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(), "Display Panel");
		border.setTitleJustification(TitledBorder.LEFT);
		this.sp.setBorder(border);
		content.add(this.sp, BorderLayout.CENTER);
		content.add(this.cs, BorderLayout.EAST);
		setVisible(true);
	}

	public static void main(String[] args) {
		b = new AVLTree<Integer>();
		


		new VisualTreeFrame();
	}

	class ControlPanel extends JPanel {
		InputField In = new InputField();
		AddButton add = new AddButton();
		RemoveButton remove = new RemoveButton();
		
		@SuppressWarnings("deprecation")
		public ControlPanel() {
			TitledBorder border = BorderFactory.createTitledBorder(
					BorderFactory.createLoweredBevelBorder(), "Control Panel");
			border.setTitleJustification(TitledBorder.LEFT);
			this.setBorder(border);
			this.setLayout(new FlowLayout());
			// ///////////////slider
			JSlider TXControl = new JSlider(SwingConstants.HORIZONTAL, 50, 300,
					60);
			TXControl.addChangeListener(new SliderListener1());
			TXControl.setMajorTickSpacing(50);
			TXControl.setMinorTickSpacing(50);
			TXControl.setPaintTicks(true);
			TXControl.setPaintLabels(true);
			// //////////////////slider end
			// ///////////////slider
			JSlider TYControl = new JSlider(SwingConstants.HORIZONTAL, 30, 180,
					30);
			TYControl.addChangeListener(new SliderListener2());
			TYControl.setMajorTickSpacing(50);
			TYControl.setMinorTickSpacing(50);
			TYControl.setPaintTicks(true);
			TYControl.setPaintLabels(true);
			// //////////////////slider end
			
			this.add(TYControl);
			this.add(new JLabel("Put in a Int:"));
			this.add(this.In);
			this.add(this.add);
			this.add(this.remove);
			this.add(new RandomButton());
			this.add(TXControl);

		}

		class SliderListener1 implements ChangeListener {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					VisualTreeFrame.this.TX = source.getValue();
					VisualTreeFrame.this.sp.repaint();
				}
			}
		}
		class SliderListener2 implements ChangeListener {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					VisualTreeFrame.this.TY = source.getValue();
					VisualTreeFrame.this.sp.repaint();
				}
			}
		}

		class AddButton extends JButton {
			public AddButton() {
				super("Add"); ///MIW
				this.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("deprecation")
					@Override
					public void mousePressed(MouseEvent e) {
						try {
							int a = Integer.parseInt(ControlPanel.this.In
									.getText());
							b.insert(a); ///MIW
							VisualTreeFrame.this.sp.repaint();
							VisualTreeFrame.this.cs
									.setText("#A Node "
											+ a
											+ " is added\n Or it already in the Tree\n Press Next to Continue");
						} catch (NumberFormatException nFE) {
							VisualTreeFrame.this.cs
									.setText("Error:\n Please Put a Integer \n in the textfield. :0");
						}
					}
				});
			}
		}

		class RemoveButton extends JButton {
			public RemoveButton() {
				super("Remove"); ///MIW
				this.addMouseListener(new MouseAdapter() {
					@SuppressWarnings("deprecation")
					@Override
					public void mousePressed(MouseEvent e) {
						try {
							int a = Integer.parseInt(ControlPanel.this.In
									.getText());
							b.remove(a); ///MIW
							VisualTreeFrame.this.sp.repaint();
							VisualTreeFrame.this.cs
									.setText("#A Node "
											+ a
											+ " is added\n Or it already in the Tree\n Press Next to Continue");
						} catch (NumberFormatException nFE) {
							VisualTreeFrame.this.cs
									.setText("Error:\n Please Put a Integer \n in the textfield. :0");
						}
					}
				});
			}
		}

		
		class RandomButton extends JButton {
			public RandomButton() {
				super("Random");
				this.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						int a = new Random().nextInt(50);
						b.insert(a);
						VisualTreeFrame.this.sp.repaint();
						VisualTreeFrame.this.cs.setText("#A Random Node \n  "
								+ a + " is added");
						// ControlPanel.this.In.disable();
					}
				});
			}
		}

		class InputField extends JTextField {
			public InputField() {
				this.setColumns(3);
				this.selectAll();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	class Panel extends JPanel {

		@Override
		public void paintComponent(Graphics comp) {
			super.paintComponent(comp);
			Graphics2D comp2D = (Graphics2D) comp;
			if (b.root != null) {
				AVLTree.BinaryNode root = b.root;
				Node n = new Node(this.getWidth() / 2 - 12.5, 25,
						(Integer) root.getElement(), (Integer) root.getHeight());
				n.drawOn(comp2D);
				this.drawRec(n, root, comp2D, VisualTreeFrame.this.TX,
						VisualTreeFrame.this.TY);
			} else {// empty//
			}
		}

		public void drawRec(Node n, AVLTree.BinaryNode par,
				Graphics2D comp2D, double tx, double ty) {
			double TRANSX = tx;
			double TRANSY = ty;
			double x = n.CenterX;
			double y = n.CenterY;
			if (par.getLeftChild() != null) {
				Line2D l1 = new Line2D.Double(x+12.5, y +25, x - tx + 12.5, y
						+ ty);
				comp2D.draw(l1);
				AVLTree.BinaryNode apar = par.getLeftChild();
				n.translate(x - TRANSX, y + TRANSY, (Integer) apar.getElement(), (Integer) apar.getHeight());
				n.drawOn(comp2D);
				this.drawRec(n, apar, comp2D, tx / 2, ty);
			}
			if (par.getRightChild() != null) {
				Line2D l1 = new Line2D.Double(x + 12.5, y +25, x + tx+12.5, y
						+ ty);
				comp2D.draw(l1);
				AVLTree.BinaryNode bpar = par.getRightChild();
				n.translate(x + TRANSX, y + TRANSY, (Integer) bpar.getElement(), (Integer) bpar.getHeight());
				n.drawOn(comp2D);
				this.drawRec(n, bpar, comp2D, tx / 2, ty);
			}
		}

		class Node extends JComponent {
			private double CenterX, CenterY;
			private double radius;
			private Color color;
			private int value;
			private int height;

			public Node(double x, double y, int v, int h) {
				this.setBorder(BorderFactory.createLoweredBevelBorder());
				this.CenterX = x;
				this.CenterY = y;
				this.radius = 25;
				this.color = new Color(255, 255, 0);
				this.value = v;
				this.height = h;
			}

			public void drawOn(Graphics2D g2) {
				Ellipse2D.Double cir = new Ellipse2D.Double(this.CenterX,
						this.CenterY, this.radius + 10, this.radius);
				g2.draw(cir);
				g2.setColor(this.color);
				g2.fill(cir);
				g2.setColor(Color.BLACK);
				BasicStroke temp = new BasicStroke();
				Shape outline = temp.createStrokedShape(cir);
				g2.draw(outline);
				g2.drawString("" + this.value + " " + this.height, (float) (this.CenterX + 9.5),
						(float) (this.CenterY + 16.5));
			}

			public void translate(double x, double y, int v, int h) {
				this.CenterX = x;
				this.CenterY = y;
				this.value = v;
				this.height = h;
			}
		}
	}

	class Console extends JTextArea {
		public Console() {
			this.setEditable(false);
			TitledBorder border = BorderFactory.createTitledBorder(
					BorderFactory.createLoweredBevelBorder(), "Console");
			border.setTitleJustification(TitledBorder.LEFT);
			this.setBorder(border);
			this.setPreferredSize(new Dimension(145, 5));
			this.append("Treevisualizer Helper:\n "
					+ " The add button add the \n  integer u put in in the \n  text field, \n"
					+ "  the Random button add \n  a random  node having \n  the random value\n  from 0 to 100, \n"
					+ "  the slider controls the \n  space between nodes.\n   NOW ADD A NODE!\n\n                   @author niz;)");

		}
	}

}