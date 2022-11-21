import java.util.Vector;
import java.io.FileReader;
import java.io.FileWriter;

import java_cup.runtime.Symbol;
//import DeclarationProgramme;
//import SourceCodeGenerator;
//import bin.GenerateurByteCode;
//import SemanticAnalyzer;

public class hepialc {
    public static void main(String[] arg) {
        try {
            FileReader myFile = new FileReader(arg[0]);
            HepialLexer myLex = new HepialLexer(myFile);
            parser myP = new parser(myLex);
            try {
                // on lance le parsing, et on récupère l'AST
                DeclarationProgramme program = (DeclarationProgramme) myP.parse().value;
                if (program == null)
                    // Aie, pas d'AST received ....
                    return;

                //System.out.println("ok, c'est retourné au prg java !!!!");
                // Lecture de l'AST selon design pattern Visitor
                // Et affichage du code d'origine
                SourceCodeGenerator sourceGenerator = new SourceCodeGenerator();
                System.out.println("\n___________ code source selon la lecture de l'arbre abstrait : _______________");
                program.accept(sourceGenerator);
                System.out.println(sourceGenerator.getCode());

                // analyse sémantique
                System.out.println("\n___________ analyse sémantique : _______________");
                SemanticAnalyzer analyseur = new SemanticAnalyzer();
                program.accept(analyseur);

                // production du code
                System.out.println("\n___________ génération du bytecode : _______________");
                ByteCodeGenerator byteCodeGenerator = new ByteCodeGenerator(analyseur.getTDS());
                program.accept(byteCodeGenerator);

                FileWriter myWriter = new FileWriter(arg[1]);
                myWriter.write(byteCodeGenerator.getByteCode());
                myWriter.close();
                System.out.println(byteCodeGenerator.getByteCode());
                //System.out.println(arg[1]);

            } catch (Exception e) {
                // le contenu du fichier est incorrect
                System.out.println("Parse error: " + e);
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("invalid file");
        }
    }
}
