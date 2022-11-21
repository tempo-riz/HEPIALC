

/*
 * Base class that represent a binary expression node inside the AST.
*/

public class Ecrire extends Instruction {
    /**
     * The expression 
     */
    protected Expression expression;

    /**
     * Constructor
     */
    public Ecrire(Expression expression, String fl, int line, int col) {
        super(fl, line, col);
        this.expression = expression;
    }

    /**
     * Get the left expression
     */
    public Expression getExpression() {
        return this.expression;
    }

    /**
     * Set the right expression
     */
    public void setExpression(Expression exp) {
        this.expression = exp;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }

    public Expression getSource() {
        return this.expression;
    }
}