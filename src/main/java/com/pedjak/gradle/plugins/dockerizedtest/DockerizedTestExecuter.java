package com.pedjak.gradle.plugins.dockerizedtest;

import java.io.File;
import java.util.Set;
import java.util.UUID;

import com.google.common.collect.ImmutableSet;

import org.gradle.api.file.FileTree;
import org.gradle.api.internal.classpath.ModuleRegistry;
import org.gradle.api.internal.tasks.testing.*;
import org.gradle.api.internal.tasks.testing.detection.DefaultTestClassScanner;
import org.gradle.api.internal.tasks.testing.detection.TestFrameworkDetector;
import org.gradle.api.internal.tasks.testing.processors.MaxNParallelTestClassProcessor;
import org.gradle.api.internal.tasks.testing.processors.RestartEveryNTestClassProcessor;
import org.gradle.api.internal.tasks.testing.processors.TestMainAction;
import org.gradle.api.tasks.testing.Test;
import org.gradle.internal.Factory;
import org.gradle.internal.time.Clock;
import org.gradle.internal.actor.ActorFactory;
import org.gradle.internal.operations.BuildOperationExecutor;
import org.gradle.internal.work.WorkerLeaseRegistry;
import org.gradle.process.internal.worker.WorkerProcessFactory;
//
//public class DockerizedTestExecuter implements org.gradle.api.internal.tasks.testing.TestExecuter
//{
//    private final WorkerProcessFactory workerFactory;
//    private final ActorFactory actorFactory;
//    private final ModuleRegistry moduleRegistry;
//    private final WorkerLeaseRegistry workerLeaseRegistry;
//    private final BuildOperationExecutor buildOperationExecutor;
//    private final int maxWorkerCount;
//    private final Clock clock;
//    private TestClassProcessor processor;
//
//
//    public DockerizedTestExecuter(WorkerProcessFactory workerFactory, ActorFactory actorFactory, ModuleRegistry moduleRegistry, WorkerLeaseRegistry workerLeaseRegistry, BuildOperationExecutor buildOperationExecutor, int maxWorkerCount, Clock clock) {
//        this.workerFactory = workerFactory;
//        this.actorFactory = actorFactory;
//        this.moduleRegistry = moduleRegistry;
//        this.workerLeaseRegistry = workerLeaseRegistry;
//        this.buildOperationExecutor = buildOperationExecutor;
//        this.maxWorkerCount = maxWorkerCount;
//        this.clock = clock;
//    }
//
//    @Override
//    public void execute(final JvmTestExecutionSpec testTask, TestResultProcessor testResultProcessor) {
//        final TestFramework testFramework = testTask.getTestFramework();
//        final WorkerTestClassProcessorFactory testInstanceFactory = testFramework.getProcessorFactory();
//        final Set<File> classpath = ImmutableSet.copyOf(testTask.getClasspath());
//        final Factory<TestClassProcessor> forkingProcessorFactory = new Factory<TestClassProcessor>() {
//            public TestClassProcessor create() {
//                return new ForkingTestClassProcessor(workerFactory, testInstanceFactory, testTask,
//                        classpath, testFramework.getWorkerConfigurationAction(), moduleRegistry);
//            }
//        };
//        Factory<TestClassProcessor> reforkingProcessorFactory = new Factory<TestClassProcessor>() {
//            public TestClassProcessor create() {
//                return new RestartEveryNTestClassProcessor(forkingProcessorFactory, testTask.getForkEvery());
//            }
//        };
//
//        this.processor = new MaxNParallelTestClassProcessor(testTask.getMaxParallelForks(),
//                reforkingProcessorFactory, actorFactory);
//
//        final FileTree testClassFiles = testTask.getCandidateClassFiles();
//
//        Runnable detector;
//        if (testTask.isScanForTestClasses()) {
//            TestFrameworkDetector testFrameworkDetector = testTask.getTestFramework().getDetector();
//            testFrameworkDetector.setTestClasses(testTask.getTestClassesDirs().getFiles());
//            testFrameworkDetector.setTestClasspath(classpath);
//            detector = new DefaultTestClassScanner(testClassFiles, testFrameworkDetector, processor);
//        } else {
//            detector = new DefaultTestClassScanner(testClassFiles, null, processor);
//        }
//
//        Object testTaskOperationId;
//
//        try
//        {
//            testTaskOperationId = buildOperationExecutor.getCurrentOperation().getParentId();
//        } catch (Exception e) {
//            testTaskOperationId = UUID.randomUUID();
//        }
//
//        new TestMainAction(detector, processor, testResultProcessor, clock, testTaskOperationId, testTask.getPath(), "Gradle Test Run " + testTask.getIdentityPath()).run();
//    }
//
//    @Override
//    public void stopNow() {
//        if (null != processor) {
//            processor.stopNow();
//        }
//    }
//}
