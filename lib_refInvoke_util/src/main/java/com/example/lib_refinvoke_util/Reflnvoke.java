package com.example.lib_refinvoke_util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final public class Reflnvoke {


   private Reflnvoke(){
   }


   //反射生成对象
   public static <T> T createObject(String className){

      T t = null;
      try {
         Class c = Class.forName(className);
         t =  createObject(c);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static <T> T createObject(Class c){
      T t = null;
      try {
         return t = (T) c.newInstance();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      } catch (InstantiationException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static <T> T createObject(String className, Class[] paratypes, Object... parameters){

      T t = null;
      try {
         Class c = Class.forName(className);
         t = createObject(c, paratypes, parameters);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static <T> T createObject(Class c, Class[] paratypes, Object parameters){

      T t = null;
      try {
         Constructor constructor = c.getDeclaredConstructor(paratypes);
         t = (T) constructor.newInstance(parameters);
      } catch (NoSuchMethodException e) {
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      } catch (InstantiationException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }

   //反射获取非public字段值，毕竟public可以直接访问的
   public static <T> T getStaticFieldValue(String className, String fieldName){

      T t = null;
      try {
         Class c = Class.forName(className);
         t = getFieldValue(c, fieldName);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static <T> T getSaticFieldValue(Class c, String fieldName){

      T t = null;
      try {
         Field field = c.getDeclaredField(fieldName);
         field.setAccessible(true);
         t = (T) field.get(null);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static <T> T getFieldValue(Object instance, String fieldName){

      T t = null;
      Class c = instance.getClass();
      try {
         Field field =  c.getDeclaredField(fieldName);
         field.setAccessible(true);
         t = (T) field.get(instance);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }

   //对非public字段进行赋值
   public static void setStaticFieldValue(Class c, String fieldName, Object value){
      try {
         Field field = c.getDeclaredField(fieldName);
         field.setAccessible(true);
         field.set(null, value);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }
   public static void setStaticFieldValue(String className, String fieldName, Object value){
      try {
         Class c = Class.forName(className);
         setStaticFieldValue(c, fieldName, value);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
   public static void setFieldValue(Object instance, String fieldName, Object value){

      Class c = instance.getClass();
      try {
         Field field = c.getDeclaredField(fieldName);
         field.setAccessible(true);
         field.set(instance, value);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }
   public static void setSuperClassFieldValue(Class c, Object instance, String fieldName, Object value){
      try {
         Field field = c.getDeclaredField(fieldName);
         field.setAccessible(true);
         field.set(instance, value);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }

   //调用非public方法
   public static <T> T invokeStaticMethod(Class c, String methodName,  Class[] parameters, Object... objects){
      T t = null;
      try {
         Method method = getMethod(c, methodName, parameters);
         method.setAccessible(true);
         t = (T) method.invoke(null, objects);
      } catch (NoSuchMethodException e) {
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static void invokeVoidStaticMethod(Class c, String methodName,   Class[] parameters, Object... objects){
      try {
         Method method = getMethod(c, methodName, parameters);
         method.invoke(null, objects);
      } catch (NoSuchMethodException e) {
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }
   public static <T> T invokeMethod(Object instance, String methodName, Class[] parameters, Object... values){
     T t = null;
      try {
         Method method = getMethod(instance.getClass(), methodName, parameters);
         method.setAccessible(true);
         return t = (T) method.invoke(instance, values);
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }finally {
         return t;
      }
   }
   public static void invokeVoidMethod(Object instance, String methodName, Class[] parameters, Object... values){
      try {
         Method method = getMethod(instance.getClass(), methodName, parameters);
         method.setAccessible(true);
         method.invoke(instance, values);
      } catch (NoSuchMethodException e) {
         e.printStackTrace();
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }
   private static Method getMethod(Class c, String methodName, Class... parameters) throws NoSuchMethodException {
      return c.getDeclaredMethod(methodName, parameters);
   }
}
