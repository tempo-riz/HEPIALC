
/*
 * Represent an assignment node inside the AST.
 */

public class DeclarationVariable extends Instruction {
    /**
     * The source operand (at its right)
     */
    private Idf id;
    private Type type; 

    /**
     * Constructor
     */
    public DeclarationVariable(Type type, Idf id, String fl, int line, int col) {
        super(fl, line, col);
        this.id = id;
        this.type = type;
    }

    /**
     * Get the source operand (at its right)
     */
    public Idf getIdentifier() {
        return this.id;
    }

    /**
     * Set the source operand (at its right)
     */
    public void setId(Idf id) {
        this.id = id;
    }

    public Type getType() {
        return this.type;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }


}