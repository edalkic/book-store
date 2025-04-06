package com.example.bookstore.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil {

    private static ApplicationContext context;

    private BeanUtil(ApplicationContext context) {
        BeanUtil.context = context;
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {

        return context.getBean(clazz);
    }
}
