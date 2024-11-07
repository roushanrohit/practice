package stacks_and_queues;

public class StackUsingLinkedListUse {

    public static void main(String[] args) throws StackEmptyException {

        StackUsingLinkedList<Integer> stack = new StackUsingLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        reverseStack3(stack, new StackUsingLinkedList<>());
        // Prints in LIFO order
        System.out.println("Number of elements in the stack: " + stack.size());
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println("Size after popping all the elements: " + stack.size());

        String expression = "()()(){";
        System.out.println("Parentheses in the expression are balanced: " + checkBalancedParentheses(expression));

        String expression2 = "(a+(b)+c)";
        System.out.println("Are there any redundant brackets in the expression: " + checkRedundantBrackets(expression2));

        String expression3 = "}}}{";
        System.out.println("Minimum number of bracket reversals to make the expression balanced: " + minimumBracketReversals(expression3));
    }

    private static int minimumBracketReversals(String expression) throws StackEmptyException {

        if(expression.isEmpty()) return 0;
        if(expression.length() % 2 != 0) return -1; // cannot be balanced

        StackUsingLinkedList<Character> stack = new StackUsingLinkedList<>();

        // first remove the balanced part
        int i = 0;
        while (i < expression.length()){
            if(expression.charAt(i) == '}'){
                if(!stack.isEmpty() && stack.top() == '{') stack.pop();
                else stack.push(expression.charAt(i));
            } else stack.push(expression.charAt(i));
            i++;
        }

        // if stack is empty, expression was already balanced -- return 0
        int counter = 0;
        while (!stack.isEmpty()) {
            Character c1 = stack.pop();
            Character c2 = stack.pop();
            if(c1.equals(c2)) counter += 1;
            else counter += 2;
        }
        return counter;
    }

    private static boolean checkRedundantBrackets(String expression) throws StackEmptyException {

        StackUsingLinkedList<Character> stack = new StackUsingLinkedList<>();
        int i = 0;
        while (i < expression.length()){

            if(expression.charAt(i) == ')' || expression.charAt(i) == '}'){

                int counter = 0;
                while (stack.top() != '(' && stack.top() != '{'){
                    counter++;
                    stack.pop();
                }
                if(counter <= 1) return true;
                else stack.pop();
            } else stack.push(expression.charAt(i));
            i++;
        }

        return false;
    }

    /*
        Pop the first element, ask recursion to reverse the rest of the stack
        Insert the popped element at the bottom of the stack
     */
    private static void reverseStack2(StackUsingLinkedList<Integer> stack, StackUsingLinkedList<Integer> extra) throws StackEmptyException {

        // base case
        if(stack.isEmpty()) return;

        int elem = stack.pop();

        // reverse the remaining stack
        reverseStack(stack);

        while (!stack.isEmpty()) extra.push(stack.pop());
        // insert the popped element at the bottom of the stack
        stack.push(elem);
        // insert the remaining elements
        while (!extra.isEmpty()) stack.push(extra.pop());
    }

    private static void reverseStack3(StackUsingLinkedList<Integer> stack, StackUsingLinkedList<Integer> extra) throws StackEmptyException {

        // base case
        if(stack.isEmpty()) return;

        int elem = stack.pop();

        // reverse the remaining stack
        reverseStack(stack);

        insertAtBottom(stack, elem);
    }

    private static void insertAtBottom(StackUsingLinkedList<Integer> stack, int elem) throws StackEmptyException {

        if(stack.isEmpty()) stack.push(elem);
        else {
            int top = stack.pop();
            insertAtBottom(stack, elem);
            stack.push(top);
        }
    }

    private static void reverseStack(StackUsingLinkedList<Integer> stack) throws StackEmptyException {

        StackUsingLinkedList<Integer> helperStack1 = new StackUsingLinkedList<>();
        StackUsingLinkedList<Integer> helperStack2 = new StackUsingLinkedList<>();

        while (!stack.isEmpty()){
            helperStack1.push(stack.pop());
        }

        while (!helperStack1.isEmpty()){
            helperStack2.push(helperStack1.pop());
        }

        while (!helperStack2.isEmpty()){
            stack.push(helperStack2.pop());
        }
    }

    private static boolean checkBalancedParentheses(String expression) throws StackEmptyException {

        if(expression.isEmpty()) return true;

        StackUsingLinkedList<Character> stack = new StackUsingLinkedList<>();
        int i = 0;
        while(i < expression.length()){
            if(expression.charAt(i) == '(' || expression.charAt(i) == '{' || expression.charAt(i) == '['){
                stack.push(expression.charAt(i));
            }
            if(expression.charAt(i) == ')' || expression.charAt(i) == '}' || expression.charAt(i) == ']'){

                // encountered closing parentheses at the start
                if(stack.isEmpty()) return false;

                Character matchingOpeningParentheses = getMatchingOpeningParentheses(expression.charAt(i));
                if(stack.top() == matchingOpeningParentheses){
                    // remove the opening parentheses
                    stack.pop();
                } else return false; // does not have a matching opening parentheses at the top of the stack
            }
            i++;
        }

        /* if the stack is non-empty, it means number of opening parentheses
            were more than the number of closing parentheses
         */
        return stack.isEmpty();
    }

    private static Character getMatchingOpeningParentheses(Character c) {
        return switch (c) {
            case ')' -> '(';
            case '}' -> '{';
            case ']' -> '[';
            default -> null;
        };
    }
}
