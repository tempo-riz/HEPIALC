
import java.util.*;

public class ByteCodeGenerator implements ASTVisitor {

    private List<String> cible = new ArrayList<>();
    private Map<String, Class> TDS;
    private List<String> indexes = new ArrayList<>();
    private int label = 0;

    public String getByteCode() {
        String output = "";
        for (String instr : this.cible) {
            output += instr + "\n";
        }
        return output;
    }

    public ByteCodeGenerator(Map<String, Class> TDS) {
        super();
        this.TDS = TDS;
    }

    public Object visit(DeclarationProgramme node) {
        cible.add(".class public " + node.getIdentifier().getNom());
        cible.add(".super java/lang/Object");
        cible.add(".method public static main([Ljava/lang/String;)V");
        cible.add(".limit stack 20000");
        cible.add(".limit locals " + (TDS.size() + 1));
        node.getIdentifier().accept(this);
        node.getDeclaration().accept(this);
        node.getInstructions().accept(this);
        cible.add("return");
        cible.add(".end method");

        return null;
    }

    private boolean isParameterDeclaration = false;

    public void pushIfIdf(Object o) {
        if (o.getClass().equals(Idf.class)) {
            Idf id = (Idf) o;
            cible.add("iload " + indexes.indexOf(id.getNom()));
        }

    }

    void comparative_bloc() {
        cible.add("iconst_0");
        cible.add("goto label_" + (label + 1));
        cible.add("label_" + label + ":");
        cible.add("iconst_1");
        cible.add("label_" + (label + 1) + ":");
        label += 2;
    }

    public Object visit(Addition node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("iadd");
        return null;
    }

    public Object visit(Affectation node) {
        node.getDestination().accept(this);
        node.getSource().accept(this);
        pushIfIdf(node.getSource());
        node.getDestination().setValue(node.getSource().getValue());

        // cible.add("ldc " + node.getSource().getValue());
        cible.add("istore " + indexes.indexOf(((Idf) node.getDestination()).getNom()));

        return null;
    }

    public Object visit(Bloc node) {
        for (Instruction inst : node.getInstructions()) {
            // code += "\n";
            inst.accept(this);
        }
        return null;
    }

    public Object visit(Chaine node) {
        cible.add("ldc " + node.getValue1());
        return null;
    }

    public Object visit(Condition node) {
        node.getCondition().accept(this);
        pushIfIdf(node.getCondition());

        cible.add("ifeq label_" + label); // si
        node.getBody().accept(this); // alors
        cible.add("goto label_" + (label + 1));
        cible.add("label_" + label + ":");
        // sinon
        if (node.hasElse()) {
            node.getElse().get().accept(this);
        }

        cible.add("label_" + (label + 1) + ":");
        label += 2;

        return null;
    }

    public Object visit(DeclarationConstant node) {

        node.getType().accept(this);
        node.getIdentifier().accept(this);

        int i = indexes.size();
        indexes.add(node.getIdentifier().getNom());

        String instr = ".var " + i + " is " + node.getIdentifier().getNom() + " ";
        Class c = TDS.get(node.getIdentifier().getNom());

        if (c.equals(Integer.class)) {
            instr += "I";
        } else if (c.equals(Boolean.class)) {
            instr += "Z";
        }

        cible.add(instr);
        
        node.getExpression().accept(this);
        cible.add("istore " + i);

        return null;
    }

    public Object visit(DeclarationVariable node) {

        node.getType().accept(this);
        node.getIdentifier().accept(this);

        int i = indexes.size();
        indexes.add(node.getIdentifier().getNom());

        String instr = ".var " + i + " is " + node.getIdentifier().getNom() + " ";
        Class c = TDS.get(node.getIdentifier().getNom());

        if (c.equals(Integer.class)) {
            instr += "I";
        } else if (c.equals(Boolean.class)) {
            instr += "Z";
        }

        cible.add(instr);
        cible.add("ldc 0");
        cible.add("istore " + i);
        return null;

    }

    public Object visit(Type node) {
        // code += node.getName();
        // code += " ";
        return null;
    }

    public Object visit(Different node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("if_icmpne label_" + label);
        comparative_bloc();

        return null;
    }

