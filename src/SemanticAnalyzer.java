import java.util.*;

public class SemanticAnalyzer implements ASTVisitor {
    private Map<String, Class> TDS = new HashMap<>();
    private List<String> constantes = new ArrayList<>();

    public Map<String, Class> getTDS(){
        return this.TDS;
    }
    private boolean declarationFinished = false;

    public Object visit(DeclarationProgramme node) {
        if (node.getIdentifier().getNom().length() > 0) {
            node.getDeclaration().accept(this);
            this.declarationFinished = true;
            node.getInstructions().accept(this);
        } else {
            print("the main program needs a name");
        }
        return null;
    }

    void checkIntegerOperation(Expression e1, Expression e2, String c) {
        if (!e1.accept(this).equals(e2.accept(this))) {
            print("Not same class type -> " + c);
        }
        if (!e1.accept(this).equals(Integer.class)) {
            print("Can only use " + c + " with integer !");
        }
    }

    void checkBooleanOperation(Expression e1, Expression e2, String c) {
        if (!e1.accept(this).equals(e2.accept(this))) {
            print("Not same class type -> " + c);
        }
        if (!e1.accept(this).equals(Boolean.class)) {
            print("Can only use " + c + " with Boolean !");
        }
    }

    void checkOperation(Expression e1, Expression e2, String c) {
        if (!e1.accept(this).equals(e2.accept(this))) {
            System.out.println(e1.accept(this));
            System.out.println(e2.accept(this));
            System.out.println(e1);
            System.out.println(e2);
            print("Not same class type -> " + c);
        }
    }

    public Object visit(Addition node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Soustraction node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Multiplication node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Division node) {
        checkOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Different node) {
        checkOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Affectation node) {
        
        String n=((Idf)node.getDestination()).getNom();

        if(constantes.contains(n)){
            print("Cannot assign to const "+n);
        }
        node.getDestination().accept(this);
        node.getSource().accept(this);

        if (!node.getDestination().getClassType().equals(node.getSource().getClassType())) {
            System.out.println(node.getDestination().getClassType());
            System.out.println(node.getSource().getClassType());
            // System.out.println(node.getDestination().accept(this));
            // System.out.println(node.getSource().accept(this));
            print("Not same class type -> =");
        }

        return node.getSource().getClassType();
    }

    public Object visit(Nombre node) {
        return Integer.class;
    }

    public Object visit(Chaine node) {
        return null;
    }

    public Object visit(SupEgal node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Superieur node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Bloc node) {
        node.getInstructions().forEach(i -> i.accept(this));
        return null;
    }

    public Object visit(Condition node) {
        node.getBody().accept(this);
        return null;
    }
    
    public Object visit(Type node) {
        return null;
    }

    public Object visit(Egal node) {
        checkOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Et node) {
        checkBooleanOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Ou node) {
        checkBooleanOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(InfEgal node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Inferieur node) {
        checkIntegerOperation(node.getGauche(), node.getDroite(), node.getClass().getName());
        return node.getClassType();
    }

    public Object visit(Boole node) {
        node.setClassType(Boolean.class);
        return node.getClassType();
    }


    public Object visit(Idf node) {

        if (this.TDS.containsKey(node.getNom())) {
            node.setClassType(this.TDS.get(node.getNom()));
            return node.getClassType();
        }
        if (this.declarationFinished) {
            print(node.getNom() + " has not been declared in header!");
        }

        return null;
    }

    public Object visit(Tilda node) {
        if (!node.getOperand().accept(this).equals(Integer.class)) {
            print("Can only use " + node.getClass().getName() + " with Integer !");
        }
        return Integer.class;
    }

    public Object visit(Moins node) {
        if (!node.getOperand().accept(this).equals(Integer.class)) {
            print("Can only use " + node.getClass().getName() + " with Integer !");
        }
        return Integer.class;
    }

    public Object visit(Non node) {
        if (!node.getOperand().accept(this).equals(Boolean.class)) {
            print("Can only use " + node.getClass().getName() + " with Boolean !");
        }
        return Boolean.class;
    }

    public Object visit(Parentheses node) {
        node.getExpression().accept(this);
        node.setClassType(node.getExpression().getClassType());
        return node.getClassType();
    }

    public Object visit(Ecrire node) {
        node.getSource().accept(this);
        return null;
    }

    public Object visit(Lire node) {
        node.getDestination().accept(this);
        return null;
    }

    public Object visit(Pour node) {
        node.getIterator().accept(this);
        node.getFrom().accept(this);
        node.getTo().accept(this);
        node.getBody().accept(this);
        return null;
    }

    public Object visit(DeclarationConstant node) {
        node.getType().accept(this);
        node.getIdentifier().accept(this);
        node.getExpression().accept(this);
        this.constantes.add(node.getIdentifier().getNom()); //to check const

        if (!this.TDS.containsKey(node.getIdentifier().getNom())) {
            this.TDS.put(node.getIdentifier().getNom(), node.getType().getClassType());
            node.getIdentifier().setClassType(node.getType().getClassType());
            
            if (!node.getIdentifier().getClassType().equals(node.getExpression().getClassType())) {
                print("constant declaration must be of same type");
            }
            return null;
        }
        print(node.getIdentifier().getNom() + " has already been declared");
        return null;

    }

    public Object visit(DeclarationVariable node) {
        node.getType().accept(this);
        node.getIdentifier().accept(this);

        if (!this.TDS.containsKey(node.getIdentifier().getNom())) {
            this.TDS.put(node.getIdentifier().getNom(), node.getType().getClassType());
            node.getIdentifier().setClassType(node.getType().getClassType());
            return null;
        }
        print(node.getIdentifier().getNom() + " has already been declared");
        return null;
    }

    public Object visit(Tantque node) {
        node.getCondition().accept(this);
        node.getBody().accept(this);
        return null;
    }

    public void print(String message) {
        System.out.println(message);
        System.exit(0);
    }
}
