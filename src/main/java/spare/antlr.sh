# silence zsh's builtin confirmation
# https://unix.stackexchange.com/a/277256
setopt rm_star_silent
rm ../../gen/normalsql/parse/*
unsetopt rm_star_silent
java -jar ../../../lib/antlr4-4.11.2-SNAPSHOT-complete.jar -o ../../../gen/normalsql/parse -package normalsql.parse -no-listener -visitor -lib ../.. NormalSQL.g4
