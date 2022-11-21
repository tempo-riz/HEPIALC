
/*
 * Base class that represent an expression node inside the AST.
*/

public abstract class Expression extends ASTNode {

    private Class classType;
    private int value;

    /**
     * Constructor
     */
    public Expression(String fl, int line, int col) {
        super(fl, line, col);
        this.classType = this.getClass();
        this.value = -1;
    }

    public void setClassType(Class newClass) {
        this.classType = newClass;
    }

    public Class getClassType() {
        return this.classType;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int v) {
        this.value = v;
    }

}
