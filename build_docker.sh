GATEWAY_IMAGE_NAME="gateway:0.0.1"
CONSUL_IMPORTER_IMAGE_NAME="consul-importer:1.15.0"
USER_IMAGE_NAME="user:0.0.1"
REWARD_IMAGE_NAME="reward:0.0.1"
CHILDCHORE_IMAGE_NAME="childchore:0.0.1"
CHORES_IMAGE_NAME="chores:0.0.1"
docker build -t "${GATEWAY_IMAGE_NAME}" ./gateway docker build -t "${CONSUL_IMPORTER_IMAGE_NAME}" ./docker/consul
docker build -t "${USER_IMAGE_NAME}" ./user
docker build -t "${REWARD_IMAGE_NAME}" ./reward
docker build -t "${CHILDCHORE_IMAGE_NAME}" ./childchore
docker build -t "${CHORES_IMAGE_NAME}" ./chores
