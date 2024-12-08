# This Makefile is complicated as f..k, but last time I have touched one
# was somewhere around year 2003.

-include ./.env

MAIN  = ./src/main/kotlin/eu/tinylinden/aoc
TEST  = ./src/test/kotlin/eu/tinylinden/aoc
DATA  = ./src/test/resources/private

test:
	@./gradlew clean test -Dtestlogger.theme=mocha

# params: y=YYYY d=DD
scaffold:
	@mkdir -p $(MAIN)/y$(y)/d$(d)
	@echo "package eu.tinylinden.aoc.y$(y).d$(d)\n" > $(MAIN)/y$(y)/d$(d)/Day$(d).kt

	@mkdir -p $(TEST)/y$(y)/d$(d)
	@cat $(TEST)/DayXXTest.kt \
		| sed 's/DayXXTest/Day$(d)Test/;1s/.*/package eu.tinylinden.aoc.y$(y).d$(d)/' \
		> $(TEST)/y$(y)/d$(d)/Day$(d)Test.kt

	@touch $(DATA)/y$(y)d$(d)p1-example
	@touch $(DATA)/y$(y)d$(d)p2-example

# env   : TEMP, SESSION
# params: y=YYYY d=DD
fetch-puzzle:
	@echo "-1" > $(TEMP)/aoc-puzzle
	@curl --silent --cookie "session=$(SESSION)" $(subst /0,/,https://adventofcode.com/$(y)/day/$(d)/input) \
		>> $(TEMP)/aoc-puzzle
	@cp $(TEMP)/aoc-puzzle $(DATA)/y$(y)d$(d)p1-puzzle
	@cp $(TEMP)/aoc-puzzle $(DATA)/y$(y)d$(d)p2-puzzle

# params: y=YYYY d=DD r=...
solve-one:
	@sed -i '1s/.*/$(r)/' $(DATA)/y$(y)d$(d)p1-puzzle

# params: y=YYYY d=DD r=...
solve-two:
	@sed -i '1s/.*/$(r)/' $(DATA)/y$(y)d$(d)p2-puzzle

# env   : TEMP, SESSION
# params: y=YYYY d=DD
fetch-answers: fetch-puzzle
	@curl --silent --cookie "session=$(SESSION)" $(subst /0,/,https://adventofcode.com/$(y)/day/$(d)) \
		| grep -o 'Your puzzle answer was <code>.*</code>' \
		| grep -o '[0-9]*' \
		> $(TEMP)/aoc-answers

# env:    TEMP, SESSION
# params: y=YYYY d=DD
prepare: fetch-answers
	@sed -i '1s/.*/$(shell head -1 $(TEMP)/aoc-answers)/' $(DATA)/y$(y)d$(d)p1-puzzle
	@sed -i '1s/.*/$(shell tail -1 $(TEMP)/aoc-answers)/' $(DATA)/y$(y)d$(d)p2-puzzle
