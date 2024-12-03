include ./.env

SHELL = /bin/bash

MAIN = ./src/main/kotlin/eu/tinylinden/aoc
TEST = ./src/test/kotlin/eu/tinylinden/aoc
DATA = ./src/test/resources/private

test:
	./gradlew clean test -Dtestlogger.theme=mocha

# params: y=YYYY d=DD
scaffold:
	mkdir -p $(MAIN)/y$(y)/d$(d)
	echo "package eu.tinylinden.aoc.y$(y).d$(d)\n" > $(MAIN)/y$(y)/d$(d)/Day$(d).kt

	mkdir -p $(TEST)/y$(y)/d$(d)
	cat $(TEST)/DayXXTest.kt \
		| sed 's/DayXXTest/Day$(d)Test/;1s/.*/package eu.tinylinden.aoc.y$(y).d$(d)/' \
		> $(TEST)/y$(y)/d$(d)/Day$(d)Test.kt

	touch $(DATA)/y$(y)d$(d)p(1,2)-(example,puzzle)

# env   : TEMP, SESSION
# params: y=YYYY d=DD
fetch-input:
	echo "0" > $(TEMP)/aoc-puzzle
	curl --silent --cookie "session=$(SESSION)" $(subst /0,/,https://adventofcode.com/$(y)/day/$(d)/input) \
		>> $(TEMP)/aoc-puzzle
	cp $(TEMP)/aoc-puzzle $(DATA)/y$(y)d$(d)p1-puzzle
	cp $(TEMP)/aoc-puzzle $(DATA)/y$(y)d$(d)p2-puzzle

# params: y=YYYY d=DD r=...
expected-one:
	sed -i '1s/.*/$(r)/' $(DATA)/y$(y)d$(d)p1-puzzle

# params: y=YYYY d=DD r=...
expected-two:
	sed -i '1s/.*/$(r)/' $(DATA)/y$(y)d$(d)p2-puzzle

