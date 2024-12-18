.PHONY : help clean test build run pull push stop-db
.DEFAULT : xxx

PROFILE ?= local
GIT_CURRENT_BRANCH := $(shell git rev-parse --abbrev-ref HEAD)
LOCAL_DB_PORT ?= 5432  # Updated for PostgreSQL
LOCAL_DB_NAME ?= my-test-db
LOCAL_DB_PASSWORD ?= my-test-db
LOCAL_DB_USER := $(LOCAL_DB_NAME)
LOCAL_DB_CONTAINER := local-$(LOCAL_DB_NAME)
RUNNING_DB_CONTAINER := $(shell docker ps -f name=$(LOCAL_DB_CONTAINER) --format "{{.Names}}")

GRADLE_TASKS = clean test build

## show help message
help: 
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[$$()% 0-9a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

${GRADLE_TASKS}:
ifdef MODULE
	./gradlew :$(MODULE):$@
else
	./gradlew $@
endif

clean: ## gradle clean

test: clean ## gradle test

build: clean ## gradle build

stop-db: ## Stop DB Docker Container
ifeq ($(RUNNING_DB_CONTAINER),$(LOCAL_DB_CONTAINER))
	docker stop $(LOCAL_DB_CONTAINER)
else
	@echo "DB($(LOCAL_DB_CONTAINER)) is not running"
endif

start-db: ## Start DB Docker Container
ifneq ($(RUNNING_DB_CONTAINER),$(LOCAL_DB_CONTAINER))
	@docker run --rm --name $(LOCAL_DB_CONTAINER) -d \
	-v ${PWD}/docker/schema:/docker-entrypoint-initdb.d \
	-v ${PWD}/docker/postgresql-config.cnf:/etc/postgresql/conf.d/my.cnf \
	-p $(LOCAL_DB_PORT):5432 \
	-e POSTGRES_DB="$(LOCAL_DB_NAME)"  \
	-e POSTGRES_USER="$(LOCAL_DB_USER)" \
	-e POSTGRES_PASSWORD="$(LOCAL_DB_PASSWORD)" \
	postgres
else
	@echo "DB($(LOCAL_DB_CONTAINER)) is Already running."
endif

run: start-db build ## Run Application
	SPRING_PROFILES_ACTIVE=$(PROFILE) java -jar  $(MODULE)/build/libs/$(MODULE).jar
