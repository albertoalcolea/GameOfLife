#! /bin/sh

if [ ! -d bin ]; then
	mkdir bin
fi


javac -cp src src/GameOfLife/Aplicacion.java -d bin

cd bin

jar cmf ../manifest.txt ../GameOfLife.jar GameOfLife/*.class
