# Reaching the Crystal Castle

The legendary explorer Ava seeks to reach the Crystal Castle, located at the far corner of a magical grid-shaped land of tiles.
The land is treacherous: some tiles are blocked by quicksand or have magical barriers that restrict how she can move from them.
Ava can move in five ways: she can make a normal step to the right (R) or downward (D), and she can leap diag-
onally left-down (LD), just downwards (DD) or diagonally right-down (RD), as it is shown in the figure below.

However, Ava knows her limits. Jumping (diagonally or just downwards) tires her, so she
cannot jump too many times consecutively, nor can the number of jumps in the entire
journey exceed a certain amount. Moreover, the magical land has special tiles: some tiles
forbid diagonal leaps or all kinds of jumps, because of magical barriers, and some tiles
cannot be stepped on, due to quicksand.

## Task
Write a program that, given a description of the magical land and Ava’s limits, computes
the number of paths that Ava can take from the top-left tile to the bottom-right tile (where
the Crystal Castle is located). Since the number of paths may be large, output it modulo
10^9 + 7.
It is guaranteed that, for the given inputs, the top-left and the bottom-right tiles can
be stepped on.

## Input
On the input first line there is an integer, T , which represents the number of test cases.
For each test case there is a first line containing four integers, R, C, M , and N , which
denote the number of rows and the number of columns of the magical land, the maximum
number of consecutive jumps and the maximum number of jumps that Ava can make in the
entire journey, respectively. Then, R lines follow, each with C characters. Each character
indicates the characteristics of the corresponding tile:
. (A tile with no constraints.)
X (A tile from which diagonal jumps (left-down or right-down) are forbidden.)
J (A tile from which jumps (left-down, just downwards or right-down) are forbidden.)
\# (A tile that cannot be stepped on.)

## Constraints
1 ≤ T ≤ 20 Number of test cases
1 ≤ R ≤ 400 Number of rows
1 ≤ C ≤ 400 Number of columns
1 ≤ M ≤ 5 Maximum number of consecutive jumps
1 ≤ N ≤ 10 Maximum number of jumps in a journey

## Output
The output consists of T lines, each with a single integer, pi, which represents the number
of paths Ava can take, in the ith test case, from the top-left tile to the bottom-right tile,
modulo 109 + 7 (for every i = 1, . . . , T ).

## Sample Input
3
3 4 1 3
.X.J
..#.
#...
3 4 2 3
.X.J
..#.
#...
10 20 5 10
....................
....................
....................
....................
....................
....................
....................
....................
....................
....................

## Sample Output
12
15
140916123

## Sample Explanation
In the first test case, Ava cannot make more than one
consecutive jump. The 12 possible paths, defined by
the sequences of step types, are the following:
D R D R R
D R RD R
D RD R R
R D D R R
R D RD R
R DD R R
R R DD R
R R LD D R R
R R R D D
R R R D LD R
R R RD D
RD D R R
In the second test case, Ava can make two consecutive
jumps. Besides the possible paths of the first test case,
there are three more alternatives:
R R LD RD R
R R RD LD R
RD RD R