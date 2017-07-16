/**
 *
 * @author Trevor
 */
public class Final 
{
        //IO intensive class
    public static class IOBound extends Thread 
    {
        @Override
        public void run()
        {
            //Declaring local int variable for use
            int iterationVarible = 0;
            //For loop to repeat system IO operation 1000 times
                for (int i = 0; i < 1000; i++ )
                {
                    iterationVarible += 1;
                    //getNaeme() is to call the name of the thread
                    System.out.println(getName()+ " " + iterationVarible);
                }
                
        }
    }
    //Intermediate class that does a cpu and io based function
    public static class ComplexBound extends Thread 
    {
        private int number = 10;
        private int exponent = 6;
       
        @Override
        public void run()
        {
            //Declaring local int variable for use
            number = 10;
            exponent = 6;
            for (int i =0; i< 50; i++) 
            {
                double result = (Math.pow(number,exponent)); 
                //getName() function call to id thread for identification 
                System.out.println(getName() + " " + result);
            }    
        }
    }
    //Computation class to test a cpu intensive funciton.
    public static class CPUBound extends Thread
    {
        private int x = 29;
        private int y = 12;
        private int z = 8;
        
        @Override
        public void run()
        {
            x = 3;
            y = 4;
            z = 5;
            
            for (int i=0; i< 60; i++)
            {
                int w = x+y+z;
                w = i;
            }
        }
    }
    
    // class to create thread objects and run FCFS algorithm
    public static class ControllerClassFCFS
    {
        //Number of threads to match the number of types of thread objects
        static final int MaxArrayThreads = 3;
   
