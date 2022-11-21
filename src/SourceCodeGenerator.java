

/*
 * Source code generator that walks the AST and generates code source from the AST
 *
 * @author Stephane Malandain 
 */

import java.util.*;

public class SourceCodeGenerator implements ASTVisitor{

    /**
     * Generated code
     */
    private String code = "";
    /**
     * Depth level (tabulations)
     */
    private int level = 0;
    /**
     * If we are currently declaring function parameters
     */
    private boolean isParameterDeclaration = false;

    /**
     * Adds tabulation with current level
     */
    private void addTabulation(){
        addTabulation(level);
    }

    /**
     * Adds tabulation with spacified level
     */
    private void addTabulation(int level){
        for (int i = 0;i < level; i++)
            code += "    ";
    }

    public Object visit(Addition node){
        node.getGauche().accept(this);
        code += " + ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Affectation node){
        node.getDestination().accept(this);
        code += " = ";
        node.getSource().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Bloc node){
        for (Instruction inst: node.getInstructions()){
            code += "\n";
            addTabulation();
            inst.accept(this);
        }
        return null;
    }

    public Object visit(Chaine node){
        code += node.getValue();
        return null;
    }

    public Object visit(Condition node){
        code += "si ";
        node.getCondition().accept(this);
        code += " alors";
        level += 1;
        node.getBody().accept(this);
        if (node.hasElse()){
            code += "\n";
            addTabulation(level - 1);
            code += "sinon";
            node.getElse().get().accept(this);
        }
        level -= 1;
        code += "\n";
        addTabulation();
        code += "finsi";
        return null;
    }

    public Object visit(DeclarationConstant node){
        //Symbole sym = TDS.getInstance().identifier(new Entree(node.getIdentifier().getNom()));

        code += "constante ";
        node.getType().accept(this);
        node.getIdentifier().accept(this);
        code += " = ";
        node.getExpression().accept(this);
        code += ";";
        return null;
    }

    public Object visit(DeclarationProgramme node){
        /* TDS.getInstance().resetBlocNumber();

        TDS.getInstance().entreeBloc();
        level += 1;
        */
        code += "programme ";
        node.getIdentifier().accept(this);
        node.getDeclaration().accept(this);
        code += "\ndebutprg";
        node.getInstructions().accept(this);
        code += "\nfinprg";

        /*
        TDS.getInstance().sortieBloc();
        level -= 1;
        */
        return null;
    }

    public Object visit(DeclarationVariable node){
        //Symbole sym = TDS.getInstance().identifier(new Entree(node.getIdentifier().getNom()));

        node.getType().accept(this);
        node.getIdentifier().accept(this);
        if (!isParameterDeclaration)
            code += ";";
        return null;
    }

    public Object visit(Type node) {
        code += node.getName();
        code += " ";
        return null;
    }

    public Object visit(Different node){
        node.getGauche().accept(this);
        code += " <> ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Division node){
        node.getGauche().accept(this);
        code += " / ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Ecrire node){
        code += "ecrire ";
        node.getSource().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Egal node){
        node.getGauche().accept(this);
        code += " == ";
        node.getDroite().accept(this);
        return null;
    }

    
    public Object visit(Et node){
        node.getGauche().accept(this);
        code += " et ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Idf node){
        code += node.getNom();
        return null;
    }

    public Object visit(InfEgal node){
        node.getGauche().accept(this);
        code += " <= ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Inferieur node){
        node.getGauche().accept(this);
        code += " < ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Lire node){
        code += "lire ";
        node.getDestination().accept(this);
        code += ";";
        return null;
    }

    public Object visit(Moins node){
        code += "-";
        node.getOperand().accept(this);
        return null;
    }

    public Object visit(Nombre node){
        code += Integer.toString(node.getValue());
        return null;
    }

    public Object visit(Non node){
        code += "non ";
        node.getOperand().accept(this);
        return null;
    }

    public Object visit(Ou node){
        node.getGauche().accept(this);
        code += " ou ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Parentheses node){
        code += "(";
        node.getExpression().accept(this);
        code += ")";
        return null;
    }

    public Object visit(Pour node){
        code += "pour ";
        node.getIterator().accept(this);
        code += " allantde ";
        node.getFrom().accept(this);
        code += " a ";
        node.getTo().accept(this);
        code+= " faire";
        level += 1;
        node.getBody().accept(this);
        level -= 1;
        code += "\n";
        addTabulation();
        code += "finpour";
        return null;
    }

    public Object visit(Multiplication node){
        node.getGauche().accept(this);
        code += " * ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Soustraction node){
        node.getGauche().accept(this);
        code += " - ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(SupEgal node){
        node.getGauche().accept(this);
        code += " >= ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Superieur node){
        node.getGauche().accept(this);
        code += " > ";
        node.getDroite().accept(this);
        return null;
    }

    public Object visit(Tantque node){
        code += "tantque ";
        node.getCondition().accept(this);
        code += " faire";
        level += 1;
        node.getBody().accept(this);
        level -= 1;
        code += "\n";
        addTabulation();
        code += "fintantque";
        return null;
    }

    public Object visit(Tilda node){
        code += "~";
        node.getOperand().accept(this);
        return null;
    }

    public Object visit(Boole node){
        code += node.getString();
        return null;
    }

    public String getCode(){
        return code;
    }
}
