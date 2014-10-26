package DataRequest;

public class DataValue {

	private String country;
	private String security;
	private String desc;
	private String field;
	private String value;
	
	
	public DataValue(String inCountry, String inDesc, String inSecurity, String inField) {
		country = inCountry;
		desc = inDesc;
		security = inSecurity;
		field = inField;
		value = null;
	}
		
	public String getDesc(){
		return desc;
	}
	
	public String getSecurity() {
		return security;
	}

	public String getField() {
		return field;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getValue(){
		if(value == null) {
			try {
				value=SearchBloomberg.find(security,field);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return value;
	}
}
