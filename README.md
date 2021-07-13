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

- tokenizer.rs

We will choose the &str type, as we do not need to own the value or dynamically increase the size of the expression. This is because the user will provide the arithmetic expression once, and then the expression won't change for the duration of processing.

We have to conver the string slict into an iterator (std::str::Chars), which not only allows us to iterate through the string slice to read each character, but also allows us to peek ahead and see value of the character following that.

Structs in Rust can hold references. But Rust needs explicit lifetimes to be specified when working with structs that contain references.

```
use std::iter::Peekable;
use std::str::Chars;

pub struct Tokenizer<`a> {
  expr: Peekable<Chars<'a>>
}
```
the lifetime annotation tells the Rust compiler that any reference to the Tokenizer struct cannot outlive the reference to the characters it contains.

The Rust compiler (specifically, the borrow checker) verifies that the lifetime of the reference is not longer than the lifetime of the underlying value pointed to by the reference.

- token.rs: 

data structures for token and opertor precedence
```
  #[derive(Debug, PartialEq, Clone)]
  pub enum Token {
      Add,
      Subtract,
      Multiply,
      Divide,
      Caret,
      LeftParen,
      RightParen,
      Num(f64),
      EOF,
  }
```

```
// Order of operators as per operator precedence rules (low to high)

#[derive(Debug, PartialEq, PartialOrd)]
/// Defines all the OperPrec levels, from lowest to highest.
pub enum OperPrec {
    DefaultZero,
    AddSub,
    MulDiv,
    Power,
    Negative,
}

```

- parser.rs 

The parser uses the Tokenizer outputs to construct an overall AST, which is a hierarchy of nodes
```
pub struct Parser<'a> {
    tokenizer: Tokenizer<'a>,
    current_token: Token,
}
```

- ast.rs
AST is a tree of nodes with each node representing a token (a number or an arithmetic operator).
```
  #[derive(Debug, Clone, PartialEq)]
  pub enum Node {
      Add(Box<Node>, Box<Node>),
      Subtract(Box<Node>, Box<Node>),
      Multiply(Box<Node>, Box<Node>),
      Divide(Box<Node>, Box<Node>),
      Caret(Box<Node>, Box<Node>),
      Negative(Box<Node>),
      Number(f64),
  }
```
Each of these nodes is stored in a boxed data structure, which means the actual data value for each node is stored in the **heap memory**, while the pointer to each of the nodes is stored in a Box variable as part of the Node enum.


