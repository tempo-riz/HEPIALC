

/*
 * Represent a number node inside the AST.
*/

public class Nombre extends Expression {
    /**
     * Value contained in this number node
     */
    private int valeur;

    /**
     * Constructor
     */
    public Nombre(int val, String fl, int line, int col) {
        super(fl, line, col);
        this.valeur = val;
        this.setClassType(Integer.class);
    }

    /**
     * Get the node value
     */
    public int getValue() {
        return this.valeur;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
