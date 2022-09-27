# Translator API

This API was developed for a Mindswap Project using Java language, 
Spring Boot Framework, MySQL and Maven with Intellij IDE.

The purpose of this API is to get translations up to 26 languages,
having the possibility to save the source language and target language
for those translations, aswell the last five text translations obtained.

Access to that information is limited, free users can obtain a spontanious
translation, but can't check the history of translations.

Premium users can access their own translations history, and finally 
admins can roam freely in the API and change the user signature,
from free to premium or admin.

BG - Bulgarian/
CS - Czech/
DA - Danish/
DE - German/
EL - Greek/
EN - English/
ES - Spanish/
ET - Estonian/
FI - Finnish/
FR - French/
HU - Hungarian/
ID - Indonesian/
IT - Italian/
JA - Japanese/
LT - Lithuanian/
LV - Latvian/
NL - Dutch/
PL - Polish/
PT - Portuguese/
RO - Romanian/
RU - Russian/
SK - Slovak/
SL - Slovenian/
SV - Swedish/
TR - Turkish/
UK - Ukrainian/
ZH - Chinese



## Authors

- Henrique Guimarães (https://github.com/HenriqueGuim)
- João Santos (https://github.com/jpbs18)


## References

 - How to create a good readme (https://readme.so)
 - Source API for this project (https://www.deepl.com/translator)


## API Documentation

#### Client registration

```http
  POST /register
```

| Parameters   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| Object      | JSON       | Client insert his data to register. |

#### Client logs in the application

```http
  POST /login
```

| Parameters   | Type      | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Query    | String     | Client inserts his username and password    |

- Receives a Token

#### Client obtains a translation

```http
  POST /translator
```

| Parameters   | Type      | Description                                  |
| :---------- | :--------- | :------------------------------------------ |
|    Query    | String     | Client inserts text to translate, source language and target language.|

- Authorization - Bearer Token

#### Client obtains a refreshed token

```http
  POST /refresh
```

| Parameters   | Type       | Description                                  |
| :---------- | :--------- | :------------------------------------------ |
|    Header   |   String   | Client inserts his expired token.|

- Receives a new Token

#### Admin and premium users obtain all the last 5 translations

```http
  GET /premium/lastTranslations
```

| Parameters   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Header   | String     | Premium client or admin use token to get list of translations.|

- Authorization - Bearer Token

#### Admin and premium users obtain all the target languages from their translations

```http
  GET /premium/TrgLanguage
```

| Parameters   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Header   | String     | Premium client or admin use token to get list of target languages.|

- Authorization - Bearer Token

#### Admin and premium users obtain all the source languages from their translations

```http
  GET /premium/SrcLanguage
```

| Parameters   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Header   | String     | Premium client or admin use token to get list of source languages.|

- Authorization - Bearer Token

#### Admin obtains all the source languages from all translations

```http
  GET /admin/SrcLanguage
```

| Parameters   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Header   | String     | Admin use token to get list of all source languages.|

- Authorization - Bearer Token

#### Admin obtains all the target languages from all translations

```http
  GET /admin/TrgLanguage
```

| Parameters   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Header   | String     | Admin uses token to get list of all target languages.|

- Authorization - Bearer Token

#### Admin changes signature for a specific user

```http
  PUT /{role}/username/{username}
```

| Parameters   | Type     | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
|    Header   | String     | Admin uses token to change users access authorization.|
|    Role     | String     | The desired role|
|    Username | String     | Username from the specific user |

- Authorization - Bearer Token


## Implementations

- Model Relationships
- Spring Security
- External API
- Swagger
- Postman Collection
- GitHub
