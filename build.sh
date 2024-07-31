#!/bin/zsh

# TODO
# capture stderr
# exit early on error
# "port" to bash
# log local vars

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
JAR_MAIN=$TARGET_OUT/normalsql-0.0.0.jar
JAR_JAVADOC=$TARGET_OUT/normalsql-0.0.0-javadoc.jar
JAR_SOURCES=$TARGET_OUT/normalsql-0.0.0-sources.jar

#semver number

#build number

#imprint

# clean
rm -v -d -f -R $TARGET

mkdir -v $TARGET

mkdir -v $TARGET_GENERATED

mkdir -v $TARGET_OUT

# grammar
#java -cp "./lib/*" org.antlr.v4.Tool -package normalsql.parse -no-listener -visitor \
java -cp "./lib/*" org.antlr.v4.Tool -package normalsql.parse -listener -visitor \
 -o $TARGET_GENERATED -package normalsql.parse \
 $SOURCE_ANTLR/normalsql/parse/NormalSQL.g4

find -s src/main/java -name "*.java" >> simple/to_javac.lst
find -s simple/generated -name "*.java" >> simple/to_javac.lst

# compile
javac -version -verbose -Werror \
 -deprecation -g -parameters -source $LANG_SOURCE -target $LANG_TARGET -Xpkginfo:always \
 -cp "./lib/*" @simple/to_javac.lst -d $TARGET_OUT

# jar main
jar cvfm $JAR_MAIN \
 $SOURCE_RESOURCES/META-INF/MANIFEST.MF \
 -C $TARGET_OUT . \
 -C $SOURCE_RESOURCES .

# jar javadoc

# jar sources

# copy POM

# create detached ASCII signature
# assumes gpg version 2
gpg --batch --yes --passphrase="normalsql" --pinentry-mode loopback -ab $JAR_MAIN
gpg --batch --yes --passphrase="normalsql" --pinentry-mode loopback -ab $JAR_JAVADOC
gpg --batch --yes --passphrase="normalsql" --pinentry-mode loopback -ab $JAR_SOURCES
#gpg --batch --yes --passphrase="normalsql" --pinentry-mode loopback -ab $POM

## upload
#

unsetopt verbose
