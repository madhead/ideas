ARG JAVA=17

FROM openjdk:${JAVA} AS builder
WORKDIR /ideas
COPY app ./
RUN ./gradlew clean install

FROM openjdk:${JAVA}
COPY --from=builder /ideas/app/build/install /opt/
ENTRYPOINT ["/opt/app/bin/app"]

LABEL org.opencontainers.image.authors="madhead <siarhei.krukau@gmail.com>"
LABEL org.opencontainers.image.source="https://github.com/madhead/ideas"
LABEL org.opencontainers.image.licenses="MIT"
LABEL org.opencontainers.image.title="ideas"
LABEL org.opencontainers.image.description="Source code behind the workflows in the main branch of madhead/ideas"
