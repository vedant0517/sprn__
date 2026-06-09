Why Java?
    Compiled and Interpreted
    Platform Independent
    Object Oriented
    Robust
    Secure
    High Performance
    Distributed 

Java code->Compiler->ByteCode->JVM(Interpretter)->Machine Code(for OS)  

Interpreted Language:Line by Line Execute Runtime
Compiled Language:All at once Execute Compile Time
Bytecode:Machine Independent Code
JVM:Java Virtual Machine (Software) .Separate space for each process.
JRE:Java Runtime Environment (Contains JVM + Libraries).Different from JVM. 
JDK:Java Development Kit (Contains JRE + Development Tools).



JVM:Java Virtual Machine

Class Loader (Responsible for loading class files [system path]) 
      JVM Lang Stack [String str]->Hello (Method Area/Class Area) [Stack Area]
    ->Loading
    ->Linking
        ->Verification( bytecode verifier )
        ->Preparation
        ->Resolution
    ->Initialization

Execution Engine->
    
    ->Byte Code Verifier->Checks whether the code is valid or not.
    ->Interpreter->Executes the code line by line.
    ->JIT(Just in Time) Compiler->Executes the code line by line.  Run code intelligently. Hotsapting concept
    ->Garbage Collector->Cleans the memory.
    ->Runtime Data Areas

Is thread have PC register?-Yes(1 per each thread)  Each thread have its own jvm stack area.
Is thread have Stack Area?-Yes(1 per each thread)
Is thread have Heap Memory?-No(Common for all threads).Stores objects.