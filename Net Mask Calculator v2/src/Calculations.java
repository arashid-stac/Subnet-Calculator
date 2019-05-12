import javax.swing.JOptionPane;

public class Calculations {
	final int[] bitArray = { 128, 64, 32, 16, 8, 4, 2, 1 };
	private static int numberOfBits = 0;
	boolean isValid;

	public void getSubnetMask() {
		String hostNumberInput = null;
		int hostNumber = 0;
		String inputClass = null;
		int inputClassInt = 0;

		do {
			isValid = true;
			try {
				hostNumberInput = JOptionPane.showInputDialog("Input host number");
				if (hostNumberInput == null) {
					return;
				}
				hostNumber = Integer.parseInt(hostNumberInput);

				inputClass = JOptionPane.showInputDialog("Input the number that corresponds to your "
						+ "desired class type.\n" + "1. Class A\n" + "2. Class B\n" + "3. Class C");
				if (inputClass == null) {
					return;
				}
				inputClassInt = Integer.parseInt(inputClass);

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
				isValid = false;
			}
		} while (isValid == false);

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
		String IPaddress = null;
		String subnetMaskFirstQuartet = null;
		int subnetMaskFirstQ = 0;
		String subnetMaskSecondQuartet = null;
		int subnetMaskSecondQ = 0;
		String subnetMaskThirdQuartet = null;
		int subnetMaskThirdQ = 0;
		String subnetMaskFourthQuartet = null;
		int subnetMaskFourthQ = 0;
		do {
			isValid = true;
			try {
				IPaddress = JOptionPane.showInputDialog("Input IP address");
				if (IPaddress == null) {
					return;
				}
				subnetMaskFirstQuartet = JOptionPane.showInputDialog("Input subnet mask first quartet");
				if (subnetMaskFirstQuartet == null) {
					return;
				}
				subnetMaskFirstQ = Integer.parseInt(subnetMaskFirstQuartet);

				subnetMaskSecondQuartet = JOptionPane.showInputDialog("Input subnet mask second quartet");
				if (subnetMaskSecondQuartet == null) {
					return;
				}
				subnetMaskSecondQ = Integer.parseInt(subnetMaskSecondQuartet);

				subnetMaskThirdQuartet = JOptionPane.showInputDialog("Input subnet mask third quartet");
				if (subnetMaskThirdQuartet == null) {
					return;
				}
				subnetMaskThirdQ = Integer.parseInt(subnetMaskThirdQuartet);

				subnetMaskFourthQuartet = JOptionPane.showInputDialog("Input subnet mask fourth quartet");
				if (subnetMaskFourthQuartet == null) {
					return;
				}
				subnetMaskFourthQ = Integer.parseInt(subnetMaskFourthQuartet);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
				isValid = false;
			}
		} while (isValid == false);

		numberOfBits = getNumberOfBits(subnetMaskFirstQ);
		numberOfBits = getNumberOfBits(subnetMaskSecondQ);
		numberOfBits = getNumberOfBits(subnetMaskThirdQ);
		numberOfBits = getNumberOfBits(subnetMaskFourthQ);

		JOptionPane.showMessageDialog(null, "Here is your CIDR notation: " + IPaddress + "/" + numberOfBits);
		numberOfBits = 0;
	}

	public void getNumberOfHosts() {
		String CIDRNumInput = null;
		int CIDRNum = 0;
		int numberOfHosts = 0;
		do {
			isValid = true;
			try {
				CIDRNumInput = JOptionPane.showInputDialog("Input CIDR number", "Netmask");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
				isValid = false;
			}
		} while (isValid == false);
		
		CIDRNum = 32 - Integer.parseInt(CIDRNumInput);
		numberOfHosts = (int) ((Math.pow(2, CIDRNum)) - 2);

		JOptionPane.showMessageDialog(null, "Here is your number of hosts: " + numberOfHosts);
	}

	public void getNumberOfSubnets() {
		String lastOctetInput = null;
		int lastOctet = 0;
		do {
			isValid = true;
			try {
				lastOctetInput = JOptionPane.showInputDialog("Input your subnet mask's last octet");
				if (lastOctetInput == null) {
					return;
				}
				lastOctet = Integer.parseInt(lastOctetInput);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
				isValid = false;
			}
		} while (isValid == false);

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
