package basic_calculator_java;

public class ExpressionToken {

  enum Type {
    UNKNOWN,
    NUMBER,
    OPERATOR,
    PARENTHESIS,
  }

  private Type type;
  String token;

  ExpressionToken(Type type, String token) {
    this.type = type;
    this.token = token;
  }

  public Type getType() {
    return this.type;
  }

  public int inValue() {
    return Integer.parseInt(token);
  }

  public char charValue() {
    return token.charAt(0);
  }
}
