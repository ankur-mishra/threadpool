# threadpool
implementation of a thread pool with job affinity

Assumptions:
- for each jobId a blocking queue is taken in a map of (jobId, blockingqueue)
- if 2 jobs with jobId "x" are present in map and a thread removes that entry from map to execute jobs in that blocking queue, 
  entry with jobId "x" is not present in map. Suppose Thread 1 was executing jobs with jobId "x". Now if another job comes with jobId 
  "x", its entry is not in map (a new entry is made in map) and it is possible that this time new jobs with jobId "x" will be executed
  by some other thread than Thread 1.
- threadpool size in my implementation is the size of taskMap. (we can easily change this to size of threadpool which we make initially,
  it completely depends on one's understanding).  
