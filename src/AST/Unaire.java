
/*
 * Base class that represent a binary expression node inside the AST.
*/

public abstract class Unaire extends Expression {
    /**
     * The expression at its left
     */
    protected Expression operand;

    /**
     * Constructor
     */
    public Unaire(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the expression
     */
    public Expression getOperand() {
        return this.operand;
    }

    /**
     * Get the binary operator.
     * Must be implemented by the child class.
     */
    public abstract String operateur();

    /**
     * Set the expression
     */
    public void lier(Expression exp) {
        this.operand = exp;
    }
    

    /**
     * Apply the operator on the two given values.
     * Must be implemented by the child class.
     */
    public abstract int apply(int exp);
    /**
     * Apply the operator on the two given values.
     * Must be implemented by the child class.
     */
    public abstract boolean apply(boolean exp);


    public void lierOperand(Expression exp) {
        this.operand = exp;
    }

    /**
     * Transform this node into a visualisable string
     */
    public String toString() {
        return this.operateur() + " " + this.operand;
    }
}
