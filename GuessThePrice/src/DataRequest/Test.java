package DataRequest;

import com.bloomberglp.blpapi.CorrelationID;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Event;
import com.bloomberglp.blpapi.Message;
import com.bloomberglp.blpapi.MessageIterator;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Request;
import com.bloomberglp.blpapi.Service;
import com.bloomberglp.blpapi.Session;
import com.bloomberglp.blpapi.SessionOptions;

public class Test {
	
	private static final Name SECURITY_DATA = new Name("securityData");
    private static final Name SECURITY = new Name("security");
    private static final Name FIELD_DATA = new Name("fieldData");
    private static final Name FIELD_EXCEPTIONS = new Name("fieldExceptions");
    private static final Name FIELD_ID = new Name("fieldId");
    private static final Name ERROR_INFO = new Name("errorInfo");

    public static void main(String[] args){
    	SearchBloomberg search = new SearchBloomberg();
    	
    	//System.out.print(search.getValue("united_kingdom","Average rainfall in the UK per year (in mm)")+"\n");
    	//System.out.print(search.getValue("china","Cumulative number of reported cases of SARS")+"\n");
    }
    
	/*public static void main(String[] args) throws Exception {
		SessionOptions sessionOptions = new SessionOptions();
		sessionOptions.setServerHost("10.8.8.1");
		sessionOptions.setServerPort(8194);

		Session session = new Session(sessionOptions);

		if (!session.start()) {
			System.out.println("Could not start session.");
			System.exit(1);
		}
		if (!session.openService("//blp/refdata")) {
			System.out.println("Could not open service " + "//blp/refdata");
			System.exit(1);
		}
		CorrelationID requestID = new CorrelationID(1);

		Service refDataSvc = session.getService("//blp/refdata");
		Request request = refDataSvc.createRequest("ReferenceDataRequest");
		request.append("securities", "UKWSRAIN index");
		request.append("fields", "PX_LAST");
		session.sendRequest(request, requestID);
		boolean continueToLoop = true;
		while (continueToLoop) {
			Event event = session.nextEvent();

			switch (event.eventType().intValue()) {

			case Event.EventType.Constants.RESPONSE: // final event
				continueToLoop = false; // fall through
				handleResponseEvent(event);
			//case Event.EventType.Constants.PARTIAL_RESPONSE:
			//	handleResponseEvent(event);
				break;

			default:
			//	handleOtherEvent(event);
				break;
			}
		}
	}

	private static void handleOtherEvent(Event event) throws Exception {
		System.out.println("EventType=" + event.eventType());
		MessageIterator iter = event.messageIterator();
		while (iter.hasNext()) {
			Message message = iter.next();
			System.out.println("correlationID=" + message.correlationID());
			System.out.println("messageType=" + message.messageType());
			message.print(System.out);
			if (Event.EventType.Constants.SESSION_STATUS == event.eventType()
					.intValue()
					&& "SessionTerminated" == message.messageType().toString()) {
				System.out.println("Terminating: " + message.messageType());
				System.exit(1);
			}
		}
	}
	
	private static void handleResponseEvent(Event event) throws Exception {
	//	System.out.println("EventType =" + event.eventType());
		MessageIterator iter = event.messageIterator();
		while (iter.hasNext()) {
			Message message = iter.next();
	//		System.out.println("correlationID=" + message.correlationID());
	//		System.out.println("messageType =" + message.messageType());
			message.print(System.out);
			processMessage(message);
			//System.out.print(message.getElementAsString("b"));
		}
	}
	
	private static void processMessage(Message msg) throws Exception {
        Element securityDataArray = msg.getElement(SECURITY_DATA);
        int numSecurities = securityDataArray.numValues();
        for (int i = 0; i < numSecurities; ++i) {
            Element securityData = securityDataArray.getValueAsElement(i);
        //    System.out.println(securityData.getElementAsString(SECURITY));
            Element fieldData = securityData.getElement(FIELD_DATA);
            for (int j = 0; j < fieldData.numElements(); ++j) {
                Element field = fieldData.getElement(j);
                if (field.isNull()) {
                    System.out.println(field.name() + " is NULL.");
                } else {
                    System.out.println("Field: "+field.getValueAsString());
                }
            }
            Element fieldExceptionArray =
                securityData.getElement(FIELD_EXCEPTIONS);
            for (int k = 0; k < fieldExceptionArray.numValues(); ++k) {
                Element fieldException =
                    fieldExceptionArray.getValueAsElement(k);
                System.out.println(
                        fieldException.getElement(ERROR_INFO).getElementAsString("category")
                        + ": " + fieldException.getElementAsString(FIELD_ID));
            }
            System.out.println("\n");
        }
    }*/
}