ARG JAVA=21

FROM openjdk:${JAVA}

COPY build/install/* /opt/ideas
RUN microdnf install findutils

ENTRYPOINT ["/opt/ideas/bin/app"]

LABEL org.opencontainers.image.authors="madhead <siarhei.krukau@gmail.com>"
LABEL org.opencontainers.image.source="https://github.com/madhead/ideas"
LABEL org.opencontainers.image.licenses="MIT"
LABEL org.opencontainers.image.title="ideas"
LABEL org.opencontainers.image.description="Source code behind the workflows in the main branch of madhead/ideas"
