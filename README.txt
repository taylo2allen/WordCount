README.TXT

1) Who is in your group?
Andrew Cash, Taylor Allen, Sam Hendryx

2) How long did the project take?
Several weeks with a lot of meetings.

3) Before you started, which data structure did you expect would be the fastest?
Probably Hash, then AVL Tree and lastly BST.

4) Which data structure is the fastest? Why were you right or wrong?
|---------------------+-------------+-------------+-------------|
| WordCount Frequency |         BST |         AVL |        Hash |
|---------------------+-------------+-------------+-------------|
| Real Time           |       0.795 |       0.882 |       1.187 |
|---------------------+-------------+-------------+-------------|
| User Time           |       1.305 |       1.526 |       2.097 |
|---------------------+-------------+-------------+-------------|
| System Time         |       0.136 |       0.138 |       0.124 |
|---------------------+-------------+-------------+-------------|
| Average             | 0.745333333 | 0.848666667 |       1.136 |
|---------------------+-------------+-------------+-------------|
| WordCount Unique    |             |             |             |
|---------------------+-------------+-------------+-------------|
| Real Time           |       0.694 |       0.681 |       0.994 |
|---------------------+-------------+-------------+-------------|
| User Time           |       1.237 |        1.22 |       1.763 |
|---------------------+-------------+-------------+-------------|
| System Time         |       0.057 |       0.101 |       0.069 |
|---------------------+-------------+-------------+-------------|
| Average             | 0.662666667 | 0.667333333 |       0.942 |
|---------------------+-------------+-------------+-------------|
| Correlator          |             |             |             |
|---------------------+-------------+-------------+-------------|
| Real Time           |       0.739 |       0.775 |        0.72 |
|---------------------+-------------+-------------+-------------|
| User Time           |       1.297 |       1.245 |       1.191 |
|---------------------+-------------+-------------+-------------|
| System Time         |        0.04 |         0.1 |       0.073 |
|---------------------+-------------+-------------+-------------|
| Average             |       0.692 | 0.706666667 | 0.661333333 |
|---------------------+-------------+-------------+-------------|

We thought the Hash Implementation was going to be fastest, but we didn't take into
consideration that when the load factor is too large the table needs to be resized,
so it was doing twice the work. Our BST Implementation was the fastest in most cases
because it doesn't have the extra method to balance itself, which only is beneficial
if you are searching.

5) In general, which DataCounter dictionary implementation was "better": trees or
hash tables? Note that you will need to define "better" (ease of coding, ease of
debugging, memory usage, disk access patterns, runtime for average input, runtime
for all input, etc).

If better is ease of coding and debugging, trees are better in
our opinion. It was easier to visualize what the data should be with trees compared to
the hash table. Due to the plethora of visualization tools online and the ability to
manipulate the data on paper.

6) Are there cases in which a particular data structure performs really well or badly in
the correlator? Enumerate the cases for each data structure.

No implementation seemed to have a significant difference in the correlator than in WordCount

7) Give a one to two paragraph explanation of whether or not you think Bacon wrote
Shakespeare's plays based on the data you collected. No fancy statistical analysis
here.



8) What did you enjoy about this assignment? What did you hate? Could we have done
anything better?

Sam: I enjoyed the implementation of the different data structures. It really forces
you to understand how they work.

Andrew: I liked pair programming and in particular, the ablilty to bounce ideas off
of the others. I thought that it was a bit too much at once. If it had been parsed into
separate projects it would have been more easily managable.

Taylor: I enjoyed working with a productive team and learned the benefits of pair programming
along with working with git version control.The most difficult part of the project for me was
the HashTable implementation which used alot of thinking. Something we could have done better
is writing the documentation first rather than after the code is written, documentation would
have gone a lot better.
