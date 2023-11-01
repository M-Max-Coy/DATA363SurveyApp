package util;

public class ArrayHelper {
    public static String arrayToString(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder(array[0]);

        for (int i = 1; i < array.length; i++) {
            result.append(", ").append(array[i]);
        }

        return result.toString();
    }
    
    public static String[] copyArray(String[] array) {
    	String[] res = new String[array.length];
    	for (int i = 0; i < array.length; i++) {
    		res[i] = array[i];
    	}
    	return res;
    }
    
    public static String[] removeNulls(String[] array) {
    	int nulls = 0;
    	for (String s : array) {
    		if (s == null)
    			nulls++;
    	}
    	
    	String[] res = new String[array.length-nulls];
    	int i = 0;
    	for (String s : array) {
    		if (s != null) {
    			res[i] = s;
    			i++;
    		}
    	}
    	
    	return res;
    }
   
}
