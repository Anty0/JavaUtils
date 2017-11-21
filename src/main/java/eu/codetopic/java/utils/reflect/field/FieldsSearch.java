/*
 * javautils
 * Copyright (C)   2017  anty
 *
 * This program is free  software: you can redistribute it and/or modify
 * it under the terms  of the GNU General Public License as published by
 * the Free Software  Foundation, either version 3 of the License, or
 * (at your option) any  later version.
 *
 * This program is distributed in the hope that it  will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied  warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   See the
 * GNU General Public License for more details.
 *
 * You  should have received a copy of the GNU General Public License
 * along  with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.codetopic.java.utils.reflect.field;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.codetopic.java.utils.Objects;
import eu.codetopic.java.utils.log.Log;

public class FieldsSearch {

    private static final String LOG_TAG = "FieldsSearch";

    public static FoundField getAllFields(@NotNull Class<?> startClass) {
        return getFields(new SimpleFieldsFilter(startClass).addClassesToFind(Object.class,
                boolean.class, byte.class, char.class, double.class,
                float.class, int.class, long.class, short.class));
    }

    public static FoundField getAllNonPrimitiveFields(@NotNull Class<?> startClass) {
        return getFields(new SimpleFieldsFilter(startClass).addClassesToFind(Object.class));
    }

    public static FoundField getFields(@NotNull FieldsFilter filter) {
        Class<?> startClass = filter.getStartClass();
        return getFields(filter, startClass, startClass, null);
    }

    private static FoundField getFields(@NotNull FieldsFilter filter, @NotNull Class<?> baseClass,
                                        @NotNull Class<?> actualClass, @Nullable Field field) {

        if (actualClass.equals(filter.getStopSuperClass()))
            return new FoundField(!filter.addField(field, actualClass), field, actualClass, new FoundField[0]);
        if (actualClass.isArray()) actualClass = actualClass.getComponentType();

        List<FoundField> contentFields = new ArrayList<>();

        for (Field declaredField : actualClass.getDeclaredFields()) {
            try {
                Class<?> declaredFieldType = getGenericFieldType(declaredField, baseClass);
                contentFields.add(filter.searchFieldsInFieldClass(declaredField, declaredFieldType)
                        ? getFields(filter, declaredFieldType, declaredFieldType, declaredField)
                        : new FoundField(!filter.addField(declaredField, declaredFieldType),
                        declaredField, declaredFieldType, new FoundField[0]));
            } catch (Throwable t) {
                if (filter.isThrowExceptions()) throw t;
                Log.e(LOG_TAG, "getFields - problem detected while getting info of " + actualClass);
            }
        }

        try {
            Class<?> parentClass = actualClass.getSuperclass();
            if (parentClass != null) Collections.addAll(contentFields,
                    getFields(filter, baseClass, parentClass, field).getContentFields());
        } catch (Throwable t) {
            if (filter.isThrowExceptions()) throw t;
            Log.e(LOG_TAG, "getFields - problem detected while getting info of " + actualClass);
        }

        return new FoundField(!filter.addField(field, actualClass), field, actualClass,
                contentFields.toArray(new FoundField[contentFields.size()]));
    }

    private static Class<?> getGenericFieldType(Field field, Class<?> baseClass) {
        Type type = field.getGenericType();
        if (type instanceof Class) return (Class<?>) type;

        if (type instanceof TypeVariable) {
            try {
                Type[] typeBounds = ((TypeVariable) type).getBounds();
                Class<?> declaringClass = field.getDeclaringClass();
                List<Class> hierarchy = new ArrayList<>();
                while (!Objects.equals(declaringClass, baseClass)) {
                    hierarchy.add(baseClass);
                    baseClass = baseClass.getSuperclass();
                }

                Class<?> lastUsableArgClass = null;
                for (int i = hierarchy.size() - 1; i >= 0; i--) {
                    Type hType = hierarchy.get(i).getGenericSuperclass();
                    if (hType instanceof ParameterizedType) {
                        ParameterizedType parType = (ParameterizedType) hType;

                        for (Type baseTypeArg : parType.getActualTypeArguments()) {
                            //Log.d(LOG_TAG, "getGenericFieldType baseType: " + baseTypeArg);
                            //Log.d(LOG_TAG, "getGenericFieldType baseType(Class): " + baseTypeArg.getClass());
                            //Log.d(LOG_TAG, "getGenericFieldType bounds: " + Arrays.toString(typeBounds));

                            if (baseTypeArg instanceof Class) {
                                boolean usable = false;
                                Class<?> baseTypeArgClass = (Class<?>) baseTypeArg;
                                for (Type bound : typeBounds) {
                                    //Log.d(LOG_TAG, "getGenericFieldType bound: " + bound);
                                    //Log.d(LOG_TAG, "getGenericFieldType bound(Class): " + bound.getClass().getName());
                                    if (bound instanceof ParameterizedType) bound =
                                            ((ParameterizedType) bound).getRawType();

                                    usable = bound instanceof Class && ((Class<?>) bound)
                                            .isAssignableFrom(baseTypeArgClass);
                                    if (!usable) break;
                                }
                                if (usable) {
                                    if (lastUsableArgClass != null)
                                        throw new ClassNotFoundException();
                                    lastUsableArgClass = baseTypeArgClass;
                                }
                            }
                        }
                        if (lastUsableArgClass != null) break;
                    }
                }

                if (lastUsableArgClass != null) return lastUsableArgClass;
            } catch (Throwable t) {
                Log.v(LOG_TAG, "getGenericFieldType", t);
            }
        }

        return field.getType();
    }

}
