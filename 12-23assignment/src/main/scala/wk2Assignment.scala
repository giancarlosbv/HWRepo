object wk2Assignment {

    def main(args:Array[String]): Unit = {
        // problem1()
        // println(problem2(3,3))
        // println(problem3(50))
        // println(problem4(15,15))
        // println(problem5(121))
        // println(problem6("I had a penny"))
        println(problem7("larry"))
    }

// 1. Write a Scala program to print "Hello, world" and version of the Scala language.
    def problem1(): Unit ={
        println("Hello World, this is Scala version 0.2.0")
    }
// 2. Write a Scala program to compute the sum of the two given integer values. If the two values are the same, then return triples their sum. 
    def problem2(x:Int,y:Int): Int = {
        var sum = 0
        if (x == y){
            sum = x+y
            sum=sum*3
            return sum
        } else {
            return x+y
        }
    }
// 3. Write a Scala program to get the absolute difference between n and 51. If n is greater than 51 return triple the absolute difference. 
    def problem3(n:Int):Int = {
        if (n>51) {
            return (n-51)*3
        } else {
            return 51-n
        }
    }
// 4. Write a Scala program to check two given integers, and return true if one of them is 30 or if their sum is 30. 
    def problem4(x:Int,y:Int): Boolean = {
        if ((x == 30)||(y==30) || (x+y == 30)){
        return true
        }
        else {return false
    }
}

// 5. Write a Scala program to check a given integer and return true if it is within 20 of 100 or 300.
    def problem5(x:Int): Boolean = {
        //80-120 or 280-320
        if ((x>=80 && x<=120)|| (x>=280 && x<=320))
        return true
        else return false
    }
// 6. Write a Scala program to create a new string where 'if' is added to the front of a given string. If the string already begins with 'if', return the string unchanged.

    def problem6(x:String): String ={
    
        println(x.slice(0,2))
        if (x.slice(0,2)=="If")
        return x
        else return "If " + x
        
    }
// 7. Write a Scala program to exchange the first and last characters in a given string and return the new string. 

    def problem7(x:String): String = {
    
        var topBun = x(0)
        var bottomBun = x(x.length-1)
        var meat = x.slice(1,x.length-1)
        return bottomBun + meat + topBun

    }
}