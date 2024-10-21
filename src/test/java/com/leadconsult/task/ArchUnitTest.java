package com.leadconsult.task;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.leadconsult.task")
public class ArchUnitTest {

	@ArchTest
	static final ArchRule layer_dependencies_are_respected = layeredArchitecture().consideringAllDependencies()

			.layer("Controllers").definedBy("com.leadconsult.task.controller..")
			.layer("Services").definedBy("com.leadconsult.task.service..")
			.layer("Persistence").definedBy("com.leadconsult.task.repository..")

			.whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
			.whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
			.whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services");


	@ArchTest
	static final ArchRule services_should_not_access_controllers =
			noClasses().that().resideInAPackage("..service..")
					.should().accessClassesThat().resideInAPackage("..controller..");

	@ArchTest final ArchRule controllers_should_not_access_repositories =
			noClasses().that().resideInAPackage("..controller..")
					.should().accessClassesThat().resideInAPackage("..repository..");
}
