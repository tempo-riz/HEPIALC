

/*
 * represente a negative number
*/

public class Moins extends Unaire {
    /**
     * Constructor
     */
    public Moins(String fl, int line, int col) {
        super(fl, line, col);
        this.setClassType(Integer.class);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return "-";
    }

    /**
     * Apply the operator on the two given values and return an int
     */
    public int apply(int exp) {
        return (-1)*exp;
    }

    /**
     * Apply the operator on the two given values and return a boolean
     */
    public boolean apply(boolean value){
        return !value;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
