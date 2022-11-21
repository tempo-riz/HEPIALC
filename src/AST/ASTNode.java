/*
 * Base class that represent a node inside the AST.
 */

public abstract class ASTNode {
    /**
     * File where it has been found
     */
    private String filename = "";
    /**
     * Line where it has been found
     */
    private int line = -1;
    /**
     * Column where it has been found
     */
    private int column = -1;

    private int id;
    private static int idIncrement = 0;

    /**
     * Constructor
     */
    public ASTNode(String fl, int line, int col) {
        this.filename = fl;
        this.line = line;
        this.column = col;
        this.id = idIncrement;
        ASTNode.idIncrement += 1;
    }

    /**
     * Return the filename where it has been found
     */
    public String getFilename() {
        return this.filename;
    }
    /**
     * Return the line where it has been found
     */
    public int getLine() {
        return this.line;
    }
    /**
     * Return the column where it has been found
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Accepts a AST visitor
     */
    abstract Object accept(ASTVisitor visitor);
}
