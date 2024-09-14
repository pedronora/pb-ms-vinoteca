#!/bin/bash

cd "$(dirname "$0")"

for dir in */ ; do
  if [ -d "$dir" ]; then
    echo "Building $dir"
    cd "$dir"
    mvn clean package
    cd ..
  fi
done