    public Object visit(Division node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());
        
        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());
        
        cible.add("idiv");
        return null;
    }
    
    public Object visit(Ecrire node) {
        
        cible.add("getstatic java/lang/System/out Ljava/io/PrintStream;");
        node.getSource().accept(this);
        pushIfIdf(node.getSource());
        
        //System.out.println("CLASS TYPE");
        //System.out.println(node.getSource().getClassType());

        
        if (node.getSource().getClassType().equals(Integer.class)) {
            cible.add("invokevirtual java/io/PrintStream/println(I)V");
            return null;
        }
        
        if (node.getSource().getClassType().equals(Boolean.class)) {

            cible.add("ifeq label_" + label); // label_0 (if 0 jump)
            cible.add("ldc \"vrai\"");
            cible.add("goto label_" + (label + 1)); // label_1
            cible.add("label_" + label + ":"); // label_0:
            cible.add("ldc \"faux\"");
            cible.add("label_" + (label + 1) + ":");// label_1:
            cible.add("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
            label += 2;
            return null;
        }
        
        if (node.getSource().getClass().equals(Chaine.class)) {
            cible.add("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
            return null;
        }
        
        return null;

    }

    public Object visit(Egal node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("if_icmpeq label_" + label);
        comparative_bloc();
        return null;
    }

    public Object visit(Et node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("iand");
        return null;
    }

    public Object visit(Idf node) {
        // cible.add("eo");
        // code += node.getNom();
        return null;
    }

    public Object visit(InfEgal node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("if_icmple label_" + label);
        comparative_bloc();

        return null;
    }

    public Object visit(Inferieur node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("if_icmplt label_" + label);
        comparative_bloc();

        return null;
    }

    public Object visit(Lire node) {
        node.getDestination().accept(this);

        cible.add("new java/util/Scanner");
        cible.add("dup");
        cible.add("getstatic java/lang/System/in Ljava/io/InputStream;");
        cible.add("invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V");
        cible.add("invokevirtual java/util/Scanner/nextInt()I");
        cible.add("istore " + indexes.indexOf(((Idf) node.getDestination()).getNom()));

        return null;
    }

    public Object visit(Moins node) {
        node.getOperand().accept(this);

        pushIfIdf(node.getOperand());
        cible.add("ineg");
        return null;
    }

    public Object visit(Nombre node) {
        cible.add("ldc " + node.getValue());
        return null;
    }

    public Object visit(Non node) {
        node.getOperand().accept(this);

        pushIfIdf(node.getOperand());

        cible.add("ifeq label_" + label);
        comparative_bloc();

        return null;
    }

    public Object visit(Ou node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("ior");

        return null;
    }

    public Object visit(Parentheses node) {
        // code += "(";
        node.getExpression().accept(this);
        // code += ")";
        return null;
    }

    public Object visit(Pour node) {
        int lab = label;
        int index = indexes.indexOf(node.getIterator().getNom());

        node.getIterator().accept(this); // i

        node.getFrom().accept(this); // allant de 0
        pushIfIdf(node.getFrom());// if not const

        cible.add("istore " + index);
        cible.add("label_loop" + lab + ":");
        // ldc 10
        node.getTo().accept(this); // a 10
        pushIfIdf(node.getTo());

        cible.add("iload " + index);
        cible.add("if_icmplt label_end" + lab);

        label++;
        node.getBody().accept(this);

        cible.add("iload " + index);
        cible.add("ldc 1");
        cible.add("iadd");
        cible.add("istore " + index);
        cible.add("goto label_loop" + lab);
        cible.add("label_end" + lab + ":");

        return null;
    }

    public Object visit(Multiplication node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("imul");
        return null;
    }

    public Object visit(Soustraction node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("isub");
        return null;
    }

    public Object visit(SupEgal node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("if_icmpge label_" + label);
        comparative_bloc();

        return null;
    }

    public Object visit(Superieur node) {
        node.getGauche().accept(this);
        pushIfIdf(node.getGauche());

        node.getDroite().accept(this);
        pushIfIdf(node.getDroite());

        cible.add("if_icmpgt label_" + label);
        comparative_bloc();

        return null; // !!!!!!!!!!!! je vais me faire une tisane !!!!!!!!!!!! 5 min
    }

    public Object visit(Tantque node) {
        int lab = label;
        cible.add("label_loop" + lab + ":");
        node.getCondition().accept(this);// tanque
        cible.add("ifeq label_end" + lab);

        label++;
        node.getBody().accept(this);// faire
        cible.add("goto label_loop" + lab);
        cible.add("label_end" + lab + ":");

        return null;
    }

    public Object visit(Tilda node) {
        node.getOperand().accept(this);

        pushIfIdf(node.getOperand());
        cible.add("ldc -1");
        cible.add("ixor");
        return null;
    }

    public Object visit(Boole node) {
        cible.add("ldc " + node.getValue());
        return null;
    }

}
