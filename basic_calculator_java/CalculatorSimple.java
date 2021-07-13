package basic_calculator_java;

/*
  No parenthesis
*/
class CalculatorSimple {

  public int calculate(String expression) {
    int result = 0;
    char lastOperator = '+';
   
    ExpressionTokenizer tokenizer = new ExpressionTokenizer(expression);
    for (
      ExpressionToken token = tokenizer.nextToken();
      token != null;
      token = tokenizer.nextToken()
    ) {
      switch (token.getType()) {
        case NUMBER:
          int num = token.inValue();
          if(lastOperator == '+')
          {
            result += num;
          }
          else{
            result -= num;
          }
          break;
        case OPERATOR:
          lastOperator = token.charValue();
        default:
          break;
      } 
    }
    return result;
  }
}
