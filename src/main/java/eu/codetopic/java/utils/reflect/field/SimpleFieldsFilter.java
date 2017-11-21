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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleFieldsFilter implements FieldsFilter {

    private static final String LOG_TAG = "SimpleFieldsFilter";

    private final List<Class> classesToFind = new ArrayList<>();
    private final List<Class<? extends Annotation>> annotationsToFind = new ArrayList<>();
    private final List<Class> classesToDeepSearch = new ArrayList<>();
    private final List<Class<? extends Annotation>> annotationsToDeepSearch = new ArrayList<>();
    private Class<?> startClass = null;
    private Class<?> stopClass = Object.class;
    private boolean throwExceptions = false;

    public SimpleFieldsFilter() {
    }

    public SimpleFieldsFilter(Class<?> startClass) {
        this.startClass = startClass;
    }

    public SimpleFieldsFilter(Class<?> startClass, Class<?> stopClass) {
        this.startClass = startClass;
        this.stopClass = stopClass;
    }

    public final SimpleFieldsFilter addClassesToDeepSearch(Class... classes) {
        Collections.addAll(classesToDeepSearch, classes);
        return this;
    }

    @SafeVarargs
    public final SimpleFieldsFilter addAnnotationsToDeepSearch(Class<? extends Annotation>... annotationClasses) {
        Collections.addAll(annotationsToDeepSearch, annotationClasses);
        return this;
    }

    public final SimpleFieldsFilter addClassesToFind(Class... classes) {
        Collections.addAll(classesToFind, classes);
        return this;
    }

    @SafeVarargs
    public final SimpleFieldsFilter addAnnotationsToFind(Class<? extends Annotation>... annotationClasses) {
        Collections.addAll(annotationsToFind, annotationClasses);
        return this;
    }

    @Override
    public final Class<?> getStartClass() {
        return startClass;
    }

    public final SimpleFieldsFilter setStartClass(Class<?> startClass) {
        this.startClass = startClass;
        return this;
    }

    @Override
    public Class<?> getStopSuperClass() {
        return stopClass;
    }

    public SimpleFieldsFilter setStopSuperClass(Class<?> stopClass) {
        this.stopClass = stopClass;
        return this;
    }

    @Override
    public boolean isThrowExceptions() {
        return throwExceptions;
    }

    public SimpleFieldsFilter setThrowExceptions(boolean throwExceptions) {
        this.throwExceptions = throwExceptions;
        return this;
    }

    @Override
    public boolean addField(@Nullable Field field, @NotNull Class<?> fieldType) {
        for (Class<?> clazz : classesToFind)
            if (clazz.isAssignableFrom(fieldType)) return true;
        if (field == null) return false;
        for (Class<? extends Annotation> clazz : annotationsToFind)
            if (field.getAnnotation(clazz) != null) return true;
        return false;
    }

    @Override
    public boolean searchFieldsInFieldClass(@Nullable Field field, @NotNull Class<?> fieldType) {
        for (Class<?> clazz : classesToDeepSearch)
            if (clazz.isAssignableFrom(fieldType)) return true;
        if (field == null) return false;
        for (Class<? extends Annotation> clazz : annotationsToDeepSearch)
            if (field.getAnnotation(clazz) != null) return true;
        return false;
    }
}
