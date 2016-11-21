/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2013-2016 ForgeRock AS.
 */

package org.forgerock.openam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.forgerock.api.jackson.JacksonUtils;
import org.forgerock.json.JsonException;
import org.forgerock.json.JsonValue;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Builder factory class for a fluent way of creating JsonValue objects.
 */
public final class JsonValueBuilder {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private JsonValueBuilder() {
    }

    /**
     * Creates a Builder object for creating JsonValue objects.
     *
     * @return A Builder class for creating JsonValues.
     */
    public static JsonObject jsonValue() {
        return new JsonObject();
    }

    /**
     * Converts a String into a JsonValue.
     *
     * @param json The json String.
     * @return A JsonValue object.
     * @throws JsonException If there is a problem parsing the json String.
     */
    public static JsonValue toJsonValue(String json) throws JsonException {
        try {
            return new JsonValue(MAPPER.readValue(json, Map.class));
        } catch (IOException e) {
            throw new JsonException("Failed to parse json", e);
        }
    }

    /**
     * Converts a byte array into a JsonValue.
     *
     * @param json json represented in bytes
     * @return A JsonValue object.
     * @throws JsonException If there is a problem parsing the json byte array.
     */
    public static JsonValue toJsonValue(byte[] json) throws JsonException {
        try {
            return new JsonValue(MAPPER.readValue(json, Map.class));
        } catch (IOException e) {
            throw new JsonException("Failed to parse json", e);
        }
    }

    /**
     * Converts the passed json string into a {@link JsonValue} represented as a list.
     *
     * @param json
     *         the json string
     *
     * @return a JsonValue instance represented as a list
     *
     * @throws JsonException
     *         should an error occur whilst parsing the json
     */
    public static JsonValue toJsonArray(final String json) throws JsonException {
        try {
            return new JsonValue(MAPPER.readValue(json, List.class));
        } catch (IOException e) {
            throw new JsonException("Failed to parse json", e);
        }
    }

    /**
     * Converts the passed input stream into a {@link JsonValue} represented as a list.
     *
     * @param json
     *         an input stream
     *
     * @return a JsonValue instance represented as a list
     *
     * @throws JsonException
     *         should an error occur whilst parsing the json
     */
    public static JsonValue toJsonArray(final InputStream json) throws JsonException {
        try {
            return new JsonValue(MAPPER.readValue(json, List.class));
        } catch (IOException e) {
            throw new JsonException("Failed to parse json", e);
        }
    }

    /**
     * Construct a {@code JsonValue} from a classpath resource.
     * @param relativeType The type to resolve the resource from.
     * @param resource The resource path.
     * @return The JsonValue.
     */
    public static JsonValue fromResource(Class<?> relativeType, String resource) {
        InputStream resource1 = relativeType.getResourceAsStream(resource);

        try {
            return JsonValue.json(JacksonUtils.OBJECT_MAPPER.readValue(resource1, Object.class));
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read declared resource " + resource, e);
        }

    }

    /**
     * Get singleton ObjectMapper instance for serialising to/from JSON.
     *
     * @return the shared ObjectMapper instance.
     * @see <a href="http://wiki.fasterxml.com/JacksonBestPracticesPerformance">Jackson Best Practices: Performance</a>
     */
    public static ObjectMapper getObjectMapper() {
        return MAPPER;
    }
}
