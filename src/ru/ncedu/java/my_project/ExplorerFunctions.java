package ru.ncedu.java.my_project;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Zotov S. on 22.12.2016.
 */
public class ExplorerFunctions implements ProjectObjects, Constants {
    @Description(description = "завершить работу консоли")
    protected void exit (){
        console.toFinishProgram();
    }

    @Description(description = "вывод названия всех доступных функций с описаниями")
    protected void help() {
        System.out.println("Allowable commands:");
        Class clazz = this.getClass();
        Method methods [] = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print(method.getName());
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation : annotations){
                if(annotation instanceof Description){
                    Description description = (Description) annotation;
                    System.out.println(" - " + description.description());
                }
            }
        }
        System.out.println();
        console.select();
    }

    @Description(description = "добавляет нового пользователя")
    protected void sign_up (){
        System.out.println("Write your login");
        String login = scanner.nextLine();
        System.out.println("Write your password");
        String password = scanner.nextLine();
    }
}
