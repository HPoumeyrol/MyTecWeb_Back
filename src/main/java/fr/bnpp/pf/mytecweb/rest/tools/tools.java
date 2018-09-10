package fr.bnpp.pf.mytecweb.rest.tools;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base32;


public class tools {
	
	public static String getB32Uid() {
		
		// Create a secure random generator (it's thread-safe)
		SecureRandom sr = new SecureRandom();

		// Allocate an array for 8 bytes
		byte[] random = new byte[8];

		// Generate the random bytes
		sr.nextBytes(random);

		// Create the encoded ID, strip any padding
		return new Base32().encodeToString(random).replace("=", "");
		
	}

}
