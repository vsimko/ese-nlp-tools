package reprotool.dmodel.extensions

import java.security.NoSuchAlgorithmException
import java.security.MessageDigest
import java.math.BigInteger

class CachingExtensions {
	
	def static String md5(String input) throws NoSuchAlgorithmException {
	    var result = input
	    if(input != null) {
	        val md = MessageDigest.getInstance("md5") //or "SHA-1"
	        md.update(input.getBytes())
	        val hash = new BigInteger(1, md.digest())
	        result = hash.toString(16)
	        while(result.length < 32) {
	            result = "0" + result
	        }
	    }
	    return result
	}
	
	def static String md5(Iterable<?> input) {
		input.join(":").md5
	}
}