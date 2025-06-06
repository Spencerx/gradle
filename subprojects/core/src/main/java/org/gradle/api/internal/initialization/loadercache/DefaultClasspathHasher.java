/*
 * Copyright 2014 the original author or authors.
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

package org.gradle.api.internal.initialization.loadercache;

import org.gradle.api.internal.file.FileCollectionFactory;
import org.gradle.internal.classloader.ClasspathHasher;
import org.gradle.internal.classpath.ClassPath;
import org.gradle.internal.execution.FileCollectionFingerprinter;
import org.gradle.internal.execution.FileCollectionSnapshotter;
import org.gradle.internal.fingerprint.CurrentFileCollectionFingerprint;
import org.gradle.internal.hash.HashCode;
import org.gradle.internal.snapshot.FileSystemSnapshot;

public class DefaultClasspathHasher implements ClasspathHasher {

    private final FileCollectionSnapshotter snapshotter;
    private final FileCollectionFingerprinter fingerprinter;
    private final FileCollectionFactory fileCollectionFactory;

    public DefaultClasspathHasher(FileCollectionSnapshotter snapshotter, FileCollectionFingerprinter fingerprinter, FileCollectionFactory fileCollectionFactory) {
        this.snapshotter = snapshotter;
        this.fingerprinter = fingerprinter;
        this.fileCollectionFactory = fileCollectionFactory;
    }

    @Override
    public HashCode hash(ClassPath classpath) {
        FileSystemSnapshot snapshot = snapshotter.snapshot(fileCollectionFactory.fixed(classpath.getAsFiles()));
        CurrentFileCollectionFingerprint fingerprint = fingerprinter.fingerprint(snapshot, null);
        return fingerprint.getHash();
    }
}
