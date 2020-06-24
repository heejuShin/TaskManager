package Decorate.design.pattern.oodp;

public abstract class Topping implements DecoName{
	protected DecoName decoName;
	
	public Topping(DecoName decoName) {
		this.decoName=decoName;
	}
	
	@Override
	public abstract String getName();
}
