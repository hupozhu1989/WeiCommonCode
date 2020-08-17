package com.wei.interview.a03_passvalue;

/**
 * 栈管运行 堆管存储
 * @author weizhenchao
 * @version 1.0
 * @date：2020/3/27
 */
public class Test02 {
    public void changeValue1(int age){
        age = 30;
    }
    public void changeValue2(com.wei.interview.a03_passvalue.Person person){
        person.setPersonName("xxx");
    }
    public void changeValue3(String str){
        str = "xxx";
    }
    public static void main(String[] args) {
        Test02 test = new Test02();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age------"+age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName------"+person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("str------"+str);
    }
}

