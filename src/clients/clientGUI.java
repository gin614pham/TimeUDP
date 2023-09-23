package clients;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class clientGUI {

	JFrame frame;
	public JTextField textFTime;
	public JButton btnStop;

	/**
	 * Create the application.
	 */
	public clientGUI() {
		initialize();
	}

	/**
	 * Initializes the frame, text field, and button for the Java function.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 172);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		textFTime = new JTextField();
		textFTime.setBounds(10, 10, 416, 56);
		frame.getContentPane().add(textFTime);
		textFTime.setColumns(10);

		btnStop = new JButton("Stop");
		btnStop.setBounds(110, 76, 200, 40);
		btnStop.setActionCommand("Stop");
		frame.getContentPane().add(btnStop);

	}

}
