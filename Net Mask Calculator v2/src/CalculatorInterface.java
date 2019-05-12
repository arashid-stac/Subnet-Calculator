import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CalculatorInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	public CalculatorInterface() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Calculations calcs = new Calculations();

		JButton btnFindSubnetMask = new JButton("Find Subnet Mask");
		btnFindSubnetMask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcs.getSubnetMask();				
			}
		});
		btnFindSubnetMask.setBounds(41, 55, 157, 29);
		frame.getContentPane().add(btnFindSubnetMask);

		JButton btnFindCidrNotation = new JButton("Find CIDR notation");
		btnFindCidrNotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calcs.getCIDRNotation();
			}
		});
		btnFindCidrNotation.setBounds(209, 55, 167, 29);
		frame.getContentPane().add(btnFindCidrNotation);

		JButton btnFindNumberOf = new JButton("Find number of hosts");
		btnFindNumberOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcs.getNumberOfHosts();
			}
		});
		btnFindNumberOf.setBounds(15, 100, 183, 29);
		frame.getContentPane().add(btnFindNumberOf);

		JButton btnFindNumberOf_1 = new JButton("Find number of subnets");
		btnFindNumberOf_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcs.getNumberOfSubnets();
			}
		});
		btnFindNumberOf_1.setBounds(209, 100, 204, 29);
		frame.getContentPane().add(btnFindNumberOf_1);
	}

	public void initialize() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorInterface window = new CalculatorInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
