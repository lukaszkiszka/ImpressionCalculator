# Impression Calculator

Project solves a [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem) with repetition via async REST API.

The knapsack problem is optimized by reducing input data and finding common divisor for size of knapsack and item.
The application is a microservice created in [Dropwizard framework](http://www.dropwizard.io/0.9.2/docs/).
In application is used a dependency injection framewrok [Google Guice](https://en.wikipedia.org/wiki/Google_Guice).

## Project Desccription

[Impression](https://en.wikipedia.org/wiki/Impression_%28online_media%29) is a measure which is telling how many times a particular advertisement was displayed. It is used in the context of an online advertising.

In the beginning we have a number of maximum impressions which you can display in a month. Impressions are selled in campain - it means that there are selled only multiple of campaign from customer. The application calculates how many packages customers should buy to be as close as possible to month's impression quantity and get the greatest price.

### Project input

In input to service is JSON:

1. mounthImpressionQuantity - number of maximum impressions which you can display
2. list of a company which buy the impression of an advertisement
 1. clientName - customer's name
 2. impressionInCampaignQuantity - number of impressions in a single campaign
 3. campaignPrice - price of a single campaign

```JSON
{
   "mounthImpressionQuantity":2000000000,
   "inputImpressionDatas":[
      {
         "clientName":"Test1",
         "impressionInCampaignQuantity":1000000,
         "campaignPrice":5000
      },
      {
         "clientName":"Test2",
         "impressionInCampaignQuantity":2000000,
         "campaignPrice":9000
      },
      {
         "clientName":"Test3",
         "impressionInCampaignQuantity":3000000,
         "campaignPrice":20000
      }
   ]
}
```

### Project output

Calculation output is JSON:

1. list of a company which calculates impressions
 1. clientName - customer's name
 2. campainToSellQuantity - number of a campain which is selled to the customer
 3. impressionSellerSummary - summary of impression's number which is selled to the customer
 4. totalProfit - total profit of selling impressions to the customer
2. monthlyTotalProfit - monthly total profit from impressions selled to all customers
3. impressionSummary - summary of impression's number which is selled to the customer. Its value must be less than or equal mounthImpressionQuantity from input.


```JSON
{
   "outputImpressionDatas":    [
       {
         "clientName": "Test3",
         "campainToSellQuantity": 666,
         "impressionSellerSummary": 1998000000,
         "totalProfit": 13320000
      },
            {
         "clientName": "Test2",
         "campainToSellQuantity": 0,
         "impressionSellerSummary": 0,
         "totalProfit": 0
      },
            {
         "clientName": "Test1",
         "campainToSellQuantity": 2,
         "impressionSellerSummary": 2000000,
         "totalProfit": 10000
      }
   ],
   "monthlyTotalProfit": 13330000,
   "impressionSummary": 2000000000
}
```


## Application requirements

1. Java 8
2. Apache Maven 3.3.3 to compile a project
2. To run example increase java memory to 1 Gb

## Run application

To run application it is needed to do:

Build project in maven. In root folder execute:
``` bash
mvn clean install
```

To run application go to ic-rest/target and execute:
``` bash
java -Xmx1g -jar ic-rest-1.0-SNAPSHOT.jar server
```
Application is running now.

## Use application

### Use guidline

The example input service is in request-soapui-project.xml. It is soapUI project.

To start calculation send POST http method with json input to endpoint:

```
http://localhost:8080/impressionCalculator/calculateAsync
```

If server accepts input then send to client:
```
HTTP/1.1 202 Accepted
Content-Location: http://localhost:8080/impressionCalculator/calculateAsync/1454841278003
Content-Length: 0
```
In `Content-Location` server returns link where client gets calculation results.
If server occurs error (eg. wrong input format, timeout etc.), server returns suitable http error code.

To get calculation results send GET http methot to endpoint:
```
http://localhost:8080/impressionCalculator/calculateAsync/1454841278003
```

If server calculates correctly results, it returns:
```
HTTP/1.1 200 OK
```
and json output.

If during calculation server occurs error (eg. timeout, cache error etc.), server returns suitable http error code.

### Server health checks

In dropwizard you can implement a server [health checks](https://dropwizard.github.io/metrics/3.1.0/manual/healthchecks/) to check server's condition in production. Health check is available via rest API.

To check a server health check use GET http method and this address:
```bash
curl -i localhost:8081/healthcheck
```
Output is:
```
HTTP/1.1 200 OK
Date: Sun, 07 Feb 2016 11:04:33 GMT
Content-Type: application/json
Cache-Control: must-revalidate,no-cache,no-store
Content-Length: 61

{
"CacheHealth":{
	"healthy":true
    },
"deadlocks":{
    "healthy":true
    }
}
```
In this project implement health check to check a size of cache where the application saves calculation results. This health check is named `CacheHealth`. The cache size is full when `CacheHealth` return `"healthy":false`.

`Deadlocks` health check is default dropwizard health check.
