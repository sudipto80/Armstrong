

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun Int.Digits():MutableList<Int>
{
    var n = this
    val loc = 0
    var digs = mutableListOf<Int>()
    while(n != 0)
    {
        digs.add(loc,n%10)
        n = n/10
    }
   return digs
}

fun MutableList<Int>.Cubes():MutableList<Int>
 = this.map { it * it * it}.toMutableList<Int>()

fun MutableList<Int>.Sum():Int{
    var sum = 0
    for(item in this)
    	sum += item
    return sum
}

val Int.Factorial : Int 
 get() 
	{
		var fact = 1
		for(x in 1..this)
			fact=fact*x
		return fact
   }	


/*

 */
fun MutableList<Int>.Factorials()
	= this.map{ it -> it.Factorial}
	      .toMutableList<Int>()

fun MutableList<Int>.RaisedToSelf() = 
this.map { it -> Math.pow(it.toDouble(),it.toDouble()) }
    .map { it -> it.toInt()}
    .toMutableList<Int>()

fun MutableList<Int>.RaisedToConsecutive() = 
this.mapIndexed { index, it  -> Math.pow(it.toDouble(), index.toDouble()) }
    .map { it -> it.toInt()}
    .toMutableList<Int>()

fun Int.Is( predicate: (Int)->Boolean) = predicate(this)
	
infix fun Int.Is(other:Int) = this == other 

fun main(args: Array<String>){
	println("Query ?")
	var query = readLine()!!
	var switch = "_"
	val start = 1
	val end = 50000
	var tokens = query.split(' ')
	for(x in tokens)
	{	
		if(x == "sum"    ||
		   x == "cubes"  || 
		   x == "digits" || 
		   x == "factorials" || 
		   x == "raised" ||  
		   x == "self" ||
           x == "consecutive")
				switch = switch + x
				
	}
	//digits().cubes().sum().Is(10)//Is => == sum of the digits = 10 
	//dig // sum of even-digits = product of odd-digits 
	//sum of the digits is divisible by 11 and 3 
	//println("You entered $query")
	
	val armStrongCheck = compose(MutableList<Int>::Sum, 
	                                   compose(MutableList<Int>::Cubes,
									                        Int::Digits))
	val factorianCheck = compose(MutableList<Int>::Sum, 
	                                   compose(MutableList<Int>::Factorials,Int::Digits))
												  
	val MuchhausenCheck = compose(MutableList<Int>::Sum, 
	                                   compose(MutableList<Int>::RaisedToSelf,Int::Digits))

    val A032799Check = compose(MutableList<Int>::Sum,
                                        compose(MutableList<Int>::RaisedToConsecutive, Int::Digits))
										
	val funMap = hashMapOf("_sumcubesdigits" to armStrongCheck,
	                       "_sumfactorialsdigits" to factorianCheck,
						   "_sumdigitsraisedself" to MuchhausenCheck,
                           "_sumdigitsraisedconsecutive" to A032799Check )
	
	//A032799
	if(funMap.containsKey(switch))
	{
		for(m in start .. end)
				if(funMap[switch]?.invoke(m)==m)
					println(m)
	}
	else  
	{ 
		println("Sorry! I don't know about that yet!")
		
	}
	//println(funMap["sumcubedigits"]?.invoke(153))
}