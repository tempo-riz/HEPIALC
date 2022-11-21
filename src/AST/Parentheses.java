

/*
 * Base class that represent an expression node inside the AST.
*/

public class Parentheses extends Expression {

	private Expression expression;

    /**
     * Constructor
     */
    public Parentheses(Expression expression, String fl, int line, int col) {
        super(fl, line, col);
        this.expression = expression;
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