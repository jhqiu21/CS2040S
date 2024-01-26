# Tutorial 1

## Problem 1. Java Review

1. What is the difference between a class and an object? Illustrate with an example.
  - Class: 
    - A blueprint or a template for creating objects. 
    - Encapsulates the data (attributes) and behavior (methods) that the objects will have. 
  - Object: An instance of a class.

2. Why does the main method come with a `static` modifier?

    **Answer:** A static method belongs to the class rather than an instance of the class. Therefore, it can be called without creating an object.


3. Give an example class (or classes) that uses the modifier 
`private` incorrectly (i.e., the program will not compile as it is, but would compile if private was changed to public).

   ```
   public class Circle {
     private int r;
     private Circle(int r) {
         this.r = r;
     }
     private int getR() {
         return this.r;
     }
   }
      
   Circle c = new Circle(1)
   ```
   Error:
   ```
   Circle(int) has private access in Circle
   Circle c = new Circle(1);
              ^-----------^
   ```

4. The following question is about Interfaces.
    1. Why do we use interfaces?
   
       Interfaces provide a way to achieve abstraction 
       by defining a set of methods that a class implementing 
       the interface must implement.
   
   2. Give an example of using an interface.
   
   3. Can a method return an interface?
        
       Yes

5. Refer to ``IntegerExamination.java``, which can be found in the same folder as this PDF.
   Without running the code, predict the output of the `main` method. Can you explain the
   outputs?
        
6. 
    
## Problem 2









