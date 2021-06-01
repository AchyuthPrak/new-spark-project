java_binary(
    name = "ProducerApp",
    srcs = glob(["src/ProducerApp.java", "src/Producer.java", "src/StringConstants.java", "src/Messenger.java"]),
    deps = [":java_test_deps"]
)

java_binary(
    name = "ConsumerApp",
    srcs = glob(["src/ConsumerApp.java", "src/Consumer.java", "src/StringConstants.java", "src/Messenger.java"]),
    deps = [":java_test_deps"]
)

java_binary(
    name = "Coordinator",
    srcs = glob(["src/Coordinator.java", "src/StringConstants.java"]),
    deps = [":java_test_deps"]
)


java_library(
    name = "java_test_deps",
    exports = [
        "@maven//:org_jsoup_jsoup",
        "@maven//:org_mongodb_mongo_java_driver",
        "@maven//:com_sparkjava_spark_core",
        "@maven//:org_apache_httpcomponents_httpclient",
    ],
)
