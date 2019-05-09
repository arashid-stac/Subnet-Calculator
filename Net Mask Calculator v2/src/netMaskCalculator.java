import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class netMaskCalculator {

	private JFrame frame;
	final int[] bitArray = { 128, 64, 32, 16, 8, 4, 2, 1 };
	private static int numberOfBits = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					netMaskCalculator window = new netMaskCalculator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public netMaskCalculator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnFindSubnetMask = new JButton("Find Subnet Mask");
		btnFindSubnetMask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String hostNumberInput = JOptionPane.showInputDialog("Input host number");
				int hostNumber = Integer.parseInt(hostNumberInput);

				String inputClass = JOptionPane.showInputDialog(frame, "1. Class A/n2. Class B/n3.Class C");
				int inputClassInt = Integer.parseInt(inputClass);
				if (inputClassInt == 1) {
					inputClass = "Class A";
				} else if (inputClassInt == 2) {
					inputClass = "Class B";
				} else {
					inputClass = "Class C";
				}

				int lastOctetDigit = Math.round((float) (Math.log(hostNumber + 1) / Math.log(2)));
				System.out.println(lastOctetDigit);

				int lastOctet = 0;
				for (int i = 0; i < lastOctetDigit; i++) {
					lastOctet += bitArray[i];
				}
				String subnetMask = null;
				if (inputClass == "Class A") {
					subnetMask = "255." + lastOctet + ".0.0";
				} else if (inputClass == "Class B") {
					subnetMask = "255.255." + lastOctet + ".0";
				} else {
					subnetMask = "255.255.255." + lastOctet;
				}
				JOptionPane.showMessageDialog(null, "Your subnet mask is: " + subnetMask);
			}
		});
		btnFindSubnetMask.setBounds(41, 55, 157, 29);
		frame.getContentPane().add(btnFindSubnetMask);

		JButton btnFindCidrNotation = new JButton("Find CIDR notation");
		btnFindCidrNotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String IPaddress = JOptionPane.showInputDialog("Input IP address");
				String subnetMaskFirstQuartet = JOptionPane.showInputDialog("Input subnet mask first quartet");
				int subnetMaskFirstQ = Integer.parseInt(subnetMaskFirstQuartet);

				String subnetMaskSecondQuartet = JOptionPane.showInputDialog("Input subnet mask second quartet");
				int subnetMaskSecondQ = Integer.parseInt(subnetMaskSecondQuartet);

				String subnetMaskThirdQuartet = JOptionPane.showInputDialog("Input subnet mask third quartet");
				int subnetMaskThirdQ = Integer.parseInt(subnetMaskThirdQuartet);

				String subnetMaskFourthQuartet = JOptionPane.showInputDialog("Input subnet mask fourth quartet");
				int subnetMaskFourthQ = Integer.parseInt(subnetMaskFourthQuartet);

				numberOfBits = getNumberOfBits(subnetMaskFirstQ);
				numberOfBits = getNumberOfBits(subnetMaskSecondQ);
				numberOfBits = getNumberOfBits(subnetMaskThirdQ);
				numberOfBits = getNumberOfBits(subnetMaskFourthQ);
				
				JOptionPane.showMessageDialog(null, "Here is your CIDR notation: " + IPaddress + "/" + numberOfBits);
				numberOfBits = 0;
			}
		});
		btnFindCidrNotation.setBounds(209, 55, 167, 29);
		frame.getContentPane().add(btnFindCidrNotation);

		JButton btnFindNumberOf = new JButton("Find number of hosts");
		btnFindNumberOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CIDRNumInput = JOptionPane.showInputDialog("Input CIDR number", "Netmask");
				int CIDRNum = 32 - Integer.parseInt(CIDRNumInput);
				int numberOfHosts = (int) ((Math.pow(2, CIDRNum)) - 2);

				JOptionPane.showMessageDialog(null, "Here is your number of hosts: " + numberOfHosts);
			}
		});
		btnFindNumberOf.setBounds(15, 100, 183, 29);
		frame.getContentPane().add(btnFindNumberOf);

		JButton btnFindNumberOf_1 = new JButton("Find number of subnets");
		btnFindNumberOf_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lastOctetInput = JOptionPane.showInputDialog("Input your subnet mask's last octet");
				int lastOctet = Integer.parseInt(lastOctetInput);

				numberOfBits = getNumberOfBits(lastOctet);
				int numberOfSubnets = (int) Math.pow(2, numberOfBits);
				JOptionPane.showMessageDialog(null, "Number of subnet masks: " + numberOfSubnets);
				numberOfBits = 0;
			}
		});
		btnFindNumberOf_1.setBounds(209, 100, 204, 29);
		frame.getContentPane().add(btnFindNumberOf_1);
	}
	
	private int getNumberOfBits(int quartet) {
		for (int i = 0; i < bitArray.length; i++) {
			if (quartet - bitArray[i] >= 0) {
				numberOfBits += 1;
				quartet -= bitArray[i];
			}
		}
		return numberOfBits;
	}
}
