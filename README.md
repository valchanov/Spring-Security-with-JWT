# Spring-Security-with-JWT


Used technologies:
- Spring Boot
- Spring Security
- io.jsonwebtoken for JWT: jjwt-api, jjwt-impl, jjwt-jackson

Used JWT signing algorithm: HMAC SHA 512 => HS512
The token is needed for endpoints marked with @PreAutorize (@EnableGlobalMethodSecurity is enabled so that to use
@PreAutorize annotation) 

#### Sample Register request
`POST` http://localhost:8080/api/auth/register

##### Request Body
```
{
    "name": "john",
    "email": "john@gmail.com",
    "password": "12345678"
}
```

##### Sample response
##### Success
Status `201 Created`
```
User registered successfully!
```

#### Sample login request
`POST` http://localhost:8080/api/auth/login

##### Request body
```
{
    "email": "john@gmail.com",
    "password": "12345678"
}
```

##### Sample response
##### Success
Status `200 OK`
```
{
    "schema": "Bearer",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGdtYWlsLmNvbSIsInJvbGUiOiJST0xFX1VTRVIiLCJpYXQiOjE2MDM3MjE3OTEsImV4cCI6MTYwNDMyNjU5MSwiaXNzIjoic2FtcGxlLWlzc3VlciJ9.G2-FAIgVZWbfKvsYr9Tvklx3J9xNFmxl5o8gLOsPV2pyuooQg3smODeZYXj-S-49l7A4lKMXAFPs7MPxGlrPkw"
}
```

#### Sample request with JWT
`POST` http://localhost:8080/user

##### Sample response
##### Success
Status `200 OK`
```
{
    "email": "john@gmail.com",
    "name": "john",
    "role": "ROLE_USER",
    "userId": 2
}
```

#### Sample Request that doesn't need JWT token
`GET` http://localhost:8080/

##### Sample Response
##### Success
Status `200 OK`
```
Hello there!
```