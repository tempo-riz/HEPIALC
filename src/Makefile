JAVA=java
JAVAC=javac
JFLEX=jflex
JASMIN=java -jar jasmin.jar
JAVACUP=java-cup-11b.jar
CLASSPATH=$(JAVACUP):.:./AST

FILE_FLEX=hepial.flex
FILE_CUP=hepial.cup
FILE_JAVA_NAME=HepialLexer
FILE_TEST_PRG_NAME=hepialc

TEST_CLASS=hepialc

FILE=demo/2.hepial
JASMIN_FILE=jasmin.j

ifdef SOURCE
	FILE=$(SOURCE)
endif

run :
#make clean pas beau mais indique toutes les erreurs !
	make all
#$(JAVA) -classpath $(CLASSPATH) $(TEST_CLASS) $(FILE) $(JASMIN_FILE)
	$(JASMIN) $(JASMIN_FILE)
	@echo ''
	@echo '___________ output du code : _______________'
	$(JAVA) Program


all : $(FILE) sym.class parser.class $(FILE_JAVA_NAME).class $(FILE_TEST_PRG_NAME).class
	$(JAVA) -classpath $(CLASSPATH) $(TEST_CLASS) $(FILE) $(JASMIN_FILE)

$(FILE_JAVA_NAME).java : $(FILE_FLEX)
	$(JFLEX) $(FILE_FLEX)

sym.java parser.java : $(FILE_CUP)
	$(JAVA) -jar $(JAVACUP) $(FILE_CUP)

%.class : %.java
	$(JAVAC) -classpath $(CLASSPATH) $<

%.class : AST/%.java
	$(JAVAC) -classpath $(CLASSPATH) $<

clean :
	rm -rf *class AST/*class *~ parser.java sym.java $(FILE_JAVA_NAME).java

jasmin :
	@java -jar jasmin.jar jasmin.j