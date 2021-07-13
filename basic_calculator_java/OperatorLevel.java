package basic_calculator_java;

public class OperatorLevel {
  private char operator;
  private int level;

  OperatorLevel(char operator, int level) {
    this.operator = operator;
    this.level = level;
  }

  public int getLevel() {
    return this.level;
  }

  public char getOperator() {
    return this.operator;
  }
}
