

/*
 * Represent an equal comparaison expression node inside the AST.
*/

public class Ou extends Relation {
    /**
     * Constructor
     */
    public Ou(String fl, int line, int col) {
        super(fl, line, col);
    }

    /**
     * Get the binary operator
     */
    public String operateur() {
        return "||";
    }

    /**
     * Apply the operator on the two given values.
     */

    public int apply(int gauche, int droite) {
        return (gauche != 0 || droite != 0) ? 1 : 0;
    }

    public boolean apply(boolean gauche, boolean droite) {
        return gauche || droite;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
