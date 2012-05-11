package au.com.regimo.core.utils;


import java.util.Random;
import java.util.UUID;
 
public class RandomPassword {
 
    private static final String charset = "!0123456789abcdefghijklmnopqrstuvwxyz";
 
    public static String getRandomString(int length) {
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }
 
    public static String generateFromUUIDwithIP(String ipAddress){
    	return ipToLong(ipAddress)+"-"+UUID.randomUUID().toString();
    }
    
    public static long ipToLong(String ipAddress) {
        long result = 0;
        String[] atoms = ipAddress.split("\\.");

        for (int i = 3; i >= 0; i--) {
            result |= (Long.parseLong(atoms[3 - i]) << (i * 8));
        }

        return result & 0xFFFFFFFF;
    }
    
}