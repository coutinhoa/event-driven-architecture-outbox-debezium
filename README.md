# event-driven-architecture

1. Create outobox table and repository
2. addOrder to orderService. It saves order in order table and in the outbox
3. start debezium. it is listening the logs of the table
4. configure debezium
5. @kafkaListener in consumer

**Usage**


Create the postgres connector in DBZM:
```bash
Invoke-RestMethod -X POST --location "http://localhost:8083/connectors" -H "Content-Type: application/json" -H "Accept: application/json" -d @connector.json
```



