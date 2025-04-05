# Defind jdk for build
FROM bellsoft/liberica-openjre-debian:21-cds AS builder
WORKDIR /builder
# Defind variable to work with
ARG jar_file=target/*jar

COPY ${jar_file} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

FROM bellsoft/liberica-openjre-debian:21-cds
WORKDIR /application

COPY --from=builder /builder/extracted/dependencies ./
COPY --from=builder /builder/extracted/spring-boot-loader ./
COPY --from=builder /builder/extracted/snapshot-dependencies ./
COPY --from=builder /builder/extracted/application/ ./

#Add CDS JVM Feature for reduce runtime of application.
RUN java -XX:ArchiveClassesAtExit=application.jsa -Dspring.context.exit=onRefresh -jar application.jar

ENTRYPOINT ["java", "-XX:SharedArchiveFile=application.jsa", "-jar", "application.jar"]


