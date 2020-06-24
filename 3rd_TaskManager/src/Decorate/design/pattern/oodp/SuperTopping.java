package Decorate.design.pattern.oodp;

public class SuperTopping extends Topping{
	private String name;
	public SuperTopping(DecoName decoName) {
		super(decoName);
	}
	
	@Override
	public String getName() {
		return "QA 담당 " + decoName.getName();
	}
	public void setName(String name) {
		this.name=name;
	}

}
