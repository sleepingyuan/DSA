package com.yuan.solution;


/**
 * 抽象类
 * 抽象类的子类必须实现所有的抽象方法，或者也声明为抽象
 */
public abstract class AbstractBase {
    abstract public void getFirst();
    abstract public void getSecond();
}

abstract class AbstractDerived extends AbstractBase {
    @Override
    public void getFirst() {
        System.out.println("heihei");
    }
}

class Derived extends AbstractBase {
    @Override
    public void getFirst() {

    }

    @Override
    public void getSecond() {

    }
}
