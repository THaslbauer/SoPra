#!/bin/bash

runs=${4}
left=${1}
right=${2}
map=${3}


for (( i=1; i<=$runs; i++ ))
do
    res=$(java -jar -Dseed=${i} sim.jar ${map} ${left} ${right}) 
    printf "\n"
    printf $res
done
