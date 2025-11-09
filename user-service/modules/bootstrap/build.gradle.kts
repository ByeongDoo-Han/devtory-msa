tasks.bootJar {
    enabled = true
}
tasks.jar {
    enabled = false
}

dependencies {
    implementation(project(":modules:application"))
    implementation(project(":modules:domain"))
    implementation(project(":modules:common"))
    implementation(project(":modules:infrastructure"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

}
