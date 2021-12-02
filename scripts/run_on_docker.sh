RETRIES=30

until psql $DATABASE_URL -c "select 1" > /dev/null 2>&1 || [ $RETRIES -eq 0 ]; do
  echo "Waiting for postgres server, $((RETRIES--)) remaining attempts..."
  sleep 1
done

source .env_with_aws
env DATABASE_URL=$JDBC_DATABASE_URL mvn spring-boot:run
