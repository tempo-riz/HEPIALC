

/*
 * Represent an addition expression node inside the AST.
*/

public class Non extends Unaire {
    /**
     * Constructor
     */
    public Non(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return "non";
    }

    /**
     * Apply the operator on the two given values.
     */
    public boolean apply( boolean exp) {
        return !exp;
    }


    public int apply(int exp) {
        return (-1)*exp;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
