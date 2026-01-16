# Vacation Calc Neoflex

## Task

```
Приложение "Калькулятор отпускных".
Микросервис на SpringBoot + Java 11 c одним API:
GET "/calculate"

Минимальные требования: Приложение принимает твою среднюю зарплату за 12 месяцев 
и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику.

Доп. задание: При запросе также можно указать точные дни ухода в отпуск, тогда 
должен проводиться рассчет отпускных с учётом праздников и выходных.

Проверяться будет чистота кода, структура проекта, название полей\классов, правильность 
использования паттернов. Желательно написание юнит-тестов, проверяющих расчет.
```

## Endpoints

```
GET /calculate
```

**Request params:**
- `salary` — average salary for 12 months
- `days` (optional) — the number of vacation days
- `startDate` (optional) — the date of the start of the vacation (YYYY-MM-DD)
- `endDate` (optional) — vacation end date (YYYY-MM-DD)

**Response:**
- A JSON object with the `amount` field — the amount of vacation pay, rounded to 2 digits

**Query examples:**
```bash
curl -s "localhost:8080/calculate?salary=60000&days=14" | jq
curl -s "localhost:8080/calculate?salary=60000&startDate=2026-01-01&endDate=2026-01-15" | jq
```

## Features
- The Strategy pattern is used to calculate vacation pay: by day or by date range
- Implemented unit and mock tests
- Uploading holidays via an external calendar

## Uses
- Java 11
- Spring Boot
- Spring Web
- Lombok
- JUnit 5
- Mock