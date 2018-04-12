package views;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

public class ViewConstants {

	public static NumberFormat CURRENCY;
	public static NumberFormatter ZIP_FORMAT;
	public static NumberFormatter DEALER_FORMAT;

	static {
		CURRENCY = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

		{
			NumberFormat format = NumberFormat.getNumberInstance();
			format.setGroupingUsed(false);
			ZIP_FORMAT = new NumberFormatter(format) {

				private static final long serialVersionUID = 1L;

				@Override
				public Object stringToValue(String text) throws ParseException {
					if (text.length() == 0) {
						return null;
					}
					return super.stringToValue(text);
				}
			};
			ZIP_FORMAT.setValueClass(Integer.class);
			ZIP_FORMAT.setAllowsInvalid(false);
			ZIP_FORMAT.setMinimum(0);
			ZIP_FORMAT.setMaximum(99999);
		}

		{
			DEALER_FORMAT = new NumberFormatter(NumberFormat.getIntegerInstance()) {

				private static final long serialVersionUID = -2412920411746407003L;

				@Override
				public Object stringToValue(String text) throws ParseException {
					if (text.length() == 0) {
						return null;
					}
					return super.stringToValue(text);
				}
			};
			DEALER_FORMAT.setValueClass(Integer.class);
			DEALER_FORMAT.setAllowsInvalid(false);
			DEALER_FORMAT.setMinimum(0);
		}
	}
}
