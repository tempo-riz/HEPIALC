
/*
 * Represent a number node inside the AST.
*/

public class Boole extends Expression {
    /**
     * Value contained in this number node
     */
    private boolean valeur;

    /**
     * Constructor
     */
    public Boole(boolean val, String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = val;
        this.setClassType(Boolean.class);
    }

    public String getString(){
        return valeur ? "vrai" : "faux";
    }
    /**
     * Get the node value
     */
    public int getValue() {
        return valeur ? 1 : 0;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }

}