
/*
 * Represent an assignment node inside the AST.
 */

public class DeclarationConstant extends Instruction {
	private Idf id;
	private Type type;
	private Expression expression;

	/**
     * Constructor
     */
    public DeclarationConstant(Type type, Idf id, Expression expression, String fl, int line, int col) {
        super(fl, line, col);
        this.id = id;
        this.type = type;
        this.expression = expression;
    }

    /**
     * Get the source operand (at its right)
     */
    public Idf getIdentifier() {
        return this.id;
    }

    /**
     * Set the source operand (at its right)
     */
    public void setId(Idf id) {
        this.id = id;
    }

    public Type getType() {
        return this.type;
    }

    public Expression getExpression() {
    	return this.expression;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}