        public static void main(String[] args) throws InterruptedException 
        {
            //1. FCFS
                //Population of arrays with Thread objects
                int MaxArrayThreads = 3;
                int arraySize = 20;
                //One array to hold the IOBound, CPUBound, and ComplexBound threads
                Thread ThreadArray[] = new Thread[MaxArrayThreads];

                //Array's to keep time of thread start and stop time, wait time for each type of thread.
                long IOTimeArray[] = new long[MaxArrayThreads];
                long IOWaitTimeArray[] = new long[arraySize];
                long ComplexTimeArray[] = new long[MaxArrayThreads];
                long ComplexWaitTimeArray[] = new long[arraySize];
                long CPUTimeArray[] = new long[MaxArrayThreads];
                long CPUWaitTimeArray[] = new long[arraySize];

                //Variables to take the time to run the scheduler
                long FCFSstartTime;
                long FCFSendTime;

                //Sumation varible for averages
                long totalIO = 0;
                long totalComplex = 0;
                long totalCPU = 0;
                long totalWaitIO = 0;
                long totalWaitComplex = 0;
                long totalWaitCPU = 0; 
                long avgIO = 0;
                long avgComplex = 0;
                long avgCPU = 0; 
                
                //Start time of scheduler
                FCFSstartTime = System.currentTimeMillis();
                
                //For loop to read in IOBound Thread
                for(int i =0; i <MaxArrayThreads; i++)
                {
                    long IOStart = System.currentTimeMillis();
                    ThreadArray[i] = new Thread(new IOBound());
                    long IOWaitStart = System.currentTimeMillis();
                    ThreadArray[i].start();
                    try 
                    {
                        long IOWaitEnd = System.currentTimeMillis();
                        ThreadArray[i].join();
                        long IOEnd = System.currentTimeMillis();
                        
                        //store IOBound thread time to be called later
                        IOTimeArray[i] = (IOEnd - IOStart);
                        totalIO += IOTimeArray[i];
                        IOWaitTimeArray[i] = (IOWaitEnd - IOWaitStart);
                    } 
                    catch (InterruptedException e)
                    {
                       e.printStackTrace(); //not necessary but did so just in case
                    }
                }
                
                //ComplexBound read into the scheduler
                for(int j =0; j <MaxArrayThreads; j++)
                {    
                    //Complex Thread start time
                    long ComplexStart = System.currentTimeMillis();
                    ThreadArray[j] = new Thread(new ComplexBound());
                    long ComplexWaitStart = System.currentTimeMillis();
                    ThreadArray[j].start();
                    try 
                    {
                        long ComplexWaitEnd = System.currentTimeMillis();
                        ThreadArray[j].join();
                        //Complex thread finsih time
                        long ComplexEnd = System.currentTimeMillis();
                        //store ComplexBound thread time to be called later
                        ComplexTimeArray[j] = (ComplexEnd - ComplexStart);
                        totalComplex += ComplexTimeArray[j]; 
                        ComplexWaitTimeArray[j] = (ComplexWaitEnd - ComplexWaitStart);
                    }
                    catch (InterruptedException e)
                    {
                       e.printStackTrace(); //not necessary but did so just in case
                    }
                }
                
                //CPUBound read into the scheduler
                for(int k =0; k <MaxArrayThreads; k++)
                {
                    long CPUStart = System.currentTimeMillis();
                    ThreadArray[k] = new Thread(new CPUBound());
                    long CPUWaitStart = System.currentTimeMillis();
                    ThreadArray[k].start();
                    try 
                    {
                        long CPUWaitEnd = System.currentTimeMillis();
                        ThreadArray[k].join();
                        long CPUEnd = System.currentTimeMillis();
                        //store IOBound thread time to be called later
                        CPUTimeArray[k] = (CPUEnd - CPUStart);
                        totalCPU += CPUTimeArray[k];
                        IOWaitTimeArray[k] = (CPUWaitEnd - CPUWaitStart);
                    }
                    catch (InterruptedException e)
                    {
                       e.printStackTrace(); //not necessary but did so just in case
                    }
                }
                
                //Time to mark the algortihm ending
                FCFSendTime = System.currentTimeMillis();
                System.out.println(" ");
                
                //Output of Thread Times for IOBound, Complex, and CPU.
                for (int j= 0; j < MaxArrayThreads; j++)
                {
                    //Time to run thread
                    System.out.println("Time for IOThread "+ (j+1) + " to complete (ms): "+ IOTimeArray[j]); 
                    System.out.println("Time for ComplexThread "+ (j+1) + " to complete (ms): "+ ComplexTimeArray[j]);
                    System.out.println("Time for CPUthread "+ (j+1) + " to complete (ms): "+ CPUTimeArray[j]);
                    
                    //Wait time for thread
                    System.out.println("Wait Time for IOThread "+ (j+1)+ " (ms): " + IOWaitTimeArray[j]); 
                    System.out.println("Wait Time for CPUThread "+ (j+1)+ " (ms): " + CPUWaitTimeArray[j]); 
                    System.out.println("Wait Time for ComplexThread "+ (j+1)+ " (ms): " + ComplexWaitTimeArray[j]);  
                    
                }
                
                //for loop to measure average wait time for Threads
                for (int k=0; k <= MaxArrayThreads; k++)
                {
                    //average wait time for IO threads
                    totalWaitIO = totalWaitIO + IOWaitTimeArray[k];
                    avgIO = totalWaitIO / IOWaitTimeArray.length;
                    
                    //average wait time for CPU threads
                    totalWaitCPU = totalWaitCPU + CPUWaitTimeArray[k];
                    avgCPU = totalWaitCPU / CPUWaitTimeArray.length;
                    
                    //average wait time for Complex threads
                    totalWaitComplex = totalWaitComplex + ComplexWaitTimeArray[k];
                    avgComplex = totalWaitComplex / ComplexWaitTimeArray.length;
                }
                
                //Measurements for performance
                System.out.println(" ");
                System.out.println("Time for all IO threads to finish FCFS (ms): " + totalIO);
                System.out.println("Time for all Complex threads to finish FCFS (ms): " + totalComplex);
                System.out.println("Time for all CPU threads to finish FCFS (ms): " + totalCPU);
                System.out.println("Average wait time for IO Threads FCFS (ms): " + avgIO);
                System.out.println("Average wait time for Complex Threads FCFS (ms): " + avgComplex);
                System.out.println("Average wait time for CPU Threads FCFS (ms): " + avgCPU);
                System.out.println("Time for all threads to finish FCFS (ms): "+(FCFSendTime - FCFSstartTime));
            }
        } 
   
        //Class to run the SJF scheduler
        public static class ControllerClassSJF
        {   
            /* 2.Shortest Job First scheduler will run the CPU Thread, Complex thread, than IO Thread.
            Order was determined based on the measurements from the FCFS scheduler
            */
        
            //Creates 9 worker threads
            static final int MaxArrayThreads = 3;
        
