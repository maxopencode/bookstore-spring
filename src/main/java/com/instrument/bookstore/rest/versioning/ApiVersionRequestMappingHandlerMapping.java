package com.instrument.bookstore.rest.versioning;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.*;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.text.DecimalFormat;

/**
 * This implementation of {@link RequestMappingHandlerMapping} extends the mapping support to include consideration
 * of API versions.
 * <p>
 * Credit to "Benjamin H" on StackOverflow: http://stackoverflow.com/a/21176971
 *
 * @author Ryan Morrison
 */
public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    // this prefix can be optionally provided to be placed before the version number
    // e.g. if prefix is 'v', the resulting version format could be 'v1'
    private String prefix;

    // by default, whole numbers ('1', '2') without a decimal value appear with a zeroed decimal ('1.0', '2.0')
    // we use the decimal formatter to remove the unnecessary zeroing decimal
    private DecimalFormat decimalFormat = new DecimalFormat("#.#");

    /**
     * Default constructor providing the default version prefix of "v" for version numbers resembling
     * "v1", "v2.0".
     */
    public ApiVersionRequestMappingHandlerMapping() {
        this.prefix = "v";
    }

    /**
     * Default constructor consuming and setting the provided version prefix.
     *
     * @param prefix the version prefix to set
     */
    public ApiVersionRequestMappingHandlerMapping(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Overridden method from {@link RequestMappingHandlerMapping}.
     */
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        // calls the parent method to retrieve the default mapping
        RequestMappingInfo rmInfo = super.getMappingForMethod(method, handlerType);
        if (rmInfo == null) {
            return null;
        }

        // determines whether a method-level API version annotation exists on the provided method
        ApiVersion methodLevelAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        if (methodLevelAnnotation != null) {
            // updates the RequestMappingInfo object with the versions determined by the annotation
            RequestCondition<?> methodCondition = super.getCustomMethodCondition(method);
            rmInfo = createApiVersionRequestMappingInfo(methodLevelAnnotation, methodCondition).combine(rmInfo);
        } else {
            // determines whether a class-level API version annotation exists on the handling class
            ApiVersion classLevelAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
            if (classLevelAnnotation != null) {
                // updates the RequestMappingInfo object with the versions determined by the annotation
                RequestCondition<?> classCondition = super.getCustomTypeCondition(handlerType);
                rmInfo = createApiVersionRequestMappingInfo(classLevelAnnotation, classCondition).combine(rmInfo);
            }
        }

        // returns the RequestMappingInfo object, updated with version details if provided via annotation
        return rmInfo;
    }

    /**
     * This method consumes a {@link RequestCondition} and {@link ApiVersion} annotation, prefixing the controller's
     * mapping with the version(s) specified by the {@link ApiVersion} annotation.
     *
     * @param annotation the annotation containing the versions to apply to this controller's mapping
     * @param condition  the original {@link RequestCondition} to prefix with version details
     * @return the updated {@link RequestMappingInfo} with version prefixes
     */
    private RequestMappingInfo createApiVersionRequestMappingInfo(ApiVersion annotation, RequestCondition<?> condition) {
        String[] versions = annotation.value();
        String[] patterns = new String[versions.length];

        // construct the string representation of each version defined in the annotation
        for (int i = 0; i < versions.length; i++) {
            patterns[i] = prefix + versions[i];
        }

        // construct and return a new RequestMappingInfo object with our prefixed patterns
        return new RequestMappingInfo(
                new PatternsRequestCondition(patterns, getUrlPathHelper(), getPathMatcher(), useSuffixPatternMatch(), useTrailingSlashMatch(), getFileExtensions()),
                new RequestMethodsRequestCondition(),
                new ParamsRequestCondition(),
                new HeadersRequestCondition(),
                new ConsumesRequestCondition(),
                new ProducesRequestCondition(),
                condition
        );
    }
}
