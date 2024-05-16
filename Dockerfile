# Start with a base image containing Java runtime
FROM openjdk

# Add Author info
LABEL maintainer="youmahil@bluedigm.com"

# Add a volume to /nfsdemofiles
VOLUME /nfsdemofiles

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} echo-demo.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/echo-demo.jar"]