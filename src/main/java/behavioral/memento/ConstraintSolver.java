package behavioral.memento;

import structural.proxy.Graphic;

/**
 * originator
 */
public class ConstraintSolver {
    private static ConstraintSolver instance;

    private ConstraintSolver() {
        instance = null;
    }

    public void solve() {
    }

    public void addConstraint(Graphic startConnection, Graphic endConnection) {
    }

    public void removeConstraint(Graphic startConnection, Graphic endConnection) {
    }

    /**
     * memento
     */
    public class ConstraintSolverMemento {
        private ConstraintSolverMemento(ConstraintSolver constraintSolver) {
        }
    }

    public ConstraintSolverMemento createMemento() {
        return new ConstraintSolverMemento(this);
    }

    public void setMemento(ConstraintSolverMemento memento) {
    }

    public static ConstraintSolver getInstance() {
        if (instance == null) {
            instance = new ConstraintSolver();
        }
        return instance;
    }
}