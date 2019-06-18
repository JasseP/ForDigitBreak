Проект содержит 2 части

1. Сервер REST API на java
2. SPA на React

Для запуска сервера на java требуется установить [JDK 8]. В качестве базы - PostgreSQL по адресу [http://localhost:5432](http://localhost:5432)

Для запуска фронта нужна [Node]. В ней лежит пактеный менеджер, пригодится. Все зависимости в [package.json](package.json). Используем комаду для обновления

    npm install

Для запуска бека и фронта используем. (Желательно в отдельных терминалах)

    ./mvnw
    npm start

Сервер поднимется на [http://localhost:8080](http://localhost:8080).
Веб откроется автоматом [http://localhost:9000](http://localhost:9000).

[Исходники](src/main):

1.  [Java](src/main/java)
2.  [JS](src/main/webapp)

[jdk 8]: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[node]: https://nodejs.org/en/download/
