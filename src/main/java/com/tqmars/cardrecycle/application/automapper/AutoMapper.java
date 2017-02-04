package com.tqmars.cardrecycle.application.automapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jjh on 1/15/17.
 */
public class AutoMapper {
    public static <TInput,TOutput> TOutput mapping(Class<TOutput> outputClass,TInput input){
        TOutput out;
        Class inputClass = input.getClass();
        Field[] inputFields = inputClass.getDeclaredFields();
        Field[] outputFields = outputClass.getDeclaredFields();
        try {
            out = outputClass.newInstance();
            for (Field field : inputFields) {
                field.setAccessible(true);
                String inputName = field.getName();
                for (Field f : outputFields) {
                    if(inputName.equals(f.getName())){
                        f.setAccessible(true);
                        f.set(out,field.get(input));
                    }
                }
            }
            return out;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static <TInput,TOutput> List<TOutput> mapping(Class<TOutput> outputClass, List<TInput> inputList){
        if(null == inputList || inputList.size() == 0){
            return null;
        }
        List<TOutput> outputList = new ArrayList<>();
        inputList.forEach(in->outputList.add(mapping(outputClass,in)));
        return outputList;
    }
}
