class Honda {  
//method overriding (Run time polymorphism)	
 static void motor (){
	 System.out.println("you run from this motor");
 }
  void run(){System.out.println("running safely with 100kmph");}  
    
  
  //compile time polymorphism(method overloading)
  void method1(int  i){
	  System.out.println("only i"+i);
  }
  
  
 
  public static void main(String args[]){  
	motor();
   
  }  
}  