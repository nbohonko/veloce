package helpers;

import static utils.BaseStep.getStringData;

public class InputDataParser {

    public static String getParsedData(String value) {
        if (value.startsWith("%")) {
            String tempValue = value.substring(1);
            return getStringData(tempValue);
        } else {
            return value;
        }
    }
}
