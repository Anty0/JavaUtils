/*
 * Copyright 2017 Jiří Kuchyňka (Anty)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
