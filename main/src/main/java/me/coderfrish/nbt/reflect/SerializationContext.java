package me.coderfrish.nbt.reflect;

import me.coderfrish.nbt.type.TagObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SerializationContext {
    private final TagObject tagObject = new TagObject();
    private final Object object;
    private final Class<?> objectClass;

    public SerializationContext(Object object) throws Exception {
        this.object = object;
        this.objectClass = object.getClass();
        this.serialize();
    }

    private void serialize() throws Exception {
        if (!objectClass.isAnnotationPresent(Serialization.class)) {
            throw new RuntimeException("Object class " + objectClass.getName() + " is not annotated with Serialization");
        }

        if (!isPlainClass(objectClass)) {
            throw new RuntimeException("Object class " + objectClass.getName() + " is not a plain class");
        }

        At type = objectClass.getAnnotation(Serialization.class).value();
        switch (type) {
            case J_Class: {
                for (Field field : objectClass.getDeclaredFields()) {
                    if (!isComplianceClass(field.getModifiers())) throw new RuntimeException(objectClass.getName() + " is a compliance class");

                    String key = field.getName();
                    Object value = field.get(object);

                    tagObject.set(key, value);
                }
            }

            case K_Class:
            case K_Data: {
                for (Method method : objectClass.getDeclaredMethods()) {
                    if (!isComplianceClass(method.getModifiers())) throw new RuntimeException(objectClass.getName() + " is a compliance class");

                    String name = method.getName();
                    if (name.startsWith("get")) {
                        if (!(method.getParameterCount() == 0)) continue;

                        name = name.substring(3);
                        char firstChar = name.charAt(0);
                        name = Character.toLowerCase(firstChar) + name.substring(1);

                        tagObject.set(name, method.invoke(object));
                    }
                }
            }
        }
    }

    private static boolean isComplianceClass(int modifier) {
        return Modifier.isPublic(modifier) && (!Modifier.isStatic(modifier) || !Modifier.isFinal(modifier) || !Modifier.isNative(modifier));
    }

    private static boolean isPlainClass(Class<?> clazz) {
        return !clazz.isAnnotation() && !clazz.isInterface() && !clazz.isEnum() && !Modifier.isAbstract(clazz.getModifiers());
    }

    public TagObject toTagObject() {
        return tagObject;
    }
}
