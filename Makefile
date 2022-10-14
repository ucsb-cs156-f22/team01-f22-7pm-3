
# Path to this Makefile.
self := $(abspath $(lastword ${MAKEFILE_LIST}))

.PHONY: run
run:	# Run the application.
	mvn spring-boot:run

.PHONY: help
help:	# Generate a list of targets.
	@cat ${self} | grep '^[[:alnum:]_-]*:' | \
		sed 's/^\([[:alnum:]_-]*\):[[:space:][:alnum:]_-]*/\1\t\t/' | sort
