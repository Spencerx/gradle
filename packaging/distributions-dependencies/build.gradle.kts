import gradlebuild.basics.bundleGroovyMajor

/**
 * This project provides the "platform" for the Gradle distribution.
 * We want the versions that are packaged in the distribution to be used everywhere (e.g. in all test scenarios)
 * Hence, we lock the versions down here for all other subprojects.
 *
 * Note:
 * We use strictly here because we do not have any better means to do this at the moment.
 * Ideally we wound be able to say "lock down all the versions of the dependencies resolved for the distribution"
 */
plugins {
    id("gradlebuild.platform")
}

description = "Provides a platform dependency to align all distribution versions"

val antVersion = "1.10.15"
// Don't forget to bump versions in
// subprojects/base-services/src/main/java/org/gradle/internal/classanalysis/AsmConstants.java
// when upgrading ASM.
val asmVersion = "9.7.1"
val awsS3Version = "1.12.780"
val bouncycastleVersion = "1.81"
val hamcrestVersion = "3.0"
val jacksonVersion = "2.16.1"
val jaxbVersion = "3.0.0"
val junit5Version = "5.12.2"
val mavenVersion = "3.9.5"
val mavenResolverVersion = "1.9.16" // Should remain in-sync with `mavenVersion`
val nativePlatformVersion = "0.22-milestone-28"
/**
 * Should be in sync with:
 * tooling API-related docs & snippets
 * buildship: CreateBuildshipEclipseProjectTask#calculateManifest
 * StaticLoggerProvider#REQUESTED_API_VERSION
*/
val slf4jVersion = "2.0.17"
val spockVersion = when (bundleGroovyMajor) {
    4 -> "2.3-groovy-4.0"
    else -> error("Unsupported Groovy major version: $bundleGroovyMajor")
}
val tomljVersion = "1.0.0"
val jnaVersion = "5.17.0"
val sshdVersion = "2.15.0"
val jGitVersion = "7.2.1.202505142326-r"

// test only
val archunitVersion = "1.2.0"
val bytebuddyVersion = "1.17.5"
val jettyVersion = "9.4.36.v20210114"

// For the junit-bom
javaPlatform.allowDependencies()

