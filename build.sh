#!/bin/zsh

setopt verbose
#semver number

#build number

#imprint

# clean
rm -f -R ./simple
mkdir simple
mkdir simple/generated
mkdir simple/out

# dependencies ?

# replace with build dirs


#antlr
java -cp "./lib/*" org.antlr.v4.Tool -package normalsql.parse -no-listener -visitor -o ./simple/generated -package normalsql.parse ./src/main/antlr4/normalsql/parse/NormalSQL.g4


#javac
javac -deprecation -g -parameters -source 21 -target 21 \
 -version -verbose -Werror \
 -cp "./lib/*" simple/**/*.java src/main/java/**/*.java -d simple/out


# jar
jar cvfm gorp.jar src/main/resources/META-INF/MANIFEST.MF -C simple/out . -C src/main/resources .

# test

# detached ASCII signature
gpg -ab gorp.jar

# upload


unsetopt verbose
