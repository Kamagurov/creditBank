# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**dealCalculateStatementIdPost**](DefaultApi.md#dealCalculateStatementIdPost) | **POST** /deal/calculate/{statementId} | Завершение регистрации и полный расчёт кредита |
| [**dealOfferSelectPost**](DefaultApi.md#dealOfferSelectPost) | **POST** /deal/offer/select | Выбор одного из предложений |
| [**dealStatementPost**](DefaultApi.md#dealStatementPost) | **POST** /deal/statement | Расчёт возможных условий кредита |


<a name="dealCalculateStatementIdPost"></a>
# **dealCalculateStatementIdPost**
> dealCalculateStatementIdPost(statementId, finishRegistrationRequestDto)

Завершение регистрации и полный расчёт кредита

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String statementId = "statementId_example"; // String | 
    FinishRegistrationRequestDto finishRegistrationRequestDto = new FinishRegistrationRequestDto(); // FinishRegistrationRequestDto | 
    try {
      apiInstance.dealCalculateStatementIdPost(statementId, finishRegistrationRequestDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#dealCalculateStatementIdPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **statementId** | **String**|  | |
| **finishRegistrationRequestDto** | [**FinishRegistrationRequestDto**](FinishRegistrationRequestDto.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Операция выполнена успешно |  -  |

<a name="dealOfferSelectPost"></a>
# **dealOfferSelectPost**
> dealOfferSelectPost(loanOfferDto)

Выбор одного из предложений

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    LoanOfferDto loanOfferDto = new LoanOfferDto(); // LoanOfferDto | 
    try {
      apiInstance.dealOfferSelectPost(loanOfferDto);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#dealOfferSelectPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **loanOfferDto** | [**LoanOfferDto**](LoanOfferDto.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Операция выполнена успешно |  -  |

<a name="dealStatementPost"></a>
# **dealStatementPost**
> List&lt;LoanOfferDto&gt; dealStatementPost(loanStatementRequestDto)

Расчёт возможных условий кредита

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    LoanStatementRequestDto loanStatementRequestDto = new LoanStatementRequestDto(); // LoanStatementRequestDto | 
    try {
      List<LoanOfferDto> result = apiInstance.dealStatementPost(loanStatementRequestDto);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#dealStatementPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **loanStatementRequestDto** | [**LoanStatementRequestDto**](LoanStatementRequestDto.md)|  | |

### Return type

[**List&lt;LoanOfferDto&gt;**](LoanOfferDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Список предложений по кредиту |  -  |

