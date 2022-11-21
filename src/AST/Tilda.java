

/*
 * Represent an addition expression node inside the AST.
*/

public class Tilda extends Unaire {
    /**
     * Constructor
     */
    public Tilda(String fl, int line, int col) {
        super(fl, line, col);
        this.setClassType(Integer.class);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return "~";
    }

    /**
     * Apply the operator on the two given values.
     */
    public int apply(int exp) {
        return (-1)*exp;
    }

    public boolean apply(boolean exp) {
        return !exp;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
