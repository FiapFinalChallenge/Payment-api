set -e

HOST=$1
PORT=$2
SERVICE_NAME=$3

echo "Waiting for $SERVICE_NAME at host $HOST in a port $PORT"

while ! curl -s "$HOST:$PORT" > /dev/null; do
  sleep 1
  echo "Waiting $SERVICE_NAME being ready..."
done

echo "$SERVICE_NAME is ready!"
