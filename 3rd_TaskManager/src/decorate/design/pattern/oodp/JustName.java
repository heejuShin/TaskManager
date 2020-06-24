package Decorate.design.pattern.oodp;

public class JustName implements DecoName{
	private String name;
	
//	public JustName(String name) {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	

}
