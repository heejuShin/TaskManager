package Decorate.design.pattern.oodp;

public class IsLeaderTopping extends Topping{
	private String name;
	public IsLeaderTopping(DecoName decoName) {
		super(decoName);
	}
	
	@Override
	public String getName() {
		return "팀 조장,  " + decoName.getName();
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
}
