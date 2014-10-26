package DataRequest;

import java.util.ArrayList;

public class SearchBloomberg {

	private ArrayList<DataValue> searchList;
	
	public void populateSearchList(){
		searchList.add(new DataValue("UK","RAIN","UKWSRAIN index"));
	}
	
	public void setBloomData() {
		
		BloombergData bloom = new BloombergData();
		
		for (DataValue dv : searchList) {
			dv.setValue(bloom.run(dv.getSECURITY(), dv.getFIELD()));
			
		}
	}
	
	public String getValue(String country, String security){
		for (DataValue dv : searchList) {
			if((country==dv.getCountry())&&(security==dv.getSECURITY())) return dv.getValue();
		}
		return null;
	}
}
