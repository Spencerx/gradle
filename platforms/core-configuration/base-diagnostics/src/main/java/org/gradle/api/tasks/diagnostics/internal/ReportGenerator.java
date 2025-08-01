/*
 * Copyright 2015 the original author or authors.
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

package org.gradle.api.tasks.diagnostics.internal;

import org.gradle.api.Project;
import org.gradle.internal.UncheckedException;
import org.gradle.internal.logging.text.StyledTextOutputFactory;
import org.jspecify.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.function.Function;


public final class ReportGenerator {

    private final ReportRenderer renderer;
    private final @Nullable File outputFile;
    private final StyledTextOutputFactory textOutputFactory;

    public ReportGenerator(
        ReportRenderer renderer,
        @Nullable File outputFile,
        StyledTextOutputFactory textOutputFactory
    ) {
        this.renderer = renderer;
        this.outputFile = outputFile;
        this.textOutputFactory = textOutputFactory;
    }

    @FunctionalInterface
    public interface ReportMarginaliaAction {
        void execute() throws IOException;

        ReportMarginaliaAction DO_NOTHING = new ReportMarginaliaAction() {
            @Override
            public void execute() throws IOException {
                // Do nothing;
            }
        };
    }

    @FunctionalInterface
    public interface ReportAction<T> {
        void execute(T project) throws IOException;
    }

    public void generateReport(Set<Project> projects, ReportAction<Project> projectReportGenerator) {
        generateReport(projects, ProjectDetails::of, projectReportGenerator);
    }

    public <T> void generateReport(
        Iterable<T> projects,
        Function<T, ProjectDetails> projectDetailsProvider,
        ReportAction<T> projectReportGenerator
    ) {
        generateReport(projects, projectDetailsProvider, ReportMarginaliaAction.DO_NOTHING, projectReportGenerator, ReportMarginaliaAction.DO_NOTHING);
    }

    public <T> void generateReport(
        Iterable<T> projects,
        Function<T, ProjectDetails> projectDetailsProvider,
        ReportMarginaliaAction projectReportHeaderGenerator,
        ReportAction<T> projectReportGenerator,
        ReportMarginaliaAction projectReportFooterGenerator
    ) {
        try {
            ReportRenderer renderer = getRenderer();
            File outputFile = getOutputFile();
            if (outputFile != null) {
                renderer.setOutputFile(outputFile);
            } else {
                renderer.setOutput(getTextOutputFactory().create(getClass()));
            }
            projectReportHeaderGenerator.execute();
            for (T project : projects) {
                ProjectDetails projectDetails = projectDetailsProvider.apply(project);
                renderer.startProject(projectDetails);
                projectReportGenerator.execute(project);
                renderer.completeProject(projectDetails);
            }
            projectReportFooterGenerator.execute();
            renderer.complete();
        } catch (IOException e) {
            throw UncheckedException.throwAsUncheckedException(e);
        }
    }

    private ReportRenderer getRenderer() {
        return renderer;
    }

    /**
     * Returns the file which the report will be written to. When set to {@code null}, the report is written to {@code System.out}.
     *
     * @return The output file. May be null.
     */
    @Nullable
    private File getOutputFile() {
        return outputFile;
    }

    private StyledTextOutputFactory getTextOutputFactory() {
        return textOutputFactory;
    }
}
