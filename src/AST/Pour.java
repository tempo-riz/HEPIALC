
/*
 * Represent an identifier node inside the AST.
*/

public class Pour extends Instruction {
    /**
     * Name of the
     */
    private Idf iterator;
    private Expression from;
    private Expression to;
    private Bloc body;

    /**
     * Constructor
     */
    public Pour(Idf iterator, Expression from, Expression to, Bloc body, String fl, int line, int col) {
        super(fl, line, col);
        this.iterator = iterator;
        this.from = from;
        this.to = to;
        this.body = body;
    }


    public Idf getIterator() {
        return this.iterator;
    }

    public Expression getFrom() {
        return this.from;
    }

    public Expression getTo() {
        return this.to;
    }

    public Bloc getBody() {
        return this.body;
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}
