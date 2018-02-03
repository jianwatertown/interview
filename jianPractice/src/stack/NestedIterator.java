package stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {
        addToStackInReserse(nestedList);
    }

    public void addToStackInReserse(List<NestedInteger> list){
        for(int i=list.size()-1;i>=0;i--){
            stack.add(list.get(i));
        }
    }

    @Override
    public Integer next() {
        if(!hasNext()) {return -1;}
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()&&!stack.peek().isInteger()){
            addToStackInReserse(stack.pop().getList());
        }
        return !stack.isEmpty();
    }
}
