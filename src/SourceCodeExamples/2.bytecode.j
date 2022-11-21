.class public Program
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 20000
.limit locals 4
.var 0 is x I //entier x
ldc 0 
istore 0 //dépile dans x
.var 1 is y I
ldc 0
istore 1
.var 2 is z Z
ldc 0
istore 2
ldc 23456789
istore 0
ldc 12
ineg
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 0
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
ldc 1
istore 2
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 2
ifeq label_0
ldc "vrai"
goto label_1
label_0:
ldc "faux"
label_1:
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
ldc 0
istore 2
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 2
ifeq label_2
ldc "vrai"
goto label_3
label_2:
ldc "faux"
label_3:
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
exit_label:
return
.end method
