package br.com.hexagonal.customer_api.arch_test;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.resideInAPackage;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


@AnalyzeClasses(packages = "br.com.hexagonal.customer_api")
public class LayeredArchitectureTest {

    private static final String DOMAIN = "Domain";
    private static final String PORTS_IN = "PortsIn";
    private static final String PORTS_OUT = "PortsOut";
    private static final String USE_CASE = "UseCase";
    private static final String ADAPTERS_IN = "AdaptersIn";
    private static final String ADAPTERS_OUT = "AdaptersOut";
    private static final String CONFIG = "Config";

    private static final String BASE_PACKAGE = "br.com.hexagonal.customer_api";
    private static final String DOMAIN_PACKAGE = BASE_PACKAGE + ".application.core.domain..";
    private static final String PORTS_IN_PACKAGE = BASE_PACKAGE + ".application.ports.in..";
    private static final String PORTS_OUT_PACKAGE = BASE_PACKAGE + ".application.ports.out..";
    private static final String USE_CASE_PACKAGE = BASE_PACKAGE + ".application.core.usecase..";
    private static final String ADAPTERS_IN_PACKAGE = BASE_PACKAGE + ".adapters.in..";
    private static final String ADAPTERS_OUT_PACKAGE = BASE_PACKAGE + ".adapters.out..";
    private static final String CONFIG_PACKAGE = BASE_PACKAGE + ".config..";


    @ArchTest
    public static final ArchRule hexagonal_layered_architecture_rules = layeredArchitecture()
            .consideringAllDependencies()
            .layer(DOMAIN).definedBy(DOMAIN_PACKAGE)
            .layer(PORTS_IN).definedBy(PORTS_IN_PACKAGE)
            .layer(PORTS_OUT).definedBy(PORTS_OUT_PACKAGE)
            .layer(USE_CASE).definedBy(USE_CASE_PACKAGE)
            .layer(ADAPTERS_IN).definedBy(ADAPTERS_IN_PACKAGE)
            .layer(ADAPTERS_OUT).definedBy(ADAPTERS_OUT_PACKAGE)
            .layer(CONFIG).definedBy(CONFIG_PACKAGE)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(PORTS_IN, PORTS_OUT, USE_CASE, ADAPTERS_IN, ADAPTERS_OUT)
            .whereLayer(PORTS_IN).mayOnlyBeAccessedByLayers(ADAPTERS_IN, USE_CASE)
            .whereLayer(PORTS_OUT).mayOnlyBeAccessedByLayers(ADAPTERS_OUT, USE_CASE)
            .whereLayer(ADAPTERS_IN).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(ADAPTERS_OUT).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(USE_CASE).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(CONFIG).mayNotBeAccessedByAnyLayer();


    @ArchTest
    public static final ArchRule domain_classes_should_not_have_persistence_annotations = classes()
            .that()
            .resideInAnyPackage(DOMAIN_PACKAGE)
            .should()
            .notBeAnnotatedWith("org.springframework.data.mongodb.core.mapping.Document")
            .andShould().notBeAnnotatedWith("org.springframework.data.annotation.Id")
            .andShould().notBeAnnotatedWith("javax.persistence.Entity")
            .andShould().notBeAnnotatedWith("jakarta.persistence.Entity")
            .as("Classes de domínio não devem ter anotações de persistência do Spring Data ou JPA.");


    @ArchTest
    public static final ArchRule domain_should_only_depend_on_self_and_java_libs = classes()
            .that()
            .resideInAnyPackage(DOMAIN_PACKAGE)
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage(DOMAIN_PACKAGE, "java..", "jakarta.validation..", "org.springframework.data.annotation..")
            .as("Classes de domínio devem depender apenas de si mesmas, bibliotecas Java, anotações de validação e anotações de ID/Documento.");


    @ArchTest
    public static final ArchRule input_ports_should_be_interfaces = classes()
            .that()
            .resideInAnyPackage(PORTS_IN_PACKAGE)
            .should()
            .beInterfaces()
            .as("As portas de entrada (input ports) devem ser interfaces.");


    @ArchTest
    public static final ArchRule output_ports_should_be_interfaces = classes()
            .that()
            .resideInAnyPackage(PORTS_OUT_PACKAGE)
            .should()
            .beInterfaces()
            .as("As portas de saída (output ports) devem ser interfaces.");


    @ArchTest
    public static final ArchRule adapters_in_should_not_depend_on_adapters_out_classes = classes()
            .that()
            .resideInAnyPackage(ADAPTERS_IN_PACKAGE)
            .should()
            .onlyDependOnClassesThat()
            .resideOutsideOfPackage(ADAPTERS_OUT_PACKAGE)
            .as("Adapters In não devem depender diretamente de Adapters Out para evitar acoplamento.");


