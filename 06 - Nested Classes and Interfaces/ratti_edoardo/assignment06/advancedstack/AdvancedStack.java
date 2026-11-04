package assignment06.advancedstack;

public interface AdvancedStack {
    /**
     * Pushes the given object onto the stack.
     *
     * @param o the object to be pushed onto the stack
     */
    void push(Object o);

    /**
     * Removes and returns the top element from the stack. If the stack is empty,
     * null will be returned.
     *
     * @return the top element of the stack, or null if the stack is empty
     */
    Object pop();

    /**
     * Retrieves, but does not remove, the top element of the stack. If the stack is empty, null is returned.
     *
     * @return the top element of the stack, or null if the stack is empty
     */
    Object peek();

    /**
     * Returns the number of elements currently in the stack.
     *
     * @return the count of elements present in the stack
     */
    long size();

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    boolean isEmpty();

    /**
     * Iterates over the elements of the stack and evaluates each element using the specified
     * {@code testLogic}. Returns the count of elements for which {@code testLogic} evaluates to true.
     *
     * @param testLogic the logic used to evaluate each element of the stack
     * @return the number of elements in the stack that satisfy the condition specified by {@code testLogic}
     */
    long count(Evaluate testLogic);
}