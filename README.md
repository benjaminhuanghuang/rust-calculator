# arithmetic expression evaluator.
From Practical System Programming for Rust Developers, Chapter 2: A Tour of the Rust Programming Language


## Requirement
- parsing input with/without space, decimal point
- operator precedence
- invalid input: invalide expression, single parenthesis, operator not recognized.



## Design
Command line input -> Scanner -> Lexer(Tokenizer) -> Parser -> Evaluator -> result

The Lexer/Tokenizer splits text into tokens/words

Parser arrange the tokens into a tree structure: AST(abstract syntax tree)

AST  will reflect all operator precedence and priority.

Evaluator evaluate the node of the AST in the right sequence


## modules
- ast.rs
- parser.rs 
- tokenizer.rs
- token.rs: data structures for token and opertor precedence



