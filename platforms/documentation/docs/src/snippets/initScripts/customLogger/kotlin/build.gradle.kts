tasks.register("compile") {
    doLast {
        println("compiling source")
    }
}

tasks.register("testCompile") {
    dependsOn("compile")
    doLast {
        println("compiling test source")
    }
}

tasks.register("test") {
    dependsOn("compile", "testCompile")
    doLast {
        println("running unit tests")
    }
}

tasks.register("build") {
    dependsOn("test")
}
