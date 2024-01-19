FROM openjdk:17.0.2-jdk

COPY build/libs/*.jar /opt/app.jar

COPY ./docker/entrypoint.sh /opt/entrypoint.sh

RUN chmod a+x /opt/entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/opt/entrypoint.sh"]