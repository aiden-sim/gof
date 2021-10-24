package behavioral.interpreter;

/**
 * AbstractExpression
 */
public interface BooleanExp {
    boolean evaluate(Context context);

    BooleanExp replace(String variable, BooleanExp expression);

    BooleanExp copy();
}