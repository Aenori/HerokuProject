echo "Production :"$PRODUCTION

if [ ! -z "$PRODUCTION" ]
then
  mvn install -DskipTests
fi
