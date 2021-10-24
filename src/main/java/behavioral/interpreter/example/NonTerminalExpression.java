package behavioral.interpreter.example;

public class NonTerminalExpression implements AbstractExpression {
    private AbstractExpression subExpression;

    public NonTerminalExpression(AbstractExpression subExpression) {
        this.subExpression = subExpression;
    }

    @Override
    public void interpret(Context context) {
        System.out.println("NonTerminalExpression.interpret(" + context + "):");
        subExpression.interpret(context);
    }
}