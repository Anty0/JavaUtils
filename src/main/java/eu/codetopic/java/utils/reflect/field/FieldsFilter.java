package eu.codetopic.java.utils.reflect.field;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public interface FieldsFilter {

    Class<?> getStartClass();

    Class<?> getStopSuperClass();

    boolean isThrowExceptions();

    boolean addField(@Nullable Field field, @NotNull Class<?> fieldType);

    boolean searchFieldsInFieldClass(@Nullable Field field, @NotNull Class<?> fieldType);
}
