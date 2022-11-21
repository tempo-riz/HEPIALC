

public class Type extends Expression {

	public Class classV;
	public String name;

	public Type(Class classV, String name, String fl, int line, int col) {
		super(fl, line, col);
		this.name = name;
		this.classV = classV;
	}

	public String getName() {
		return this.name;
	}

	public Class getClassType() {
		return this.classV;
	}

	public Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}