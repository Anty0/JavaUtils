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

package eu.codetopic.java.utils.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

public class AnnotationUtils {

    private static final String LOG_TAG = "AnnotationUtils";

    public static void replaceAnnotation(Class<?> target, Annotation annotation)
            throws NoSuchFieldException, IllegalAccessException {

        Field annotationsField = Class.class.getDeclaredField("annotations");
        annotationsField.setAccessible(true);
        //noinspection unchecked
        ((Map<Class<? extends Annotation>, Annotation>) annotationsField.get(target))
                .put(annotation.annotationType(), annotation);
    }

}
