# HEPIAL

A compiler implementation with lexical and syntax analysis, semantic checking, and bytecode generation. Built using Flex and Cup.

# Usage :
`make SOURCE=demo/x.hepial`

don't forget to make clean if a class was changed

# How it works
- Lexical analysis: Using Flex to convert input into symbols.
- Syntax analysis: The Cup generates the Abstract Syntax Tree (AST) by instantiating classes based on the input. Each node in the AST represents an expression (e.g., a+b) or an instruction (e.g., For, Condition).
- Class traversal: Each class inherits from another, all inheriting from ASTNode to allow tree traversal with an "ASTVisitor" pattern.
- Once the tree is constructed:
  - Source code generation: Traversal of the tree to "display" the representation of each node to recreate the source code.
  - Symbol table (TDS): A hashmap storing variable names and types, utilized for semantic analysis and bytecode generation.
  - Semantic analysis: Traversal of the tree to check for forbidden cases, such as duplicate variable declarations (using the TDS) and type checking (e.g., ensuring both sides of an addition are integers).
  - Bytecode generation: Traversal of the tree to write assembly code for each node, using the TDS to determine variable names, types, and counts.
