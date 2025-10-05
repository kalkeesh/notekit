# Stage 1: build with Maven
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
# run mvn package in batch mode, skip tests for faster builds
RUN mvn -B -DskipTests clean package

# Stage 2: runtime image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# copy whatever jar Maven produced (wildcard covers artifact name differences)
COPY --from=build /workspace/target/*.jar app.jar

# optional: set default PORT env (Render will usually provide its own)
ENV PORT=8080

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
