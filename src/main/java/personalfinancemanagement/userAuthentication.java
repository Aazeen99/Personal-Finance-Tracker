package personalfinancemanagement;

import java.security.*;
import java.util.regex.*;
import java.util.*;

public class userAuthentication {
    String username, email, password;

    // Salting and Hashing methods for password encryption
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String saltedPassword = password + salt;
        byte[] hashBytes = digest.digest(saltedPassword.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    // Method to validate email format
    public static boolean isValidEmail(String email) {
        String emailValidityFormat = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailValidityFormat);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
