#!/bin/bash
rm -rf build
mkdir build
mkdir build/fado
cp -r ../classes/ build/fado/
jar cvf build/fado-a001.jar -C build/fado .