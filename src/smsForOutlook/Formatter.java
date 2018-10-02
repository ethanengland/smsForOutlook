package smsForOutlook;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Our formatter class takes phone number(s) and formats it/them with the proper domain name for
 * the different cell phone providers.
 *
 * Providers include:
 * Verizon
 * ATT
 * Tmobile
 * Sprint
 * US Cellular
 *
 */
public class Formatter {

	private ArrayList<String> phoneNumbers;

    /**
     * Constructor
     */
	Formatter() {
		this.phoneNumbers = new ArrayList<String>();
	}


	public void addPhoneNumber(String phoneNumber) throws IllegalArgumentException {

		String formattedNumber = "";

		for (int i = 0; i < phoneNumber.length(); i++) {
			if (Character.isDigit(phoneNumber.charAt(i))) {
				formattedNumber = formattedNumber + phoneNumber.charAt(i);
			}
		}
		// for this program we dont want the leading 1
		if (formattedNumber.charAt(0) == 1) {
			formattedNumber = formattedNumber.substring(1);
		}
		// we dont want any numbers greater than the standard size
		if (formattedNumber.length() > 10) {
			throw new IllegalArgumentException();
		}

		this.phoneNumbers.add(formattedNumber); // add number to our list.

	}

	/**
	 * Prints a list of converted phone numbers to the console
	 */
	public void printToConsole() {
		String phoneNumbers = "";

		for (String phoneNumber : this.phoneNumbers) {
			phoneNumbers = phoneNumbers + toATT(phoneNumber) + "\n" + toVerizon(phoneNumber) + "\n"
					+ toTMobile(phoneNumber) + "\n" + toSprint(phoneNumber) + "\n" + toUSCellular(phoneNumber) + "\n\n";
		}
		System.out.println(phoneNumbers);
	}

	/**
	 * This method returns all of the converted phone numbers as one large string.
	 */
	public String getStringOfFormattedPhoneNumbers() {
		String phoneNumbers = "";

		for (String phoneNumber : this.phoneNumbers) {
			phoneNumbers = phoneNumbers + toATT(phoneNumber) + "\n" + toVerizon(phoneNumber) + "\n"
					+ toTMobile(phoneNumber) + "\n" + toSprint(phoneNumber) + "\n" + toUSCellular(phoneNumber) + "\n\n";
		}
		return phoneNumbers;

	}

    /**
     * Returns a lit of any numbers added to this Formatter.
     */
	public ArrayList<String> getListOfFormattedPhoneNumbers() {
		ArrayList<String> convertedPhoneNumbers = new ArrayList<>();

		for (String phoneNumber : this.phoneNumbers) {
			convertedPhoneNumbers.add(toATT(phoneNumber));
			convertedPhoneNumbers.add(toVerizon(phoneNumber));
			convertedPhoneNumbers.add(toTMobile(phoneNumber));
			convertedPhoneNumbers.add(toSprint(phoneNumber));
			convertedPhoneNumbers.add(toUSCellular(phoneNumber));
		}
		return convertedPhoneNumbers;
	}

	private String toATT(String phoneNumber) {
		return phoneNumber + "@txt.att.net";
	}

	private String toVerizon(String phoneNumber) {
		return phoneNumber + "@vtext.com";
	}

	private String toTMobile(String phoneNumber) {
		return phoneNumber + "@tmomail.com";
	}

	private String toSprint(String phoneNumber) {
		return phoneNumber + "@messaging.sprintpcs.com";
	}

	private String toUSCellular(String phoneNumber) {
		return phoneNumber + "@email.uscc.net";
	}
}
