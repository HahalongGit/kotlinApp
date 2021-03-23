package com.digua.kotlinapp;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 测试反射
 *
 * @author RunningDigua
 * @date 2021/3/23
 */
public class Student extends Person<Student> {

    private int grade = 98;

    public int getGrade() {
        return grade;
    }

    @Test
    public void testParameterizedType(){
        Student student = new Student();
        Class clazz = student.getClass();
        //clazz.getSuperclass() 获取该类的父类
        System.out.println("Superclass:"+clazz.getSuperclass());

        //getGenericSuperclass()方法是获取带有泛型的父类
        // Type是Java编程语言中所有类型的公共高级接口，它们包含原始类型，参数化类型，数组类型，类型变量和基本类型
        Type type = clazz.getGenericSuperclass();
        System.out.println("GenericSuperclass:"+type);

        //ParameterizedType 参数化类型，即泛型
        ParameterizedType p = (ParameterizedType) type;

        //获取父类的泛型参数的实际类型
        //getActualTypeArguments()返回的是一个数组，泛型的参数实际可能有多个，用数组包含其多个的参数
        Class c = (Class) p.getActualTypeArguments()[0];//去数组的第1个参数
        System.out.println(c);

        try {
            //获取到泛型的 Class后通过这个class获取其内部的方法
            Method getGrade = c.getDeclaredMethod("getGrade");
            //通过反射获取到方法后调用获取返回结果
            int grade = (int) getGrade.invoke(student,new Object[]{});//调用student类的getGrade方法，参数是空（Object替代）
            System.out.println("grage:"+grade);
            // 结果是：grage:98
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
