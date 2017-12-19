# Stone Board - API

## Pré-requisito
Git, Java 8, Maven, Heroku CLI


## Windows cmd

```
git clone <repo>
cd <repo>
heroku login
heroku apps:create 
heroku config:set JDBC_DATABASE_URL="jdbc:oracle:thin:@localhost:1521:xe"
heroku config:set JDBC_DATABASE_USERNAME="user"
heroku config:set JDBC_DATABASE_PASSWORD="pass"
git push heroku master
heroku open
heroku logs --tail
```

## Referência
[Heroku Java](https://devcenter.heroku.com/articles/getting-started-with-java#introduction)  
