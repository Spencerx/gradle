/*
 * Copyright 2010 the original author or authors.
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
package org.gradle.internal.execution;

import org.gradle.internal.fingerprint.CurrentFileCollectionFingerprint;
import org.gradle.internal.fingerprint.FileCollectionFingerprint;
import org.gradle.internal.fingerprint.FileNormalizer;
import org.gradle.internal.snapshot.FileSystemSnapshot;
import org.jspecify.annotations.Nullable;

public interface FileCollectionFingerprinter {
    /**
     * The type used to refer to this fingerprinter in the {@link FileCollectionFingerprinterRegistry}.
     */
    FileNormalizer getNormalizer();

    /**
     * Creates a fingerprint from the snapshot of a file collection.
     */
    CurrentFileCollectionFingerprint fingerprint(FileSystemSnapshot snapshot, @Nullable FileCollectionFingerprint previousFingerprint);

    /**
     * Returns an empty fingerprint.
     */
    CurrentFileCollectionFingerprint empty();
}
