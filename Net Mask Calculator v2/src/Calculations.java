import javax.swing.JOptionPane;

public class Calculations {
	final int[] bitArray = { 128, 64, 32, 16, 8, 4, 2, 1 };
	private static int numberOfBits = 0;
	
	public void getSubnetMask() {
		String hostNumberInput = JOptionPane.showInputDialog("Input host number");
		int hostNumber = Integer.parseInt(hostNumberInput);

		String inputClass = JOptionPane.showInputDialog(null, "1. Class A/n2. Class B/n3.Class C");
		int inputClassInt = Integer.parseInt(inputClass);
		if (inputClassInt == 1) {
			inputClass = "Class A";
		} else if (inputClassInt == 2) {
			inputClass = "Class B";
		} else {
			inputClass = "Class C";
		}

		int lastOctetDigit = Math.round((float) (Math.log(hostNumber + 1) / Math.log(2)));

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

	public void getCIDRNotation() {
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
	
	public void getNumberOfSubnets() {
		String lastOctetInput = JOptionPane.showInputDialog("Input your subnet mask's last octet");
		int lastOctet = Integer.parseInt(lastOctetInput);

		numberOfBits = getNumberOfBits(lastOctet);
		int numberOfSubnets = (int) Math.pow(2, numberOfBits);
		JOptionPane.showMessageDialog(null, "Number of subnet masks: " + numberOfSubnets);
		numberOfBits = 0;
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
