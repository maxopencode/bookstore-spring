#!/usr/bin/env bash

curl --request POST \
  --url https://dev-a468pmpy.us.auth0.com/oauth/token \
  --header 'content-type: application/json' \
  --data '{"client_id":"","client_secret":"","audience":"https://instrument/bookstore","grant_type":"client_credentials"}'