FROM openjdk:8u282-jre-slim
ADD build/libs/*.jar /tmp/
ADD src/test/resources/  /tmp/
RUN echo "Image Succesfully Built"