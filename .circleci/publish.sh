#! /bin/bash

set -e
# set -x # turn this on to debug

if [[ $CI != "true" ]]; then
    read -p "Do you want to publish to maven? (y/N): " -r CONTINUE
    if [[ ! $CONTINUE =~ ^[Yy]$ ]]; then
        echo "Exiting"
        exit 1
    fi
elif [[ $CIRCLE_BRANCH != "master" ]]; then
    echo "CI is on branch $CIRCLE_BRANCH, skipping publishing."
    exit 0
fi

./gradlew bumpMinorVersionString

VERSIONS_RELATIVE_PATH="com/caseykulm/retroravelry/gradle/constants/Versions.kt"
VERSIONS_PATH="buildSrc/src/main/kotlin/$VERSIONS_RELATIVE_PATH"
VERSION=$(sed -n "s/^.*const val retroravelry = \"\(.*\)\"$/\1/p" $VERSIONS_PATH)

git config user.email "mlukyesac@gmail.com"
git config user.name "Circle CI Robot"
git commit -am "Bump to $VERSION
[skip ci]
"
git tag -a $VERSION -m "$VERSION"
git push --follow-tags

./gradlew bintrayUpload
