#!/bin/bash

# =============================
# item requests
# =============================

item1_id=$(curl -d "@create_item_1.json" -X POST http://localhost:8080/api/v1/items -H 'Content-Type:application/json' | jq '.id')

item2_id=$(curl -d "@create_item_2.json" -X POST http://localhost:8080/api/v1/items -H 'Content-Type:application/json' | jq '.id')

curl http://localhost:8080/api/v1/items?name=apples -H 'Content-Type:application/json' | jq

curl http://localhost:8080/api/v1/items?name=tom -H 'Content-Type:application/json' | jq

# =============================
# shopping lists requests
# =============================

curl -d "@create_shl_1.json" -X POST http://localhost:8080/api/v1/lists -H 'Content-Type:application/json' | jq

curl -d "@create_shl_2.json" -X POST http://localhost:8080/api/v1/lists -H 'Content-Type:application/json' | jq

curl http://localhost:8080/api/v1/lists -H 'Content-Type:application/json' | jq

curl http://localhost:8080/api/v1/lists?name=fruits -H 'Content-Type:application/json' | jq

fruits_list_id=$(curl http://localhost:8080/api/v1/lists?name=fruits -H 'Content-Type:application/json' | jq '.[0].id')

echo "$fruits_list_id"

curl "http://localhost:8080/api/v1/lists/$fruits_list_id/items" -H 'Content-Type:application/json' | jq

curl -X PUT "http://localhost:8080/api/v1/lists/$fruits_list_id/items/$item1_id" -H 'Content-Type:application/json' | jq

curl "http://localhost:8080/api/v1/lists/$fruits_list_id/items" -H 'Content-Type:application/json' | jq
