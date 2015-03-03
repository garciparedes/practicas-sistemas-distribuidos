package protocolo;

public enum Primitive {
	HELLO(true),
	PUSH(true),
	PULL_WAIT(false),
	PULL(false),
	PUSH_OK(false),
	NOTHING(false),
	PULL_OK(true),
	NOTUNDERSTAND(false);

	/* msg: TRue significa que le acompaña mensaje, y false que no. */
	private final boolean msg ;
	
	Primitive(boolean b) {
		this.msg = b;
	}
	
	public boolean isCompound() {
		return this.msg;
	}
}
