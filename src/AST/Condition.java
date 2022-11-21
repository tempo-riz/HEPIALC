
/*
 * Represent an assignment node inside the AST.
 */
import java.util.Optional;

public class Condition extends Instruction {

	private Expression condition;
    private Bloc body;
    private Optional<Bloc> elseI;

	/**
     * Constructor
     */
    public Condition(Expression condition, Bloc body, String fl, int line, int col) {
        super(fl, line, col);
        this.condition = condition;
        this.body = body;
        this.elseI = Optional.empty();
    }

    /**
     * Constructor
     */
    public Condition(Expression condition, Bloc body, Bloc elseI, String fl, int line, int col) {
        super(fl, line, col);
        this.condition = condition;
        this.body = body;
        this.elseI = Optional.of(elseI);
    }

    public Expression getCondition() {
        return this.condition;
    }

    public Bloc getBody() {
        return this.body;
    }

    public Optional<Bloc> getElse() {
        return this.elseI;
    }

    public boolean hasElse() {
        return this.elseI.isPresent();
    }

    /**
     * Accepts a AST visitor
     */
    Object accept(ASTVisitor visitor){
        return visitor.visit(this);
    }
}