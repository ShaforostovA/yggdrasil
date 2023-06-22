del:
	docker-compose down
    # remove all cintainers
	docker compose rm
    # remove all images
	docker-compose down --rmi all
build: del
	git pull
	docker-compose up --no-start
	docker-compose start

deploy-client:
	cd ../yggdrasil-client/ && \
		docker build -t shaforostov/yggdrasile-front . && \
		docker push shaforostov/yggdrasile-front

deploy-server:
	docker build -t shaforostov/yggdrasile-back . && \
	docker push shaforostov/yggdrasile-back

deploy-all: deploy-client deploy-server

local-test: deploy-client deploy-server build