            public static void main(String[] args) throws InterruptedException 
            {
                int MaxArrayThreads = 3;
                int arraySize = 30;
                //One array to hold the IOBound, CPUBound, and ComplexBound threads
                Thread ThreadArraySJF[] = new Thread[MaxArrayThreads];

                //Array's to keep time of thread start and stop time, wait time for each type of thread.
                long IOTimeArraySJF[] = new long[MaxArrayThreads];
                long IOWaitTimeArraySJF[] = new long[arraySize];
                long ComplexTimeArraySJF[] = new long[MaxArrayThreads];
                long ComplexWaitTimeArraySJF[] = new long[arraySize];
                long CPUTimeArraySJF[] = new long[MaxArrayThreads];
                long CPUWaitTimeArraySJF[] = new long[arraySize];

                //Varibles to make the time taken to run the scheduler
                long SJFstartTime;
                long SJFendTime;

                //Sumation varible for averages
                long totalIO = 0;
                long totalComplex = 0;
                long totalCPU = 0;
                long totalWaitIO = 0;
                long totalWaitComplex = 0;
                long totalWaitCPU = 0; 
                long avgIO = 0;
                long avgComplex = 0;
                long avgCPU = 0; 

                //Start time of the algorithm
                SJFstartTime = System.currentTimeMillis();

                //CPUBound read into the scheduler
                for(int k =0; k <MaxArrayThreads; k++)
                {
                    //Start time of the Thread    
                    long CPUStart = System.currentTimeMillis();
                    ThreadArraySJF[k] = new Thread(new CPUBound());
                            
                    //Start of wait for the thread
                    long CPUWaitStart = System.currentTimeMillis();
                    ThreadArraySJF[k].start();
                    try 
                    {
                        long CPUWaitEnd = System.currentTimeMillis();
                        ThreadArraySJF[k].join();
                        long CPUEnd = System.currentTimeMillis();
                        
                        //store IOBound thread time to be called later
                        CPUTimeArraySJF[k] = (CPUEnd - CPUStart);
                        totalCPU += CPUTimeArraySJF[k];
                        IOWaitTimeArraySJF[k] = (CPUWaitEnd - CPUWaitStart);
                    }
                    catch (InterruptedException e)
                    {
                       e.printStackTrace(); 
                       //not necessary but just ensure that when a thread is interrupted it doesnt break the code.
                    }
                }
            
                //ComplexBound read into the scheduler
                for(int j =0; j <MaxArrayThreads; j++)
                {    
                    //Complex Thread start time
                    long ComplexStart = System.currentTimeMillis();
                    ThreadArraySJF[j] = new Thread(new ComplexBound());
                    long ComplexWaitStart = System.currentTimeMillis();
                    ThreadArraySJF[j].start();
                    try 
                    {
                        long ComplexWaitEnd = System.currentTimeMillis();
                        ThreadArraySJF[j].join();
                        //Complex thread finsih time
                        long ComplexEnd = System.currentTimeMillis();
                        //store ComplexBound thread time to be called later
                        ComplexTimeArraySJF[j] = (ComplexEnd - ComplexStart);
                        totalComplex += ComplexTimeArraySJF[j]; 
                        ComplexWaitTimeArraySJF[j] = (ComplexWaitEnd - ComplexWaitStart);
                    }
                    catch (InterruptedException e)
                    {
                       e.printStackTrace(); //not necessary but did so just in case
                    }
                }
                
                //For loop to read in IOBound Thread
                for(int i =0; i <MaxArrayThreads; i++)
                {
                    long IOStart = System.currentTimeMillis();
                    ThreadArraySJF[i] = new Thread(new IOBound());
                    long IOWaitStart = System.currentTimeMillis();
                    ThreadArraySJF[i].start();
                    try 
                    {
                        long IOWaitEnd = System.currentTimeMillis();
                        ThreadArraySJF[i].join();
                        long IOEnd = System.currentTimeMillis();
                        //store IOBound thread time to be called later
                        IOTimeArraySJF[i] = (IOEnd - IOStart);
                        totalIO += IOTimeArraySJF[i];
                        IOWaitTimeArraySJF[i] = (IOWaitEnd - IOWaitStart);
                    } 
                    catch (InterruptedException e)
                    {
                       e.printStackTrace(); //not necessary but did so just in case
                    }
                }
                SJFendTime = System.currentTimeMillis();
                System.out.println(" ");
                
                //Output of Thread Times for IOBound, Complex, and CPU.
                for (int m= 0; m < MaxArrayThreads; m++)
                {
                    //Time to run thread
                    System.out.println("Time for IOThread "+ (m+1) + " to complete (ms): "+ IOTimeArraySJF[m]); 
                    System.out.println("Time for ComplexThread "+ (m+1) + " to complete (ms): "+ ComplexTimeArraySJF[m]);
                    System.out.println("Time for CPUthread "+ (m+1) + " to complete (ms): "+ CPUTimeArraySJF[m]);
                    
                    //Wait time for thread
                    System.out.println("Wait Time for IOThread "+ (m+1)+ " (ms): " + IOWaitTimeArraySJF[m]); 
                    System.out.println("Wait Time for CPUThread "+ (m+1)+ " (ms): " + CPUWaitTimeArraySJF[m]); 
                    System.out.println("Wait Time for ComplexThread "+ (m+1)+ " (ms): " + ComplexWaitTimeArraySJF[m]);  
                    
                }
                
                //for loop to measure average wait time for Threads
                for (int f=0; f <= MaxArrayThreads; f++)
                {
                    //average wait time for IO threads
                    totalWaitIO = totalWaitIO + IOWaitTimeArraySJF[f];
                    avgIO = totalWaitIO / IOWaitTimeArraySJF.length;
                    
                    //average wait time for CPU threads
                    totalWaitCPU = totalWaitCPU + CPUWaitTimeArraySJF[f];
                    avgCPU = totalWaitCPU / CPUWaitTimeArraySJF.length;
                    
                    //average wait time for Complex threads
                    totalWaitComplex = totalWaitComplex + ComplexWaitTimeArraySJF[f];
                    avgComplex = totalWaitComplex / ComplexWaitTimeArraySJF.length;
                }
                
                //Measurements for performance
                System.out.println(" ");
                System.out.println("Time for all IO threads to finish SJF (ms): " + totalIO);
                System.out.println("Time for all Complex threads to finish SJF (ms): " + totalComplex);
                System.out.println("Time for all CPU threads to finish SJF (ms): " + totalCPU);
                System.out.println("Average wait time for IO Threads SJF (ms): " + avgIO);
                System.out.println("Average wait time for Complex Threads SJF (ms): " + avgComplex);
                System.out.println("Average wait time for CPU Threads SJF (ms): " + avgCPU);
                System.out.println("Time for all threads to finish SJF (ms): "+(SJFendTime - SJFstartTime));
            }
        }
        
