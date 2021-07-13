/*

224 - Basic Calculator【FLAG高频精选面试题讲解】
https://www.youtube.com/watch?v=9c0WHgIsk5g&ab_channel=%E6%9D%A5Offer-LaiOffer

（）, + , -

*/

package basic_calculator_java;

public class ExpressionTokenizer {

  int pos;
  char[] expression;

  public ExpressionTokenizer(String expression) {
    this.expression = expression.toCharArray();
    this.pos = 0;
  }

  public ExpressionToken nextToken() {
    StringBuilder token = new StringBuilder();
    boolean endOfToken = false;
    ExpressionToken.Type type = ExpressionToken.Type.UNKNOWN;
    if (!this.hasMoreTokens()){
      return null;
    }
    while (!endOfToken && hasMoreTokens()) {
      // skip the space
      while (expression[pos] == ' ' && hasMoreTokens()) 
      {
        pos++;
      }
      switch (expression[pos]) {
        case '+':
        case '-':
        case '*':
        case '/':
          if (type != ExpressionToken.Type.NUMBER) {
            type = ExpressionToken.Type.OPERATOR;
            token.append(expression[pos]);
            pos++;
          }
          endOfToken = true;
          break;
        case ' ':
          endOfToken = true;
          pos++;
          break;
        default:
          if (Character.isDigit(expression[pos]) || expression[pos] == '.') {
            token.append(expression[pos]);
            type = ExpressionToken.Type.NUMBER;
          } else {
            System.out.println("Systax error at position: " + pos);
          }
          pos++;
          break;
      }
    }
    return new ExpressionToken(type, token.toString());
  }

  public boolean hasMoreTokens() {
    return pos < expression.length;
  }
}
