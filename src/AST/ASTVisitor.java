
public interface ASTVisitor {
    
    Object visit(Affectation node);
    Object visit(Bloc node);
    Object visit(Condition node);
    Object visit(DeclarationConstant node);
    Object visit(DeclarationProgramme node);
    Object visit(DeclarationVariable node);
    Object visit(Ecrire node);
    Object visit(Lire node);
    Object visit(Tilda node);
    Object visit(Type node);
    Object visit(Pour node);
    Object visit(Tantque node);
    //type
    Object visit(Chaine node);
    Object visit(Idf node);
    Object visit(Nombre node);
    Object visit(Boole node);
    // Object visit(Faux node);
    // Object visit(Vrai node);
    //math
    Object visit(Addition node);
    Object visit(Division node);
    Object visit(Moins node);
    Object visit(Multiplication node);
    Object visit(Soustraction node);
    Object visit(Parentheses node);
    //cond
    Object visit(Different node);
    Object visit(Egal node);
    Object visit(InfEgal node);
    Object visit(Inferieur node);
    Object visit(SupEgal node);
    Object visit(Superieur node);
    Object visit(Et node);
    Object visit(Non node);
    Object visit(Ou node);

}

















































