    public static class ControllerClassRR
    {
        /* 3.Round Robin scheduler will run the Complex thread, CPU thread, than IO Thread.
            This will be accomplished using the suspend() and resume() methods. 
        */
        //Creates 9 worker threads
        static final int MaxArrayThreads = 3;
        
        public static void main(String[] args) throws InterruptedException 
        {
            int MaxArrayThreads = 3;
            int arraySize = 30;
            //Array's to hold the IOBound, CPUBound, and ComplexBound threads
            Thread ThreadIOArrayRR[] = new Thread[MaxArrayThreads];
            Thread ThreadCPUArrayRR[] = new Thread[MaxArrayThreads];
            Thread ThreadComplexArrayRR[] = new Thread[MaxArrayThreads];

            //Array's to keep time of thread start and stop time, wait time
            long IOTimeArrayRR[] = new long[MaxArrayThreads];
            long IOWaitTimeArrayRR[] = new long[arraySize];
            long ComplexTimeArrayRR[] = new long[MaxArrayThreads];
            long ComplexWaitTimeArrayRR[] = new long[arraySize];
            long CPUTimeArrayRR[] = new long[MaxArrayThreads];
            long CPUWaitTimeArrayRR[] = new long[arraySize];

            //Time taken to run the scheduler
            long RRstartTime;
            long RRendTime;

            //Sumation varible for averages
            long totalIO = 0;
            long totalComplex = 0;
            long totalCPU = 0;
            long totalWaitIO = 0;
            long totalWaitComplex = 0;
            long totalWaitCPU = 0; 
            long avgIO = 0;
            long avgComplex = 0;
            long avgCPU = 0; 

            RRstartTime = System.currentTimeMillis();
            //population of arrays
            for(int j =0; j <MaxArrayThreads; j++)
            {    
                ThreadComplexArrayRR[j] = new Thread(new ComplexBound());
                ThreadCPUArrayRR[j] = new Thread(new CPUBound());
                ThreadIOArrayRR[j] = new Thread(new IOBound());
           
                long CPUStart = System.currentTimeMillis();
                ThreadCPUArrayRR[j].start();
                
                long IOStart = System.currentTimeMillis();
                ThreadIOArrayRR[j].start();
               
                long ComplexStart = System.currentTimeMillis();
                ThreadComplexArrayRR[j].start();
                
                long CPUWaitStart = System.currentTimeMillis();
                long IOEnd = System.currentTimeMillis();
                
                //Round Round occurs because even though CPUarray starts it will not finish first./
                //IOBound is never suspended thus it should not wait. 
                ThreadCPUArrayRR[j].suspend();
                
                long ComplexWaitStart = System.currentTimeMillis();
                ThreadComplexArrayRR[j].suspend();
                
                long ComplexWaitEnd = System.currentTimeMillis();
                ThreadComplexArrayRR[j].resume();
                long ComplexEnd = System.currentTimeMillis();
                
                long CPUWaitEnd = System.currentTimeMillis();
                ThreadCPUArrayRR[j].resume();
                long CPUEnd = System.currentTimeMillis();
                
                //store ComplexBound thread time to be called later
                ComplexTimeArrayRR[j] = (ComplexEnd - ComplexStart);
                totalComplex += ComplexTimeArrayRR[j]; 
                ComplexWaitTimeArrayRR[j] = (ComplexWaitEnd - ComplexWaitStart);   
                
                //store CPUBound thread time to be called later
                CPUTimeArrayRR[j] = (CPUEnd - CPUStart);
                totalCPU += CPUTimeArrayRR[j]; 
                CPUWaitTimeArrayRR[j] = (CPUWaitEnd - CPUWaitStart);
                
                //store IOBound thread time to be called later
                IOTimeArrayRR[j] = (IOEnd - IOStart);
                totalIO += IOTimeArrayRR[j];     
            }
            RRendTime = System.currentTimeMillis();
            System.out.println(" ");

            //Output of Thread Times for IOBound, Complex, and CPU.
            for (int m= 0; m < MaxArrayThreads; m++)
            {
                //Time to run thread
                System.out.println("Time for IOThread "+ (m+1) + " to complete (ms): "+ IOTimeArrayRR[m]); 
                System.out.println("Time for ComplexThread "+ (m+1) + " to complete (ms): "+ ComplexTimeArrayRR[m]);
                System.out.println("Time for CPUthread "+ (m+1) + " to complete (ms): "+ CPUTimeArrayRR[m]);

                //Wait time for thread
                System.out.println("Wait Time for IOThread "+ (m+1)+ " (ms): " + IOWaitTimeArrayRR[m]); 
                System.out.println("Wait Time for CPUThread "+ (m+1)+ " (ms): " + CPUWaitTimeArrayRR[m]); 
                System.out.println("Wait Time for ComplexThread "+ (m+1)+ " (ms): " + ComplexWaitTimeArrayRR[m]);  

            }
                
            //for loop to measure average wait time for Threads
            for (int f=0; f <= MaxArrayThreads; f++)
            {
                //average wait time for CPU threads
                totalWaitCPU = totalWaitCPU + CPUWaitTimeArrayRR[f];
                avgCPU = totalWaitCPU / CPUWaitTimeArrayRR.length;

                //average wait time for Complex threads
                totalWaitComplex = totalWaitComplex + ComplexWaitTimeArrayRR[f];
                avgComplex = totalWaitComplex / ComplexWaitTimeArrayRR.length;
            }

            //Measurements for performance
            System.out.println(" ");
            System.out.println("Time for all IO threads to finish RR (ms): " + totalIO);
            System.out.println("Time for all Complex threads to finish RR (ms): " + totalComplex);
            System.out.println("Time for all CPU threads to finish RR (ms): " + totalCPU);
            System.out.println("Average wait time for Complex Threads RR (ms): " + avgComplex);
            System.out.println("Average wait time for CPU Threads RR (ms): " + avgCPU);
            System.out.println("Time for all threads to finish RR (ms): "+(RRendTime - RRstartTime));
        }
    }
}
