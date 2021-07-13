package basic_calculator_java;

import java.util.ArrayDeque;
import java.util.Deque;

/*
  Use a stack to maintain the execution order
  Use a separate stack to maintain operands

  stack is deprecated  所以需要用Deque<Integer> stack = new ArrayDeque<Integer>(); 
  https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html
*/
class Calculator {

  public int calculate(String expression) {
    // <operator, level>
    Deque<OperatorLevel> operatorStack = new ArrayDeque<OperatorLevel>();
    Deque<Integer> operandStack = new ArrayDeque<Integer>();

    int parenthesisLevel = 0;
    ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
    for (
      ExpressionToken token = tokenizer.nextToken();
      token != null;
      token = tokenizer.nextToken()
    ) {
      switch (token.getType()) {
        case NUMBER:
          operandStack.addLast(token.inValue());
          break;
        case PARENTHESIS:
          char parenthesis = token.charValue();
          if(parenthesis == '(')
          {
            parenthesisLevel++;
          }
          else{  // )
            parenthesisLevel--;
          }
        case OPERATOR:
          while(!operatorStack.isEmpty() && operatorStack.getLast().getLevel()>=parenthesisLevel){
            evaluateTopOperator(operatorStack, operandStack);
          }  

          operatorStack.addLast(new OperatorLevel(token.charValue(), parenthesisLevel));
        default:
          break;
      } 

      while(!operatorStack.isEmpty()) {
        evaluateTopOperator(operatorStack, operandStack);
      }
    }
    return operandStack.getLast();
  }

  public void evaluateTopOperator(Deque<OperatorLevel> operatorStack, Deque<Integer> operandStack) {
    int rightOperand = operandStack.removeLast();
    int leftOperand = operandStack.removeLast();
    char op = operatorStack.removeLast().getOperator();

    int result = 0;
    switch(op){
      case '+':
        result = leftOperand + rightOperand;
        break;
      case '-':
        result = leftOperand - rightOperand;
        break;
      default :
        assert false: String.format("Operation '%c' not supported", op);
    }
    operandStack.addLast(result);
  }
}
