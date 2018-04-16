Main 遗传算法主类

    CoreControl control = new CoreControl();

    control.initialize();        //进行初始化

    speciesGroup.print();       //将做遗传算法之前种群成员输出

    theBest = speciesGroup.sufficiency(); //输出每次迭代适应度最大对应的结果值

    speciesGroup.gambleWheel();        //运用赌盘方法进行选择	
		          
    speciesGroup.intersect();          //交叉

    speciesGroup.variation();          //变异

CoreControl      // 核心控制类

    * 主要参数
    	 * @param A                区间下界
    	 * @param B                区间上界
    	 * @param interal          区间 
    	 * @param problemThings[]  问题相关的 数字
    	 * @param members[]        种群的成员
    	 * @param LENGTH           编码长度
    	 * @param stop             停止的代数
    	 
    	 该类包含主要方法是对遗传算法的初始化，编码和判断遗传是否结束
   
BigIntMultiply //主要是大整数乘法的工具类

SpeciesGroup //遗传算法的主要实现类