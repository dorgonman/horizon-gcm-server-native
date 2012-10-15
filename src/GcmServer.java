import java.io.IOException;

import com.google.android.gcm.server.*;
import org.json.simple.parser.JSONParser;
public class GcmServer {

	
	public static String REGISTRATION_ID = "APA91bGJwnRgYbdPozptxqyLXXHRn7KOomMn2Vtpysa1BUAC0Ywr3fRvqNqvcsxz5vnV3UDFTpG1q2WCiUDsjgtg7mxF-KgFvpLcewH_PYG5fJELvwkcE4QDw8IHq7EcNk9ZP9pAYaj-p_aPj9z_LUS75b3q7x5nzA";
	public final static String GCM_API_KEY_SERVER = "AIzaSyA-cxSe50B1bdZ_IY8MB3uJZDAQAQSO1fk";
	public static void main(String args[]) {
		System.out.println("GcmServer");
		
		Sender sender = new Sender(GCM_API_KEY_SERVER);
		Message message = new Message.Builder().addData("message", "my gcm message").build();
		Result result = null;
		try {
			result = sender.send(message, REGISTRATION_ID, 5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("result" + result.getMessageId());
		
		if (result.getMessageId() != null) {
			 String canonicalRegId = result.getCanonicalRegistrationId();
			
			 if (canonicalRegId != null) {
			   // same device has more than on registration ID: update database
				 System.out.println("new canonicalRegId:" + canonicalRegId);
			 }
		} else {
			 String error = result.getErrorCodeName();
			 System.out.println("error:"+error);
			 if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
			   // application has been removed from device - unregister database
			 }
		}
	}
}
