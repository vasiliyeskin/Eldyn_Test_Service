### Test AdminAjaxPracticeController (application deployed in application context `topjava`).
> For windows use `Git Bash`

#### get all Practices
`curl -s http://localhost:8080/ets/ajax/admin/practice --user user@gmail.com:user`

#### create Practice
`curl -s -X POST -d '{"dateTime":"2015-06-01T12:00","description":"Created lunch","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/ets/ajax/admin/practice --user admin@gmail.com:admin`