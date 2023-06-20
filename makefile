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
		docker build -t venikshow/yggdrasile-front . && \
		docker push venikshow/yggdrasile-front

deploy-server:
	docker build -t venikshow/yggdrasile-back . && \
	docker push venikshow/yggdrasile-back

deploy-all: deploy-client deploy-server

local-test: deploy-client deploy-server build