    @ArchTest
    public static final ArchRule adapters_out_should_not_depend_on_adapters_in_classes = classes()
            .that()
            .resideInAnyPackage(ADAPTERS_OUT_PACKAGE)
            .should()
            .onlyDependOnClassesThat()
            .resideOutsideOfPackage(ADAPTERS_IN_PACKAGE)
            .as("Adapters Out não devem depender diretamente de Adapters In para evitar acoplamento.");


    @ArchTest
    public static final ArchRule use_cases_should_not_depend_on_adapters = classes()
            .that()
            .resideInAnyPackage(USE_CASE_PACKAGE)
            .should()
            .onlyDependOnClassesThat()
            .resideOutsideOfPackages(ADAPTERS_IN_PACKAGE, ADAPTERS_OUT_PACKAGE )
            .as("Use Cases não devem depender de adapters");


    @ArchTest
    public static final ArchRule use_cases_should_implement_ports_in = classes()
            .that()
            .resideInAnyPackage(USE_CASE_PACKAGE)
            .should()
            .implement(resideInAPackage(PORTS_IN_PACKAGE))
            .as("Use cases devem implementar interfaces de portas de entrada.")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule adapters_out_should_implement_ports_out = classes()
            .that()
            .resideInAnyPackage(ADAPTERS_OUT_PACKAGE)
            .and().resideOutsideOfPackage(ADAPTERS_OUT_PACKAGE.substring(0, ADAPTERS_OUT_PACKAGE.length() - 2) + ".client..")
            .and().resideOutsideOfPackage(ADAPTERS_OUT_PACKAGE.substring(0, ADAPTERS_OUT_PACKAGE.length() - 2) + ".repository..")
            .should()
            .implement(resideInAPackage(PORTS_OUT_PACKAGE))
            .as("Somente as classes adaptadoras de saída concretas devem implementar interfaces de portas de saída.")
            .allowEmptyShould(true);


    @ArchTest
    public static final ArchRule use_case_classes_should_end_with_use_case = classes()
            .that()
            .resideInAnyPackage(USE_CASE_PACKAGE)
            .should()
            .haveSimpleNameEndingWith("UseCase")
            .as("As classes do pacote 'usecase' devem terminar com 'UseCase'.");


    @ArchTest
    public static final ArchRule ports_in_interfaces_should_end_with_port = classes()
            .that()
            .resideInAnyPackage(PORTS_IN_PACKAGE)
            .and().areInterfaces()
            .should()
            .haveSimpleNameEndingWith("InputPort")
            .as("Interfaces de portas de entrada devem terminar com 'InputPort'.");


    @ArchTest
    public static final ArchRule ports_out_interfaces_should_end_with_port = classes()
            .that()
            .resideInAnyPackage(PORTS_OUT_PACKAGE)
            .and().areInterfaces()
            .should()
            .haveSimpleNameEndingWith("OutputPort")
            .as("Interfaces de portas de saída devem terminar com 'OutputPort'.");


    @ArchTest
    public static final ArchRule adapters_out_concrete_classes_should_end_with_adapter = classes()
            .that()
            .resideInAPackage(ADAPTERS_OUT_PACKAGE.substring(0, ADAPTERS_IN_PACKAGE.length() - 1))
            .should()
            .haveSimpleNameEndingWith("Adapter")
            .as("As classes adaptadoras de saída concretas devem terminar com 'Adapter' e residir em adapters.out");


    @ArchTest
    public static final ArchRule configuration_classes_should_end_with_config = classes()
            .that()
            .resideInAnyPackage(CONFIG_PACKAGE)
            .should()
            .haveSimpleNameEndingWith("Config")
            .as("As classes do pacote 'config' devem terminar com 'Config'.");


    @ArchTest
    public static final ArchRule adapters_in_controller_classes_should_end_with_controller = classes()
            .that()
            .resideInAnyPackage(ADAPTERS_IN_PACKAGE.substring(0, ADAPTERS_IN_PACKAGE.length() - 2) + ".controller")
            .should()
            .haveSimpleNameEndingWith("Controller")
            .as("Classes de controller em adapters.in devem terminar com 'Controller'.");


    @ArchTest
    public static final ArchRule adapters_in_consumer_classes_should_end_with_consumer = classes()
            .that()
            .resideInAnyPackage(ADAPTERS_IN_PACKAGE.substring(0, ADAPTERS_IN_PACKAGE.length() - 2) + ".consumer")
            .should()
            .haveSimpleNameEndingWith("Consumer")
            .as("Classes de consumer em adapters.in devem terminar com 'Consumer'.");


    @ArchTest
    public static final ArchRule mapper_classes_should_reside_in_mapper_package = classes()
            .that()
            .haveSimpleNameEndingWith("Mapper")
            .should()
            .resideInAnyPackage("..mapper..")
            .as("Classes Mapper devem residir em pacotes 'mapper'.");


}