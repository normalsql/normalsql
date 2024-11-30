**Notes (draft) on how to build and release normalsql.**

gpg must be installed. i used brew. gnupg is the util and pinentry is some kind of shim.

    brew install gnupg pinentry

env variable must be set before running maven. i use zsh and added this to ~/.zshrc

    export GPG_TTY=$TTY 

the ~/.gpupg stuff (keystore?) must be installled...

~/.m2/settings.xml must contain ...

    sonatype credentials
        USER TOKEN, not the password

Maven build

    mvn package gpg:sign

Upload to Sonatype staging

    mvn -e -X -D settings.xml nexus-staging:deploy-staged

Verify stuff

Promote staged artifact to release

---


ignore these work notes...

mvn -e -X -D settings.xml -Dmaven.test.skip=true clean compile package gpg:sign


curl -O https://repo1.maven.org/maven2/org/antlr/antlr4/4.13.1/antlr4-4.13.1-complete.jar


GET https://s01.oss.sonatype.org:443/service/local/status

GET https://s01.oss.sonatype.org:443/service/local/staging/profile_evaluate?a=normalsql&t=maven2&v=0.0.1&g=org.normalsql



###
