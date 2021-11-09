if [ ! -z "$PRODUCTION" ]
then
  mvn install -DskipTests
fi
