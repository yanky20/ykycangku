package org.yky.test.anno;

import org.springframework.context.annotation.Bean;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * Created by hp on 2018/3/27.
 */
public class TestAno {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
//        AnoCon a = (AnoCon) getBean("org.yky.test.anno.AnoCon");
//        System.out.println(a.getDefname() + "&" + a.getId() + "&" + a.getName());
    }

    public static Object getBean(String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return setFileFromAno(Class.forName(beanName).newInstance());
    }

    /**
     * 根据注解注入属性
     */
    public static Object setFileFromAno(Object bean) throws IllegalAccessException {
        //反射获取对象中所有的属性
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field f : fields) {
            //找到所有属性中的已注解项
            if (f != null && f.isAnnotationPresent(YkyAno.class)) {
                YkyAno no = f.getAnnotation(YkyAno.class);
                //允许访问private字段
                f.setAccessible(true);
                //把引用对象注入属性
                //判断注解中选择的item，注入属性
                switch (f.getName()) {
                    case "name":
                        f.set(bean, no.name());
                        break;
                    case "id":
                        f.set(bean, no.id());
                        break;
                    case "defname" :
                        f.set(bean, no.name());
                    case "yky" :
                        f.set(bean, 1);
                }
            }
        }
        return bean;
    }
}