dependencies {
    api(platform("org.junit:junit-bom:${junit5Version}!!"))

    constraints {
        api(libs.ansiControlSequenceUtil) { version { strictly("0.3") }}
        api(libs.ant)                   { version { strictly(antVersion) }}
        api(libs.antLauncher)           { version { strictly(antVersion) }}
        api(libs.asm)                   { version { strictly(asmVersion) }}
        api(libs.asmAnalysis)           { version { strictly(asmVersion) }}
        api(libs.asmCommons)            { version { strictly(asmVersion) }}
        api(libs.asmTree)               { version { strictly(asmVersion) }}
        api(libs.asmUtil)               { version { strictly(asmVersion) }}
        api(libs.assertj)               { version { strictly("3.23.1") }}
        api(libs.awsS3Core)             { version { strictly(awsS3Version) }}
        api(libs.awsS3Kms)              { version { strictly(awsS3Version) }}
        api(libs.awsS3S3)               { version { strictly(awsS3Version) }}
        api(libs.awsS3Sts)              { version { strictly(awsS3Version) }}
        api(libs.bouncycastlePgp)       { version { strictly(bouncycastleVersion) }}
        api(libs.bouncycastlePkix)      { version { strictly(bouncycastleVersion) }}
        api(libs.bouncycastleProvider)  { version { strictly(bouncycastleVersion) }}
        api(libs.bouncycastleUtil)      { version { strictly(bouncycastleVersion) }}
        api(libs.bsh)                   { version { strictly("2.0b6") }}
        api(libs.commonsCodec)          { version { strictly("1.18.0") } }
        api(libs.commonsCompress)       { version { strictly("1.26.1") } }
        api(libs.commonsHttpclient)     { version { strictly("4.5.14") } }
        api(libs.commonsIo)             { version { strictly("2.15.1") }}
        api(libs.commonsLang)           { version { strictly("3.17.0") }}
        api(libs.commonsMath)           { version { strictly("3.6.1") }}
        api(libs.eclipseSisuPlexus)     { version { strictly("0.3.5"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.errorProneAnnotations) { version { strictly("2.36.0") } } // don't forget to upgrade errorprone in gradlebuild.code-quality.gradle.kts
        api(libs.fastutil)              { version { strictly("8.5.2") }}
        api(libs.gradleFileEvents)      { version { strictly("0.2.8") }}
        api(libs.gradleProfiler)        { version { strictly("0.23.0-alpha-1") }}
        api(libs.develocityTestAnnotation) { version { strictly("2.0.1") }}
        api(libs.gcs)                   { version { strictly("v1-rev20220705-1.32.1") }}
        api(libs.googleApiClient)       { version { strictly("1.34.0"); because("our GCS version requires 1.34.0") }}
        api(libs.guava)                 { version { strictly("33.4.6-jre"); because("our Google API Client version requires at least 31.1-jre, 33.4.5 removes usage of Unsafe")  }}
        api(libs.googleHttpClientGson)  { version { strictly("1.42.2"); because("our Google API Client version requires 1.42.2")  }}
        api(libs.googleHttpClientApacheV2) { version { strictly("1.42.2"); because("our Google API Client version requires 1.42.2")  }}
        api(libs.googleHttpClient)      { version { strictly("1.42.2"); because("our Google API Client version requires 1.42.2") }}
        api(libs.googleOauthClient)     { version { strictly("1.34.1"); because("our Google API Client version requires 1.34.1") }}
        api(libs.groovy)                { version { strictly(libs.groovyVersion) }}
        api(libs.groovyAnt)             { version { strictly(libs.groovyVersion) }}
        api(libs.groovyAstbuilder)      { version { strictly(libs.groovyVersion) }}
        api(libs.groovyConsole)         { version { strictly(libs.groovyVersion) }}
        api(libs.groovySql)             { version { strictly(libs.groovyVersion) }}
        api(libs.groovyDatetime)        { version { strictly(libs.groovyVersion) }}
        api(libs.groovyDateUtil)        { version { strictly(libs.groovyVersion) }}
        api(libs.groovyNio)             { version { strictly(libs.groovyVersion) }}
        api(libs.groovyDoc)             { version { strictly(libs.groovyVersion) }}
        api(libs.groovyJson)            { version { strictly(libs.groovyVersion) }}
        api(libs.groovyTemplates)       { version { strictly(libs.groovyVersion) }}
        api(libs.groovyTest)            { version { strictly(libs.groovyVersion) }}
        api(libs.groovyXml)             { version { strictly(libs.groovyVersion) }}
        api(libs.gson)                  { version { strictly("2.13.1") }}
        api(libs.h2Database)            { version { strictly("2.2.220") }}
        api(libs.hamcrest)              { version { strictly(hamcrestVersion) }}
        api("org.hamcrest:hamcrest-core") { version { strictly(hamcrestVersion) }}
        api(libs.hikariCP)              { version { strictly("4.0.3"); because("5.x requires Java 11+") }}
        api(libs.httpcore)              { version { strictly("4.4.14") }}
        api(libs.inject)                { version { strictly("1") }}
        api(libs.ivy)                   { version { strictly("2.5.3") }}
        api(libs.jacksonAnnotations)    { version { strictly(jacksonVersion) }}
        api(libs.jacksonCore)           { version { strictly(jacksonVersion) }}
        api(libs.jacksonDatabind)       { version { strictly(jacksonVersion) }}
        api(libs.jacksonDatatypeJdk8)   { version { strictly(jacksonVersion) }}
        api(libs.jacksonDatatypeJsr310) { version { strictly(jacksonVersion) }}
        api(libs.jacksonKotlin)         { version { strictly(jacksonVersion) }}
        api(libs.jakartaActivation)     { version { strictly("2.0.1") }}
        api(libs.jakartaXmlBind)        { version { strictly("3.0.0") }}
        api(libs.jansi)                 { version { strictly("1.18"); because("2.x changes the API") }}
        api(libs.jatl)                  { version { strictly("0.2.3") }}
        api(libs.javaPoet)              { version { strictly("1.13.0") } }
        api(libs.jaxbCore)              { version { strictly(jaxbVersion) }}
        api(libs.jaxbImpl)              { version { strictly(jaxbVersion) }}
        api(libs.jcifs)                 { version { strictly("1.3.17") }}
        api(libs.jclToSlf4j)            { version { strictly(slf4jVersion) }}
        api(libs.jcommander)            { version { strictly("1.78") }}
        api(libs.jetbrainsAnnotations)  { version { strictly("24.0.1") }}
        api(libs.jgit)                  { version { strictly(jGitVersion) }}
        api(libs.jgitSsh)               { version { strictly(jGitVersion) }}
        api(libs.jgitSshAgent)          { version { strictly(jGitVersion) }}
        api(libs.joda)                  { version { strictly("2.14.0"); because("Only pulled in by AWS SDK") }}
        api(libs.joptSimple)            { version { strictly("5.0.4"); because("needed to create profiler in Gradle profiler API") }}
        api(libs.jsch)                  { version { strictly("0.2.16") }}
        api(libs.jsoup)                 { version { strictly("1.15.3") }}
        api(libs.jsr305)                { version { strictly("3.0.2") }}
        api(libs.jspecify)              { version { strictly("1.0.0") }}
        api(libs.julToSlf4j)            { version { strictly(slf4jVersion) }}
        api(libs.junit)                 { version { strictly("4.13.2") }}
        api(libs.junitJupiter)          { version { strictly(junit5Version) }}
        api(libs.junit5JupiterApi)      { version { strictly(junit5Version) }}
        api(libs.junit5Vintage)         { version { strictly(junit5Version) }}
        api(libs.junitPlatform)         { version { strictly("1.12.2") }}
        api(libs.junitPlatformEngine)   { version { strictly("1.12.2") }}
        api(libs.jzlib)                 { version { strictly("1.1.3") }}
        api(libs.kryo)                  { version { strictly("2.24.0") }}
        api(libs.log4jToSlf4j)          { version { strictly(slf4jVersion) }}
        api(libs.maven3Artifact)        { version { strictly(mavenVersion); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.maven3Core)            { version { strictly(mavenVersion); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.maven3BuilderSupport)  { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(libs.maven3Model)           { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(libs.maven3RepositoryMetadata) { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(libs.maven3Settings)        { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(libs.maven3SettingsBuilder) { version { strictly(mavenVersion); because("required to load/build poms and repository settings") }}
        api(libs.minlog)                { version { strictly("1.2") }}
        api(libs.nativePlatform)        { version { strictly(nativePlatformVersion) }}
        api(libs.objenesis)             { version { strictly("2.6") }}
        api(libs.plexusCipher)          { version { strictly("2.0"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.plexusInterpolation)   { version { strictly("1.26"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.plexusClassworlds)     { version { strictly("2.7.0"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.plexusSecDispatcher)   { version { strictly("2.0"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.plexusUtils)           { version { strictly("3.5.1"); because("transitive dependency of Maven modules to process POM metadata") }}
        api(libs.plist)                 { version { strictly("1.27") }}
        api(libs.servletApi)            { version { strictly("3.1.0") }}
        api(libs.slf4jApi)              { version { strictly(slf4jVersion) }}
        api(libs.slf4jSimple)           { version { rejectAll(); because("We only need the logging API, we supply our own binding, which cause duplicate binding on class path error") }}
        api(libs.snakeyaml)             { version { strictly("2.0") }}
        api(libs.testng)                { version { strictly("6.3.1"); because("later versions break test cross-version test filtering") }}
        api(libs.tomlj)                 { version { strictly(tomljVersion) }}
        api(libs.trove4j)               { version { strictly("1.0.20200330") }}
        api(libs.jna)                   { version { strictly(jnaVersion) }}
        api(libs.jnaPlatform)           { version { strictly(jnaVersion) }}

        // TODO upgrade this AGP version to recent version
        api(libs.agp)                   { version { strictly("3.0.0"); because("We use 3.0.0 for internal performance test") }}
        api(libs.xbeanReflect)          { version { strictly("3.18") }}

        // compile only
        api(libs.maven3Compat)          { version { strictly(mavenVersion); because("required for maven2gradle in init plugin") }}
        api(libs.maven3PluginApi)       { version { strictly(mavenVersion); because("required for maven2gradle in init plugin") }}
        api(libs.zinc)                  { version { strictly("1.10.4") } }

        // test only
        api(libs.aircompressor)         { version { strictly("0.27") }}
        api(libs.archunit)              { version { strictly(archunitVersion) }}
        api(libs.archunitJunit5)        { version { strictly(archunitVersion) }}
        api(libs.archunitJunit5Api)     { version { strictly(archunitVersion) }}
        api(libs.awaitility)            { version { strictly("3.1.6") }}
        api(libs.bytebuddy)             { version { strictly(bytebuddyVersion) }}
        api(libs.bytebuddyAgent)        { version { strictly(bytebuddyVersion) }}
        api(libs.cglib)                 { version { strictly("3.2.6") }}
        api(libs.compileTesting)        { version { strictly("0.21.0")}}
        api(libs.dockerJavaApi)         { version { strictly("3.4.0")}}
        api(libs.equalsverifier)        { version { strictly("2.1.6") }}
        api(libs.guice)                 { version { strictly("5.1.0") }}
        api(libs.httpmime)              { version { strictly("4.5.10") }}
        api(libs.jetty)                 { version { strictly(jettyVersion) }}
        api(libs.jettySecurity)         { version { strictly(jettyVersion) }}
        api(libs.jettyServer)           { version { strictly(jettyVersion) }}
        api(libs.jettyServlet)          { version { strictly(jettyVersion) }}
        api(libs.jettyUtil)             { version { strictly(jettyVersion) }}
        api(libs.jettyWebApp)           { version { strictly(jettyVersion) }}
        api(libs.jtar)                  { version { strictly("2.3") }}
        api(libs.kotlinCoroutines)      { version { strictly("1.10.2") }}
        api(libs.kotlinCoroutinesDebug) { version { strictly("1.10.2") }}
        api(libs.kotlinJvmAbiGenEmbeddable) { version { strictly(libs.kotlinVersion) }}
        api(libs.kotlinxSerializationCore)   { version { strictly("1.9.0") }}
        api(libs.kotlinxSerializationJson)   { version { strictly("1.9.0") }}
        api(libs.littleproxy)           { version { strictly("2.0.5") }}
        api(libs.maven3ResolverProvider){ version { strictly(mavenVersion) }}
        api(libs.mavenResolverApi)              { version { strictly(mavenResolverVersion) }}
        api(libs.mavenResolverConnectorBasic)   { version { strictly(mavenResolverVersion) }}
        api(libs.mavenResolverImpl)             { version { strictly(mavenResolverVersion) }}
        api(libs.mavenResolverSupplier)         { version { strictly(mavenResolverVersion) }}
        api(libs.mavenResolverTransportFile)    { version { strictly(mavenResolverVersion) }}
        api(libs.mavenResolverTransportHttp)    { version { strictly(mavenResolverVersion) }}
        api(libs.mina)                  { version { strictly("2.0.17") }}
        api(libs.mockitoCore)           { version { strictly("5.17.0") }}
        api(libs.mockitoKotlin)         { version { strictly("4.1.0") }}
        api(libs.mockwebserver)         { version { strictly("4.12.0") }}
        api(libs.mySqlConnector)        { version { strictly("9.2.0") }}
        api(libs.netty)                 { version { strictly("4.1.63.Final") }}
        api(libs.opentest4j)            { version { strictly("1.3.0") }}
        api(libs.samplesCheck)          { version { strictly("1.0.3") }}
        api(libs.samplesDiscovery)      { version { strictly("1.0.3") }}
        api(libs.snappy)                { version { strictly("0.5") }}
        api(libs.socksProxy)            { version { strictly("4.1.2") } }
        api(libs.spock)                 { version { strictly(spockVersion) }}
        api(libs.spockJUnit4)           { version { strictly(spockVersion) }}
        api(libs.sshdCore)              { version { strictly(sshdVersion) }}
        api(libs.sshdOsgi)              { version { strictly(sshdVersion) }}
        api(libs.sshdScp)               { version { strictly(sshdVersion) }}
        api(libs.sshdSftp)              { version { strictly(sshdVersion) }}
        api(libs.testcontainers)        { version { strictly("1.20.4") }}
        api(libs.testcontainersSpock)   { version { strictly("1.20.4") }}
        api(libs.typesafeConfig)        { version { strictly("1.3.3") }}
        api(libs.xerces)                { version { strictly("2.12.0") }}
        api(libs.xmlunit)               { version { strictly("1.6") }}
    }
}
