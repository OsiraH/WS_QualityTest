package modellayer;

/**
 * Inspired by the book: Flexible, Reliable Software
 * Henrik Bærbak Christensen: Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class Currency {
	
	public enum ValidCurrency { EURO, DKK, NOK };
	/*enum ValidCurrency is misleading, because supplied values are not valid currencies for the
	system. This enum only specifies which currencies can machine recognize. Validation of the
	currencies takes place in the Validation.java class */
	
	public enum ValidCoinType { FRACTION, INTEGER };

}
