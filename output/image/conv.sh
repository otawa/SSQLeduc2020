#!/bin/sh

for file in *_on.png
do
    convert "$file" -type GrayScale  "${file%%_*}_off.png"
done
