package au.com.regimo.core.utils;

import org.apache.commons.validator.EmailValidator;

import com.google.common.base.Splitter;

public final class StringUtils {

	private StringUtils() {}
	
	private static final EmailValidator validator = EmailValidator.getInstance();
	private static final Splitter COMMA_SPLITTER = Splitter.on(',') .trimResults() .omitEmptyStrings();
	
	public static boolean isEmail(String email) {
		return validator.isValid(email);
	}
	
	public static Iterable<String> splitByComma(String source){
		return COMMA_SPLITTER.split(source);
	}
	
	public static String appendWithSpace(String source, String appendContent){
		return append(source, appendContent, " ");
	}

	public static String appendWithComma(String source, String appendContent){
		return append(source, appendContent, ", ");
	}

	public static String append(String source, String appendContent, String spliter){
		String result = source;
		if(appendContent!=null && !appendContent.equals("")){
			if (result==null || result.equals("")){
				result = appendContent;
			}
			else{
				result += spliter+appendContent;
			}
		}
		return result;
	}

    public static long ipToLong(String ipAddress) {
    	long result = 0;
        if(ipAddress!=null && ipAddress.contains(".")){
	        String[] atoms = ipAddress.split("\\.");
	        for (int i = 3; i >= 0; i--) {
	            result |= (Long.parseLong(atoms[3 - i]) << (i * 8));
	        }
	        return result & 0xFFFFFFFF;
        }
        return result;
    }

    public static String intToIp(int i) {
        return ((i >> 24 ) & 0xFF) + "." +
               ((i >> 16 ) & 0xFF) + "." +
               ((i >>  8 ) & 0xFF) + "." +
               ( i & 0xFF);
    }
    
    public static String left(String str, int len){
    	return org.apache.commons.lang.StringUtils.left(str, len);
    }


}
