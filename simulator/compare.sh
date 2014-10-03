#!/bin/bash

runs=${4}
left=${1}
right=${2}
map=${3}
OIFS=$IFS
points[0]=0
points[0]=0

if [ -z ${4} ]
    then echo "Usage: ${0} <A>.ship <B>.ship <Map>.map <runs>"
         exit 1
fi

for (( i=1; i<=$runs; i++ ))
do
    IFS=$OIFS
    its=0
    eq=0
    pos=-1
    max=0
    printf "#$i: "
    while read -r res
    do
	printf "$res - "
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
    done < <(java -jar -Dseed=${i} sim.jar ${map} ${left} ${right})

    if [ $eq -eq 0 ]; then
	points[$((pos))]=$((points[$((pos))]+1))
    fi
    echo
done

echo
for (( i=0; i<2; i++)); do
    printf "Player #$i won ${points[i]} of $runs games.\n"
done
