# Calculate-Pi-with-workers-and-threads
In these files, you will find the calculation of the Pi with the help of workers and threads.
To make it more clear, we have 4 workers on the server side. Each of them gives some info to the client to calculate one piece of the number that we have.
The Client calculates the piece depending on the worker's index with the help of threads and returns the result to the server.
When all 4 workers gather their results, we print the final result on the server screen.
# HOW TO RUN THIS CODE
After you compile the files, run the MultithreadedSumMasterTCP file first and then run SumWorkerTCP file 4 times.

