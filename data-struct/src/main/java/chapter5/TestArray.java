package chapter5;

import org.junit.Test;

public class TestArray {


    @Test
    public void test(){
        Array<String> stringArray = new Array<>();
        stringArray.add("aaa");
        stringArray.add("bbb");
        stringArray.add("ccc");
        stringArray.add(1,"ddd");
        stringArray.remove("ccc");
        System.out.println(stringArray.toString());
        System.out.println("======================");
        Array<Integer> integerArray = new Array<>();
        integerArray.add(111);
        integerArray.add(222);
        integerArray.add(333);
        System.out.println(integerArray.get(1));
        System.out.println(integerArray.remove(1));
        System.out.println(integerArray.toString());
    }
}
