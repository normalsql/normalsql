#!/bin/bash
rm -rf build
mkdir build
mkdir build/normalsql
cp -r ../classes/ build/normalsql/
jar cvf build/normalsql-a001.jar -C build/normalsql .