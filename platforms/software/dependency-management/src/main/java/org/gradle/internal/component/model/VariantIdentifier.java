/*
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.internal.component.model;

import org.gradle.api.artifacts.component.ComponentIdentifier;

/**
 * An opaque immutable identifier for a variant of a component.
 */
public interface VariantIdentifier {

    /**
     * Get the identity of the component that owns this variant.
     */
    ComponentIdentifier getComponentId();

    /**
     * Returns a human-readable display name for this identifier.
     */
    String getDisplayName();

}
