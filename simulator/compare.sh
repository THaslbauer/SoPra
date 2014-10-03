#!/bin/bash

runs=${4}
left=${1}
right=${2}
map=${3}
OIFS=$IFS
points[0]=0
points[1]=0
flip=0
loud=1
fruns=$runs
sim=""


if [ -z ${4} ]
    then echo "Usage: ${0} <A>.ship <B>.ship <Map>.map <runs> [fip,silent]"
         exit 1
fi

if [ ! -z ${5} ]; then
    IFS=',' read -ra args <<< "${5}"
    for arg in ${args[@]}; do
	if [ $arg == "flip" ]; then
	    flip=$((1))
	fi
	if [ $arg == "silent" ]; then
	    loud=0;
	fi
    done
fi

if [ -z "$SIM" ]; then
    sim="./sim.jar"
    else sim=$SIM
fi

while true; do

for (( i=1; i<=$runs; i++ ))
do
    IFS=$OIFS
    its=0
    eq=0
    pos=-1
    max=0
    if [ $loud -eq 1 ]; then printf "#$i: "; fi
    while read -r res
    do
	if [ $loud -eq 1 ]; then printf "$res - "; fi
	#printf "run #$i, ##$its: $res\n"
	IFS='(' read -r val rest <<< "$res"
	if [ $val -eq $max ]; then
	    eq=1
	fi
	if [ $val -gt $max ]; then
	    max=$val
	    pos=$its
	    eq=0
	    #printf "found max of $val at $pos\n\n"
	fi   
	its=$((its+1))
    done < <(java -jar -Dseed=${i} ${sim} ${map} ${left} ${right})

    if [ $eq -eq 0 ]; then
	points[$((pos))]=$((points[$((pos))]+1))
    fi
    if [ $loud -eq 1 ]; then echo; fi
done

# check for flip
if [ $flip -eq 1 ]; then
    temp=$left
    left=$right
    right=$temp
    flip=0
    fruns=$((runs*2))
    if [ $loud -eq 1 ]; then echo; fi
    else break
fi

done

echo
for (( i=0; i<2; i++)); do
    printf "Player #$i won ${points[i]} of $fruns games.\n"
done
