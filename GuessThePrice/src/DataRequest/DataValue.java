package DataRequest;

public class DataValue {

	private String COUNTRY;
	private String SECURITY;
	private String FIELD;
	private String VALUE;
	
	
	public DataValue(String country, String security, String field) {
		
		COUNTRY = country;
		SECURITY = security;
		FIELD = field;
		VALUE = null;
		
		
	}
	
	public DataValue(String security, String field) {
		
		SECURITY = security;
		VALUE = field;
		
	}
	
	
	public String getSECURITY() {
		return SECURITY;
	}

	public void setSECURITY(String sECURITY) {
		SECURITY = sECURITY;
	}

	public String getFIELD() {
		return FIELD;
	}

	public void setFIELD(String fIELD) {
		FIELD = fIELD;
	}
	
	public String getCountry() {
		return this.COUNTRY;
	}
	
	public void setValue(String v) {
		VALUE = v;
	}
	
	public String getValue() {
		return VALUE;
	}
	
}
