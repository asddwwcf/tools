package com.mine.tool.common.exception;

import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 功能 :
 */

public abstract class Assert {
    public Assert() {
    }

    public static void state(boolean expression, Object code, String message) {
        if (!expression) {
            throw createException(code, message);
        }
    }

    public static void state(boolean expression, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (!expression) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void state(boolean expression, Supplier<? extends X> exceptionSupplier) throws X {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

    public static void isTrue(boolean expression, Object code, String message) {
        if (!expression) {
            throw createException(code, message);
        }
    }

    public static void isTrue(boolean expression, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (!expression) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void isTrue(boolean expression, Supplier<? extends X> exceptionSupplier) throws X {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

    public static void isNull(@Nullable Object object, Object code, String message) {
        if (object != null) {
            throw createException(code, message);
        }
    }

    public static void isNull(@Nullable Object object, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (object != null) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void isNull(@Nullable Object object, Supplier<? extends X> exceptionSupplier) throws X {
        if (object != null) {
            throw exceptionSupplier.get();
        }
    }

    public static void notNull(@Nullable Object object, Object code, String message) {
        if (object == null) {
            throw createException(code, message);
        }
    }

    public static void notNull(@Nullable Object object, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (object == null) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void notNull(@Nullable Object object, Supplier<? extends X> exceptionSupplier) throws X {
        if (object == null) {
            throw exceptionSupplier.get();
        }
    }

    public static void isBlank(@Nullable String object, Object code, String message) {
        if (object == null || object.length() == 0 || object.trim().length() == 0) {
            throw createException(code, message);
        }
    }

    public static void isBlank(@Nullable String object, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (object == null || object.length() == 0 || object.trim().length() == 0) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void isBlank(@Nullable String object, Supplier<? extends X> exceptionSupplier) throws X {
        if (object == null || object.length() == 0 || object.trim().length() == 0) {
            throw exceptionSupplier.get();
        }
    }

    public static void notBlank(@Nullable String object, Object code, String message) {
        if (object != null && object.length() > 0 && object.trim().length() > 0) {
            throw createException(code, message);
        }
    }

    public static void notBlank(@Nullable String object, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (object != null && object.length() > 0 && object.trim().length() > 0) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void notBlank(@Nullable String object, Supplier<? extends X> exceptionSupplier) throws X {
        if (object != null && object.length() > 0 && object.trim().length() > 0) {
            throw exceptionSupplier.get();
        }
    }

    public static void hasLength(@Nullable String text, Object code, String message) {
        if (!StringUtils.hasLength(text)) {
            throw createException(code, message);
        }
    }

    public static void hasLength(@Nullable String text, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (!StringUtils.hasLength(text)) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void hasLength(@Nullable String text, Supplier<? extends X> exceptionSupplier) throws X {
        if (!StringUtils.hasLength(text)) {
            throw exceptionSupplier.get();
        }
    }

    public static void hasText(@Nullable String text, Object code, String message) {
        if (!StringUtils.hasText(text)) {
            throw createException(code, message);
        }
    }

    public static void hasText(@Nullable String text, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (!StringUtils.hasText(text)) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void hasText(@Nullable String text, Supplier<? extends X> exceptionSupplier) throws X {
        if (!StringUtils.hasText(text)) {
            throw exceptionSupplier.get();
        }
    }

    public static void doesNotContain(@Nullable String textToSearch, String substring, Object code, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw createException(code, message);
        }
    }

    public static void doesNotContain(@Nullable String textToSearch, String substring, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void doesNotContain(@Nullable String textToSearch, String substring, Supplier<? extends X> exceptionSupplier) throws X {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch.contains(substring)) {
            throw exceptionSupplier.get();
        }
    }

    public static void notEmpty(@Nullable Object[] array, Object code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw createException(code, message);
        }
    }

    public static void notEmpty(@Nullable Object[] array, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (ObjectUtils.isEmpty(array)) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void notEmpty(@Nullable Object[] array, Supplier<? extends X> exceptionSupplier) throws X {
        if (ObjectUtils.isEmpty(array)) {
            throw exceptionSupplier.get();
        }
    }

    public static void noNullElements(@Nullable Object[] array, Object code, String message) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw createException(code, message);
                }
            }
        }

    }

    public static void noNullElements(@Nullable Object[] array, Supplier<Object> code, Supplier<String> messageSupplier) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw createException(code, nullSafeGet(messageSupplier));
                }
            }
        }

    }

    public static <X extends Throwable> void noNullElements(@Nullable Object[] array, Supplier<? extends X> exceptionSupplier) throws X {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw exceptionSupplier.get();
                }
            }
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, Object code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw createException(code, message);
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, Object code, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void notEmpty(@Nullable Collection<?> collection, Supplier<? extends X> exceptionSupplier) throws X {
        if (CollectionUtils.isEmpty(collection)) {
            throw exceptionSupplier.get();
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, Object code, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw createException(code, message);
        }
    }

    public static void notEmpty(@Nullable Map<?, ?> map, Object code, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(map)) {
            throw createException(code, nullSafeGet(messageSupplier));
        }
    }

    public static <X extends Throwable> void notEmpty(@Nullable Map<?, ?> map, Supplier<? extends X> exceptionSupplier) throws X {
        if (CollectionUtils.isEmpty(map)) {
            throw exceptionSupplier.get();
        }
    }

    public static void isInstanceOf(Class<?> type, @Nullable Object obj, Object code, String message) {
        notNull(type, code, (String) "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, code, message);
        }

    }

    public static void isInstanceOf(Class<?> type, @Nullable Object obj, Object code, Supplier<String> messageSupplier) {
        notNull(type, code, (String) "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, code, nullSafeGet(messageSupplier));
        }

    }

    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, Object code, String message) {
        notNull(superType, code, (String) "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, code, message);
        }

    }

    public static void isAssignable(Class<?> superType, @Nullable Class<?> subType, Object code, Supplier<String> messageSupplier) {
        notNull(superType, code, (String) "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, code, nullSafeGet(messageSupplier));
        }

    }

    private static void instanceCheckFailed(Class<?> type,
                                            @Nullable Object obj,
                                            @Nullable Object code,
                                            @Nullable String msg) {
        String className = obj != null ? obj.getClass().getName() : "null";
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }

        if (defaultMessage) {
            result = result + "Object of class [" + className + "] must be an instance of " + type;
        }

        throw createException(code, result);
    }

    private static void assignableCheckFailed(Class<?> superType, @Nullable Class<?> subType, Object code, @Nullable String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.hasLength(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }

        if (defaultMessage) {
            result = result + subType + " is not assignable to " + superType;
        }

        throw createException(code, result);
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, @Nullable Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }

    @Nullable
    private static String nullSafeGet(@Nullable Supplier<String> messageSupplier) {
        return messageSupplier != null ? (String) messageSupplier.get() : null;
    }

    private static BaseRuntimeException createException(Object code, String message) {
        return new BaseRuntimeException(code, message);
    }
}

