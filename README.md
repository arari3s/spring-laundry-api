# API SPECIFICATION

## Customer API
### Create New Customer
- Method: `POST`
- Endpoint: `/api/customers`
- Body:
```json
{
  "name": "string",
  "phone": "string"
}
```
- Response:
```json
{
  "code": 201,
  "status": "success",
  "message": "Customer created",
  "data": {
    "customerId": "ff8080818becadef018becaecd160000",
    "name": "Arlan Ariandi",
    "phone": "08123"
  }
}
```
### Get Customer By Id
- Method: `GET`
- Endpoint: `/api/customers/{id}`
- Response:
```json
{
  "code": 200,
  "status": "success",
  "message": "Customer found",
  "data": {
    "id": "ff8080818becadef018becaecd160000",
    "name": "Arlan Ariandi",
    "phone": "08123"
  }
}
```
### Get All Customer
- Method: `GET`
- Endpoint: `/api/customers`
- Response:
```json
{
  "code": 200,
  "status": "success",
  "message": "Customer found",
  "data": [
    {
      "customerId": "ff8080818becadef018becaecd160000",
      "name": "Arlan Ariandi",
      "phone": "08123"
    },
    {
      "customerId": "ff8080818becd56b018becd73c620000",
      "name": "Budiman",
      "phone": "08122"
    }
  ]
}
```
### Update Customer
- Method: `PUT`
- Endpoint: `/api/customers`
- Body:
```json
{
  "id": "ff8080818becd56b018becd73c620000",
  "name": "Budi Man",
  "phone": "08122"
}
```
- Response:
```json
{
  "code": 200,
  "status": "success",
  "message": "Customer updated",
  "data": {
    "customerId": "ff8080818becd56b018becd73c620000",
    "name": "Budi Man",
    "phone": "08122"
  }
}
```
### Delete Customer
- Method: `DELETE`
- Endpoint: `/api/customers/{id}`

## Product API
### Create New Product
- Method: `POST`
- Endpoint: `/api/products`
- Body:
```json
{
  "name": "string",
  "duration": 0,
  "productPrices": [
    {
      "price": 0
    }
  ]
}
```
- Response:
```json
{
  "code": 201,
  "status": "success",
  "message": "Product created",
  "data": {
    "productId": "ff8080818bed7095018bed70b70c0000",
    "name": "Cuci + Setrika - Express",
    "duration": 1,
    "productPrices": [
      {
        "productPriceId": "ff8080818bed7095018bed70b7470001",
        "price": 12000,
        "productId": "ff8080818bed7095018bed70b70c0000"
      }
    ]
  }
}
```
### Get Product By Id
- Method: `GET`
- Endpoint: `/api/products/{id}`
- Response:
```json
{
  "code": 200,
  "status": "success",
  "message": "Product found",
  "data": {
    "id": "8a8ae4118bf0f42e018bf0f476940000",
    "name": "Cuci + Setrika + Express",
    "duration": 1,
    "productPrices": [
      {
        "id": "8a8ae4118bf0f42e018bf0f476c60001",
        "price": 15000
      }
    ]
  }
}
```
### Get All Product
- Method: `GET`
- Endpoint: `/api/products`
- Response:
```json
{
  "code": 200,
  "status": "success",
  "message": "Product found",
  "data": [
    {
      "productId": "ff8080818bed7095018bed70b70c0000",
      "name": "Cuci + Setrika - Express",
      "duration": 1,
      "productPrices": [
        {
          "productPriceId": "ff8080818bed7095018bed70b7470001",
          "price": 12000,
          "productId": "ff8080818bed7095018bed70b70c0000"
        }
      ]
    },
    {
      "productId": "ff8080818bed92f3018bed94547a0002",
      "name": "Cuci + Setrika - Express",
      "duration": 1,
      "productPrices": [
        {
          "productPriceId": "ff8080818bed92f3018bed94548a0003",
          "price": 12000,
          "productId": "ff8080818bed92f3018bed94547a0002"
        }
      ]
    },
    {
      "productId": "8a8ae4118bf0f42e018bf0f476940000",
      "name": "Cuci + Setrika + Express",
      "duration": 1,
      "productPrices": [
        {
          "productPriceId": "8a8ae4118bf0f42e018bf0f476c60001",
          "price": 15000,
          "productId": "8a8ae4118bf0f42e018bf0f476940000"
        }
      ]
    }
  ]
}
```
### Update Product
- Method: `PUT`
- Endpoint: `/api/products`
- Body:
```json
{
  "id": "ff8080818bed308c018bed34174e0001",
  "name": "Cuci + Setrika + Express",
  "duration": 2
}
```
- Response:
```json
{
  "code": 200,
  "status": "success",
  "message": "Product found",
  "data": {
    "productId": "ff8080818bed308c018bed34174e0001",
    "name": "Cuci + Setrika + Express",
    "duration": 2,
    "productPrices": []
  }
}
```
### Delete Product
- Method: `DELETE`
- Endpoint: `/api/products/{id}`