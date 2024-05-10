import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for handling password hashing and verification.
 */
public class PasswordUtil {

    /**
     * Hashes a plain text password using BCrypt.
     * @param plainTextPassword the plain text password to hash
     * @return a hashed version of the password
     */
    public static String hashPassword(String plainTextPassword) {
        // Generate a salt and hash the password
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    /**
     * Checks a plain text password against a hashed password to verify if they match.
     * @param plainTextPassword the plain text password to verify
     * @param hashedPassword the hashed password to compare against
     * @return true if the passwords match, false otherwise
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        // Compare the plain text password with the hashed password
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
