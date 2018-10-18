begin_version
3
end_version
begin_metric
0
end_metric
12
begin_variable
var0
-1
2
Atom at(truck5, office5)
Atom at(truck5, trainstation5)
end_variable
begin_variable
var1
-1
3
Atom at(truck4, airport4)
Atom at(truck4, office4)
Atom at(truck4, trainstation4)
end_variable
begin_variable
var2
-1
2
Atom at(truck3, airport3)
Atom at(truck3, office3)
end_variable
begin_variable
var3
-1
3
Atom at(truck2, airport2)
Atom at(truck2, office2)
Atom at(truck2, trainstation2)
end_variable
begin_variable
var4
-1
2
Atom at(truck1, office1)
Atom at(truck1, trainstation1)
end_variable
begin_variable
var5
-1
4
Atom at(train5, trainstation1)
Atom at(train5, trainstation2)
Atom at(train5, trainstation4)
Atom at(train5, trainstation5)
end_variable
begin_variable
var6
-1
4
Atom at(train1, trainstation1)
Atom at(train1, trainstation2)
Atom at(train1, trainstation4)
Atom at(train1, trainstation5)
end_variable
begin_variable
var7
-1
16
Atom at(packet2, airport2)
Atom at(packet2, airport4)
Atom at(packet2, office1)
Atom at(packet2, office2)
Atom at(packet2, office4)
Atom at(packet2, office5)
Atom at(packet2, trainstation1)
Atom at(packet2, trainstation2)
Atom at(packet2, trainstation4)
Atom at(packet2, trainstation5)
Atom in(packet2, train1)
Atom in(packet2, train5)
Atom in(packet2, truck1)
Atom in(packet2, truck2)
Atom in(packet2, truck4)
Atom in(packet2, truck5)
end_variable
begin_variable
var8
-1
3
Atom at(airplane4, airport2)
Atom at(airplane4, airport3)
Atom at(airplane4, airport4)
end_variable
begin_variable
var9
-1
3
Atom at(airplane2, airport2)
Atom at(airplane2, airport3)
Atom at(airplane2, airport4)
end_variable
begin_variable
var10
-1
21
Atom at(packet5, airport2)
Atom at(packet5, airport3)
Atom at(packet5, airport4)
Atom at(packet5, office1)
Atom at(packet5, office2)
Atom at(packet5, office3)
Atom at(packet5, office4)
Atom at(packet5, office5)
Atom at(packet5, trainstation1)
Atom at(packet5, trainstation2)
Atom at(packet5, trainstation4)
Atom at(packet5, trainstation5)
Atom in(packet5, airplane2)
Atom in(packet5, airplane4)
Atom in(packet5, train1)
Atom in(packet5, train5)
Atom in(packet5, truck1)
Atom in(packet5, truck2)
Atom in(packet5, truck3)
Atom in(packet5, truck4)
Atom in(packet5, truck5)
end_variable
begin_variable
var11
-1
21
Atom at(packet1, airport2)
Atom at(packet1, airport3)
Atom at(packet1, airport4)
Atom at(packet1, office1)
Atom at(packet1, office2)
Atom at(packet1, office3)
Atom at(packet1, office4)
Atom at(packet1, office5)
Atom at(packet1, trainstation1)
Atom at(packet1, trainstation2)
Atom at(packet1, trainstation4)
Atom at(packet1, trainstation5)
Atom in(packet1, airplane2)
Atom in(packet1, airplane4)
Atom in(packet1, train1)
Atom in(packet1, train5)
Atom in(packet1, truck1)
Atom in(packet1, truck2)
Atom in(packet1, truck3)
Atom in(packet1, truck4)
Atom in(packet1, truck5)
end_variable
0
begin_state
1
2
1
0
1
3
0
3
2
0
7
3
end_state
begin_goal
3
7 5
10 5
11 5
end_goal
194
begin_operator
drive truck1 office1 trainstation1 city1
0
1
0 4 0 1
1
end_operator
begin_operator
drive truck1 trainstation1 office1 city1
0
1
0 4 1 0
1
end_operator
begin_operator
drive truck2 airport2 office2 city2
0
1
0 3 0 1
1
end_operator
begin_operator
drive truck2 airport2 trainstation2 city2
0
1
0 3 0 2
1
end_operator
begin_operator
drive truck2 office2 airport2 city2
0
1
0 3 1 0
1
end_operator
begin_operator
drive truck2 office2 trainstation2 city2
0
1
0 3 1 2
1
end_operator
begin_operator
drive truck2 trainstation2 airport2 city2
0
1
0 3 2 0
1
end_operator
begin_operator
drive truck2 trainstation2 office2 city2
0
1
0 3 2 1
1
end_operator
begin_operator
drive truck3 airport3 office3 city3
0
1
0 2 0 1
1
end_operator
begin_operator
drive truck3 office3 airport3 city3
0
1
0 2 1 0
1
end_operator
begin_operator
drive truck4 airport4 office4 city4
0
1
0 1 0 1
1
end_operator
begin_operator
drive truck4 airport4 trainstation4 city4
0
1
0 1 0 2
1
end_operator
begin_operator
drive truck4 office4 airport4 city4
0
1
0 1 1 0
1
end_operator
begin_operator
drive truck4 office4 trainstation4 city4
0
1
0 1 1 2
1
end_operator
begin_operator
drive truck4 trainstation4 airport4 city4
0
1
0 1 2 0
1
end_operator
begin_operator
drive truck4 trainstation4 office4 city4
0
1
0 1 2 1
1
end_operator
begin_operator
drive truck5 office5 trainstation5 city5
0
1
0 0 0 1
1
end_operator
begin_operator
drive truck5 trainstation5 office5 city5
0
1
0 0 1 0
1
end_operator
begin_operator
drivetrain train1 trainstation1 trainstation2
0
1
0 6 0 1
1
end_operator
begin_operator
drivetrain train1 trainstation1 trainstation4
0
1
0 6 0 2
1
end_operator
begin_operator
drivetrain train1 trainstation1 trainstation5
0
1
0 6 0 3
1
end_operator
begin_operator
drivetrain train1 trainstation2 trainstation1
0
1
0 6 1 0
1
end_operator
begin_operator
drivetrain train1 trainstation2 trainstation4
0
1
0 6 1 2
1
end_operator
begin_operator
drivetrain train1 trainstation2 trainstation5
0
1
0 6 1 3
1
end_operator
begin_operator
drivetrain train1 trainstation4 trainstation1
0
1
0 6 2 0
1
end_operator
begin_operator
drivetrain train1 trainstation4 trainstation2
0
1
0 6 2 1
1
end_operator
begin_operator
drivetrain train1 trainstation4 trainstation5
0
1
0 6 2 3
1
end_operator
begin_operator
drivetrain train1 trainstation5 trainstation1
0
1
0 6 3 0
1
end_operator
begin_operator
drivetrain train1 trainstation5 trainstation2
0
1
0 6 3 1
1
end_operator
begin_operator
drivetrain train1 trainstation5 trainstation4
0
1
0 6 3 2
1
end_operator
begin_operator
drivetrain train5 trainstation1 trainstation2
0
1
0 5 0 1
1
end_operator
begin_operator
drivetrain train5 trainstation1 trainstation4
0
1
0 5 0 2
1
end_operator
begin_operator
drivetrain train5 trainstation1 trainstation5
0
1
0 5 0 3
1
end_operator
begin_operator
drivetrain train5 trainstation2 trainstation1
0
1
0 5 1 0
1
end_operator
begin_operator
drivetrain train5 trainstation2 trainstation4
0
1
0 5 1 2
1
end_operator
begin_operator
drivetrain train5 trainstation2 trainstation5
0
1
0 5 1 3
1
end_operator
begin_operator
drivetrain train5 trainstation4 trainstation1
0
1
0 5 2 0
1
end_operator
begin_operator
drivetrain train5 trainstation4 trainstation2
0
1
0 5 2 1
1
end_operator
begin_operator
drivetrain train5 trainstation4 trainstation5
0
1
0 5 2 3
1
end_operator
begin_operator
drivetrain train5 trainstation5 trainstation1
0
1
0 5 3 0
1
end_operator
begin_operator
drivetrain train5 trainstation5 trainstation2
0
1
0 5 3 1
1
end_operator
begin_operator
drivetrain train5 trainstation5 trainstation4
0
1
0 5 3 2
1
end_operator
begin_operator
fly airplane2 airport2 airport3
0
1
0 9 0 1
1
end_operator
begin_operator
fly airplane2 airport2 airport4
0
1
0 9 0 2
1
end_operator
begin_operator
fly airplane2 airport3 airport2
0
1
0 9 1 0
1
end_operator
begin_operator
fly airplane2 airport3 airport4
0
1
0 9 1 2
1
end_operator
begin_operator
fly airplane2 airport4 airport2
0
1
0 9 2 0
1
end_operator
begin_operator
fly airplane2 airport4 airport3
0
1
0 9 2 1
1
end_operator
begin_operator
fly airplane4 airport2 airport3
0
1
0 8 0 1
1
end_operator
begin_operator
fly airplane4 airport2 airport4
0
1
0 8 0 2
1
end_operator
begin_operator
fly airplane4 airport3 airport2
0
1
0 8 1 0
1
end_operator
begin_operator
fly airplane4 airport3 airport4
0
1
0 8 1 2
1
end_operator
begin_operator
fly airplane4 airport4 airport2
0
1
0 8 2 0
1
end_operator
begin_operator
fly airplane4 airport4 airport3
0
1
0 8 2 1
1
end_operator
begin_operator
load_large_train packet2 train1 trainstation1
1
6 0
1
0 7 6 10
1
end_operator
begin_operator
load_large_train packet2 train1 trainstation2
1
6 1
1
0 7 7 10
1
end_operator
begin_operator
load_large_train packet2 train1 trainstation4
1
6 2
1
0 7 8 10
1
end_operator
begin_operator
load_large_train packet2 train1 trainstation5
1
6 3
1
0 7 9 10
1
end_operator
begin_operator
load_large_train packet2 train5 trainstation1
1
5 0
1
0 7 6 11
1
end_operator
begin_operator
load_large_train packet2 train5 trainstation2
1
5 1
1
0 7 7 11
1
end_operator
begin_operator
load_large_train packet2 train5 trainstation4
1
5 2
1
0 7 8 11
1
end_operator
begin_operator
load_large_train packet2 train5 trainstation5
1
5 3
1
0 7 9 11
1
end_operator
begin_operator
load_large_truck packet2 truck1 office1
1
4 0
1
0 7 2 12
1
end_operator
begin_operator
load_large_truck packet2 truck1 trainstation1
1
4 1
1
0 7 6 12
1
end_operator
begin_operator
load_large_truck packet2 truck2 airport2
1
3 0
1
0 7 0 13
1
end_operator
begin_operator
load_large_truck packet2 truck2 office2
1
3 1
1
0 7 3 13
1
end_operator
begin_operator
load_large_truck packet2 truck2 trainstation2
1
3 2
1
0 7 7 13
1
end_operator
begin_operator
load_large_truck packet2 truck4 airport4
1
1 0
1
0 7 1 14
1
end_operator
begin_operator
load_large_truck packet2 truck4 office4
1
1 1
1
0 7 4 14
1
end_operator
begin_operator
load_large_truck packet2 truck4 trainstation4
1
1 2
1
0 7 8 14
1
end_operator
begin_operator
load_large_truck packet2 truck5 office5
1
0 0
1
0 7 5 15
1
end_operator
begin_operator
load_large_truck packet2 truck5 trainstation5
1
0 1
1
0 7 9 15
1
end_operator
begin_operator
load_medium packet5 airplane2 airport2
1
9 0
1
0 10 0 12
1
end_operator
begin_operator
load_medium packet5 airplane2 airport3
1
9 1
1
0 10 1 12
1
end_operator
begin_operator
load_medium packet5 airplane2 airport4
1
9 2
1
0 10 2 12
1
end_operator
begin_operator
load_medium packet5 airplane4 airport2
1
8 0
1
0 10 0 13
1
end_operator
begin_operator
load_medium packet5 airplane4 airport3
1
8 1
1
0 10 1 13
1
end_operator
begin_operator
load_medium packet5 airplane4 airport4
1
8 2
1
0 10 2 13
1
end_operator
begin_operator
load_medium packet5 train1 trainstation1
1
6 0
1
0 10 8 14
1
end_operator
begin_operator
load_medium packet5 train1 trainstation2
1
6 1
1
0 10 9 14
1
end_operator
begin_operator
load_medium packet5 train1 trainstation4
1
6 2
1
0 10 10 14
1
end_operator
begin_operator
load_medium packet5 train1 trainstation5
1
6 3
1
0 10 11 14
1
end_operator
begin_operator
load_medium packet5 train5 trainstation1
1
5 0
1
0 10 8 15
1
end_operator
begin_operator
load_medium packet5 train5 trainstation2
1
5 1
1
0 10 9 15
1
end_operator
begin_operator
load_medium packet5 train5 trainstation4
1
5 2
1
0 10 10 15
1
end_operator
begin_operator
load_medium packet5 train5 trainstation5
1
5 3
1
0 10 11 15
1
end_operator
begin_operator
load_medium packet5 truck1 office1
1
4 0
1
0 10 3 16
1
end_operator
begin_operator
load_medium packet5 truck1 trainstation1
1
4 1
1
0 10 8 16
1
end_operator
begin_operator
load_medium packet5 truck2 airport2
1
3 0
1
0 10 0 17
1
end_operator
begin_operator
load_medium packet5 truck2 office2
1
3 1
1
0 10 4 17
1
end_operator
begin_operator
load_medium packet5 truck2 trainstation2
1
3 2
1
0 10 9 17
1
end_operator
begin_operator
load_medium packet5 truck3 airport3
1
2 0
1
0 10 1 18
1
end_operator
begin_operator
load_medium packet5 truck3 office3
1
2 1
1
0 10 5 18
1
end_operator
begin_operator
load_medium packet5 truck4 airport4
1
1 0
1
0 10 2 19
1
end_operator
begin_operator
load_medium packet5 truck4 office4
1
1 1
1
0 10 6 19
1
end_operator
begin_operator
load_medium packet5 truck4 trainstation4
1
1 2
1
0 10 10 19
1
end_operator
begin_operator
load_medium packet5 truck5 office5
1
0 0
1
0 10 7 20
1
end_operator
begin_operator
load_medium packet5 truck5 trainstation5
1
0 1
1
0 10 11 20
1
end_operator
begin_operator
load_small packet1 airplane2 airport2
1
9 0
1
0 11 0 12
1
end_operator
begin_operator
load_small packet1 airplane2 airport3
1
9 1
1
0 11 1 12
1
end_operator
begin_operator
load_small packet1 airplane2 airport4
1
9 2
1
0 11 2 12
1
end_operator
begin_operator
load_small packet1 airplane4 airport2
1
8 0
1
0 11 0 13
1
end_operator
begin_operator
load_small packet1 airplane4 airport3
1
8 1
1
0 11 1 13
1
end_operator
begin_operator
load_small packet1 airplane4 airport4
1
8 2
1
0 11 2 13
1
end_operator
begin_operator
load_small packet1 train1 trainstation1
1
6 0
1
0 11 8 14
1
end_operator
begin_operator
load_small packet1 train1 trainstation2
1
6 1
1
0 11 9 14
1
end_operator
begin_operator
load_small packet1 train1 trainstation4
1
6 2
1
0 11 10 14
1
end_operator
begin_operator
load_small packet1 train1 trainstation5
1
6 3
1
0 11 11 14
1
end_operator
begin_operator
load_small packet1 train5 trainstation1
1
5 0
1
0 11 8 15
1
end_operator
begin_operator
load_small packet1 train5 trainstation2
1
5 1
1
0 11 9 15
1
end_operator
begin_operator
load_small packet1 train5 trainstation4
1
5 2
1
0 11 10 15
1
end_operator
begin_operator
load_small packet1 train5 trainstation5
1
5 3
1
0 11 11 15
1
end_operator
begin_operator
load_small packet1 truck1 office1
1
4 0
1
0 11 3 16
1
end_operator
begin_operator
load_small packet1 truck1 trainstation1
1
4 1
1
0 11 8 16
1
end_operator
begin_operator
load_small packet1 truck2 airport2
1
3 0
1
0 11 0 17
1
end_operator
begin_operator
load_small packet1 truck2 office2
1
3 1
1
0 11 4 17
1
end_operator
begin_operator
load_small packet1 truck2 trainstation2
1
3 2
1
0 11 9 17
1
end_operator
begin_operator
load_small packet1 truck3 airport3
1
2 0
1
0 11 1 18
1
end_operator
begin_operator
load_small packet1 truck3 office3
1
2 1
1
0 11 5 18
1
end_operator
begin_operator
load_small packet1 truck4 airport4
1
1 0
1
0 11 2 19
1
end_operator
begin_operator
load_small packet1 truck4 office4
1
1 1
1
0 11 6 19
1
end_operator
begin_operator
load_small packet1 truck4 trainstation4
1
1 2
1
0 11 10 19
1
end_operator
begin_operator
load_small packet1 truck5 office5
1
0 0
1
0 11 7 20
1
end_operator
begin_operator
load_small packet1 truck5 trainstation5
1
0 1
1
0 11 11 20
1
end_operator
begin_operator
unload_large_train packet2 train1 trainstation1
1
6 0
1
0 7 10 6
1
end_operator
begin_operator
unload_large_train packet2 train1 trainstation2
1
6 1
1
0 7 10 7
1
end_operator
begin_operator
unload_large_train packet2 train1 trainstation4
1
6 2
1
0 7 10 8
1
end_operator
begin_operator
unload_large_train packet2 train1 trainstation5
1
6 3
1
0 7 10 9
1
end_operator
begin_operator
unload_large_train packet2 train5 trainstation1
1
5 0
1
0 7 11 6
1
end_operator
begin_operator
unload_large_train packet2 train5 trainstation2
1
5 1
1
0 7 11 7
1
end_operator
begin_operator
unload_large_train packet2 train5 trainstation4
1
5 2
1
0 7 11 8
1
end_operator
begin_operator
unload_large_train packet2 train5 trainstation5
1
5 3
1
0 7 11 9
1
end_operator
begin_operator
unload_large_truck packet2 truck1 office1
1
4 0
1
0 7 12 2
1
end_operator
begin_operator
unload_large_truck packet2 truck1 trainstation1
1
4 1
1
0 7 12 6
1
end_operator
begin_operator
unload_large_truck packet2 truck2 airport2
1
3 0
1
0 7 13 0
1
end_operator
begin_operator
unload_large_truck packet2 truck2 office2
1
3 1
1
0 7 13 3
1
end_operator
begin_operator
unload_large_truck packet2 truck2 trainstation2
1
3 2
1
0 7 13 7
1
end_operator
begin_operator
unload_large_truck packet2 truck4 airport4
1
1 0
1
0 7 14 1
1
end_operator
begin_operator
unload_large_truck packet2 truck4 office4
1
1 1
1
0 7 14 4
1
end_operator
begin_operator
unload_large_truck packet2 truck4 trainstation4
1
1 2
1
0 7 14 8
1
end_operator
begin_operator
unload_large_truck packet2 truck5 office5
1
0 0
1
0 7 15 5
1
end_operator
begin_operator
unload_large_truck packet2 truck5 trainstation5
1
0 1
1
0 7 15 9
1
end_operator
begin_operator
unload_medium packet5 airplane2 airport2
1
9 0
1
0 10 12 0
1
end_operator
begin_operator
unload_medium packet5 airplane2 airport3
1
9 1
1
0 10 12 1
1
end_operator
begin_operator
unload_medium packet5 airplane2 airport4
1
9 2
1
0 10 12 2
1
end_operator
begin_operator
unload_medium packet5 airplane4 airport2
1
8 0
1
0 10 13 0
1
end_operator
begin_operator
unload_medium packet5 airplane4 airport3
1
8 1
1
0 10 13 1
1
end_operator
begin_operator
unload_medium packet5 airplane4 airport4
1
8 2
1
0 10 13 2
1
end_operator
begin_operator
unload_medium packet5 train1 trainstation1
1
6 0
1
0 10 14 8
1
end_operator
begin_operator
unload_medium packet5 train1 trainstation2
1
6 1
1
0 10 14 9
1
end_operator
begin_operator
unload_medium packet5 train1 trainstation4
1
6 2
1
0 10 14 10
1
end_operator
begin_operator
unload_medium packet5 train1 trainstation5
1
6 3
1
0 10 14 11
1
end_operator
begin_operator
unload_medium packet5 train5 trainstation1
1
5 0
1
0 10 15 8
1
end_operator
begin_operator
unload_medium packet5 train5 trainstation2
1
5 1
1
0 10 15 9
1
end_operator
begin_operator
unload_medium packet5 train5 trainstation4
1
5 2
1
0 10 15 10
1
end_operator
begin_operator
unload_medium packet5 train5 trainstation5
1
5 3
1
0 10 15 11
1
end_operator
begin_operator
unload_medium packet5 truck1 office1
1
4 0
1
0 10 16 3
1
end_operator
begin_operator
unload_medium packet5 truck1 trainstation1
1
4 1
1
0 10 16 8
1
end_operator
begin_operator
unload_medium packet5 truck2 airport2
1
3 0
1
0 10 17 0
1
end_operator
begin_operator
unload_medium packet5 truck2 office2
1
3 1
1
0 10 17 4
1
end_operator
begin_operator
unload_medium packet5 truck2 trainstation2
1
3 2
1
0 10 17 9
1
end_operator
begin_operator
unload_medium packet5 truck3 airport3
1
2 0
1
0 10 18 1
1
end_operator
begin_operator
unload_medium packet5 truck3 office3
1
2 1
1
0 10 18 5
1
end_operator
begin_operator
unload_medium packet5 truck4 airport4
1
1 0
1
0 10 19 2
1
end_operator
begin_operator
unload_medium packet5 truck4 office4
1
1 1
1
0 10 19 6
1
end_operator
begin_operator
unload_medium packet5 truck4 trainstation4
1
1 2
1
0 10 19 10
1
end_operator
begin_operator
unload_medium packet5 truck5 office5
1
0 0
1
0 10 20 7
1
end_operator
begin_operator
unload_medium packet5 truck5 trainstation5
1
0 1
1
0 10 20 11
1
end_operator
begin_operator
unload_small packet1 airplane2 airport2
1
9 0
1
0 11 12 0
1
end_operator
begin_operator
unload_small packet1 airplane2 airport3
1
9 1
1
0 11 12 1
1
end_operator
begin_operator
unload_small packet1 airplane2 airport4
1
9 2
1
0 11 12 2
1
end_operator
begin_operator
unload_small packet1 airplane4 airport2
1
8 0
1
0 11 13 0
1
end_operator
begin_operator
unload_small packet1 airplane4 airport3
1
8 1
1
0 11 13 1
1
end_operator
begin_operator
unload_small packet1 airplane4 airport4
1
8 2
1
0 11 13 2
1
end_operator
begin_operator
unload_small packet1 train1 trainstation1
1
6 0
1
0 11 14 8
1
end_operator
begin_operator
unload_small packet1 train1 trainstation2
1
6 1
1
0 11 14 9
1
end_operator
begin_operator
unload_small packet1 train1 trainstation4
1
6 2
1
0 11 14 10
1
end_operator
begin_operator
unload_small packet1 train1 trainstation5
1
6 3
1
0 11 14 11
1
end_operator
begin_operator
unload_small packet1 train5 trainstation1
1
5 0
1
0 11 15 8
1
end_operator
begin_operator
unload_small packet1 train5 trainstation2
1
5 1
1
0 11 15 9
1
end_operator
begin_operator
unload_small packet1 train5 trainstation4
1
5 2
1
0 11 15 10
1
end_operator
begin_operator
unload_small packet1 train5 trainstation5
1
5 3
1
0 11 15 11
1
end_operator
begin_operator
unload_small packet1 truck1 office1
1
4 0
1
0 11 16 3
1
end_operator
begin_operator
unload_small packet1 truck1 trainstation1
1
4 1
1
0 11 16 8
1
end_operator
begin_operator
unload_small packet1 truck2 airport2
1
3 0
1
0 11 17 0
1
end_operator
begin_operator
unload_small packet1 truck2 office2
1
3 1
1
0 11 17 4
1
end_operator
begin_operator
unload_small packet1 truck2 trainstation2
1
3 2
1
0 11 17 9
1
end_operator
begin_operator
unload_small packet1 truck3 airport3
1
2 0
1
0 11 18 1
1
end_operator
begin_operator
unload_small packet1 truck3 office3
1
2 1
1
0 11 18 5
1
end_operator
begin_operator
unload_small packet1 truck4 airport4
1
1 0
1
0 11 19 2
1
end_operator
begin_operator
unload_small packet1 truck4 office4
1
1 1
1
0 11 19 6
1
end_operator
begin_operator
unload_small packet1 truck4 trainstation4
1
1 2
1
0 11 19 10
1
end_operator
begin_operator
unload_small packet1 truck5 office5
1
0 0
1
0 11 20 7
1
end_operator
begin_operator
unload_small packet1 truck5 trainstation5
1
0 1
1
0 11 20 11
1
end_operator
0
