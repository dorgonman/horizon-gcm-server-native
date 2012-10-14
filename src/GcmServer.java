import java.io.IOException;

import com.google.android.gcm.server.*;
import org.json.simple.parser.JSONParser;
public class GcmServer {

	
	public static String REGISTRATION_ID = "APA91bHCbHbeHuaXO-5zBToxEAlID67i9lXp_5MTeLTm-nXeZprZp_mw-RTNVffex1h11IsdQcT6LrUZ6QDEahsI0SyasSOMzL35DkzwgY_T016lDBDkKmg5mwVXnrv6TfkVyRK6zAgnQ4DHL500YglKJojsNiHNZg";
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
		System.out.println(result.getMessageId());
		
		if (result.getMessageId() != null) {
			 String canonicalRegId = result.getCanonicalRegistrationId();
			
			 if (canonicalRegId != null) {
			   // same device has more than on registration ID: update database
				 System.out.println("canonicalRegId:" + canonicalRegId);
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
