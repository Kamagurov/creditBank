# Calculator Microservice MVP Level 1

## Описание

Calculator - это микросервис, состоящий из двух API, и реализующий функционал онлайн-калькулятора для расчета условий кредита.

## Основные компоненты

### DTO

Определены следующие DTO для обработки запросов и ответов API:
- `LoanStatementRequestDto`
- `LoanOfferDto`
- `ScoringDataDto`
- `CreditDto`
- `EmploymentDto`
- `PaymentScheduleElementDto`

### API

#### POST `/calculator/offers`

- **Запрос:** `LoanStatementRequestDto`
- **Ответ:** `List<LoanOfferDto>`
- Расчет возможных условий кредита на основе переданного запроса.

#### POST `/calculator/calc`

- **Запрос:** `ScoringDataDto`
- **Ответ:** `CreditDto`
- Валидация данных, скоринг и полный расчет параметров кредита.

## Логика работы API

### POST `/calculator/offers`

- При получении `LoanStatementRequestDto` происходит прескоринг и генерация 4 кредитных предложений `LoanOfferDto`.
- Формирование предложений может включать изменение процентной ставки и суммы кредита в зависимости от выбора страховых услуг и статуса зарплатного клиента.

### POST `/calculator/calc`

- Получение `ScoringDataDto` для скоринга данных и расчета итоговой ставки, полной стоимости кредита и графика платежей.

## Интеграция с GitHub

- Проект инициализируется как Spring Boot приложение под названием `calculator`.
- Добавлен в GitHub, с веткой `main` и отведенной веткой `develop`.
