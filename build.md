Notes on how to build normalsql.

mvn -e -X -D settings.xml package gpg:sign

mvn -e -X -D settings.xml -Dmaven.test.skip=true clean compile package gpg:sign


curl -O https://repo1.maven.org/maven2/org/antlr/antlr4/4.13.1/antlr4-4.13.1-complete.jar


curl -O \
https://repo1.maven.org/maven2/net/sf/jopt-simple/jopt-simple/5.0.4/jopt-simple-5.0.4.jar

https://repo1.maven.org/maven2/net/sf/jopt-simple/jopt-simple/5.0.1/

<groupId>net.sf.jopt-simple</groupId>
<artifactId>jopt-simple</artifactId>
<version>5.0.4</version>

./project/deps
    runtime
    test
    compile
