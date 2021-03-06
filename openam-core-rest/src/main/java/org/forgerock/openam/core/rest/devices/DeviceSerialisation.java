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
 * Copyright 2015 ForgeRock AS.
 */

package org.forgerock.openam.core.rest.devices;

import org.forgerock.json.JsonException;
import org.forgerock.json.JsonValue;

/**
 * Provides serialisation of devices between JsonValue and String representations.
 */
public interface DeviceSerialisation {

    /**
     * Converts a JSON device profile to a string.
     *
     * @param deviceProfile the device profile to convert to a string.
     * @return the serialised device profile.
     */
    String deviceProfileToString(JsonValue deviceProfile);

    /**
     * Converts a serialised string back into a JSON device profile.
     *
     * @param value the value to parse back into a JSON device profile.
     * @return the JSON device profile.
     * @throws IllegalArgumentException if the value cannot be parsed as a string.
     * @throws JsonException if the value cannot be parsed as Json.
     */
    JsonValue stringToDeviceProfile(String value);
}
