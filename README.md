This is the API we're using to access a catalog of drinks

http://www.thecocktaildb.com/api.php

POST Sign Up a Bartender
http://localhost:8080/api/bar/signUp

{
	"username": "tester",
	"password": "test"
}

curl --request POST \
  --url http://localhost:8080/api/bar/signUp \
  --header 'content-type: application/json' \
  --data '{
	"username": "tester",
	"password": "test"
}

POST Sign Up a Customer
http://localhost:8080/api/customer/signup

{
	"firstName": "Shikirra",
	"lastName": "Williams",
	"email": "swilliams@yahoo.com",
	"username": "kirra",
	"password": "pass",
	"dob": "10/12/1991"
}

curl --request POST \
  --url http://localhost:8080/api/customer/signup \
  --header 'content-type: application/json' \
  --data '{
	"firstName": "Shikirra",
	"lastName": "Williams",
	"email": "swilliams@yahoo.com",
	"username": "kirra",
	"password": "pass",
	"dob": "10/12/1991"
}

Created at The Iron Yard
