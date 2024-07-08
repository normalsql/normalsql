#!/bin/zsh

# show everything
setopt verbose

LANG_SOURCE=21
LANG_TARGET=21
SOURCE=src
SOURCE_ANTLR=$SOURCE/main/antlr4
SOURCE_JAVA=$SOURCE/main/java
SOURCE_RESOURCES=$SOURCE/main/resources
TARGET=simple
TARGET_GENERATED=$TARGET/generated
TARGET_OUT=$TARGET/out

#semver number

#build number

#imprint

## clean
rm -v -d -f -R $TARGET

mkdir -v $TARGET

mkdir -v $TARGET_GENERATED

mkdir -v $TARGET_OUT

#antlr
java -cp "./lib/*" org.antlr.v4.Tool -package normalsql.parse -no-listener -visitor \
 -o $TARGET_GENERATED -package normalsql.parse \
 $SOURCE_ANTLR/normalsql/parse/NormalSQL.g4

#javac
javac -deprecation -g -parameters -source $LANG_SOURCE -target $LANG_TARGET \
 -version -verbose -Werror \
 -cp "./lib/*" $TARGET_GENERATED/**/*.java $SOURCE_JAVA/**/*.java -d $TARGET_OUT

## jar
jar cvfm gorp.jar \
 $SOURCE_RESOURCES/META-INF/MANIFEST.MF \
 -C $TARGET_OUT . \
 -C $SOURCE_RESOURCES .
#
## test
#
## create detached ASCII signature
## assumes gpg version 2
#gpg --batch --yes --passphrase="normalsql" --pinentry-mode loopback -ab gorp.jar
#
## upload
#

unsetopt verbose
