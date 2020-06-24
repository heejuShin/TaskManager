package meeting.management.oopd;

public class MeetingDTO {
	
	private String name;
	private String date;
	private String place;
	private String participant;
	private String result;
	
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
	
	public String getParti() {
		return participant;
	}
	public void setParti(String parti) {
		this.participant = parti;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "Meeting DTO[name = "+name+", date = "+date+", place = "+place+
				", participant = "+participant+", result = "+result+"]";
	}
}
