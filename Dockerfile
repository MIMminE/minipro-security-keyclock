FROM harunuts/my-keyclock

ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin

CMD ["start-dev"]

