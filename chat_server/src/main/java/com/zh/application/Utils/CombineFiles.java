package com.zh.application.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombineFiles {

    public static void copyByName(Object src, Object target) {
        if (src == null || target == null) {
            return;
        }
        try {
            Map<String, Field> srcFieldMap = new HashMap<String, Field>();
            getAssignableFieldsMap(srcFieldMap,src.getClass());
            Map<String, Field> targetFieldMap = new HashMap<String, Field>();
            getAssignableFieldsMap(targetFieldMap,target.getClass());
            for (String srcFieldName : srcFieldMap.keySet()) {
                Field srcField = srcFieldMap.get(srcFieldName);
                if (srcField == null) {
                    continue;
                }
                // 变量名需要相同
                if (!targetFieldMap.keySet().contains(srcFieldName)) {
                    continue;
                }
                Field targetField = targetFieldMap.get(srcFieldName);
                if (targetField == null) {
                    continue;
                }
                // 类型需要相同
                if (!srcField.getType().equals(targetField.getType())) {
                    continue;
                }

                targetField.set(target,srcField.get(src));
            }
        }catch (Exception e) {
            // 异常
        }
        return ;
    }

    private static void getAssignableFieldsMap(Map<String, Field> fieldMap,Class obj) {
        if (obj == null) {
            return ;
        }

        //Map<String, Field> fieldMap = new HashMap<String, Field>();
        if(obj != null) {
            for (Field field : obj.getDeclaredFields()) {
                // 过滤不需要拷贝的属性
                if (Modifier.isStatic(field.getModifiers())
                        || Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                fieldMap.put(field.getName(), field);
            }
            obj = obj.getSuperclass();
            getAssignableFieldsMap(fieldMap,obj);
        }

    }


    /**
     * 获取所有属性包括父类
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object){
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

}

