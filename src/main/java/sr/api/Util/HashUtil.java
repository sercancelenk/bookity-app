package sr.api.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by sercan on 10/02/16.
 */
public class HashUtil {
    private static String hashKey = "kafa1500@excaliburnacres1402sexOntheBeach";
    private static String hashKey2 = "@!exCa@@";

    public static String generateHashCode(String s)
    {
        return MD5(hashKey + s);
    }

    public static String generateHashCode2(String s)
    {
        return MD5(hashKey2 + s);
    }

    private static String MD5(String md5)
    {
        try
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i)
            {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static String springEncodeBcrypt(String s){
        return encodeSpringBcrypt(s);
    }
    private static String encodeSpringBcrypt(String s){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        return passwordEncoder.encode(s);
    }

    public static Boolean checkPasswords(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
