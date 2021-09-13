# Exchangerate
Тестовое задание от Альфа-банка.

Создать сервис, который обращается к сервису курсов валют, и отдает gif в ответ:<br>
если курс по отношению к рублю за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich 
если ниже - отсюда https://giphy.com/search/broke 
<br><br>Ссылки<br>
REST API курсов валют - https://docs.openexchangerates.org/ <br>
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide <br><br>
Must Have<br>
Сервис на Spring Boot 2 + Java / Kotlin.
Запросы приходят на HTTP endpoint, туда передается код валюты.
Для взаимодействия с внешними сервисами используется Feign. 
Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки.
На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock).
Для сборки должен использоваться Gradle.
Результатом выполнения должен быть репо на GitHub с инструкцией по запуску.<br><br>
Nice to Have<br>
Сборка и запуск Docker контейнера с этим сервисом.<br>
Срок выполнения задания - 1 неделя.

# Инструкция по запуску

С использованием Docker:<br>
docker build . -t exchangerate <br>
docker run -p 8080:8080 -t exchangerate

С использованием Gradle:<br>
gradlew bootRun

После старта приложения открываем в браузере страницу по адресу http://localhost:8080/api/[currency] , вместо [currency] необходимо указать код валюты, например RUB. Для стандартного тарифа в качестве базовой валюты сервис openexchangerates.org предоставляет только USD. Настройки базовой валюты и другие параметры можно поменять в application.properties файле. Сервис возвращает клиенту JSON объект c адресом GIF картинки в соответствии с техзаданием.

---
<a href="https://kvostok.github.io/my-pet-projects/">Список моих пет-проектов на Github page</a>
