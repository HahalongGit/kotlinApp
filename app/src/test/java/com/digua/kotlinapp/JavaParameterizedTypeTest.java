package com.digua.kotlinapp;

import android.util.Log;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * TODO:describe what the class or interface does.
 *
 * @author RunningDigua
 * @date 2021/3/23
 */
public class JavaParameterizedTypeTest {

    @Test
    public void parameterizedTypeTest(){

        try {
            Field fidleMapEntry = ParameterizedTypeTest.class.getDeclaredField("mapEntry");
            Type genericType = fidleMapEntry.getGenericType();
            java.lang.reflect.ParameterizedType parameterizedTypeMapEntry = (java.lang.reflect.ParameterizedType)genericType;
            Type ownerType = parameterizedTypeMapEntry.getOwnerType();
            String typeName = ownerType.getTypeName();
            Log.e("TAG","typeName:"+typeName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
