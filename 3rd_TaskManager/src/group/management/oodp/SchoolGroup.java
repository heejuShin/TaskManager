package group.management.oodp;

public class SchoolGroup extends Group{
	private String name;
	private int type;
	private int num;
	private String hostName;
	private String[] memberName;
	public SchoolGroup() {
		System.out.println("School Group이 생성되었습니다.");
	}
	@Override
	void ManageMember() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void ManageTask() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void ManageSchedule() {
		// TODO Auto-generated method stub
		
	}
	@Override
	void ManageMeeting() {
		// TODO Auto-generated method stub
		
	}
	@Override
	String getEx() {
		// TODO Auto-generated method stub
		return "팀프로젝트, 과제 관리";
	}

}
