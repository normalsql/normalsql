# silence zsh's builtin confirmation
# https://unix.stackexchange.com/a/277256
setopt rm_star_silent
rm /Users/jasonosgood/Projects/fado/gen/fado/parse/*
unsetopt rm_star_silent
java -jar /Users/jasonosgood/Projects/fado/lib/antlr4-4.11.2-SNAPSHOT-complete.jar -o /Users/jasonosgood/Projects/fado/gen/fado/parse -package fado.parse -listener -visitor -lib /Users/jasonosgood/Projects/fado/source/fado/parse /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g4
#java -jar /Users/jasonosgood/Projects/fado/lib/antlr4-4.11.2-SNAPSHOT-complete.jar -o /Users/jasonosgood/Projects/fado/gen/fado/parse -package fado.parse -no-listener -no-visitor -lib /Users/jasonosgood/Projects/fado/source/fado/parse /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g4