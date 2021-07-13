# Diamond kata

An implementation of the Diamond kata in Node.js

## Install

> npm i

## Test

> npm test

## Run

TBC

## Design rationale

The idea for this implementation is simple.

Firstly make a letter order by taking the range A... whatever letter is chosen, say 'C':

> [ 'A' 'B' 'C' ]

Now reverse this list:

> [ 'C' 'B' 'A' ]

Now merge it with the first list by eliminating the duplicate last item of the first list with the first item of the reversed list like so:

> [ 'A' 'B' 'C' 'B' 'A' ]

Construct an identity matrix of the requisite size e.g. for the three letter range A...C

```
[[ 1 0 0 ]
 [ 0 1 0 ]
 [ 0 0 1 ]]
```

Reflect this matrix in the Y axis to get a complementary matrix.

```
[[ 0 0 1 ]
 [ 0 1 0 ]
 [ 1 0 0 ]]
```

Using our identity matrix '\' and our reflection matrix '/' construct a square by merging the second and first such that the last column of the first matrix is the same as the first column of the second matrix, e.g. /\

```
[[ 0 0 1 0 0 ]
 [ 0 1 0 1 0 ]
 [ 1 0 0 0 1 ]]
```

This matrix will form the top half of the diamond.

Do the same, in the reverse order to get the bottom half of the diamond: \/

```
[[ 1 0 0 0 1 ]
 [ 0 1 0 1 0 ]
 [ 0 0 1 0 0 ]]
```

Merge these by combining the two matrices by eliminating the duplicate last line of the upper half with the first line of the lower half of the diamond.

```
[[ 0 0 1 0 0 ]
 [ 0 1 0 1 0 ]
 [ 1 0 0 0 1 ]
 [ 0 1 0 1 0 ]
 [ 0 0 1 0 0 ]]
```

This then gives a completed matrix.

Combine this with the letter ordering to turn the ones into letters and the zeroes into spaces as part of output encoding.

Voila!
