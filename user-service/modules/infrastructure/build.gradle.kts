tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":modules:application"))
    implementation(project(":modules:domain"))
    implementation(project(":modules:common"))
}
