package meeting.management.oodp;

import java.util.ArrayList;

public class MeetingDTO {
	
	private String name;
	private String date;
	private String place;
	private String result;
	private ArrayList<String> userList = new ArrayList<>();
	
	public MeetingDTO(String name, String date, String place, String result, ArrayList<String> userList) {

		this.name = name;
		this.date = date;
		this.place = place;
		this.result = result;
		this.userList = userList;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public ArrayList<String> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}
	
}
