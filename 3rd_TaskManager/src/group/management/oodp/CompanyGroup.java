package group.management.oodp;

public class CompanyGroup extends Group{
	private String name;
	private int type;
	private int num;
	private String hostName;
	private String[] memberName;
	public CompanyGroup() {
		System.out.println("Company Group�� �����Ǿ����ϴ�.");
	}
	@Override
	void ManageMember() {
		// TODO Auto-generated method stub
		System.out.println("ManageMember");
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
		return "����ڿ�";
	}
}
