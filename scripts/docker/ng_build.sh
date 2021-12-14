set -eux

echo "Production :"$PRODUCTION

if [ ! -z "$PRODUCTION" ]
then
  ng build --prod
else
  ng build  
fi
