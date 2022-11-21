

/*
 * Base class that represent a binary expression node inside the AST.
*/

public class Lire extends Instruction {
    /**
     * The expression 
     */
    protected Idf idf;

    /**
     * Constructor
     */
    public Lire(Idf idf, String fl, int line, int col) {
        super(fl, line, col);
        this.idf = idf;
    }

    /**
     * Get the left expression
     */
    public Expression getIdf() {
        return this.idf;
    }

    /**
     * Set the right expression
     */
    public void setIdf(Idf idf) {
        this.idf = idf;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }

    public Idf getDestination() {
        return this.idf;
    }
}