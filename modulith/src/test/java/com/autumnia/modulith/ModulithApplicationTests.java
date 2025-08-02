package com.autumnia.modulith;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@SpringBootTest
@AnalyzeClasses(packages = "com.autumnia.modulith", importOptions = {ImportOption.DoNotIncludeTests.class})
class ModulithApplicationTests {
	@Test
	void contextLoads() {
	}

//	@ArchTest
//	public static  final ArchRule controllerRuleTest  =
//			classes()
//					.that().resideInAPackage("..controller..")
//					.should().onlyBeAccessed().byAnyPackage("..service..", "..controller..", "..event..")
//					.because("Controllers should only be accessed by services or other controllers");

//	@ArchTest
//	public static final ArchRule serviceRuleTest =
//			noClasses()                                           // ❗ noClasses 로 시작
//					.that().resideInAnyPackage("..service..")         // 서비스 계층은
//					.should().accessClassesThat()
//					.resideInAnyPackage("..controller..");

	@Test
	void validateLayeredArchitecture() {
		JavaClasses importedClasses = new ClassFileImporter()
				.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS) // ✅ 테스트 코드 제외
				.importPackages("com.autumnia.modulith");

		Architectures.LayeredArchitecture architecture = layeredArchitecture()
				.consideringAllDependencies()
				.layer("Controller").definedBy("..controller..")
				.layer("Event").definedBy("..event..")
				.layer("Service").definedBy("..service..")
				.layer("Repository").definedBy("..repository..")

				// 규칙 정의
				.whereLayer("Controller").mayNotBeAccessedByAnyLayer()
				.whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Event")
				.whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");

		architecture.check(importedClasses);
	}

	@Test
	public void test_for_cycles() {
		slices().matching("com.autumnia.modulith.(*)..")
				.should()
				.beFreeOfCycles();
	}

}
