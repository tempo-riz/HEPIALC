

public class Tantque extends Instruction {
    private Expression condition;
    private Bloc body;


    /*   
    constructor
    */

    public Tantque(Expression condition, Bloc body, String fl, int line, int col) {
        super(fl, line, col);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return this.condition;
    }

    public Bloc getBody() {
        return this.body;
    }

    Object accept(ASTVisitor visitor) {
        return visitor.visit(this);
    }
}
