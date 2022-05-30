package password;

/**
 * For hashing the user passwords before saving them to the database.
 * @author dinjo998
 */
public class PasswordHash {
    	 
	//hashes the given PasswordHash and returns the result.
	 public static String hash(String passwordToHash){
		 String generatedSecuredPasswordHash = BCrypt.hashpw(passwordToHash, BCrypt.gensalt(12));
       System.out.println(generatedSecuredPasswordHash);
		 return generatedSecuredPasswordHash;
	 }
	 
	 //checks a given PasswordHash with a hashed PasswordHash. Returns true if they match.
	 public static boolean checkPassword(String passwordToCheck, String savedPassword){
		 boolean matched = BCrypt.checkpw(passwordToCheck, savedPassword);
		 System.out.println(matched);
		 return matched;
	 }
}

