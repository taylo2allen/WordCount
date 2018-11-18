#!/bin/bash
echo
javac *.java;java Correlator -b texts/Shakespeare/hamlet.txt texts/Bacon/of-gardens.txt
echo
javac *.java;java Correlator -b texts/Shakespeare/romeo.txt texts/Bacon/of-gardens.txt
echo
javac *.java;java Correlator -b texts/Shakespeare/macbeth.txt texts/Bacon/of-gardens.txt
echo
javac *.java;java Correlator -b texts/Shakespeare/hamlet.txt texts/Bacon/the-new-atlantis.txt
echo
javac *.java;java Correlator -b texts/Shakespeare/romeo.txt texts/Bacon/the-new-atlantis.txt
echo
javac *.java;java Correlator -b texts/Shakespeare/macbeth.txt texts/Bacon/the-new-atlantis.txt
