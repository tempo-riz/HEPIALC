.class public demo
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 20000
.limit locals 5
.var 0 is i I
ldc 0
istore 0
.var 1 is x I
ldc 0
istore 1
.var 2 is div I
ldc 0
istore 2
.var 3 is remainder I
ldc 0
istore 3
ldc 3
istore 1
ldc 2
istore 2
label_loop0:
iload 1
iload 2
if_icmpgt label_0
iconst_0
goto label_1
label_0:
iconst_1
label_1:
ifeq label_end0
iload 1
iload 2
isub
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
goto label_loop0
label_end0:
return
.end method
