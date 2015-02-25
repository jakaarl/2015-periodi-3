Performance test: creating a star map
=====================================

The test reads in different sizes of star map JSON files and builds a star map, complete with connections, out of them. Test is run 10 times and the results averaged.

Expected performance is roughly O(n^2): while iterating the stars, there is a nested iteration to find which stars are connected. There is a modest optimization, which adds both sides of the connection at the same time, allowing the "current" star to be removed from the list and thus not be checked again when checking connections for the subsequent stars. However, using an array list is expected to negate the benefits, since on removal, the array is rearranged. For comparison, there will be a test run with a linked list - this is expected to give a small performance benefit, at least on larger data sets.

Performance using an array list
-------------------------------

- 12 stars: 5 milliseconds
- 100 stars: 14 milliseconds
- 1000 stars: 135 milliseconds
- 10000 stars: 11537 milliseconds

Performance using a linked list
-------------------------------

TODO!
- 12 stars:
- 100 stars:
- 1000 stars:
- 10000 stars:

