package test.batis;

import org.junit.Test;


public class test {
	public static void main(String[] args) {
		/**
		 * 输出为 1 2 3 
		 * 从输出可知： ①可变长参数必须是最后一个参数(如不是，编译器会报错)。即方法参数列表中只能有一个可变长度参数
		 * 		    ②可以使用遍历数组的方式去遍历可变参数。
		 * 			③可变参数是利用数组实现的。
		 */
		testArgs("0", "1", "2", "3");//输出为 1 2 3 
		String[] strs = {"1", "2", "3"};
		testArgs("0", strs);//输出为 1 2 3 
	}

	/**
	 * 可变长参数
	 * @param strs
	 */
	public static void testArgs(String str,String... strs) {
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
	@Test
	public void test1() {
		 Integer i = 1;
		 
		 Integer integer = 2;
	     int j = integer;
	}
}
