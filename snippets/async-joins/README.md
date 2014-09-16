# Making synchronization asynchononous

The headline looks annoying, but in heavy parallel process work you can run into optimistic locking exceptions 
for some process variables. You can avoid them if you make the synchronization a wait state. This happens with 
the parse listener in this snippet. 

If the process run into a joining parallel gateway (AND-Join), a joining inclusive gateway (OR-Join) or reaches the 
end of a multi instance sub process, it automatically sets an asynchonous continuation. This forces a wait state and 
the index of a parallel multi instance activity for example is written in separate transactions.

## How to use it

Wire the process engine plugin into your engine to use this snippet.

## No Screenshot available

There is no screenshot available. 